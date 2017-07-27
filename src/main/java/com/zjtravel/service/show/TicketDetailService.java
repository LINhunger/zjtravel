package com.zjtravel.service.show;

import com.zjtravel.pojo.po.TicketDetailPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hunger on 2017/3/29.
 */
public interface TicketDetailService {


    /**
     * 创建门票信息对象
     * @param ticketDetailPO
     * @return
     */
    Integer createTicketDetail(TicketDetailPO ticketDetailPO);

    /**
     * 更新门票信息对象
     * @param ticketDetailPO
     * @return
     */
    Integer updateTicketDetail(TicketDetailPO ticketDetailPO);

    /**
     * 删除门票信息对象
     * @param ticketDetailId
     */
    void deleteTicketDetail(Long ticketDetailId);

    /**
     * 根据门票id删除门票信息
     * @param ticketId
     */
    void deleteDetailsByTicketId(Long ticketId);

    /**
     * 根据id查找门票信息
     * @param ticketDetailId
     * @return
     */
    TicketDetailPO findOne(Long ticketDetailId);

    /**
     * 根据门票id查找门票信息
     * @param ticketId
     * @return
     */
    List<TicketDetailPO> findAllByTicketId(Long ticketId);

    /**
     * 查找全部门票信息
     * @return
     */
    List<TicketDetailPO> findAll();
}
