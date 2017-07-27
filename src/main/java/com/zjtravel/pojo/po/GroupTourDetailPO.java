package com.zjtravel.pojo.po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 跟团游具体信息
 * Created by hunger on 2017/2/28.
 */
public class GroupTourDetailPO {
    private Long id; //编号
    private Long groupTourId; //跟团游id
    private Date departureTime;//出发时间
    private Date endTime;//结束时间
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

    public Long getGroupTourId() {
        return groupTourId;
    }

    public void setGroupTourId(Long groupTourId) {
        this.groupTourId = groupTourId;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * String转日期
     * @param departureTime
     * @throws ParseException
     */
    public void setDepartureTime(String departureTime) throws ParseException {
        SimpleDateFormat sdf  =   new  SimpleDateFormat( "yyyy-MM-dd" );
        this.departureTime =  sdf.parse(departureTime);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * String转日期
     * @param endTime
     * @throws ParseException
     */
    public void setEndTime(String endTime) throws ParseException {
        SimpleDateFormat sdf  =   new  SimpleDateFormat( " yyyy-MM-dd" );
        this.endTime =  sdf.parse(endTime);
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

        GroupTourDetailPO groupTourDetailPO = (GroupTourDetailPO) o;

        if (id != null ? !id.equals(groupTourDetailPO.id) : groupTourDetailPO.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "groupTourDetailPO{" +
                "id=" + id +
                ", groupTourId=" + groupTourId +
                ", departureTime=" + departureTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", stock=" + stock +
                ", available=" + available +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}

