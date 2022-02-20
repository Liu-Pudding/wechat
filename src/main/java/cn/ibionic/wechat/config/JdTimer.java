package cn.ibionic.wechat.config;

import cn.ibionic.wechat.entity.JdOrder;
import cn.ibionic.wechat.entity.User;
import cn.ibionic.wechat.entity.Wallet;
import cn.ibionic.wechat.identity.Config;
import cn.ibionic.wechat.service.baseService.JdOrderBaseService;
import cn.ibionic.wechat.service.baseService.UserBaseService;
import cn.ibionic.wechat.service.baseService.WalletBaseService;
import cn.ibionic.wechat.util.DateUtil;
import com.jd.open.api.sdk.DefaultJdClient;
import com.jd.open.api.sdk.JdClient;
import com.jd.open.api.sdk.domain.kplunion.OrderService.request.query.OrderRowReq;
import com.jd.open.api.sdk.domain.kplunion.OrderService.response.query.OrderRowResp;
import com.jd.open.api.sdk.request.kplunion.UnionOpenOrderRowQueryRequest;
import com.jd.open.api.sdk.response.kplunion.UnionOpenOrderRowQueryResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * @author ：Yuho Liu
 * @description：京东定时任务（10min查询一次最新的）
 * @date ：2021/11/20 3:37 PM
 */
//1.主要用于标记配置类，兼备Component的效果。
@Configuration
//2.开启定时任务
@EnableScheduling
@Slf4j
@EnableAsync
public class JdTimer {

    @Autowired
    private JdOrderBaseService jdOrderBaseService;

    @Autowired
    private WalletBaseService walletBaseService;

    @Autowired
    private UserBaseService userBaseService;

    /**
     * 创建一个线程池，用于京东订单记录方法
     * @return
     */
    @Bean("taskScheduler")
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(5);
        return taskScheduler;
    }

    /**
     * 京东订单记录
     * @throws Exception
     */
    @Async("taskScheduler")
    @Scheduled(fixedRate = 60000 * 5)
    public void jdPayTask() throws Exception {
        log.info("执行京东定时任务时间: " + LocalDateTime.now());
        //先查询过去1h的订单
        JdClient client=new DefaultJdClient(Config.JD_SERVER_URL, Config.JD_ACCESS_TOKEN,Config.JD_APP_KEY,Config.JD_APP_SECRET);
        UnionOpenOrderRowQueryRequest request=new UnionOpenOrderRowQueryRequest();
        OrderRowReq orderReq=new OrderRowReq();
        orderReq.setPageIndex(1);
        orderReq.setPageSize(100);
        //十五分钟前到现在
        orderReq.setStartTime(DateUtil.calcDate(- 60 * 15, DateUtil.SDF_YYYY_MM_DD_HH_MM_SS));
        orderReq.setEndTime(DateUtil.getNowDate(DateUtil.SDF_YYYY_MM_DD_HH_MM_SS));
        log.info("查询{}到{}之间的订单", orderReq.getStartTime(), orderReq.getEndTime());
        orderReq.setType(3);
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

    /**
     * 金额发放定时任务
     * 每个月22号0点查询上个月完成交易的订单
     */
    @Scheduled(cron = "0 0 0 22 * ?")
    @Async
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

        orderReq.setType(2);
        request.setOrderReq(orderReq);
        request.setVersion("1.0");

        try {
            UnionOpenOrderRowQueryResponse response;
            //分小时查询最近一个月
            while (endCalendar.get(Calendar.MONTH) != thisMonth) {
                //execute
                orderReq.setStartTime(DateUtil.date2String(startCalendar.getTime(), DateUtil.SDF_YYYY_MM_DD_HH_MM_SS));
                orderReq.setEndTime(DateUtil.date2String(endCalendar.getTime(), DateUtil.SDF_YYYY_MM_DD_HH_MM_SS));

                do {
                    response = client.execute(request);
                    log.info("正在查询{}-{}之间完成的订单", orderReq.getStartTime(), orderReq.getEndTime());
                    if ("0".equals(response.getCode()) && response.getQueryResult().getData()!=null) {
                        for (OrderRowResp orderRowResp: response.getQueryResult().getData()) {
                            JdOrder jdOrder = new JdOrder(orderRowResp);
                            //更新订单
                            jdOrderBaseService.update(jdOrder);
                            User user = userBaseService.selectByJdPin(jdOrder.getSubUnionid());
                            Wallet wallet = new Wallet();
                            wallet.setUserId(user.getId());
                            //增加钱包金额
                            walletBaseService.increaseBalanceByUserId(jdOrder.getActualFee() * Config.GLOBAL_COEFFICIENT, user.getId());
                        }
                    }
                    orderReq.setPageIndex(orderReq.getPageIndex() + 1);
                } while (response.getQueryResult().getHasMore()!=null && response.getQueryResult().getHasMore());
            }

        } catch (Exception e) {
            log.error("22号定时任务更新异常", e);
        }
    }
}
