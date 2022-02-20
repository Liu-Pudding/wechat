package cn.ibionic.wechat;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

/**
 * @author ：Yuho Liu
 * @description：
 * @date ：2021/12/2 12:35 AM
 */
public class TaobaoConvertTest {

    @Test
    public void test() throws IOException {
        String url = getRedirectUrl("17，起得能个他去对能去里那啊 https://m.tb.cn/h.f7Fq1IX?sm=6020f8  诺梵圣诞节松露型巧克力礼盒物装送女友儿童休闲零食（代可可脂）");
        System.out.println("完整链接为:" + url);

        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "33233853", "e8ef63c3bc82ee9b6dd2467371a3e304");

        try {
            String id = url.split("id=")[1].split("&")[0];
            TbkItemInfoGetRequest productReq = new TbkItemInfoGetRequest();
            productReq.setNumIids(id);
            productReq.setPlatform(2L);
            TbkItemInfoGetResponse productRsp = client.execute(productReq);
            System.out.println(productRsp.getBody());

            TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
            req.setStartDsr(10L);
            req.setPageSize(20L);
            req.setPageNo(1L);
            req.setAdzoneId(74710183L);
            req.setQ(productRsp.getResults().get(0).getTitle());

            TbkDgMaterialOptionalResponse rsp = client.execute(req);
            for (TbkDgMaterialOptionalResponse.MapData mapData: rsp.getResultList()) {
                if (id.equals(String.valueOf(mapData.getItemId()))) {
                    String resultUrl = mapData.getUrl().replaceAll("//", "https://");
                    System.out.println(resultUrl);

                    TbkTpwdCreateRequest pwdReq = new TbkTpwdCreateRequest();
                    pwdReq.setUrl(resultUrl);
                    TbkTpwdCreateResponse pwdRsp = client.execute(pwdReq);
                    System.out.println(pwdRsp.getData().getModel());
                }
            }


        } catch (ApiException e) {
            e.printStackTrace();
        }
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
