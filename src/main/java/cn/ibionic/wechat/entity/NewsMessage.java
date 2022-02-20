package cn.ibionic.wechat.entity;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.util.List;

/**
 * @author ：Yuho Liu
 * @description：图文消息
 * @date ：2021/11/25 11:21 AM
 * <xml>
 *   <ToUserName><![CDATA[toUser]]></ToUserName>
 *   <FromUserName><![CDATA[fromUser]]></FromUserName>
 *   <CreateTime>12345678</CreateTime>
 *   <MsgType><![CDATA[news]]></MsgType>
 *   <ArticleCount>1</ArticleCount>
 *   <Articles>
 *     <item>
 *       <Title><![CDATA[title1]]></Title>
 *       <Description><![CDATA[description1]]></Description>
 *       <PicUrl><![CDATA[picurl]]></PicUrl>
 *       <Url><![CDATA[url]]></Url>
 *     </item>
 *   </Articles>
 * </xml>
 */
@Data
public class NewsMessage {
    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private long createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("ArticleCount")
    private int articleCount;

    @XStreamAlias("Articles")
    private List<Item> articles;

    @Data
    public static class Item{
        @XStreamAlias("Title")
        private String title;

        @XStreamAlias("Description")
        private String description;

        @XStreamAlias("PicUrl")
        private String picUrl;

        @XStreamAlias("Url")
        private String url;
    }
}
