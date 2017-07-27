package com.zjtravel.service.show;

import com.zjtravel.pojo.bo.TicketSearchBO;
import com.zjtravel.pojo.po.TicketPO;
import com.zjtravel.pojo.vo.TicketVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hunger on 2017/3/29.
 */
public interface TicketService {

    /**
     * 添加门票对象
     * @param ticketPO
     * @return
     */
    Integer createTicket(TicketPO ticketPO);

    /**
     * 更新门票对象
     * @param ticketPO
     * @return
     */
    Integer updateTicket(TicketPO ticketPO);

    /**
     * 删除门票对象
     * @param ticketId
     */
    void deleteTicket(Long ticketId);

    /**
     * 查找单个门票对象
     * @param ticketId
     * @return
     */
    TicketPO findOne(Long ticketId);

    /**
     * 查找全部门票对象
     * @return
     */
    List<TicketPO> findAll();


    /**
     * 按条件查找门票对象
     * @param condition
     * @return
     */
    List<TicketVO> findAllByMultipleCondition(TicketSearchBO condition);
}
