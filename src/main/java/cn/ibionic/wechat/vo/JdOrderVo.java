package cn.ibionic.wechat.vo;

import cn.ibionic.wechat.entity.JdOrder;
import cn.ibionic.wechat.identity.Config;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：Yuho Liu
 * @description：京东订单出参
 * @date ：2021/12/13 10:58 PM
 */
public class JdOrderVo implements Serializable {

    /**
     * 订单编号
     */
    private String id;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 计佣金额
     */
    private Double estimateCosPrice;

    /**
     * 预估佣金
     */
    private Double estimateFee;

    /**
     * 实际佣金
     */
    private Double actualFee;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     *订单状态
     */
    private Integer validCode;

    private String payMonth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public Double getEstimateCosPrice() {
        return estimateCosPrice;
    }

    public void setEstimateCosPrice(Double estimateCosPrice) {
        this.estimateCosPrice = estimateCosPrice;
    }

    public Double getEstimateFee() {
        return estimateFee;
    }

    public void setEstimateFee(Double estimateFee) {
        BigDecimal estimateFeeBigDecimal = BigDecimal.valueOf(estimateFee);
        this.estimateFee = estimateFeeBigDecimal.multiply(BigDecimal.valueOf(Config.GLOBAL_COEFFICIENT)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public Double getActualFee() {
        return actualFee;
    }

    public void setActualFee(Double actualFee) {
        BigDecimal actualFeeBigDecimal = BigDecimal.valueOf(actualFee);
        this.actualFee = actualFeeBigDecimal.multiply(BigDecimal.valueOf(Config.GLOBAL_COEFFICIENT)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Integer getValidCode() {
        return validCode;
    }

    public void setValidCode(Integer validCode) {
        this.validCode = validCode;
    }

    public String getPayMonth() {
        return payMonth;
    }

    public void setPayMonth(String payMonth) {
        this.payMonth = payMonth;
    }

    public JdOrderVo(JdOrder jdOrder) {
        this.id = jdOrder.getId();
        this.skuName = jdOrder.getSkuName();
        this.estimateCosPrice = jdOrder.getEstimateCosPrice();

        BigDecimal estimateFeeBigDecimal = BigDecimal.valueOf(jdOrder.getEstimateFee());
        this.estimateFee = estimateFeeBigDecimal.multiply(BigDecimal.valueOf(Config.GLOBAL_COEFFICIENT)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        BigDecimal actualFeeBigDecimal = BigDecimal.valueOf(jdOrder.getActualFee());
        this.actualFee = actualFeeBigDecimal.multiply(BigDecimal.valueOf(Config.GLOBAL_COEFFICIENT)).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();

        this.orderTime = jdOrder.getOrderTime();
        this.finishTime = jdOrder.getFinishTime();
        this.validCode = jdOrder.getValidCode();
        this.payMonth = jdOrder.getPayMonth();
    }
}
