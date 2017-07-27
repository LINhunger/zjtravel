package com.zjtravel.service.show.impl;

import com.zjtravel.dao.DiscountDAO;
import com.zjtravel.dao.TicketDAO;
import com.zjtravel.dao.TicketDetailDAO;
import com.zjtravel.pojo.bo.TicketSearchBO;
import com.zjtravel.pojo.po.TicketDetailPO;
import com.zjtravel.pojo.po.TicketPO;
import com.zjtravel.pojo.vo.TicketVO;
import com.zjtravel.service.show.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hunger on 2017/3/29.
 */
@Service
public class TicketServiceImpl  implements TicketService{
    
    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private TicketDetailDAO ticketDetailDAO;
    @Autowired
    private DiscountDAO discountDAO;
    
    @Override
    public Integer createTicket(TicketPO ticketPO) {
        return ticketDAO.createTicket(ticketPO);
    }

    @Override
    public Integer updateTicket(TicketPO ticketPO) {
        return ticketDAO.updateTicket(ticketPO);
    }

    @Override
    public void deleteTicket(Long ticketId) {
        ticketDAO.deleteTicket(ticketId);
    }

    @Override
    public TicketPO findOne(Long ticketId) {
        return ticketDAO.findOne(ticketId);
    }

    @Override
    public List<TicketPO> findAll() {
        return ticketDAO.findAll();
    }

    @Override
    public List<TicketVO> findAllByMultipleCondition( TicketSearchBO condition) {
        List<TicketVO> ticketVOList = new ArrayList<>();
        List<TicketPO> ticketPOList = ticketDAO.findAllByMultipleCondition(condition);
        if (ticketPOList == null) {
            return null;
        }
        for (TicketPO ticketPO : ticketPOList) {
            TicketVO ticketVO = new TicketVO();
            List<TicketDetailPO> detailList = ticketDetailDAO.findAllByTicketId(ticketPO.getId());
            ticketVO.setTicketPO(ticketPO);
            ticketVO.setDetailList(detailList);
            if (!ticketPO.getDiscountIds().isEmpty()) {
                ticketVO.setDiscountPO(discountDAO.findOne(ticketPO.getDiscountIds().get(0)));
            }
            ticketVOList.add(ticketVO);
        }
        return ticketVOList;
    }
}
