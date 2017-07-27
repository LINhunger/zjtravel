package com.zjtravel.service.show.impl;

import com.zjtravel.dao.GroupTourDAO;
import com.zjtravel.pojo.bo.GroupTourSearchBO;
import com.zjtravel.pojo.po.DiscountPO;
import com.zjtravel.pojo.po.GroupTourDetailPO;
import com.zjtravel.pojo.po.GroupTourPO;
import com.zjtravel.pojo.vo.GroupTourVO;
import com.zjtravel.service.show.DiscountService;
import com.zjtravel.service.show.GroupTourDetailService;
import com.zjtravel.service.show.GroupTourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hunger on 2017/3/5.
 */
@Service
public class GroupTourServiceImpl implements GroupTourService{
    
    @Autowired
    private GroupTourDAO groupTourDAO;
    @Autowired
    private GroupTourDetailService groupTourDetailService;
    @Autowired
    private DiscountService discountService;

    @Override
    public Integer createGroupTour(GroupTourPO groupTourPO) {
        return groupTourDAO.createGroupTour(groupTourPO);
    }

    @Override
    public Integer updateGroupTour(GroupTourPO groupTourPO) {
        return groupTourDAO.updateGroupTour(groupTourPO);
    }

    @Override
    @Transactional
    public void deleteGroupTour(Long groupTourId) {
        groupTourDetailService.deleteDetailsByGroupTourId(groupTourId);
        groupTourDAO.deleteGroupTour(groupTourId);
    }

    @Override
    public GroupTourPO findOne(Long groupTourId) {
        return groupTourDAO.findOne(groupTourId);
    }

    @Override
    public List<GroupTourPO> findAll() {
        return groupTourDAO.findAll();
    }


    @Override
    public List<GroupTourVO> findAllByMultipleCondition(GroupTourSearchBO condition) {
        List<GroupTourVO> groupTourVOList = new ArrayList<>();
        List<GroupTourPO> groupTourPOList = groupTourDAO.findAllByMultipleCondition(condition);
        if (groupTourPOList == null) {
            return null;
        }
        for (GroupTourPO groupTourPO : groupTourPOList) {
            GroupTourVO groupTourVO = new GroupTourVO();
            List<GroupTourDetailPO> detailList = groupTourDetailService.findAllByGroupTourId(groupTourPO.getId());
            groupTourVO.setGroupTourPO(groupTourPO);
            groupTourVO.setDetailList(detailList);
            if (!groupTourPO.getDiscountIds().isEmpty()) {
                groupTourVO.setDiscountPO(discountService.findOne(groupTourPO.getDiscountIds().get(0)));
            }
            groupTourVOList.add(groupTourVO);
        }
        return groupTourVOList;
    }

    @Override
    public List<DiscountPO> findDiscounts(Long groupTourId) {
        GroupTourPO groupTourPO = groupTourDAO.findOne(groupTourId);
        if (groupTourPO == null) {
            return Collections.EMPTY_LIST;
        }
        return discountService.findDiscounts(groupTourPO.getDiscountIds().toArray(new Long[0]));
    }
}
