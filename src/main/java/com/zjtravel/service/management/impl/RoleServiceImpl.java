package com.zjtravel.service.management.impl;

import com.zjtravel.dao.RoleDAO;
import com.zjtravel.pojo.po.RolePO;
import com.zjtravel.service.management.ResourceService;
import com.zjtravel.service.management.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hunger on 2017/2/7.
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private ResourceService resourceService;

    public Integer createRole(RolePO rolePO) {
        return roleDAO.createRole(rolePO);
    }

    public Integer updateRole(RolePO rolePO) {
        return roleDAO.updateRole(rolePO);
    }

    public void deleteRole(Long roleId) {
        roleDAO.deleteRole(roleId);
    }

    @Override
    public RolePO findOne(Long roleId) {
        return roleDAO.findOne(roleId);
    }

    @Override
    public List<RolePO> findAll() {
        return roleDAO.findAll();
    }

    @Override
    public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            RolePO rolePO = findOne(roleId);
            if(rolePO != null) {
                roles.add(rolePO.getRole());
            }
        }
        return roles;
    }

    @Override
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        for(Long roleId : roleIds) {
            RolePO rolePO = findOne(roleId);
            if(rolePO != null) {
                resourceIds.addAll(rolePO.getResourceIds());
            }
        }
        return resourceService.findPermissions(resourceIds);
    }
}
