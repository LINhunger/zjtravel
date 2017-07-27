package com.zjtravel.dao;

import com.zjtravel.pojo.po.TicketDetailPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by hunger on 2017/3/29.
 */
@Repository
public interface TicketDetailDAO {
    
    Integer createTicketDetail(@Param("ticketDetailPO")TicketDetailPO ticketDetailPO);

    Integer updateTicketDetail(@Param("ticketDetailPO")TicketDetailPO ticketDetailPO);

    void deleteTicketDetail(Long ticketDetailId);

    void deleteDetailsByTicketId(Long ticketId);

    TicketDetailPO findOne(Long ticketDetailId);

    List<TicketDetailPO> findAllByTicketId(Long ticketId);

    List<TicketDetailPO> findAll();
}
