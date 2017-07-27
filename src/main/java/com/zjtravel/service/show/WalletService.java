package com.zjtravel.service.show;

import com.zjtravel.pojo.po.WalletPO;

/**
 * Created by hunger on 2017/3/5.
 */

public interface WalletService {

    /**
     * 添加wallet对象
     * @param walletPO
     * @return
     */
    Integer createWallet(WalletPO walletPO);

    /**
     * 更新wallet对象
     * @param walletPO
     * @return
     */
    Integer updateWallet(WalletPO walletPO);

    /**
     * 删除wallet对象
     * @param userId
     */
    void deleteWallet(Long userId);

    /**
     * 查找单个wallet对象
     * @param userId
     * @return
     */
    WalletPO findOne(Long userId);

    /**
     * 增加金钱
     * @param userId
     * @param account
     * @return
     */
    Integer increaseMoney(Long userId,Double account);

    /**
     * 减少金钱
     * @param userId
     * @param account
     * @return
     */
    Integer decreaseMoney(Long userId,Double account);
}
