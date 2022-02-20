package cn.ibionic.wechat.service.baseService;

import cn.ibionic.wechat.dao.UserDao;
import cn.ibionic.wechat.entity.User;
import cn.ibionic.wechat.entity.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author ：Yuho Liu
 * @description：用户基础服务
 * @date ：2021/11/15 11:38 PM
 */
@Service
public class UserBaseService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private WalletBaseService walletBaseService;

    public User getUserByOpenId(String openId) {
        UserExample example = new UserExample();
        example.createCriteria().andOpenIdEqualTo(openId);
        List<User> users = userDao.selectByExample(example);
        return users.size() > 0 ? users.get(0) : null;
    }

    @Transactional
    public User generateUser (String openId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andOpenIdEqualTo(openId);
        if (userDao.selectByExample(userExample).size() == 0) {
            User user = new User();
            user.setOpenId(openId);
            user.setCreateTime(new Date());
            user.setJdPin(String.valueOf(System.currentTimeMillis()));
            userDao.insert(user);
            userDao.updateByPrimaryKey(user);
            walletBaseService.createWallet(user.getId());
            return user;
        }
        return null;
    }

    public User selectByJdPin(String jdPin) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andJdPinEqualTo(jdPin);
        return userDao.selectByExample(userExample).get(0);
    }
}
