package cn.ibionic.wechat.util;

import cn.ibionic.wechat.identity.Config;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkDgMaterialOptionalRequest;
import com.taobao.api.request.TbkItemInfoGetRequest;
import com.taobao.api.request.TbkTpwdCreateRequest;
import com.taobao.api.response.TbkDgMaterialOptionalResponse;
import com.taobao.api.response.TbkItemInfoGetResponse;
import com.taobao.api.response.TbkTpwdCreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author ：Yuho Liu
 * @description：淘宝转链util
 * @date ：2021/12/6 6:27 PM
 */
@Slf4j
public class TaobaoUtil {

    public static String convertTaobaoUrl(String pin, String content) {
        long start = System.currentTimeMillis();
        String url = getRedirectUrl(content);

        long getUrlWaste = System.currentTimeMillis() - start;
        log.info("获得真实url耗时{}毫秒", getUrlWaste);

        TaobaoClient client = new DefaultTaobaoClient(Config.TAOBAO_SERVER_URL, Config.TAOBAO_SERVER_URL, Config.TAOBAO_APP_SECRET);
        String result = null;
        try {
            //获得商品id
            String id = url.split("id=")[1].split("&")[0];

            TbkItemInfoGetRequest productReq = new TbkItemInfoGetRequest();
            productReq.setNumIids(id);
            productReq.setPlatform(2L);
            TbkItemInfoGetResponse productRsp = client.execute(productReq);

            //根据商品名称递归查询物料
            String title = productRsp.getResults().get(0).getTitle();
            long itemId = productRsp.getResults().get(0).getNumIid();
            TbkDgMaterialOptionalResponse.MapData mapData = getItemList(client, 1L, title, itemId, 0);
            if (mapData == null) {
                return "请使用淘宝app分享的url，或者该商品不支持返利";
            }
            String resultUrl = mapData.getUrl();
            if (id.equals(String.valueOf(mapData.getItemId()))) {
                Double commissionAmount = null;
                double truePrice = Double.parseDouble(mapData.getZkFinalPrice());
                //如果有优惠券信息
                String couponInfo = mapData.getCouponInfo();
                if (StringUtils.hasLength(mapData.getCouponId())) {
                    resultUrl = mapData.getCouponShareUrl();
                    truePrice = Double.parseDouble(mapData.getZkFinalPrice()) - Double.parseDouble(mapData.getCouponAmount());
                }

                BigDecimal multiply = BigDecimal.valueOf(truePrice).multiply(BigDecimal.valueOf(Double.parseDouble(mapData.getCommissionRate()) * 0.0001));
                log.info("真实佣金为{}", multiply.doubleValue());
                commissionAmount = multiply.multiply(BigDecimal.valueOf(Config.GLOBAL_COEFFICIENT)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
                //获得短链和淘口令
                TbkTpwdCreateRequest pwdReq = new TbkTpwdCreateRequest();
                pwdReq.setUrl(resultUrl.replaceAll("//", "https://"));
                TbkTpwdCreateResponse pwdRsp = client.execute(pwdReq);

                result = (StringUtils.hasLength(couponInfo) ? "该商品可领取" + couponInfo + "优惠券\n" : "")
                        + "购买完成后预计返利金额" + commissionAmount + "\n" + pwdRsp.getData().getModel() + "\n[复制整条消息到淘宝app即可跳转]";
                return result;
            }


        } catch (ApiException e) {
            e.printStackTrace();
        }
        return "该商品不支持返利";
    }

    public static TbkDgMaterialOptionalResponse.MapData getItemList(TaobaoClient client, long pageNo, String title, long itemId, Integer size) throws ApiException {
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        req.setPageSize(10L);
        req.setPageNo(pageNo);
        req.setAdzoneId(74710183L);
        req.setQ(title);
        TbkDgMaterialOptionalResponse rsp = client.execute(req);
        if (rsp.getResultList() != null) {
            size += rsp.getResultList().size();
            for (TbkDgMaterialOptionalResponse.MapData map : rsp.getResultList()) {
                if (map.getItemId() == itemId) {
                    return map;
                }
            }
        }
        if (size < rsp.getTotalResults()) {
            return getItemList(client, pageNo + 1, title, itemId, size);
        }
        return null;
    }

    public static String getRedirectUrl(String url) {
        url = url.split(" ")[1];
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 HttpGet 请求
        HttpGet httpGet = new HttpGet(url);
        // 请求并获得响应结果
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            // 输出请求结果
            String result = EntityUtils.toString(httpEntity);
            String completeUrl = result.split("var url = '")[1].split("';")[0];
            return completeUrl;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
