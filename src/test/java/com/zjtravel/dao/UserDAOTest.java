package com.zjtravel.dao;

import com.zjtravel.pojo.po.UserPO;
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
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    /**
     * 新增用户
     * @throws Exception
     */
    @Test
    public void createUser() throws Exception {
        UserPO user = new UserPO();
        user.setUsername("hg");
        user.setPassword("123456");
        userDAO.createUser(user);
    }

    /**
     * 用户更新
     * @throws Exception
     */
    @Test
    public void updateUser() throws Exception {
        UserPO user = new UserPO();
        user.setId(35L);
        user.setPhone("13534165258");
        userDAO.updateUser(user);
    }

    /**
     * 用户删除
     * @throws Exception
     */
    @Test
    public void deleteUser() throws Exception {
        userDAO.deleteUser(35L);
    }

    /**
     * 查找用户
     * @throws Exception
     */
    @Test
    public void findOne() throws Exception {
        System.out.println(userDAO.findOne(34L));
    }


}