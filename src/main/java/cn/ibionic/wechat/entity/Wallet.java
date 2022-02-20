package cn.ibionic.wechat.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * wallet
 * @author 
 */
@Data
public class Wallet implements Serializable {
    private Integer id;

    private Integer userId;

    private Double balance;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}