package com.zjtravel.pojo.po;

import java.util.Date;

/**
 * Created by hunger on 2017/2/28.
 */
public class DiscountPO {
    private Long id; //编号
    private String title; //标题
    private Double percent;//折扣百分比
    private Boolean available = Boolean.TRUE;//是否上架
    private Date gmtCreate;//创建时间
    private Date gmtModified;//最后修改时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
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

        DiscountPO discountPO = (DiscountPO) o;

        if (id != null ? !id.equals(discountPO.id) : discountPO.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "DiscountPO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", percent=" + percent +
                ", available=" + available +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
