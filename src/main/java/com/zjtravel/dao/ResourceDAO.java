package com.zjtravel.dao;

import com.zjtravel.pojo.po.ResourcePO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hunger on 2017/2/11.
 */
@Repository
public interface ResourceDAO {

    /**
     * 创建资源
     * @param resourcePO
     * @return
     */
    Integer createResource(@Param("resourcePO") ResourcePO resourcePO);

    /**
     * 更新资源
     * @param resourcePO
     * @return
     */
    Integer updateResource(@Param("resourcePO")ResourcePO resourcePO);

    /**
     * 删除资源
     * @param resourceId
     */
    void deleteResource(Long resourceId);

    /**
     * 删除子资源
     * @param selfParent
     */
    void deleteSubResource(String selfParent);

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
}
