package com.zjtravel.dao;

import com.zjtravel.pojo.po.GroupTourPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by hunger on 2017/6/24.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring 配置文件
@ContextConfiguration({"classpath:spring/spring-config.xml"})
public class GroupTourDAOTest {

    @Autowired
    private GroupTourDAO groupTourDAO;

    /**
     * 新增产品
     * @throws Exception
     */
    @Test
    public void createGroupTour() throws Exception {
        GroupTourPO groupTourPO = new GroupTourPO();
        groupTourPO.setTitle("titile");
        groupTourPO.setLocation("location");
        groupTourPO.setIntroduce("introduce");
        groupTourPO.setTravel("travel");
        groupTourPO.setAvailable(true);
        groupTourDAO.createGroupTour(groupTourPO);
    }

    /**
     * 更新产品
     * @throws Exception
     */
    @Test
    public void updateGroupTour() throws Exception {
        GroupTourPO groupTourPO = new GroupTourPO();
        groupTourPO.setId(22L);
        groupTourPO.setTitle("titile_change");
        groupTourPO.setLocation("location_change");
        groupTourPO.setIntroduce("introduce_change");
        groupTourPO.setTravel("travel_change");
        groupTourPO.setAvailable(true);
        groupTourDAO.updateGroupTour(groupTourPO);
    }

    /**
     * 删除产品
     * @throws Exception
     */
    @Test
    public void deleteGroupTour() throws Exception {
        groupTourDAO.deleteGroupTour(22L);
    }

    @Test
    public void findOne() throws Exception {
        groupTourDAO.findOne(22L);
    }



}