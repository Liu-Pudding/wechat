package cn.ibionic.wechat;

import cn.ibionic.wechat.entity.JdOrder;
import cn.ibionic.wechat.entity.User;
import cn.ibionic.wechat.entity.Wallet;
import cn.ibionic.wechat.identity.Config;
import cn.ibionic.wechat.service.baseService.JdOrderBaseService;
import cn.ibionic.wechat.service.baseService.UserBaseService;
import cn.ibionic.wechat.service.baseService.WalletBaseService;
import cn.ibionic.wechat.util.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.domain.kplunion.OrderService.request.query.OrderRowReq;
import com.jd.open.api.sdk.domain.kplunion.OrderService.response.query.OrderRowResp;
import com.jd.open.api.sdk.request.kplunion.UnionOpenOrderRowQueryRequest;
import com.jd.open.api.sdk.response.kplunion.UnionOpenOrderRowQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ：Yuho Liu
 * @description：
 * @date ：2021/11/26 10:47 AM
 */
@Slf4j
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class JdTimerTest {

    @Autowired
    private JdOrderBaseService jdOrderBaseService;

    @Autowired
    private WalletBaseService walletBaseService;

    @Autowired
    private UserBaseService userBaseService;

    private static Date date;


    public void finishTask() {

        JdClient client=new DefaultJdClient(Config.JD_SERVER_URL, Config.JD_ACCESS_TOKEN,Config.JD_APP_KEY,Config.JD_APP_SECRET);
        UnionOpenOrderRowQueryRequest request=new UnionOpenOrderRowQueryRequest();
        OrderRowReq orderReq=new OrderRowReq();
        orderReq.setPageIndex(1);
        orderReq.setPageSize(100);

        //获得上个月22号0点
        Calendar startCalendar = Calendar.getInstance();

        int thisMonth = startCalendar.get(Calendar.MONTH);

        startCalendar.add(Calendar.MONTH, -1);
        startCalendar.set(Calendar.DATE, 1);
        startCalendar.set(Calendar.HOUR_OF_DAY, 0);
        startCalendar.set(Calendar.MINUTE, 0);
        startCalendar.set(Calendar.SECOND, 0);
        log.info("初始开始的时间为{}", startCalendar.getTime());

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.add(Calendar.MONTH, -1);
        endCalendar.set(Calendar.DATE, 1);
        endCalendar.set(Calendar.HOUR_OF_DAY, 0);
        endCalendar.set(Calendar.MINUTE, 59);
        endCalendar.set(Calendar.SECOND, 59);
        log.info("初始结束的时间为{}", endCalendar.getTime());

        try {
            UnionOpenOrderRowQueryResponse response;
            //分小时查询最近一个月
            while (endCalendar.get(Calendar.MONTH) != thisMonth) {
                //execute
                orderReq.setStartTime(DateUtil.date2String(startCalendar.getTime(), DateUtil.SDF_YYYY_MM_DD_HH_MM_SS));
                orderReq.setEndTime(DateUtil.date2String(endCalendar.getTime(), DateUtil.SDF_YYYY_MM_DD_HH_MM_SS));

                do {
                    response = client.execute(request);
                    log.info("正在查询{}-{}之间的订单", orderReq.getStartTime(), orderReq.getEndTime());
                    if ("0".equals(response.getCode()) && response.getQueryResult().getData()!=null) {
                        for (OrderRowResp orderRowResp: response.getQueryResult().getData()) {
                            JdOrder jdOrder = new JdOrder(orderRowResp);
                            //更新订单
                            log.info("更新订单{}", jdOrder);
                            User user = userBaseService.selectByJdPin(jdOrder.getSubUnionid());
                            Wallet wallet = new Wallet();
                            wallet.setUserId(user.getId());
                            //增加钱包金额
                            log.info("给{}充钱{}", user.getId(), jdOrder.getActualFee());
                        }
                    }
                    orderReq.setPageIndex(orderReq.getPageIndex() + 1);
                    startCalendar.add(Calendar.HOUR_OF_DAY, 1);
                    endCalendar.add(Calendar.HOUR_OF_DAY, 1);
                } while (response.getQueryResult().getHasMore()!=null && response.getQueryResult().getHasMore());
            }

        } catch (Exception e) {
            log.error("22号定时任务更新异常", e);
        }
    }

    //@Test
    public void jdPayTask() throws Exception {
        log.info("执行京东定时任务时间: " + LocalDateTime.now());
        //先查询过去1h的订单
        JdClient client=new DefaultJdClient(Config.JD_SERVER_URL, Config.JD_ACCESS_TOKEN,Config.JD_APP_KEY,Config.JD_APP_SECRET);
        UnionOpenOrderRowQueryRequest request=new UnionOpenOrderRowQueryRequest();
        OrderRowReq orderReq=new OrderRowReq();
        orderReq.setPageIndex(1);
        orderReq.setPageSize(100);
        //十一分钟前到现在
        orderReq.setStartTime("2021-12-28 14:00:03");
        orderReq.setEndTime("2021-12-28 15:00:03");
        log.info("查询{}到{}之间的订单", orderReq.getStartTime(), orderReq.getEndTime());
        orderReq.setType(1);
        request.setOrderReq(orderReq);
        request.setVersion("1.0");
        UnionOpenOrderRowQueryResponse response;
        do {
            response = client.execute(request);
            if ("0".equals(response.getCode()) && response.getQueryResult().getData()!=null) {
                for (OrderRowResp orderRowResp: response.getQueryResult().getData()) {
                    //如果库里没有这个订单，保存
                    JdOrder localJdOrder = jdOrderBaseService.selectById(orderRowResp.getId());
                    JdOrder jdOrder = new JdOrder(orderRowResp);
                    //拆单订单不保存
                    if (localJdOrder == null && jdOrder.getValidCode() != 2) {
                        if (jdOrderBaseService.save(jdOrder)) {
                            //TODO 发消息
                            if (jdOrder.getValidCode() == 16) {
                                log.info("付款完成，向{}用户发送消息", jdOrder.getSubUnionid());
                            } else if (jdOrder.getValidCode() == 26) {
                                log.info("定金完成，向{}用户发送消息", jdOrder.getSubUnionid());
                            } else if (jdOrder.getEstimateCosPrice() > 0){
                                //提交订单未支付
                            }
                        }else {
                            log.error("保存失败, {}", jdOrder.toString());
                        }
                    } else {
                        //否则是更新
                        jdOrderBaseService.update(jdOrder);
                    }
                }
            }
            orderReq.setPageIndex(orderReq.getPageIndex() + 1);
        } while (response.getQueryResult().getHasMore()!=null && response.getQueryResult().getHasMore());
    }

}
