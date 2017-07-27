package com.zjtravel.service.show;

import com.zjtravel.pojo.po.OrderPO;
import com.zjtravel.pojo.vo.OrderDetailVO;

import java.util.List;

/**
 * Created by hunger on 2017/3/5.
 */
public interface OrderService {

    /**
     * 添加订单
     * @param orderPO
     * @return
     */
    Integer createOrder(OrderPO orderPO);

    /**
     * 更新订单
     * @param orderPO
     * @return
     */
    Integer updateOrder(OrderPO orderPO);

    /**
     * 删除订单
     * @param orderId
     */
    void deleteOrder(Long orderId);

    /**
     * 查找单个订单
     * @param orderId
     * @return
     */
    OrderPO findOne(Long orderId);

    /**
     * 查找全部订单
     * @return
     */
    List<OrderPO> findAll();

    /**
     * 查找用户全部订单
     * @return
     */
    List<OrderPO> findUserOrder(Long userId);

    /**
     * 更改订单状态
     * @param orderId
     * @param orderState
     * @return
     */
    Integer updateOrderState(Long orderId , OrderPO.OrderState orderState);

    /**
     *查看订单详情
     * @param orderId
     * @return
     */
    OrderDetailVO<?,?> seeOrderDetail(Long orderId);

}
