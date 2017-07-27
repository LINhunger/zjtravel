package com.zjtravel.pojo.vo;

import com.zjtravel.pojo.po.DiscountPO;
import com.zjtravel.pojo.po.TicketDetailPO;
import com.zjtravel.pojo.po.TicketPO;

import java.util.List;

/**
 * Created by hunger on 2017/3/29.
 */
public class TicketVO {

    private TicketPO ticketPO;
    private List<TicketDetailPO> detailList;
    private DiscountPO discountPO;

    public TicketVO() {}

    public TicketVO(TicketPO ticketPO, List<TicketDetailPO> detailList) {
        this.detailList = detailList;
        this.ticketPO = ticketPO;
    }

    public TicketVO(TicketPO ticketPO, List<TicketDetailPO> detailList, DiscountPO discountPO) {
        this.detailList = detailList;
        this.ticketPO = ticketPO;
        this.discountPO = discountPO;
    }

    public TicketPO getTicketPO() {
        return ticketPO;
    }

    public void setTicketPO(TicketPO ticketPO) {
        this.ticketPO = ticketPO;
    }

    public List<TicketDetailPO> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<TicketDetailPO> detailList) {
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

        TicketVO that = (TicketVO) o;

        return ticketPO != null ? ticketPO.equals(that.ticketPO) : that.ticketPO == null;

    }

    @Override
    public int hashCode() {
        return ticketPO != null ? ticketPO.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TicketVO{" +
                "ticketPO=" + ticketPO +
                ", detailList=" + detailList +
                ", discountPO=" + discountPO +
                '}';
    }
}
