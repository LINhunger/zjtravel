package com.zjtravel.pojo.vo;

import com.zjtravel.pojo.po.UserPO;

import java.util.Set;

/**
 * Created by hunger on 2017/2/23.
 */
public class UserVO {

    private UserPO userPO;//用户数据原型
    private Set<String> userRoles;//用户权限

    public UserPO getUserPO() {
        return userPO;
    }

    public void setUserPO(UserPO userPO) {
        this.userPO = userPO;
    }

    public Set<String> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<String> userRoles) {
        this.userRoles = userRoles;
    }


    @Override
    public boolean equals(Object o) {
        if (userPO != null) {
            return userPO.equals(o);
        }
        return  false;
    }

    @Override
    public int hashCode() {
        return userPO != null ? userPO.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "UserVO{" +
                "userPO=" + userPO +
                ", userRoles=" + userRoles +
                '}';
    }
}
