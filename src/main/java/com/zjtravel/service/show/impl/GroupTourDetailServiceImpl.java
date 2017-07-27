package com.zjtravel.service.show.impl;

import com.zjtravel.dao.GroupTourDetailDAO;
import com.zjtravel.pojo.po.GroupTourDetailPO;
import com.zjtravel.service.show.GroupTourDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hunger on 2017/3/5.
 */
@Service
public class GroupTourDetailServiceImpl implements GroupTourDetailService{

    @Autowired
    GroupTourDetailDAO groupTourDetailDAO;

    @Override
    public Integer createGroupTourDetail(GroupTourDetailPO groupTourDetailPO) {
        return groupTourDetailDAO.createGroupTourDetail(groupTourDetailPO);
    }

    @Override
    public Integer updateGroupTourDetail(GroupTourDetailPO groupTourDetailPO) {
        return groupTourDetailDAO.updateGroupTourDetail(groupTourDetailPO);
    }

    @Override
    public void deleteGroupTourDetail(Long groupTourDetailId) {
        groupTourDetailDAO.deleteGroupTourDetail(groupTourDetailId);
    }

    @Override
    public void deleteDetailsByGroupTourId(Long groupTourId) {
        groupTourDetailDAO.deleteDetailsByGroupTourId(groupTourId);
    }

    @Override
    public GroupTourDetailPO findOne(Long groupTourDetailId) {
        return groupTourDetailDAO.findOne(groupTourDetailId);
    }

    @Override
    public List<GroupTourDetailPO> findAllByGroupTourId(Long groupTourId) {
        return groupTourDetailDAO.findAllByGroupTourId(groupTourId);
    }

    @Override
    public List<GroupTourDetailPO> findAll() {
        return groupTourDetailDAO.findAll();
    }
}
