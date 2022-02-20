package cn.ibionic.wechat.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 京东唯一识别码
     */
    private String jdPin;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}