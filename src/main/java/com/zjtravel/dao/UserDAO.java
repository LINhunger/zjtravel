package com.zjtravel.dao;

import com.zjtravel.pojo.po.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by hunger on 2017/2/7.
 */
@Repository
public interface UserDAO {

    /**
     * 添加user数据
     * @param userPO
     * @return
     */
    Integer createUser(@Param("userPO") UserPO userPO);

    /**
     * 更新user数据
     * @param userPO
     * @return
     */
    Integer updateUser(@Param("userPO") UserPO userPO);

    /**
     * 删除user数据
     * @param userId
     */
    void deleteUser(Long userId);

    /**
     * 查找单个user数据
     * @param userId
     * @return
     */
    UserPO findOne(Long userId);

    /**
     * 查找全部user数据
     * @return
     */
    List<UserPO> findAll();

    /**
     * 查找单个user数据
     * @param username
     * @return
     */
    UserPO findByUsername(String username);

    /**
     * 查找单个user数据
     * @param phone
     * @return
     */
    UserPO findByPhone(String phone);
}
