package cn.ibionic.wechat.vo;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import lombok.Data;

import java.io.Writer;
import java.util.List;

/**
 * @author ：Yuho Liu
 * @description：图文消息
 * @date ：2021/12/14 10:34 PM
 */
@Data
public class ImageMessage {
    @XStreamAlias("ToUserName")
    private String toUserName;

    @XStreamAlias("FromUserName")
    private String fromUserName;

    @XStreamAlias("CreateTime")
    private long createTime;

    @XStreamAlias("MsgType")
    private String msgType;

    @XStreamAlias("ArticleCount")
    private Integer articleCount;

    @XStreamAlias("Image")
    private List<String> image;


    public static String imageMessageToXml(ImageMessage imageMessage) {
        XSTREAM.processAnnotations(ImageMessage.class);
        XSTREAM.alias("xml", imageMessage.getClass());

        XSTREAM.alias("MediaId", String.class);

        return XSTREAM.toXML(imageMessage);
    }
    private static final XStream XSTREAM = new XStream(new DomDriver() {
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                final boolean cdata = true;

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
