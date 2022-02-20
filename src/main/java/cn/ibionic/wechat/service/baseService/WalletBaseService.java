package cn.ibionic.wechat.service.baseService;

import cn.ibionic.wechat.dao.WalletDao;
import cn.ibionic.wechat.entity.Wallet;
import cn.ibionic.wechat.entity.WalletExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author ：Yuho Liu
 * @description：钱包基础服务类
 * @date ：2021/11/23 12:02 AM
 */
@Service
public class WalletBaseService {
    @Autowired
    private WalletDao walletDao;

    public Wallet createWallet (Integer userId) {
        Wallet wallet = new Wallet();
        wallet.setBalance(0.00);
        wallet.setUserId(userId);
        wallet.setUpdateTime(new Date());
        walletDao.insert(wallet);
        return wallet;
    }

    public boolean increaseBalanceByUserId (Double balance, Integer userId) {
        WalletExample walletExample = new WalletExample();
        walletExample.createCriteria().andUserIdEqualTo(userId);
        List<Wallet> wallets = walletDao.selectByExample(walletExample);
        if (wallets.size() > 0) {
            Wallet wallet = wallets.get(0);
            BigDecimal add = BigDecimal.valueOf(wallet.getBalance()).add(BigDecimal.valueOf(balance));
            wallet.setBalance(add.doubleValue());
            return walletDao.updateByPrimaryKeySelective(wallet) > 0;

        }
        return false;
    }

    public Double getBalanceByUserId(Integer userId) {
        WalletExample walletExample = new WalletExample();
        walletExample.createCriteria().andUserIdEqualTo(userId);
        List<Wallet> wallets = walletDao.selectByExample(walletExample);
        if (wallets.size() > 0) {
            Wallet wallet = wallets.get(0);
            return wallet.getBalance();
        }
        return 0D;
    }

}
