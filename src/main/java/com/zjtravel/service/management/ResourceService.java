package com.zjtravel.service.management;

import com.zjtravel.pojo.po.ResourcePO;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by hunger on 2017/2/11.
 */
public interface ResourceService {

    /**
     * 添加资源
     * @param resourcePO
     * @return
     */
    Integer createResource(ResourcePO resourcePO);

    /**
     * 更新资源
     * @param resourcePO
     * @return
     */
    Integer updateResource(ResourcePO resourcePO);

    /**
     * 删除资源
     * @param resourceId
     */
    void deleteResource(Long resourceId);

    /**
     * 查找单个资源
     * @param resourceId
     * @return
     */
    ResourcePO findOne(Long resourceId);

    /**
     * 查找全部资源
     * @return
     */
    List<ResourcePO> findAll();

    /**
     * 树状排序资源
     * @return
     */
    List<Map<String,?>> groupResource( List<ResourcePO> resourceList);

    /**
     * 得到资源对应的权限字符串
     * @param resourceIds
     * @return
     */
    Set<String> findPermissions(Set<Long> resourceIds);

    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<ResourcePO> findMenus(Set<String> permissions);
}
