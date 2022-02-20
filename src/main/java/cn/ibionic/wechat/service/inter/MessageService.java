package cn.ibionic.wechat.service.inter;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author ：Yuho Liu
 * @description：消息服务
 * @date ：2021/11/12 6:23 PM
 */
public interface MessageService {
    String newMessageRequest(Map<String, String> map) throws Exception;

    Map<String, String> parseXml(HttpServletRequest request) throws Exception;
}
