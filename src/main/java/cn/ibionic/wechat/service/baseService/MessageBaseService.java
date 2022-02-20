package cn.ibionic.wechat.service.baseService;

import cn.ibionic.wechat.entity.JdOrder;
import cn.ibionic.wechat.entity.User;
import cn.ibionic.wechat.identity.Config;
import cn.ibionic.wechat.util.DateUtil;
import cn.ibionic.wechat.util.TaobaoUtil;
import cn.ibionic.wechat.vo.JdOrderVo;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.domain.kplunion.GoodsService.request.query.GoodsReq;
import com.jd.open.api.sdk.domain.kplunion.GoodsService.response.query.Coupon;
import com.jd.open.api.sdk.domain.kplunion.GoodsService.response.query.GoodsResp;
import com.jd.open.api.sdk.domain.kplunion.promotionbysubunioni.PromotionService.request.get.PromotionCodeReq;
import com.jd.open.api.sdk.domain.kplunion.promotionbysubunioni.PromotionService.response.get.GetResult;
import com.jd.open.api.sdk.request.kplunion.UnionOpenGoodsQueryRequest;
import com.jd.open.api.sdk.request.kplunion.UnionOpenPromotionBysubunionidGetRequest;
import com.jd.open.api.sdk.response.kplunion.UnionOpenGoodsQueryResponse;
import com.jd.open.api.sdk.response.kplunion.UnionOpenPromotionBysubunionidGetResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：Yuho Liu
 * @description：底层service
 * @date ：2021/11/15 11:34 PM
 */
@Service
@Slf4j
public class MessageBaseService {

    @Autowired
    private JdOrderBaseService jdOrderBaseService;

    private static final String ILLEGAL_URL = "不是正确的商品链接";

    public String urlConvert(String url, User user) throws Exception {

        if (!StringUtils.hasLength(url)) {
            return ILLEGAL_URL;
        }
        log.info("收到{}的消息，内容为{}", user.getId(), url);
        //todo 判断为京东链接
        if (url.contains("jd.com")) {
            long skuId;
            if (url.contains("kpl")) {
                String[] split = url.split("wareId=");
                String id = split[1].contains("&") ? split[1].split("&")[0] : split[1];
                skuId = Long.parseLong(id);
            } else {
                String[] split = url.split(".html");
                String[] split1 = split[0].split("/");
                skuId = Long.parseLong(split1[split1.length - 1]);
            }

            JdClient client=new DefaultJdClient(Config.JD_SERVER_URL, Config.JD_ACCESS_TOKEN, Config.JD_APP_KEY, Config.JD_APP_SECRET);
            UnionOpenGoodsQueryRequest couponUrlRequest=new UnionOpenGoodsQueryRequest();
            GoodsReq goodsReqDTO=new GoodsReq();
            goodsReqDTO.setSkuIds(new Long[]{skuId});
            couponUrlRequest.setGoodsReqDTO(goodsReqDTO);
            couponUrlRequest.setVersion("1.0");

            UnionOpenGoodsQueryResponse response1=client.execute(couponUrlRequest);
            String couponUrl = null;
            if (response1.getQueryResult().getData() == null) {
                return "该商品不支持奖励哦，请更换商品链接或通过原渠道购买";
            }
            GoodsResp goodsResp = response1.getQueryResult().getData()[0];
            Coupon[] couponList = goodsResp.getCouponInfo().getCouponList();
            if (couponList.length > 0) {
                List<Coupon> collect = Arrays.stream(couponList).filter(coupon -> coupon.getIsBest() == 1).collect(Collectors.toList());
                if (collect.size() > 0) {
                    couponUrl = collect.get(0).getLink();
                }
            }
            //券后价 lowestCouponPrice
            Double lowestCouponPrice = goodsResp.getPriceInfo().getLowestCouponPrice();
            //佣金 couponCommission
            Double couponCommission = goodsResp.getCommissionInfo().getCouponCommission();
            //plus佣金 plusCouponCommission
            BigDecimal plusBigDecimal = BigDecimal.valueOf(goodsResp.getCommissionInfo().getPlusCommissionShare());
            BigDecimal normalBigDecimal = BigDecimal.valueOf(goodsResp.getCommissionInfo().getCommissionShare());
            BigDecimal divide = plusBigDecimal.divide(normalBigDecimal, RoundingMode.DOWN);
            BigDecimal couponCommissionBigDecimal = BigDecimal.valueOf(couponCommission);
            BigDecimal plusCouponCommissionBigDecimal = divide.multiply(couponCommissionBigDecimal).setScale(2, BigDecimal.ROUND_DOWN);
            log.info("商品券后价:{}, 佣金:{}, plus佣金:{}", lowestCouponPrice, couponCommission, plusCouponCommissionBigDecimal.doubleValue());
            UnionOpenPromotionBysubunionidGetRequest request=new UnionOpenPromotionBysubunionidGetRequest();
            PromotionCodeReq promotionCodeReq=new PromotionCodeReq();
            promotionCodeReq.setMaterialId(url);
            promotionCodeReq.setCouponUrl(couponUrl);
            promotionCodeReq.setSubUnionId(user.getJdPin());
            request.setPromotionCodeReq(promotionCodeReq);
            request.setVersion("1.0");
            UnionOpenPromotionBysubunionidGetResponse response=client.execute(request);

            GetResult getResult = response.getGetResult();
            String text = "商品原价为："+goodsResp.getPriceInfo().getPrice() + (lowestCouponPrice < goodsResp.getPriceInfo().getPrice() ? "\n券后价为"+lowestCouponPrice : "")+"\n完成购买您将得到"+couponCommissionBigDecimal.multiply(BigDecimal.valueOf(Config.GLOBAL_COEFFICIENT)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()+
                    "元奖励，"+ (divide.intValue() !=1?"\n如果您是plus会员，完成购买将得到"+plusCouponCommissionBigDecimal.multiply(BigDecimal.valueOf(Config.GLOBAL_COEFFICIENT)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()+"元奖励\n":"");
            url = text + "\n记得点下方链接下单哦：" + getResult.getData().getShortURL();
        } else if (url.contains("m.tb.cn")) {
            url = TaobaoUtil.convertTaobaoUrl(user.getJdPin(), url);
        } else if ("2".equals(url)) {
            //查询我的jd未结算
            List<JdOrder> jdOrderList = jdOrderBaseService.selectPayedBySubUnionid(user.getJdPin());
            List<JdOrderVo> result = new ArrayList<>();
            Date nowDate = DateUtil.getNowDate();
            url = "您已付款未结算的订单如下：\n";
            for (JdOrder jdOrder: jdOrderList) {
                if (jdOrder.getValidCode() == 17) {
                    if (StringUtils.hasLength(jdOrder.getPayMonth()) && !"0".equals(jdOrder.getPayMonth()) && DateUtil.string2Date(jdOrder.getPayMonth(), DateUtil.SDFYYYYMMDD).after(nowDate)) {
                        result.add(new JdOrderVo(jdOrder));
                    }
                }else if (jdOrder.getValidCode() == 16 || jdOrder.getValidCode() == 22){
                    result.add(new JdOrderVo(jdOrder));
                }
            }
            StringBuilder urlBuilder = new StringBuilder(url);
            for (JdOrderVo jdOrderVo: result) {
                urlBuilder/*.append("订单号：").append(jdOrderVo.getId())*/
                        .append("商品：").append(jdOrderVo.getSkuName().length() > 8 ? jdOrderVo.getSkuName().substring(0, 8) + "..." : jdOrderVo.getSkuName())
                        .append("，预计返利：").append(jdOrderVo.getValidCode() == 17 ? jdOrderVo.getActualFee() : jdOrderVo.getEstimateFee()).append("；\n");
            }
            url = urlBuilder.toString();
        }
        return url;
    }
}
