package cn.ibionic.wechat;

import cn.ibionic.wechat.identity.Config;
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
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：Yuho Liu
 * @description：
 * @date ：2021/11/26 5:46 PM
 */
public class ConvertTest {


    @Test
    public void convert() throws Exception {
        String s = "https://item.m.jd.com/product/100025429436.html?";
        System.out.println(urlConvert(s));
    }
    public String urlConvert(String url) throws Exception {


        //todo 判断为京东链接
        if (url.contains("jd.com")) {
            url = url+"jd";

            JdClient client=new DefaultJdClient(Config.JD_SERVER_URL, Config.JD_ACCESS_TOKEN, Config.JD_APP_KEY, Config.JD_APP_SECRET);
            UnionOpenGoodsQueryRequest couponUrlRequest=new UnionOpenGoodsQueryRequest();
            GoodsReq goodsReqDTO=new GoodsReq();
            goodsReqDTO.setSkuIds(new Long[]{100025429436L});
            couponUrlRequest.setGoodsReqDTO(goodsReqDTO);
            couponUrlRequest.setVersion("1.0");

            UnionOpenGoodsQueryResponse response1=client.execute(couponUrlRequest);
            String couponUrl = null;
            if (response1.getQueryResult().getData() == null) {
                return "";
            }
            GoodsResp goodsResp = response1.getQueryResult().getData()[0];
            Coupon[] couponList = goodsResp.getCouponInfo().getCouponList();
            if (couponList.length > 0) {
                List<Coupon> collect = Arrays.stream(couponList).filter(coupon->coupon.getIsBest() == 1).collect(Collectors.toList());
                couponUrl = collect.get(0).getLink();
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
            System.out.println("商品券后价:"+lowestCouponPrice+", 佣金:"+couponCommission+", plus佣金:"+plusCouponCommissionBigDecimal.doubleValue());
            UnionOpenPromotionBysubunionidGetRequest request=new UnionOpenPromotionBysubunionidGetRequest();
            PromotionCodeReq promotionCodeReq=new PromotionCodeReq();
            promotionCodeReq.setMaterialId(url);
            promotionCodeReq.setCouponUrl(couponUrl);
            request.setPromotionCodeReq(promotionCodeReq);
            request.setVersion("1.0");
            UnionOpenPromotionBysubunionidGetResponse response=client.execute(request);

            GetResult getResult = response.getGetResult();
            String text = "商品原价为："+goodsResp.getPriceInfo().getPrice() + (lowestCouponPrice < goodsResp.getPriceInfo().getPrice() ? "；券后价为"+lowestCouponPrice : "")+"\n完成购买您将得到"+couponCommissionBigDecimal.multiply(BigDecimal.valueOf(Config.GLOBAL_COEFFICIENT)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()+
                    "元奖励，"+ (divide.intValue() !=1?"\n如果您是plus会员，完成购买将得到"+plusCouponCommissionBigDecimal.multiply(BigDecimal.valueOf(Config.GLOBAL_COEFFICIENT)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue()+"元奖励\n":"");
            url = text + getResult.getData().getShortURL();
        }
        return url;
    }
}
