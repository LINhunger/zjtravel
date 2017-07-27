package com.zjtravel.dao;

import com.zjtravel.pojo.po.OrderPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hunger on 2017/3/3.
 */
@Repository
public interface OrderDAO {

    Integer createOrder(@Param("orderPO")OrderPO orderPO);

    Integer updateOrder(@Param("orderPO")OrderPO orderPO);

    void deleteOrder(Long orderId);

    OrderPO findOne(Long orderId);

    List<OrderPO> findAll();

    List<OrderPO> findUserOrder(Long userId);
}
