package com.zjtravel.service.management;

import com.zjtravel.pojo.po.RolePO;

import java.util.List;
import java.util.Set;

/**
 * Created by hunger on 2017/2/7.
 */
public interface RoleService {

    /**
     * 创建角色
     * @param rolePO
     * @return
     */
    Integer createRole(RolePO rolePO);

    /**
     * 更新角色
     * @param rolePO
     * @return
     */
    Integer updateRole(RolePO rolePO);

    /**
     * 删除角色
     * @param roleId
     */
    void deleteRole(Long roleId);

    /**
     * 查找单个角色
     * @param roleId
     * @return
     */
    RolePO findOne(Long roleId);

    /**
     * 查找全部角色
     * @return
     */
    List<RolePO> findAll();

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Long... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Long[] roleIds);
}
