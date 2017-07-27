package com.zjtravel.service.management.impl;

import com.zjtravel.dao.ResourceDAO;
import com.zjtravel.pojo.po.ResourcePO;
import com.zjtravel.service.management.ResourceService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by hunger on 2017/2/11.
 */
@Service
public class ResourceServiceImpl implements ResourceService{
    @Autowired
    private ResourceDAO resourceDAO;

    @Override
    public Integer createResource(ResourcePO resourcePO) {
        return resourceDAO.createResource(resourcePO);
    }

    @Override
    public Integer updateResource(ResourcePO resourcePO) {
        return resourceDAO.updateResource(resourcePO);
    }

    @Override
    @Transactional
    public void deleteResource(Long resourceId) {
        ResourcePO resourcePO = resourceDAO.findOne(resourceId);
        resourceDAO.deleteResource(resourceId);
        resourceDAO.deleteSubResource(resourcePO.makeSelfAsParentIds());
    }

    @Override
    public ResourcePO findOne(Long resourceId) {
        return resourceDAO.findOne(resourceId);
    }

    @Override
    public List<ResourcePO> findAll() {
        return resourceDAO.findAll();
    }

    @Override
    public List<Map<String,?>> groupResource(List<ResourcePO> resourceList) {
        List<Map<String,?>> groupList = new ArrayList<>();
        for (ResourcePO menuResource : resourceList) {
            Map<String,Object> aGroup = new TreeMap<>();
            if (menuResource.getType().toString().equals("menu") &&
                    menuResource.getParentId() != 0) {
                aGroup.put("menu",menuResource);
                Set<ResourcePO> buttons = new HashSet<>();
                for (ResourcePO buttonResource : resourceList) {
                    if (buttonResource.getType().toString().equals("button") &&
                            buttonResource.getParentIds().equals(menuResource.makeSelfAsParentIds())) {
                        buttons.add(buttonResource);
                    }
                }
                if (!CollectionUtils.isEmpty(aGroup)) {
                    aGroup.put("buttons",buttons);
                }
            }
            if (!CollectionUtils.isEmpty(aGroup)) {
                groupList.add(aGroup);
            }
        }
        return groupList;
    }

    @Override
    public Set<String> findPermissions(Set<Long> resourceIds) {
        Set<String> permissions = new HashSet<String>();
        for(Long resourceId : resourceIds) {
            ResourcePO resourcePO = findOne(resourceId);
            if(resourcePO != null && !StringUtils.isEmpty(resourcePO.getPermission())) {
                permissions.add(resourcePO.getPermission());
            }
        }
        return permissions;
    }

    @Override
    public List<ResourcePO> findMenus(Set<String> permissions) {
        List<ResourcePO> allResources = findAll();
        List<ResourcePO> menus = new ArrayList<ResourcePO>();
        for(ResourcePO resourcePO : allResources) {
            if(resourcePO.isRootNode()) {
                continue;
            }
            if(resourcePO.getType() != ResourcePO.ResourceType.menu) {
                continue;
            }
            if(!hasPermission(permissions, resourcePO)) {
                continue;
            }
            menus.add(resourcePO);
        }
        return menus;
    }

    private boolean hasPermission(Set<String> permissions, ResourcePO resourcePO) {
        if(StringUtils.isEmpty(resourcePO.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resourcePO.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
