package com.zjtravel.dao;

import com.zjtravel.pojo.po.DiscountPO;
import com.zjtravel.pojo.vo.GroupTourVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hunger on 2017/4/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit Spring 配置文件
@ContextConfiguration({"classpath:spring/spring-config.xml"})
public class DiscountDAOTest {

    @Autowired
    private DiscountDAO discountDAO;

    /**
     * 测试新增折扣
     * @throws Exception
     */
    @Test
    public void createDiscount() throws Exception {
        DiscountPO discount = new DiscountPO();
        discount.setId(100L);
        discount.setAvailable(true);
        discount.setTitle("暑假大放假");
        discountDAO.createDiscount(discount);
    }

    /**
     * 测试更新折扣
     * @throws Exception
     */
    @Test
    public void updateDiscount() throws Exception {
        DiscountPO discount = new DiscountPO();
        discount.setId(5L);
        discount.setAvailable(true);
        discount.setTitle("暑假大放假2期");
        discountDAO.updateDiscount(discount);
    }

    /**
     * 测试删除折扣
     * @throws Exception
     */
    @Test
    public void deleteDiscount() throws Exception {
        discountDAO.deleteDiscount(5L);
    }

    /**
     * 测试查询单个折扣
     * @throws Exception
     */
    @Test
    public void findOne() throws Exception {
        DiscountPO discount = discountDAO.findOne(100L);
        System.out.println(discount);
    }

    /**
     * 测试全部折扣
     * @throws Exception
     */
    @Test
    public void findAll() throws Exception {
        List<DiscountPO> discounts  = discountDAO.findAll();
        System.out.println(discounts);
    }

}