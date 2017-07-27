package com.zjtravel.dao;

import com.zjtravel.pojo.bo.GroupTourSearchBO;
import com.zjtravel.pojo.po.GroupTourPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by hunger on 2017/3/3.
 */
@Repository
public interface GroupTourDAO {

    Integer createGroupTour(@Param("groupTourPO")GroupTourPO groupTourPO);

    Integer updateGroupTour(@Param("groupTourPO")GroupTourPO groupTourPO);

    void deleteGroupTour(Long groupTourId);

    GroupTourPO findOne(Long groupTourId);

    List<GroupTourPO> findAll();


    List<GroupTourPO> findAllByMultipleCondition(@Param("condition")GroupTourSearchBO condition);
}
