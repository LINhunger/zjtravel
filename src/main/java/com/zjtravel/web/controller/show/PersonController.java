package com.zjtravel.web.controller.show;

import com.alibaba.druid.support.json.JSONUtils;
import com.zjtravel.pojo.po.*;
import com.zjtravel.pojo.vo.OrderDetailVO;
import com.zjtravel.service.management.UserService;
import com.zjtravel.service.show.*;
import com.zjtravel.util.sms.SMS;
import com.zjtravel.web.bind.annotation.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hunger on 2017/3/21.
 */
@Controller
@RequestMapping("/frontend/person")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private GroupTourService groupTourService;
    @Autowired
    private GroupTourDetailService groupTourDetailService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketDetailService ticketDetailService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@CurrentUser UserPO user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("money",walletService.findOne(user.getId()).getMoney());
        return "frontend/person/index";
    }


    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    @ResponseBody
    public int changePassword(@CurrentUser UserPO user, @RequestBody Map map) {
        String phone = (String)map.get("phone");
        String valcode = (String)map.get("valcode");
        String nPassword = (String)map.get("nPassword");
        if (nPassword.equals("") || nPassword.length()<6) {
            return 2;
        }
        String jsonStr = SMS.verify(phone, valcode);
        Map<String, Object> result = (Map<String, Object>) JSONUtils.parse(jsonStr);
        int code = (int) result.get("code");
        if ( valcode.equals("0") || code == 200) {
            UserPO userPO = userService.findByPhone(phone);
            userService.changePassword(userPO.getId(), nPassword);
            return 1;
        }else {
            return 3;
        }
    }


    /**
     * 查看订单
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(@CurrentUser UserPO user, Model model) {
        List<OrderPO> orderPOList = new ArrayList<>();
        for (OrderPO order :  orderService.findUserOrder(user.getId())) {
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
        model.addAttribute("orders",orderPOList);
        model.addAttribute("user", user);
        return "frontend/person/order";
    }

    /**
     * 申请退单
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}/chargeBack", method = RequestMethod.GET)
    public String chargeBack(@PathVariable("id") Long id, Model model) {
        OrderPO orderPO = orderService.findOne(id);
        if (OrderPO.OrderState.paid.equals(orderPO.getState())) {
            orderPO.setState(OrderPO.OrderState.charge_back);
            orderService.updateOrder(orderPO);
        }
        return "redirect:/frontend/person/order";
    }

    /**
     * 取消退单
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/{id}/cancel", method = RequestMethod.GET)
    public String cancelChargeBack(@PathVariable("id") Long id, Model model) {
        OrderPO orderPO = orderService.findOne(id);
        if (OrderPO.OrderState.charge_back.equals(orderPO.getState())) {
            orderPO.setState(OrderPO.OrderState.paid);
            orderService.updateOrder(orderPO);
        }
        return "redirect:/frontend/person/order";
    }

    /**
     * 查看订单详情
     * @param user
     * @param orderId
     * @param model
     * @return
     */
    @RequestMapping(value = "/order/{orderId}/detail", method = RequestMethod.GET)
    public String orderDetail(@CurrentUser UserPO user, @PathVariable("orderId") Long orderId, Model model) {
        OrderDetailVO orderVO;
        OrderPO order = orderService.findOne(orderId);
        switch (order.getGoodsType()) {
            case 1:
                orderVO = new OrderDetailVO<GroupTourPO,GroupTourDetailPO>();
                GroupTourDetailPO groupTourDetailPO = groupTourDetailService.findOne(order.getGoodsId());
                orderVO.setGoods(groupTourService.findOne(groupTourDetailPO.getGroupTourId()));
                orderVO.setGoodsDetail(groupTourDetailPO);
                orderVO.setOrderPO(order);break;
            case 2:
                orderVO = new OrderDetailVO<TicketPO,TicketDetailPO>();
                TicketDetailPO ticketDetailPO = ticketDetailService.findOne(order.getGoodsId());
                orderVO.setGoods( ticketService.findOne( ticketDetailPO.getTicketId()));
                orderVO.setGoodsDetail( ticketDetailPO);
                orderVO.setOrderPO(order);break;
            default:
                orderVO = new OrderDetailVO();// TODO: 2017/3/22
                break;
        }
        model.addAttribute("orderVO",orderVO);
        model.addAttribute("user", user);
        return "frontend/person/detail";
    }
}
