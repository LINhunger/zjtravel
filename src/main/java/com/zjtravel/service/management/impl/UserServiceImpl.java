package com.zjtravel.service.management.impl;

import com.zjtravel.dao.UserDAO;
import com.zjtravel.pojo.po.UserPO;
import com.zjtravel.pojo.po.WalletPO;
import com.zjtravel.pojo.vo.UserVO;
import com.zjtravel.service.management.PasswordHelper;
import com.zjtravel.service.management.RoleService;
import com.zjtravel.service.management.UserService;
import com.zjtravel.service.show.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by hunger on 2017/2/7.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordHelper passwordHelper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private WalletService walletService;

    /**
     * 创建用户
     * @param userPO
     */
    @Override
    public Integer createUser(UserPO userPO) {
        //加密密码
        passwordHelper.encryptPassword(userPO);
        Integer result = userDAO.createUser(userPO);
        WalletPO walletPO = new WalletPO();
        walletPO.setUserId(userPO.getId());
        walletPO.setMoney(100.0);
        walletService.createWallet(walletPO);
        return result;
    }

    @Override
    public Integer updateUser(UserPO userPO) {
        return userDAO.updateUser(userPO);
    }

    @Override
    public void deleteUser(Long userId) {
        userDAO.deleteUser(userId);
    }

    @Override
    public void encryptPassword(UserPO userPO) {
        passwordHelper.encryptPassword(userPO);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    @Override
    public void changePassword(Long userId, String newPassword) {
        UserPO userPO =userDAO.findOne(userId);
        userPO.setPassword(newPassword);
        passwordHelper.encryptPassword(userPO);
        userDAO.updateUser(userPO);
    }

    @Override
    public UserPO findOne(Long userId) {
        return userDAO.findOne(userId);
    }

    @Override
    public List<UserPO> findAll() {
        return userDAO.findAll();
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public UserPO findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    /**
     * 根据手机查找用户
     * @param phone
     * @return
     */
    @Override
    public UserPO findByPhone(String phone) {
        return userDAO.findByPhone(phone);
    }
    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     */
    @Override
    public Set<String> findRoles(String username) {
        UserPO userPO =findByUsername(username);
        if(userPO == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findRoles(userPO.getRoleIds().toArray(new Long[0]));
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     */
    @Override
    public Set<String> findPermissions(String username) {
        UserPO userPO =findByUsername(username);
        if(userPO == null) {
            return Collections.EMPTY_SET;
        }
        return roleService.findPermissions(userPO.getRoleIds().toArray(new Long[0]));
    }

    /**
     * 将userPO集合转化为userVO集合
     * @param userPOList
     * @return
     */
    @Override
    public List<UserVO> findAllUserVO(List<UserPO> userPOList) {
        List<UserVO> userVOList = new ArrayList<>();
        for (UserPO userPO : userPOList) {
            UserVO userVO = new UserVO();
            userVO.setUserPO(userPO);
            userVO.setUserRoles(findRoles(userPO.getUsername()));
            if (userPO != null) {
                userVOList.add(userVO);
            }
        }
        return userVOList;
    }
}
