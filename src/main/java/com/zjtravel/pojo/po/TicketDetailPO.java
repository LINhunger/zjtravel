package com.zjtravel.pojo.po;

import java.util.Date;

/**
 * Created by hunger on 2017/3/28.
 */
public class TicketDetailPO {

    private Long id; //编号
    private Long ticketId; //跟团游id
    private Date departureTime;//出发时间
    private Double price;//价格
    private Integer stock;//库存
    private Boolean available = Boolean.TRUE;//是否上架
    private Date gmtCreate;//创建时间
    private Date gmtModified;//最后修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketDetailPO ticketDetailPO = (TicketDetailPO) o;

        if (id != null ? !id.equals(ticketDetailPO.id) : ticketDetailPO.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TicketDetailPO{" +
                "id=" + id +
                ", ticketId=" + ticketId +
                ", departureTime=" + departureTime +
                ", price=" + price +
                ", stock=" + stock +
                ", available=" + available +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
