package cn.ibionic.wechat.service.baseService;

import cn.ibionic.wechat.dao.JdOrderDao;
import cn.ibionic.wechat.entity.JdOrder;
import cn.ibionic.wechat.entity.JdOrderExample;
import cn.ibionic.wechat.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author ：Yuho Liu
 * @description：jd订单基础服务
 * @date ：2021/11/21 2:01 PM
 */
@Service
public class JdOrderBaseService {
    @Autowired
    private JdOrderDao jdOrderDao;

    public boolean save(JdOrder jdOrder) {
        return jdOrderDao.insert(jdOrder) > 0;
    }

    public JdOrder selectById(String id) {
        return jdOrderDao.selectByPrimaryKey(id);
    }

    /**
     * 查询一天内未付款订单
     * @return
     */
    public List<JdOrder> selectUnpaidOrderInOneDay() {
        JdOrderExample jdOrderExample = new JdOrderExample();
        Date date = DateUtil.calcDate(-1);
        jdOrderExample.createCriteria().andValidCodeEqualTo(15).andOrderTimeLessThan(date);
        return jdOrderDao.selectByExample(jdOrderExample);
    }

    public List<JdOrder> selectPayedBySubUnionid(String subUnionid) {

        JdOrderExample jdOrderExample = new JdOrderExample();
        jdOrderExample.createCriteria().andValidCodeIn(Arrays.asList(16, 17)).andSubUnionidEqualTo(subUnionid);
        return jdOrderDao.selectByExample(jdOrderExample);
    }

    public boolean update(JdOrder jdOrder) {
        return jdOrderDao.updateByPrimaryKey(jdOrder) > 0;
    }

}
