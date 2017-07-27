package com.zjtravel.dao;

import com.zjtravel.pojo.bo.TicketSearchBO;
import com.zjtravel.pojo.po.TicketPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hunger on 2017/3/29.
 */

@Repository
public interface TicketDAO {
    
    
    Integer createTicket(@Param("ticketPO")TicketPO ticketPO);

    Integer updateTicket(@Param("ticketPO")TicketPO ticketPO);

    void deleteTicket(Long ticketId);

    TicketPO findOne(Long ticketId);

    List<TicketPO> findAll();

    List<TicketPO> findAllByMultipleCondition(@Param("condition")TicketSearchBO condition);
}
