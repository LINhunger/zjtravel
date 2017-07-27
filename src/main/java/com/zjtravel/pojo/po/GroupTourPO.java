package com.zjtravel.pojo.po;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hunger on 2017/2/28.
 */
public class GroupTourPO {
    private Long id; //编号
    private String title; //标题
    private List<String> label; //标签
    private List<String> picture; //图片
    private String location;//位置
    private String introduce;//介绍
    private String travel;//行程安排
    private List<Long> discountIds; //拥有的优惠列表
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

    public List<String> getLabel() {
        if(label == null) {
            label = new ArrayList<String>();
        }
        return label;
    }

    public void setLabel(List<String> label) {
        this.label = label;
    }

    public List<String> getPicture() {
        if(picture == null) {
            picture = new ArrayList<String>();
        }
        return picture;
    }

    public void setPicture(List<String> picture) {
        this.picture = picture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getTravel() {
        return travel;
    }

    public void setTravel(String travel) {
        this.travel = travel;
    }

    public List<Long> getDiscountIds() {
        if(discountIds == null) {
            discountIds = new ArrayList<Long>();
        }
        return discountIds;
    }

    public void setDiscountIds(List<Long> discountIds) {
        this.discountIds = discountIds;
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

    /**
     * 字符串格式添加图片
     * @param picturesStr
     */
    public void setPicturesStr(String picturesStr) {
        if(StringUtils.isEmpty(picturesStr)) {
            return;
        }
        String[] pictureStrs = picturesStr.split(",");
        for(String picture : pictureStrs) {
            if(StringUtils.isEmpty(picture)) {
                continue;
            }
            getPicture().add(picture);
        }
    }

    /**
     * 字符串格式添加标签
     * @param labelsStr
     */
    public void setLabelsStr(String labelsStr) {
        if(StringUtils.isEmpty(labelsStr)) {
            return;
        }
        String[] labelStrs = labelsStr.split(",");
        for(String label : labelStrs) {
            if(StringUtils.isEmpty(label)) {
                continue;
            }
            getLabel().add(label);
        }
    }

    /**
     * 字符串格式添加优惠
     * @param discountsStr
     */
    public void setDiscountIdsStr(String discountsStr) {
        if(StringUtils.isEmpty(discountsStr)) {
            return;
        }
        String[] discountStrs = discountsStr.split(",");
        for(String discountStr : discountStrs) {
            if(StringUtils.isEmpty(discountStr)) {
                continue;
            }
            getDiscountIds().add(Long.valueOf(discountStr));
        }
    }

    /**
     * 优惠id的拼接字符串
     * @return
     */
    public String getDiscountIdsStr() {
        if(CollectionUtils.isEmpty(discountIds)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(Long discountId : discountIds) {
            s.append(discountId);
            s.append(",");
        }
        return s.toString();
    }

    /**
     * 图片的拼接字符串
     * @return
     */
    public String getPicturesStr() {
        if(CollectionUtils.isEmpty(picture)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(String pic : picture) {
            s.append(pic);
            s.append(",");
        }
        return s.toString();
    }

    /**
     * 标签的拼接字符串
     * @return
     */
    public String getLabelsStr() {
        if(CollectionUtils.isEmpty(label)) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        for(String lab : label) {
            s.append(lab);
            s.append(",");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupTourPO groupTourPO = (GroupTourPO) o;

        if (id != null ? !id.equals(groupTourPO.id) : groupTourPO.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    @Override
    public String toString() {
        return "GroupTourPO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", label=" + label +
                ", picture=" + picture +
                ", location='" + location + '\'' +
                ", introduce='" + introduce + '\'' +
                ", travel='" + travel + '\'' +
                ", discountIds=" + discountIds +
                ", available=" + available +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
}
