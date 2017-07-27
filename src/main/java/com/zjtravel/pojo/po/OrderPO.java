package com.zjtravel.pojo.po;

import java.util.Date;

/**
 * Created by hunger on 2017/2/28.
 */
public class OrderPO {
    private Long id; //编号
    private Long userId;//用户id
    private String username;//用户名
    private Long goodsId; //商品id
    private String goodsName;//商品名称
    private Integer goodsType;//商品类型
    private Integer number;//数量
    private Double payment;//实付款
    private OrderState state;//状态
    private Date gmtModified;//最后修改时间
    private Date gmtCreate;//创建时间

    public enum OrderState {
        nupay("未付款"), processing("处理中"),completed("已完成"),charge_back("申请退单"),paid("已付款"),charge_back_success("退单成功");

        private final String info;
        OrderState(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(Integer goodsType) {
        this.goodsType = goodsType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderPO orderPO = (OrderPO) o;

        if (id != null ? !id.equals(orderPO.id) : orderPO.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OrderPO{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", goodsType=" + goodsType +
                ", number=" + number +
                ", payment=" + payment +
                ", state=" + state +
                ", gmtModified=" + gmtModified +
                ", gmtCreate=" + gmtCreate +
                '}';
    }
}
