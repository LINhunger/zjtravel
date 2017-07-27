package com.zjtravel.pojo.vo;

import com.zjtravel.pojo.po.DiscountPO;
import com.zjtravel.pojo.po.GroupTourDetailPO;
import com.zjtravel.pojo.po.GroupTourPO;

import java.util.List;

/**
 * Created by hunger on 2017/3/5.
 */
public class GroupTourVO {

    private GroupTourPO groupTourPO;
    private List<GroupTourDetailPO> detailList;
    private DiscountPO discountPO;

    public GroupTourVO() {}

    public GroupTourVO(GroupTourPO groupTourPO, List<GroupTourDetailPO> detailList) {
        this.detailList = detailList;
        this.groupTourPO = groupTourPO;
    }

    public GroupTourVO(GroupTourPO groupTourPO, List<GroupTourDetailPO> detailList, DiscountPO discountPO) {
        this.detailList = detailList;
        this.groupTourPO = groupTourPO;
        this.discountPO = discountPO;
    }

    public GroupTourPO getGroupTourPO() {
        return groupTourPO;
    }

    public void setGroupTourPO(GroupTourPO groupTourPO) {
        this.groupTourPO = groupTourPO;
    }

    public List<GroupTourDetailPO> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<GroupTourDetailPO> detailList) {
        this.detailList = detailList;
    }

    public DiscountPO getDiscountPO() {
        return discountPO;
    }

    public void setDiscountPO(DiscountPO discountPO) {
        this.discountPO = discountPO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupTourVO that = (GroupTourVO) o;

        return groupTourPO != null ? groupTourPO.equals(that.groupTourPO) : that.groupTourPO == null;

    }

    @Override
    public int hashCode() {
        return groupTourPO != null ? groupTourPO.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "GroupTourVO{" +
                "groupTourPO=" + groupTourPO +
                ", detailList=" + detailList +
                ", discountPO=" + discountPO +
                '}';
    }
}
