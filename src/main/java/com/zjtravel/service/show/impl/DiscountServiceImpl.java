package com.zjtravel.service.show.impl;

import com.zjtravel.dao.DiscountDAO;
import com.zjtravel.dao.GroupTourDAO;
import com.zjtravel.dao.TicketDAO;
import com.zjtravel.pojo.po.DiscountPO;
import com.zjtravel.pojo.po.GroupTourPO;
import com.zjtravel.pojo.po.TicketPO;
import com.zjtravel.service.show.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hunger on 2017/3/5.
 */
@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountDAO discountDAO;
    @Autowired
    private GroupTourDAO groupTourDAO;
    @Autowired
    private TicketDAO ticketDAO;

    @Override
    public Integer createDiscount(DiscountPO discountPO) {
        return discountDAO.createDiscount(discountPO);
    }

    @Override
    public Integer updateDiscount(DiscountPO discountPO) {
        if (!discountPO.getAvailable()) {
            removeProductDiscount(discountPO.getId());

        }
        return discountDAO.updateDiscount(discountPO);
    }


    @Override
    public void deleteDiscount(Long discountId) {
        removeProductDiscount(discountId);
        discountDAO.deleteDiscount(discountId);
    }

    @Override
    public DiscountPO findOne(Long discountId) {
        return discountDAO.findOne(discountId);
    }

    @Override
    public List<DiscountPO> findAll() {
        return discountDAO.findAll();
    }

    @Override
    public List<DiscountPO> findDiscounts(Long[] discountIds) {
        List<DiscountPO> discounts = new ArrayList<>();
        for(Long discountId : discountIds) {
            DiscountPO discountPO = findOne(discountId);
            if(discountPO != null) {
                discounts.add(discountPO);
            }
        }
        return discounts;
    }

    private void removeProductDiscount(Long discountId) {
        //删除跟团游优惠信息
        List<GroupTourPO> groupTourPOList = groupTourDAO.findAll();
        for (GroupTourPO groupTourPO : groupTourPOList) {
            for (Long dId : groupTourPO.getDiscountIds()) {
                if (discountId.equals(dId)){
                    groupTourPO.getDiscountIds().remove(dId);
                    groupTourDAO.updateGroupTour(groupTourPO);
                    break;
                }
            }
        }
        //删除票务优惠信息
        List<TicketPO> ticketPOList = ticketDAO.findAll();
        for (TicketPO ticketPO : ticketPOList) {
            for (Long dId : ticketPO.getDiscountIds()) {
                if (discountId.equals(dId)){
                    ticketPO.getDiscountIds().remove(dId);
                    ticketDAO.updateTicket(ticketPO);
                    break;
                }
            }
        }
    }
}
