package com.zjtravel.web.controller.management;

import com.zjtravel.pojo.po.GroupTourDetailPO;
import com.zjtravel.pojo.po.OrderPO;
import com.zjtravel.pojo.vo.OrderDetailVO;
import com.zjtravel.service.management.UserService;
import com.zjtravel.service.show.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hunger on 2017/3/19.
 */
@Controller
@RequestMapping(value = "/backstage/Order")
public class OrderController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private OrderService orderService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupTourService groupTourService;
    @Autowired
    private GroupTourDetailService groupTourDetailService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketDetailService ticketDetailService;

    /**
     * 查看订单
     * @param model
     * @return
     */
    @RequiresPermissions("order:view")
    @RequestMapping(value = "/index/{state}", method = RequestMethod.GET)
    public String index(Model model, @PathVariable("state") String state) {
        List<OrderPO> orderPOList = new ArrayList<>();
        for (OrderPO order :  orderService.findAll()) {
            if (order.getState().toString().equals(state)) {
                order.setUsername(userService.findOne(order.getUserId()).getUsername());
                switch (order.getGoodsType()) {
                    case 1:
                        long groupTourId = groupTourDetailService.findOne(order.getGoodsId()).getGroupTourId();
                        order.setGoodsName(groupTourService.findOne(groupTourId).getTitle());// TODO: 2017/3/22  已修改
                        orderPOList.add(order);break;
                    case 2:
                        long ticketId = ticketDetailService.findOne(order.getGoodsId()).getTicketId();
                        order.setGoodsName(ticketService.findOne(ticketId).getTitle());// TODO: 2017/3/22  已修改
                        orderPOList.add(order);break;
                    default:
                        break;
                }
            }
        }
        model.addAttribute("orders",orderPOList);
        return "backstage/Order/index";
    }



    /**
     * 同意退单
     * @param id
     * @return
     */
    @RequiresPermissions("order:update")
    @RequestMapping(value = "/{id}/approve", method = RequestMethod.GET)
    public String approveChargeBack(@PathVariable("id") Long id) {
        OrderPO orderPO = orderService.findOne(id);
        if (OrderPO.OrderState.charge_back.equals(orderPO.getState())) {
                orderPO.setState(OrderPO.OrderState.charge_back_success);
                orderService.updateOrder(orderPO);
                walletService.increaseMoney(orderPO.getUserId(),orderPO.getPayment());
        }
        return "redirect:/backstage/Order/index/charge_back";
    }


//    /**
//     * 删除订单
//     * @param id
//     * @param redirectAttributes
//     * @return
//     */
//    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
//    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
//        orderService.deleteOrder(id);
//        redirectAttributes.addFlashAttribute("msg", "删除成功");
//        return "redirect:/backstage/Order/index";
//    }
}
