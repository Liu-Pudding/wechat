package cn.ibionic.wechat;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Yuho Liu
 * @description：微信自定义菜单
 * @date ：2021/12/1 12:25 AM
 */
public class customMenu {


    public void getMenu() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 HttpGet 请求
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb78e6c7156840bb0&secret=0f5e940ea6dc49aa43383492b94318ff");
        // 请求并获得响应结果
        CloseableHttpResponse httpResponse = null;
        {
            try {
                httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                // 输出请求结果
                String result = EntityUtils.toString(httpEntity);
                Map map = JSONObject.parseObject(result, HashMap.class);
                String access_token = String.valueOf(map.get("access_token"));
                System.out.println(access_token);
                httpGet = new HttpGet(" https://api.weixin.qq.com/cgi-bin/menu/get?access_token=" + access_token);
                httpResponse = httpClient.execute(httpGet);
                httpEntity = httpResponse.getEntity();
                result = EntityUtils.toString(httpEntity);
                map = JSONObject.parseObject(result, HashMap.class);
                System.out.println(map);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void createMenu() {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 HttpGet 请求
        HttpGet httpGet = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb78e6c7156840bb0&secret=0f5e940ea6dc49aa43383492b94318ff");

        try {
            CloseableHttpResponse accessTokenResponse = httpClient.execute(httpGet);
            String tokenMap = EntityUtils.toString(accessTokenResponse.getEntity());
            Map map = JSONObject.parseObject(tokenMap, HashMap.class);
            String access_token = String.valueOf(map.get("access_token"));
            System.out.println(access_token);

            HttpPost httpPost = new HttpPost("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + access_token);
            HttpEntity httpEntity = new StringEntity("{\"button\":[{\"type\":\"click\",\"name\":\"提现\",\"key\":\"WITHDRAW\"},{\"type\":\"click\",\"name\":\"帮助\",\"key\":\"HELP\"}]}", ContentType.APPLICATION_JSON);
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse response = httpClient.execute(httpPost);
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
