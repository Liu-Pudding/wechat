package cn.ibionic.wechat.controller;

/**
 * @author ：Yuho Liu
 * @description：控制器
 * @date ：2021/11/12 12:15 AM
 */
import cn.ibionic.wechat.service.inter.MessageService;
import cn.ibionic.wechat.util.WechatChekoutUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 微信token获取
 * @ClassName:  TestController
 * @Description:TODO
 * @author: jp
 * @date:   2019年6月13日 下午4:01:32
 *
 * @Copyright: 2019 www.tydic.com Inc. All rights reserved.
 *
 */

@Controller
@Slf4j
public class WechatController {

    @Autowired
    private MessageService messageService;

    @Resource
    private HttpServletRequest request;

    /**
     * 微信公众号签名认证接口
     * @Title: test
     * @Description: TODO
     * @param: @param signature
     * @param: @param timestamp
     * @param: @param nonce
     * @param: @param echostr
     * @param: @return
     * @return: String
     * @throws
     */
    @RequestMapping("/wx")
    @ResponseBody
    public String token(String signature,String timestamp,String nonce,String echostr) {

        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && WechatChekoutUtil.checkSignature(signature, timestamp, nonce)) {
            return echostr;
        }
        return null;
    }

    @RequestMapping(value = "/wx",method = RequestMethod.POST)
    public void postTest(HttpServletResponse response) throws Exception {
        Map<String, String> messageMap = messageService.parseXml(request);
        String respMessage = messageService.newMessageRequest(messageMap);
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
}