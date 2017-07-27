package com.zjtravel.service.show;

import com.zjtravel.pojo.po.GroupTourDetailPO;

import java.util.List;

/**
 * Created by hunger on 2017/3/5.
 */
public interface GroupTourDetailService {

    /**
     * 创建跟团游信息对象
     * @param groupTourDetailPO
     * @return
     */
    Integer createGroupTourDetail(GroupTourDetailPO groupTourDetailPO);

    /**
     * 更新跟团游信息对象
     * @param groupTourDetailPO
     * @return
     */
    Integer updateGroupTourDetail(GroupTourDetailPO groupTourDetailPO);

    /**
     * 删除跟团游信息对象
     * @param groupTourDetailId
     */
    void deleteGroupTourDetail(Long groupTourDetailId);

    /**
     * 根据跟团游id删除跟团游信息
     * @param groupTourId
     */
    void deleteDetailsByGroupTourId(Long groupTourId);

    /**
     * 查找单个跟团游信息对象
     * @param groupTourDetailId
     * @return
     */
    GroupTourDetailPO findOne(Long groupTourDetailId);

    /**
     * 根据跟团游id查找跟团游信息对象
     * @param groupTourId
     * @return
     */
    List<GroupTourDetailPO> findAllByGroupTourId(Long groupTourId);

    /**
     * 查找全部跟团游信息对象
     * @return
     */
    List<GroupTourDetailPO> findAll();
}
