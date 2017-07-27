package com.zjtravel.dao;

import com.zjtravel.pojo.po.WalletPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by hunger on 2017/3/3.
 */
@Repository
public interface WalletDAO {

    /**
     * 添加wallet数据
     * @param walletPO
     * @return
     */
    Integer createWallet(@Param("walletPO")WalletPO walletPO);

    /**
     * 更新wallet数据
     * @param walletPO
     * @return
     */
    Integer updateWallet(@Param("walletPO")WalletPO walletPO);

    /**
     * 删除wallet数据
     * @param userId
     */
    void deleteWallet(Long userId);

    /**
     * 查找单个wallet数据
     * @param userId
     * @return
     */
    WalletPO findOne(Long userId);


}
