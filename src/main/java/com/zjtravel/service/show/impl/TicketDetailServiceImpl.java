package com.zjtravel.service.show.impl;

import com.zjtravel.dao.TicketDetailDAO;
import com.zjtravel.pojo.po.TicketDetailPO;
import com.zjtravel.service.show.TicketDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hunger on 2017/3/29.
 */
@Service
public class TicketDetailServiceImpl implements TicketDetailService{

    @Autowired
    private TicketDetailDAO ticketDetailDAO;

    @Override
    public Integer createTicketDetail(TicketDetailPO ticketDetailPO) {
        return ticketDetailDAO.createTicketDetail(ticketDetailPO);
    }

    @Override
    public Integer updateTicketDetail(TicketDetailPO ticketDetailPO) {
        return ticketDetailDAO.updateTicketDetail(ticketDetailPO);
    }

    @Override
    public void deleteTicketDetail(Long ticketDetailId) {
        ticketDetailDAO.deleteTicketDetail(ticketDetailId);
    }

    @Override
    public void deleteDetailsByTicketId(Long ticketId) {
        ticketDetailDAO.deleteDetailsByTicketId(ticketId);
    }

    @Override
    public TicketDetailPO findOne(Long ticketDetailId) {
        return ticketDetailDAO.findOne(ticketDetailId);
    }

    @Override
    public List<TicketDetailPO> findAllByTicketId(Long ticketId) {
        return ticketDetailDAO.findAllByTicketId(ticketId);
    }

    @Override
    public List<TicketDetailPO> findAll() {
        return ticketDetailDAO.findAll();
    }
}
