package cn.ibionic.wechat.service.impl;

import cn.ibionic.wechat.service.baseService.WalletBaseService;
import cn.ibionic.wechat.vo.ImageMessage;
import cn.ibionic.wechat.vo.TextMessage;
import cn.ibionic.wechat.entity.User;
import cn.ibionic.wechat.identity.Config;
import cn.ibionic.wechat.service.baseService.MessageBaseService;
import cn.ibionic.wechat.service.baseService.UserBaseService;
import cn.ibionic.wechat.service.inter.MessageService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：Yuho Liu
 * @description：
 * @date ：2021/11/12 6:26 PM
 */
@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageBaseService messageBaseService;

    @Autowired
    private UserBaseService userBaseService;

    @Autowired
    private WalletBaseService walletBaseService;

    @Override
    public String newMessageRequest(Map<String, String> map) throws Exception {
        //用户openid
        String openId = map.get("FromUserName");
        //公众号原始ID
        String mpId = map.get("ToUserName");
        TextMessage textMessageRes = new TextMessage();
        //如果是首次关注
        if ("event".equals(map.get("MsgType"))) {
            log.info("{}关注", openId);
            textMessageRes.setToUserName(openId);
            textMessageRes.setFromUserName(mpId);
            textMessageRes.setCreateTime(System.currentTimeMillis());
            textMessageRes.setMsgType("text");
            textMessageRes.setContent(Config.HELP_TEXT);
            //用户信息入库
            userBaseService.generateUser(openId);
            return TextMessage.textMessageToXml(textMessageRes);
        }

        User user = userBaseService.getUserByOpenId(openId);
        if (user == null) {
            log.info("openId:{}用户不存在, 新增", openId);
            user = userBaseService.generateUser(openId);
        }

        String jsonString = JSONObject.toJSONString(map);
        TextMessage textMessage = JSONObject.parseObject(jsonString, TextMessage.class);
        //提现
        if ("0".equals(textMessage.getContent())) {
            ImageMessage imageMessage = new ImageMessage();
            imageMessage.setToUserName(openId);
            imageMessage.setFromUserName(mpId);
            imageMessage.setCreateTime(System.currentTimeMillis());
            imageMessage.setMsgType("image");
            imageMessage.setImage(Collections.singletonList("WmyqZ8z1Glc4qEsJ9u7NZHYV6MwuWn_Lby3bV83u_hA"));
            return ImageMessage.imageMessageToXml(imageMessage);
        }

        textMessageRes.setToUserName(openId);
        textMessageRes.setFromUserName(mpId);
        textMessageRes.setCreateTime(System.currentTimeMillis());
        textMessageRes.setMsgType("text");

        //查余额
        if ("1".equals(textMessage.getContent())) {
            textMessageRes.setContent("您的可提现余额为：" +  walletBaseService.getBalanceByUserId(user.getId()));
            return TextMessage.textMessageToXml(textMessageRes);
        }

        //获得帮助
        if ("3".equals(textMessage.getContent())) {
            textMessageRes.setContent(Config.HELP_TEXT);
            return TextMessage.textMessageToXml(textMessageRes);
        }

        textMessageRes.setContent(messageBaseService.urlConvert(textMessage.getContent(), user) + Config.OPTION_HELP);

        return TextMessage.textMessageToXml(textMessageRes);
    }


    @Override
    public Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }

        // 释放资源
        inputStream.close();

        return map;
    }
}
