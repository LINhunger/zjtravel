package com.zjtravel.service.management;

import com.zjtravel.pojo.po.UserPO;
import com.zjtravel.pojo.vo.UserVO;

import java.util.List;
import java.util.Set;

/**
 * Created by hunger on 2017/2/7.
 */
public interface UserService {


    /**
     * 加密用户对象
     * @param userPO
     */
    void encryptPassword(UserPO userPO);

    /**
     * 创建用户
     * @param userPO
     */
    Integer createUser(UserPO userPO);

    /**
     * 更新用户
     * @param userPO
     * @return
     */
    Integer updateUser(UserPO userPO);

    /**
     * 删除用户
     * @param userId
     */
    void deleteUser(Long userId);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    void changePassword(Long userId, String newPassword);

    /**
     * 查找单个对象
     * @param userId
     * @return
     */
    UserPO findOne(Long userId);

    /**
     * 查找全部对象
     * @return
     */
    List<UserPO> findAll();

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    UserPO findByUsername(String username);

    /**
     * 根据手机查找用户
     * @param phone
     * @return
     */
    UserPO findByPhone(String phone);

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    Set<String> findRoles(String username);

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    Set<String> findPermissions(String username);

    /**
     * 将PO集合转化为VO集合
     * @param userPOList
     * @return
     */
    List<UserVO> findAllUserVO(List<UserPO> userPOList);
}
