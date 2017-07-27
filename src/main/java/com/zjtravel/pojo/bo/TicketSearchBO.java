package com.zjtravel.pojo.bo;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hunger on 2017/3/29.
 */
public class TicketSearchBO {

    private String title;//标题
    private List<String> label;//标签
    private String location;//位置
    private Date departureTime;//出发时间
    private Date endTime;//结束时间
    private Double price;//价格
    private Boolean available = Boolean.TRUE;//是否上架

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
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
    public String toString() {
        return "TicketSearchBO{" +
                "title='" + title + '\'' +
                ", label=" + label +
                ", location='" + location + '\'' +
                ", departureTime=" + departureTime +
                ", endTime=" + endTime +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
