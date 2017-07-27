package com.zjtravel.pojo.vo;

import com.zjtravel.pojo.po.OrderPO;

/**
 * Created by hunger on 2017/3/5.
 */
public class OrderDetailVO<T,S> {

    private T goods;
    private S goodsDetail;
    private OrderPO orderPO;
    private String username;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public T getGoods() {
        return goods;
    }

    public void setGoods(T goods) {
        this.goods = goods;
    }

    public S getGoodsDetail() {
        return goodsDetail;
    }

    public void setGoodsDetail(S goodsDetail) {
        this.goodsDetail = goodsDetail;
    }

    public OrderPO getOrderPO() {
        return orderPO;
    }

    public void setOrderPO(OrderPO orderPO) {
        this.orderPO = orderPO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetailVO<?,?> that = (OrderDetailVO<?,?>) o;

        return orderPO != null ? orderPO.equals(that.orderPO) : that.orderPO == null;

    }

    @Override
    public int hashCode() {
        return orderPO != null ? orderPO.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OrderDetailVO{" +
                "goods=" + goods +
                ", goodsDetail=" + goodsDetail +
                ", orderPO=" + orderPO +
                ", username='" + username + '\'' +
                '}';
    }
}
