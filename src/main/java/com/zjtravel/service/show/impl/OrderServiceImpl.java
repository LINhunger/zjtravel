package com.zjtravel.service.show.impl;

import com.zjtravel.dao.GroupTourDAO;
import com.zjtravel.dao.GroupTourDetailDAO;
import com.zjtravel.dao.OrderDAO;
import com.zjtravel.pojo.po.GroupTourDetailPO;
import com.zjtravel.pojo.po.GroupTourPO;
import com.zjtravel.pojo.po.OrderPO;
import com.zjtravel.pojo.vo.OrderDetailVO;
import com.zjtravel.service.show.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hunger on 2017/3/5.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private GroupTourDAO groupTourDAO;
    @Autowired
    private GroupTourDetailDAO groupTourDetailDAO;

    @Override
    public Integer createOrder(OrderPO orderPO) {
        return orderDAO.createOrder(orderPO);
    }

    @Override
    public Integer updateOrder(OrderPO orderPO) {
        return orderDAO.updateOrder(orderPO);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderDAO.deleteOrder(orderId);
    }

    @Override
    public OrderPO findOne(Long orderId) {
        return orderDAO.findOne(orderId);
    }

    @Override
    public List<OrderPO> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public List<OrderPO> findUserOrder(Long userId) {
        return orderDAO.findUserOrder(userId);
    }

    @Override
    public Integer updateOrderState(Long orderId, OrderPO.OrderState orderState) {
        OrderPO orderPO = findOne(orderId);
        if (orderPO == null) {
            return -1;
        }
        orderPO.setState(orderState);
        return orderDAO.updateOrder(orderPO);
    }

    @Override
    public OrderDetailVO<?,?> seeOrderDetail(Long orderId) {
        OrderDetailVO<GroupTourPO , GroupTourDetailPO> orderDetailVO = null;
        OrderPO orderPO = findOne(orderId);
        if (orderPO == null) {
            return null;
        }
        switch (orderPO.getGoodsType()) {
            case 1 :
                orderDetailVO = new OrderDetailVO<GroupTourPO , GroupTourDetailPO>();
                GroupTourDetailPO groupTourDetailPO = groupTourDetailDAO.findOne(orderPO.getGoodsId());
                if (groupTourDetailPO != null) {
                    GroupTourPO groupTourPO = groupTourDAO.findOne(groupTourDetailPO.getGroupTourId());
                    if (groupTourPO != null) {
                        orderDetailVO.setOrderPO(orderPO);
                        orderDetailVO.setGoods(groupTourPO);
                        orderDetailVO.setGoodsDetail(groupTourDetailPO);
                    }
                }
               break;
            case 2 : break;
            default :
                System.out.println();
        }
        return orderDetailVO;
    }
}
