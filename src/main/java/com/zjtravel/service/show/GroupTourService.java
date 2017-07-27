package com.zjtravel.service.show;

import com.zjtravel.pojo.bo.GroupTourSearchBO;
import com.zjtravel.pojo.po.DiscountPO;
import com.zjtravel.pojo.po.GroupTourPO;
import com.zjtravel.pojo.vo.GroupTourVO;

import java.util.List;


/**
 * Created by hunger on 2017/3/5.
 */
public interface GroupTourService {

    /**
     * 添加跟团游对象
     * @param groupTourPO
     * @return
     */
    Integer createGroupTour(GroupTourPO groupTourPO);

    /**
     * 更新跟团游对象
     * @param groupTourPO
     * @return
     */
    Integer updateGroupTour(GroupTourPO groupTourPO);

    /**
     * 删除跟团游对象
     * @param groupTourId
     */
    void deleteGroupTour(Long groupTourId);

    /**
     * 查找单个跟团游对象
     * @param groupTourId
     * @return
     */
    GroupTourPO findOne(Long groupTourId);

    /**
     * 查找全部跟团游对象
     * @return
     */
    List<GroupTourPO> findAll();

    /**
     * 按条件查找跟团游对象
     * @param condition
     * @return
     */
    List<GroupTourVO> findAllByMultipleCondition(GroupTourSearchBO condition);

    /**
     * 根据跟团游id查找优惠信息
     * @param groupTourId
     * @return
     */
    List<DiscountPO> findDiscounts(Long groupTourId);
}
