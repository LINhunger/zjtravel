package com.zjtravel.service.show.impl;

import com.zjtravel.dao.WalletDAO;
import com.zjtravel.pojo.po.WalletPO;
import com.zjtravel.service.show.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hunger on 2017/3/5.
 */
@Service
public class WalletServiceImpl implements WalletService{

    @Autowired
    private WalletDAO walletDAO;

    @Override
    public Integer createWallet(WalletPO walletPO) {
        return walletDAO.createWallet(walletPO);
    }

    @Override
    public Integer updateWallet(WalletPO walletPO) {
        return walletDAO.updateWallet(walletPO);
    }

    @Override
    public void deleteWallet(Long userId) {
        walletDAO.deleteWallet(userId);
    }

    @Override
    public WalletPO findOne(Long userId) {
        return walletDAO.findOne(userId);
    }

    @Override
    public Integer increaseMoney(Long userId, Double account) {
        WalletPO walletPO = findOne(userId);
        if (walletPO == null) {
            return -1;
        }
        walletPO.setMoney(walletPO.getMoney() + account);
        return updateWallet(walletPO);
    }

    @Override
    public Integer decreaseMoney(Long userId, Double account) {
        WalletPO walletPO = findOne(userId);
        if (walletPO == null) {
            return -1;
        }
        if (walletPO.getMoney()-account >= 0) {
            walletPO.setMoney(walletPO.getMoney() - account);
            return updateWallet(walletPO);
        }else {
            return 0;
        }
    }
}
