package com.zjtravel.dao;

import com.zjtravel.pojo.po.GroupTourDetailPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hunger on 2017/3/3.
 */
@Repository
public interface GroupTourDetailDAO {

    Integer createGroupTourDetail(@Param("groupTourDetailPO")GroupTourDetailPO groupTourDetailPO);

    Integer updateGroupTourDetail(@Param("groupTourDetailPO")GroupTourDetailPO groupTourDetailPO);

    void deleteGroupTourDetail(Long groupTourDetailId);

    void deleteDetailsByGroupTourId(Long groupTourId);

    GroupTourDetailPO findOne(Long groupTourDetailId);

    List<GroupTourDetailPO> findAllByGroupTourId(Long groupTourId);

    List<GroupTourDetailPO> findAll();
}
