package com.zjtravel.web.controller.show;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;
import com.zjtravel.pojo.bo.GroupTourSearchBO;
import com.zjtravel.pojo.bo.TicketSearchBO;
import com.zjtravel.pojo.dto.RequestResult;
import com.zjtravel.pojo.po.*;
import com.zjtravel.pojo.vo.GroupTourVO;
import com.zjtravel.pojo.vo.TicketVO;
import com.zjtravel.service.management.ResourceService;
import com.zjtravel.service.management.UserService;
import com.zjtravel.service.show.*;
import com.zjtravel.util.TravelFileUtil;
import com.zjtravel.util.sms.SMS;
import com.zjtravel.web.bind.annotation.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by hunger on 2017/2/24.
 */
@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private GroupTourService groupTourService;
    @Autowired
    private GroupTourDetailService groupTourDetailService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketDetailService ticketDetailService;
    @Autowired
    private  WalletService walletService;
    @Autowired
    private OrderService orderService;

    private static final String PRINCIPALS_SESSION_KEY = "org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY";


    /**
     * 主页
     * @param loginUser
     * @param model
     * @return
     */
    @RequestMapping("/")
    public String index(@CurrentUser UserPO loginUser, Model model, HttpServletRequest request ) {

        //跟团游
        List<GroupTourPO> groupTourPOList = groupTourService.findAll();
        List<GroupTourVO> groupTourVOList = new ArrayList<>(6);
        for (GroupTourPO groupTourPO : groupTourPOList) {
            if (groupTourPO.getAvailable().equals(true) ) {
                List<GroupTourDetailPO> detailList = groupTourDetailService.findAllByGroupTourId(groupTourPO.getId());
                if (detailList != null && !detailList.isEmpty()) {
                    groupTourVOList.add(new GroupTourVO(groupTourPO, detailList));
                }
            }
        }
        //票务
        List<TicketPO> ticketPOList = ticketService.findAll();
        List<TicketVO> ticketVOList = new ArrayList<>(6);
        for (TicketPO ticketPO : ticketPOList) {
            if (ticketPO.getAvailable().equals(true)) {
                List<TicketDetailPO> detailList = ticketDetailService.findAllByTicketId(ticketPO.getId());
                if (detailList != null && !detailList.isEmpty()) {
                    ticketVOList.add(new TicketVO(ticketPO, detailList));
                }
            }
        }
        setDiscounts(model);
        setCommonData(model, request);
        if (groupTourVOList.size() >6) {
            groupTourVOList = groupTourVOList.subList(0,6);
        }
        if (ticketVOList.size() >6) {
            ticketVOList = ticketVOList.subList(0,6);
        }
        model.addAttribute("groupTourVOList",groupTourVOList);
        model.addAttribute("ticketVOList",ticketVOList);
        model.addAttribute("username", request.getSession().getAttribute(PRINCIPALS_SESSION_KEY));
        return "frontend/index";
    }

    @RequestMapping(value = "/forget", method = RequestMethod.GET)
    public String showChangPasswordForm() {
        return "frontend/forgetPassword";
    }

    /**
     * 获取注册页面
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm() {
        return "frontend/register";
    }

    /**
     * 注册
     * @param map
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public int register(@RequestBody Map map) {
        UserPO userPO = new UserPO();
        userPO.setUsername((String) map.get("username"));
        userPO.setPassword((String) map.get("password"));
        userPO.setPhone((String) map.get("phone"));
        userPO.setRoleIdsStr("0");
        String valcode = (String) map.get("valcode");
        if (!userPO.getUsername().matches("[\u4e00-\u9fa5_a-zA-Z0-9_]{4,20}") || !userPO.getPassword().matches("[a-zA-Z0-9_]{6,16}")) {
            return 2;
        }
        int code = 0;
        if (!valcode.equals("0")) {
            String resultStr = SMS.verify(userPO.getPhone(), valcode);
            Map<String, Object> result = (Map<String, Object>) JSONUtils.parse(resultStr);
             code = (int) result.get("code");
        }
        if (valcode.equals("0") || code == 200){
            try {
                userService.createUser(userPO);
                return 1;
            }catch (DuplicateKeyException e) {
                return 4;
            }
        }else {
            return 3;
        }
    }

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    @RequestMapping(value = "/valcode", method = RequestMethod.POST)
    @ResponseBody
    public int getValcode(String phone) {
        if (phone.equals("") || phone.length()<6) {
            return 2;
        }
        String jsonStr = SMS.sendCode(phone);
        Map<String, Object> result = (Map<String, Object>) JSONUtils.parse(jsonStr);
        int code = (int) result.get("code");
        return code;
    }






    /**
     * 跟团游列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/groupTour", method = RequestMethod.GET)
    public String groupTourPage(Model model, HttpServletRequest request) {
        List<GroupTourPO> groupTourPOList = groupTourService.findAll();
        List<GroupTourVO> groupTourVOList = new ArrayList<>(groupTourPOList.size());
        for (GroupTourPO groupTourPO : groupTourPOList) {
            if (groupTourPO.getAvailable().equals(true)) {
                groupTourVOList.add(new GroupTourVO(groupTourPO, groupTourDetailService.findAllByGroupTourId(groupTourPO.getId())));
            }
        }
        setDiscounts(model);
        model.addAttribute("groupTourVOList",groupTourVOList);
        model.addAttribute("username", request.getSession().getAttribute(PRINCIPALS_SESSION_KEY));
        return "frontend/groupTour";
    }

    /**
     * 跟团游条件搜索
     * @param map
     * @return
     */
    @RequestMapping(value = "/groupTour", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<List> groupTourSearch(@RequestBody Map map) {
        String title = (String)map.get("title");
        String location = ((String)map.get("location")).equals("全部") ? "" : (String)map.get("location");
        String minPrice = (String)map.get("minPrice");
        String maxPrice = (String)map.get("maxPrice");
        Double min = 0.0;
        Double max = Double.MAX_VALUE;
        Date departureTime = new Date(0);
        Date endTime = new Date(System.currentTimeMillis()+86400000*7);
        if ("最近一周".equals((String)map.get("time"))) {
            departureTime = new Date(System.currentTimeMillis()-86400000);
            endTime = new Date(System.currentTimeMillis()+86400000*7);
        }

        if (minPrice != null && maxPrice != null && !"".equals(minPrice) && !"".equals(maxPrice)
                && minPrice.matches("[0-9]*(\\.?)[0-9]*") && maxPrice.matches("[0-9]*(\\.?)[0-9]*")) {
            min = Double.valueOf(minPrice);
            max = Double.valueOf(maxPrice);
        }


        GroupTourSearchBO groupTourSearchBO = new GroupTourSearchBO();
        groupTourSearchBO.setLocation(location);
        groupTourSearchBO.setTitle(title);
        List<GroupTourVO> groupTourVOList = groupTourService.findAllByMultipleCondition(groupTourSearchBO);


        List<GroupTourVO> groupTourVOs = new ArrayList<>();
        for (GroupTourVO groupTourVO : groupTourVOList) {
            DiscountPO discountPO = groupTourVO.getDiscountPO();
            if (discountPO != null) {
                if (groupTourVO.getDetailList()!= null && groupTourVO.getDetailList().isEmpty() == false && groupTourVO.getGroupTourPO().getAvailable()
                        && groupTourVO.getDetailList().get(0).getDepartureTime().after(departureTime)
                        && groupTourVO.getDetailList().get(0).getEndTime().before(endTime)
                        && groupTourVO.getDetailList().get(0).getPrice() * discountPO.getPercent() >= min
                        && groupTourVO.getDetailList().get(0).getPrice() * discountPO.getPercent() <= max) {
                    groupTourVOs.add(groupTourVO);
                }
            }else {
                if (groupTourVO.getDetailList()!= null && groupTourVO.getDetailList().isEmpty() == false && groupTourVO.getGroupTourPO().getAvailable()
                        && groupTourVO.getDetailList().get(0).getDepartureTime().after(departureTime)
                        && groupTourVO.getDetailList().get(0).getEndTime().before(endTime)
                        && groupTourVO.getDetailList().get(0).getPrice()  >= min
                        && groupTourVO.getDetailList().get(0).getPrice()  <= max) {
                    groupTourVOs.add(groupTourVO);
                }
            }
        }
        return new RequestResult<List>(1,"搜索跟团游成功",groupTourVOs);
    }

    /**
     * 跟团游详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/groupTour/{id}/detail")
    public String groupTourDetail(@PathVariable("id") Long id,  Model model, HttpServletRequest request) {
        GroupTourVO groupTourVO = new GroupTourVO();
        GroupTourPO groupTourPO = groupTourService.findOne(id);
        List<GroupTourDetailPO> list = groupTourDetailService.findAllByGroupTourId(id);// TODO: 2017/4/15
        List<GroupTourDetailPO> detailList = new ArrayList<>();
        for (GroupTourDetailPO groupTourDetailPO : list) {
            if (groupTourDetailPO.getDepartureTime().after(new Date(System.currentTimeMillis()-86400000))) {
                detailList.add(groupTourDetailPO);
            }
        }
        groupTourVO.setGroupTourPO(groupTourPO);
        groupTourVO.setDetailList(detailList);
        if (!groupTourPO.getDiscountIds().isEmpty()) {
            DiscountPO discountPO = discountService.findOne(groupTourPO.getDiscountIds().get(0));
            groupTourVO.setDiscountPO(discountPO);
        }
        model.addAttribute("groupTourVO",groupTourVO);
        model.addAttribute("username", request.getSession().getAttribute(PRINCIPALS_SESSION_KEY));
        return "frontend/groupTourDetail";
    }

    /**
     * 异步获取价格
     * @param id
     * @return
     */
    @RequestMapping(value = "/getPrice", method = RequestMethod.GET)
    @ResponseBody
    public Map getPrice(@RequestParam("id") Long id) {
        GroupTourDetailPO groupTourDetailPO = groupTourDetailService.findOne(id);
        Double oPrice = groupTourDetailPO.getPrice();
        Double nPrice = oPrice;
        GroupTourPO groupTourPO = groupTourService.findOne(groupTourDetailPO.getGroupTourId());
        if (!groupTourPO.getDiscountIds().isEmpty()) {
            DiscountPO discountPO = discountService.findOne(groupTourPO.getDiscountIds().get(0));
            nPrice = oPrice * discountPO.getPercent();
        }
        Map result = new HashMap();
        result.put("oPrice",oPrice);
        result.put("nPrice",nPrice);
        return result;
    }

    /**
     * 提交跟团游订单
     * @param  map
     * @return
     */
    @RequestMapping(value = "/groupTour/order" ,method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public RequestResult<Map> submitGroupTourOrder(@CurrentUser UserPO loginUser, @RequestBody Map map) {
        Integer goodsType =  (Integer)(map.get("type"));
        Integer num = (Integer)(map.get("num"));
        Long groupDetailId = Long.valueOf(map.get("id")+"");
        Double payment ;
        OrderPO orderPO = new OrderPO();
        GroupTourDetailPO groupTourDetailPO = groupTourDetailService.findOne(groupDetailId);
        payment = groupTourDetailPO.getPrice() * num;
        GroupTourPO groupTourPO = groupTourService.findOne(groupTourDetailPO.getGroupTourId());
        if (!groupTourPO.getDiscountIds().isEmpty()) {
            DiscountPO discountPO = discountService.findOne(groupTourPO.getDiscountIds().get(0));
            payment = payment * discountPO.getPercent();
        }

        orderPO.setUserId(loginUser.getId());
        orderPO.setGoodsId(groupDetailId);
        orderPO.setGoodsType(goodsType);
        orderPO.setNumber(num);
        orderPO.setPayment(payment);
        orderPO.setState(OrderPO.OrderState.paid);


        if (groupTourDetailPO.getStock() < num) {
            return new RequestResult<Map>(3,"商品库存不足");
        }

        if (walletService.decreaseMoney(loginUser.getId(), payment) == 1) {
            orderService.createOrder(orderPO);
            groupTourDetailPO.setStock(groupTourDetailPO.getStock()-num);
            groupTourDetailService.updateGroupTourDetail(groupTourDetailPO);
            return new RequestResult<Map>(1,"订单已提交");
        } else {
            return new RequestResult<Map>(2,"余额不足");
        }
    }




/**
 * 票务
 */



    /**
     * 票务列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/ticket", method = RequestMethod.GET)
    public String ticketPage(Model model, HttpServletRequest request) {
        List<TicketPO> ticketPOList = ticketService.findAll();
        List<TicketVO> ticketVOList = new ArrayList<>(ticketPOList.size());
        for (TicketPO ticketPO : ticketPOList) {
            if (ticketPO.getAvailable().equals(true)) {
                ticketVOList.add(new TicketVO(ticketPO, ticketDetailService.findAllByTicketId(ticketPO.getId())));
            }
        }
        setDiscounts(model);
        model.addAttribute("ticketVOList",ticketVOList);
        model.addAttribute("username", request.getSession().getAttribute(PRINCIPALS_SESSION_KEY));
        return "frontend/ticket";
    }

    /**
     * 票务条件搜索
     * @param map
     * @return
     */
    @RequestMapping(value = "/ticket", method = RequestMethod.POST)
    @ResponseBody
    public RequestResult<List> ticketSearch(@RequestBody Map map) {
        String title = (String)map.get("title");
        String location = ((String)map.get("location")).equals("全部") ? "" : (String)map.get("location");
        String minPrice = (String)map.get("minPrice");
        String maxPrice = (String)map.get("maxPrice");
        Double min = 0.0;
        Double max = Double.MAX_VALUE;
        Date departureTime = new Date(0);
        Date endTime = new Date(System.currentTimeMillis()+86400000*7);
        if ("最近一周".equals((String)map.get("time"))) {
            departureTime = new Date(System.currentTimeMillis()-86400000);
            endTime = new Date(System.currentTimeMillis()+86400000*7);
        }

        if (minPrice != null && maxPrice != null && !"".equals(minPrice) && !"".equals(maxPrice)
                && minPrice.matches("[0-9]*(\\.?)[0-9]*") && maxPrice.matches("[0-9]*(\\.?)[0-9]*")) {
            min = Double.parseDouble(minPrice);
            max = Double.parseDouble(maxPrice);
        }


        TicketSearchBO ticketSearchBO = new TicketSearchBO();
        ticketSearchBO.setLocation(location);
        ticketSearchBO.setTitle(title);
        List<TicketVO> ticketVOList = ticketService.findAllByMultipleCondition(ticketSearchBO);


        List<TicketVO> ticketVOs = new ArrayList<>();
        for (TicketVO ticketVO : ticketVOList) {
            DiscountPO discountPO = ticketVO.getDiscountPO();
            if (discountPO != null) {
                if (ticketVO.getDetailList() != null && ticketVO.getDetailList().isEmpty() == false&& ticketVO.getTicketPO().getAvailable()
                        && ticketVO.getDetailList().get(0).getDepartureTime().after(departureTime)
                        && ticketVO.getDetailList().get(0).getDepartureTime().before(endTime)
                        && ticketVO.getDetailList().get(0).getPrice() * discountPO.getPercent() >= min
                        && ticketVO.getDetailList().get(0).getPrice() * discountPO.getPercent()<= max) {
                    ticketVOs.add(ticketVO);
                }
            }else {
                if (ticketVO.getDetailList() != null && ticketVO.getDetailList().isEmpty() == false&& ticketVO.getTicketPO().getAvailable()
                        && ticketVO.getDetailList().get(0).getDepartureTime().after(departureTime)
                        && ticketVO.getDetailList().get(0).getDepartureTime().before(endTime)
                        && ticketVO.getDetailList().get(0).getPrice() >= min
                        && ticketVO.getDetailList().get(0).getPrice() <= max) {
                    ticketVOs.add(ticketVO);
                }
            }
        }
        return new RequestResult<List>(1,"搜索票务成功",ticketVOs);
    }

    /**
     * 票务详情
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/ticket/{id}/detail")
    public String ticketDetail(@PathVariable("id") Long id,  Model model, HttpServletRequest request) {
        TicketVO ticketVO = new TicketVO();
        TicketPO ticketPO = ticketService.findOne(id);
        List<TicketDetailPO> list = ticketDetailService.findAllByTicketId(id);// TODO: 2017/4/15
        List<TicketDetailPO> detailList = new ArrayList<>();
        for (TicketDetailPO ticketDetailPO : list) {
            if (ticketDetailPO.getDepartureTime().after(new Date(System.currentTimeMillis()-86400000))) {
                detailList.add(ticketDetailPO);
            }
        }
        ticketVO.setTicketPO(ticketPO);
        ticketVO.setDetailList(detailList);
        if (!ticketPO.getDiscountIds().isEmpty()) {
            DiscountPO discountPO = discountService.findOne(ticketPO.getDiscountIds().get(0));
            ticketVO.setDiscountPO(discountPO);
        }
        model.addAttribute("ticketVO",ticketVO);
        model.addAttribute("username", request.getSession().getAttribute(PRINCIPALS_SESSION_KEY));
        return "frontend/ticketDetail";
    }

    /**
     * 异步获取价格
     * @param id
     * @return
     */
    @RequestMapping(value = "/getTicketPrice", method = RequestMethod.GET)
    @ResponseBody
    public Map getTicketPrice(@RequestParam("id") Long id) {
        TicketDetailPO ticketDetailPO = ticketDetailService.findOne(id);
        Double oPrice = ticketDetailPO.getPrice();
        Double nPrice = oPrice;
        TicketPO ticketPO = ticketService.findOne(ticketDetailPO.getTicketId());
        if (!ticketPO.getDiscountIds().isEmpty()) {
            DiscountPO discountPO = discountService.findOne(ticketPO.getDiscountIds().get(0));
            nPrice = oPrice * discountPO.getPercent();
        }
        Map result = new HashMap();
        result.put("oPrice",oPrice);
        result.put("nPrice",nPrice);
        return result;
    }



    /**
     * 提交票务订单
     * @param  map
     * @return
     */
    @RequestMapping(value = "/ticket/order" ,method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public RequestResult<Map> submitTicketOrder(@CurrentUser UserPO loginUser, @RequestBody Map map) {
        Integer goodsType =  (Integer)(map.get("type"));
        Integer num = (Integer)(map.get("num"));
        Long groupDetailId = Long.valueOf(map.get("id")+"");
        Double payment ;
        OrderPO orderPO = new OrderPO();
        TicketDetailPO ticketDetailPO = ticketDetailService.findOne(groupDetailId);
        payment = ticketDetailPO.getPrice() * num;
        TicketPO ticketPO = ticketService.findOne(ticketDetailPO.getTicketId());
        if (!ticketPO.getDiscountIds().isEmpty()) {
            DiscountPO discountPO = discountService.findOne(ticketPO.getDiscountIds().get(0));
            payment = payment * discountPO.getPercent();
        }

        orderPO.setUserId(loginUser.getId());
        orderPO.setGoodsId(groupDetailId);
        orderPO.setGoodsType(goodsType);
        orderPO.setNumber(num);
        orderPO.setPayment(payment);
        orderPO.setState(OrderPO.OrderState.paid);


        if (ticketDetailPO.getStock() < num) {
            return new RequestResult<Map>(3,"商品库存不足");
        }

        if (walletService.decreaseMoney(loginUser.getId(), payment) == 1) {
            orderService.createOrder(orderPO);
            ticketDetailPO.setStock(ticketDetailPO.getStock()-num);
            ticketDetailService.updateTicketDetail(ticketDetailPO);
            return new RequestResult<Map>(1,"订单已提交");
        } else {
            return new RequestResult<Map>(2,"余额不足");
        }
    }











    private void setDiscounts(Model model) {
        List<DiscountPO> discountPOList = discountService.findAll();
        List<DiscountPO> discountPOs = new ArrayList<>();
        for (DiscountPO discountPO : discountPOList) {
            if (discountPO.getAvailable()) {
                discountPOs.add(discountPO);
            }
        }
        model.addAttribute("discounts", discountPOs);
    }

    private void setCommonData(Model model, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/WEB-INF/jsp/backstage/Page/image/cut_image");
        List<String> pictures = TravelFileUtil.travelFile(path);
        if (pictures.size() > 3) {
            pictures = pictures.subList(0,3);
        }
        model.addAttribute("pictures", pictures);
    }
}

