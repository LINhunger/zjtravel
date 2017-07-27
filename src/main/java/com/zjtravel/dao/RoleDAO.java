package com.zjtravel.dao;

import com.zjtravel.pojo.po.RolePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hunger on 2017/2/7.
 */
@Repository
public interface RoleDAO {

    /**
     * 创建角色
     * @param rolePO
     * @return
     */
    Integer createRole(@Param("rolePO") RolePO rolePO);

    /**
     * 更新角色
     * @param rolePO
     * @return
     */
    Integer updateRole(@Param("rolePO")RolePO rolePO);

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


}
