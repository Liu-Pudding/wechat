package cn.ibionic.wechat.entity;

import java.io.Serializable;
import java.util.Date;

import cn.ibionic.wechat.util.DateUtil;
import com.jd.open.api.sdk.domain.kplunion.OrderService.response.query.OrderRowResp;
import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * jd_order
 * @author 
 */
@Data
public class JdOrder implements Serializable {
    /**
     * 标记唯一订单行：订单+sku维度的唯一标识
     */
    private String id;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 父单的订单号
     */
    private Long parentId;

    /**
     * 下单时间
     */
    private Date orderTime;

    /**
     * 完成时间
     */
    private Date finishTime;

    /**
     * 下单用户是否为PLUS会员
     */
    private Boolean plus;

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 商品数量
     */
    private Integer skuNum;

    /**
     * 单价
     */
    private Double price;

    /**
     * 佣金比例(投放的广告主计划比例)
     */
    private Double commissionRate;

    /**
     * 分成比例（单位：%）
     */
    private Double subSideRate;

    /**
     * 补贴比例（单位：%）
     */
    private Double subsidyRate;

    /**
     * 最终分佣比例（单位：%）=分成比例+补贴比例
     */
    private Double finalRate;

    /**
     * 预估计佣金额
     */
    private Double estimateCosPrice;

    /**
     * 推客的预估佣金（预估计佣金额*佣金比例*最终比例），如订单完成前发生退款，此金额也会更新。
     */
    private Double estimateFee;

    /**
     * 实际计算佣金的金额。订单完成后，会将误扣除的运费券金额更正。如订单完成后发生退款，此金额会更新。
     */
    private Double actualCosPrice;

    /**
     * 推客分得的实际佣金（实际计佣金额*佣金比例*最终比例）。如订单完成后发生退款，此金额会更新。
     */
    private Double actualFee;

    /**
     * 预估结算时间，订单完成后才会返回，格式：yyyyMMdd，默认：0。表示最新的预估结算日期。当payMonth为当前的未来时间时，表示该订单可结算；当payMonth为当前的过去时间时，表示该订单已结算
     */
    private String payMonth;

    /**
     * 推广位ID
     */
    private Long positionId;

    /**
     * 子渠道标识，在转链时可自定义传入
     */
    private String subUnionid;

    /**
     * sku维度的有效码（-1：未知,2.无效-拆单,3.无效-取消,4.无效-京东帮帮主订单,5.无效-账号异常,6.无效-赠品类目不返佣,7.无效-校园订单,8.无效-企业订单,9.无效-团购订单,11.无效-乡村推广员下单,13.无效-违规订单,14.无效-来源与备案网址不符,15.待付款,16.已付款,17.已完成（购买用户确认收货）,19.无效-佣金比例为0,20.无效-此复购订单对应的首购订单无效,21.无效-云店订单，22.无效-PLUS会员佣金比例为0，23.无效-支付有礼，24.已付定金
     */
    private Integer validCode;

    private static final long serialVersionUID = 1L;
    public JdOrder() {

    }
    public JdOrder(OrderRowResp orderRowResp) {
        this.id = orderRowResp.getId();
        this.orderId = orderRowResp.getOrderId();
        this.parentId = orderRowResp.getParentId();
        this.orderTime = DateUtil.string2Date(orderRowResp.getOrderTime(), DateUtil.SDF_YYYY_MM_DD_HH_MM_SS);
        this.finishTime = !StringUtils.hasLength(orderRowResp.getFinishTime()) ? null : DateUtil.string2Date(orderRowResp.getFinishTime(), DateUtil.SDF_YYYY_MM_DD_HH_MM_SS);
        this.plus = orderRowResp.getPlus() == 1;
        this.skuId = orderRowResp.getSkuId();
        this.skuName = orderRowResp.getSkuName();
        this.skuNum = orderRowResp.getSkuNum();
        this.price = orderRowResp.getPrice();
        this.commissionRate = orderRowResp.getCommissionRate();
        this.subSideRate = orderRowResp.getSubSideRate();
        this.subsidyRate = orderRowResp.getSubsidyRate();
        this.finalRate = orderRowResp.getFinalRate();
        this.estimateCosPrice = orderRowResp.getEstimateCosPrice();
        this.estimateFee = orderRowResp.getEstimateFee();
        this.actualCosPrice = orderRowResp.getActualCosPrice();
        this.actualFee = orderRowResp.getActualFee();
        this.payMonth = orderRowResp.getPayMonth();
        this.positionId = orderRowResp.getPositionId();
        this.subUnionid = orderRowResp.getSubUnionId();
        this.validCode = orderRowResp.getValidCode();
    }
}