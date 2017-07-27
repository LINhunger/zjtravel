package com.zjtravel.web.controller.management;


import com.zjtravel.dao.GroupTourDetailDAO;
import com.zjtravel.pojo.po.*;
import com.zjtravel.pojo.vo.GroupTourVO;
import com.zjtravel.service.show.*;
import com.zjtravel.util.FormatUtil;
import com.zjtravel.util.ImageUtil;
import com.zjtravel.util.TravelFileUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by hunger on 2017/3/12.
 */
@Controller
@RequestMapping("/backstage/Product")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private GroupTourService groupTourService;
    @Autowired
    private GroupTourDetailService groupTourDetailService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private TicketDetailService ticketDetailService;
    @Autowired
    private DiscountService discountService;


    /*
    跟团游管理
     */

    /**
     * 跟团游管理页面
     * @param model
     * @return
     */
    @RequiresPermissions("product:view")
    @RequestMapping(value = "/groupTour/index", method = RequestMethod.GET)
    public String groupTourIndex(Model model) {
        List<GroupTourPO> groupTourPOList = groupTourService.findAll();
        model.addAttribute("groupTours",groupTourPOList);
        return "backstage/Product/groupTour/index";
    }


    /**
     * 跟团游子产品页面
     * @param model
     * @param groupTourId
     * @return
     */
    @RequiresPermissions("product:view")
    @RequestMapping(value = "/groupTour/{groupTourId}/detail", method = RequestMethod.GET)
    public String groupTourDetail(Model model, @PathVariable("groupTourId") Long groupTourId) {
        List<GroupTourDetailPO> groupTourDetailPOList = groupTourDetailService.findAllByGroupTourId(groupTourId);
        model.addAttribute("groupTourId", groupTourId);
        model.addAttribute("groupTourDetails",groupTourDetailPOList);
        return "backstage/Product/groupTour/detail/index";
    }

    /**
     * 添加跟团游子产品页面
     * @param model
     * @param groupTourId
     * @return
     */
    @RequiresPermissions("product:create")
    @RequestMapping(value = "/groupTour/{groupTourId}/detail/add", method = RequestMethod.GET)
    public String showGroupTourDetailCreateForm(Model model, @PathVariable("groupTourId") Long groupTourId) {
        model.addAttribute("groupTourId", groupTourId);
        return "backstage/Product/groupTour/detail/add";
    }


    /**
     * 添加跟团游子产品
     * @param groupTourDetailPO
     * @param groupTourId
     * @param redirectAttributes
     * @param departureTime
     * @param endTime
     * @return
     * @throws Exception
     */
    @RequiresPermissions("product:create")
    @RequestMapping(value = "/groupTour/{groupTourId}/detail/add", method = RequestMethod.POST)
    public String addGroupTourDetail(GroupTourDetailPO groupTourDetailPO, @PathVariable("groupTourId") Long groupTourId,
                                     RedirectAttributes redirectAttributes ,
                                     @RequestParam("dTime") String departureTime ,  @RequestParam("eTime") String endTime ) throws Exception{

        //格式转化
        groupTourDetailPO.setDepartureTime(FormatUtil.stringToDate(departureTime));
        groupTourDetailPO.setEndTime(FormatUtil.stringToDate(endTime));

        groupTourDetailPO.setGroupTourId(groupTourId);
        groupTourDetailService.createGroupTourDetail(groupTourDetailPO);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/backstage/Product/groupTour/"+groupTourId+"/detail";
    }

    /**
     * 删除跟团游子产品
     * @param groupTourDetailId
     * @param groupTourId
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:do not touch this method")
    @RequestMapping(value = "/groupTour/{groupTourId}/detail/{groupTourDetailId}/delete", method = RequestMethod.GET)
    public String deleteGroupTourDetail(@PathVariable("groupTourDetailId") Long groupTourDetailId,
                                        @PathVariable("groupTourId") Long groupTourId, RedirectAttributes redirectAttributes) {
        groupTourDetailService.deleteGroupTourDetail(groupTourDetailId);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/backstage/Product/groupTour/"+groupTourId+"/detail";
    }

    /**
     * 更新跟团游子产品
     * @param groupTourDetailId
     * @param groupTourId
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:update")
    @RequestMapping(value = "/groupTour/{groupTourId}/detail/{groupTourDetailId}/update", method = RequestMethod.GET)
    public String updateGroupTourDetail(@PathVariable("groupTourDetailId") Long groupTourDetailId,
                                        @PathVariable("groupTourId") Long groupTourId, RedirectAttributes redirectAttributes) {
        GroupTourDetailPO groupTourDetailPO = groupTourDetailService.findOne(groupTourDetailId);
        if (groupTourDetailPO.getAvailable()) {
            groupTourDetailPO.setAvailable(false);
        }else {
            groupTourDetailPO.setAvailable(true);
        }
        groupTourDetailService.updateGroupTourDetail(groupTourDetailPO);
        redirectAttributes.addFlashAttribute("msg", "更新成功");
        return "redirect:/backstage/Product/groupTour/"+groupTourId+"/detail";
    }


    /**
     * 添加跟团游页面
     * @param model
     * @param request
     * @return
     */
    @RequiresPermissions("product:create")
    @RequestMapping(value = "/groupTour/add", method = RequestMethod.GET)
    public String showGroupTourCreateForm(Model model,HttpServletRequest request) {
       setCommonData(model,request);
        setDiscounts(model);
        return "backstage/Product/groupTour/add";
    }

    /**
     * 添加跟团游
     * @param groupTourPO
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:create")
    @RequestMapping(value = "/groupTour/add", method = RequestMethod.POST)
    public String groupTourCreate(GroupTourPO groupTourPO, RedirectAttributes redirectAttributes) {
        groupTourService.createGroupTour(groupTourPO);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/backstage/Product/groupTour/index";
    }

    /**
     * 跟新跟团游页面
     * @param model
     * @param groupTourId
     * @param request
     * @return
     */
    @RequiresPermissions("product:update")
    @RequestMapping(value = "/groupTour/{groupTourId}/update", method = RequestMethod.GET)
    public String showGroupTourUpdateForm(Model model, @PathVariable("groupTourId") Long groupTourId, HttpServletRequest request) {
        setCommonData(model,request);
        setDiscounts(model);
        model.addAttribute("groupTour", groupTourService.findOne(groupTourId));
        return "backstage/Product/groupTour/update";
    }


    /**
     * 更新跟团游
     * @param groupTourPO
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:update")
    @RequestMapping(value = "/groupTour/update", method = RequestMethod.POST)
    public String groupTourUpdate(GroupTourPO groupTourPO, RedirectAttributes redirectAttributes) {
        groupTourService.updateGroupTour(groupTourPO);
        redirectAttributes.addFlashAttribute("msg", "更新成功");
        return "redirect:/backstage/Product/groupTour/index";
    }


    /**
     * 删除跟团游
     * @param groupTourId
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:do not touch this method")
    @RequestMapping(value = "/groupTour/{groupTourId}/delete", method = RequestMethod.GET)
    public String deleteGroupTour(@PathVariable("groupTourId") Long groupTourId, RedirectAttributes redirectAttributes) {
        groupTourService.deleteGroupTour(groupTourId);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/backstage/Product/groupTour/index";
    }


    /*
    票务管理
     */

    /**
     * 票务页面
     * @param model
     * @return
     */
    @RequiresPermissions("product:view")
    @RequestMapping(value = "/ticket/index", method = RequestMethod.GET)
    public String ticketIndex(Model model) {
        List<TicketPO> ticketPOList = ticketService.findAll();
        model.addAttribute("tickets",ticketPOList);
        return "backstage/Product/ticket/index";
    }

    /**
     * 票务子产品页面
     * @param model
     * @param ticketId
     * @return
     */
    @RequiresPermissions("product:view")
    @RequestMapping(value = "/ticket/{ticketId}/detail", method = RequestMethod.GET)
    public String ticketDetail(Model model, @PathVariable("ticketId") Long ticketId) {
        List<TicketDetailPO> ticketDetailPOList = ticketDetailService.findAllByTicketId(ticketId);
        model.addAttribute("ticketId", ticketId);
        model.addAttribute("ticketDetails",ticketDetailPOList);
        return "backstage/Product/ticket/detail/index";
    }

    /**
     * 票务添加子产品页面
     * @param model
     * @param ticketId
     * @return
     */
    @RequiresPermissions("product:create")
    @RequestMapping(value = "/ticket/{ticketId}/detail/add", method = RequestMethod.GET)
    public String showTicketDetailCreateForm(Model model, @PathVariable("ticketId") Long ticketId) {
        model.addAttribute("ticketId", ticketId);
        return "backstage/Product/ticket/detail/add";
    }

    /**
     * 票务添加子产品
     * @param ticketDetailPO
     * @param ticketId
     * @param redirectAttributes
     * @param departureTime
     * @return
     * @throws Exception
     */
    @RequiresPermissions("product:create")
    @RequestMapping(value = "/ticket/{ticketId}/detail/add", method = RequestMethod.POST)
    public String addTicketDetail(TicketDetailPO ticketDetailPO, @PathVariable("ticketId") Long ticketId,
                                  RedirectAttributes redirectAttributes ,
                                  @RequestParam("dTime") String departureTime ) throws Exception{

        //格式转化
        ticketDetailPO.setDepartureTime(FormatUtil.stringToDate(departureTime));

        ticketDetailPO.setTicketId(ticketId);
        ticketDetailService.createTicketDetail(ticketDetailPO);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/backstage/Product/ticket/"+ticketId+"/detail";
    }

    /**
     * 删除票务子产品
     * @param ticketDetailId
     * @param ticketId
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:do not touch this method")
    @RequestMapping(value = "/ticket/{ticketId}/detail/{ticketDetailId}/delete", method = RequestMethod.GET)
    public String deleteTicketDetail(@PathVariable("ticketDetailId") Long ticketDetailId,
                                     @PathVariable("ticketId") Long ticketId, RedirectAttributes redirectAttributes) {
        ticketDetailService.deleteTicketDetail(ticketDetailId);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/backstage/Product/ticket/"+ticketId+"/detail";
    }

    /**
     * 更新票务子产品
     * @param ticketDetailId
     * @param ticketId
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:update")
    @RequestMapping(value = "/ticket/{ticketId}/detail/{ticketDetailId}/update", method = RequestMethod.GET)
    public String updateTicketDetail(@PathVariable("ticketDetailId") Long ticketDetailId,
                                     @PathVariable("ticketId") Long ticketId, RedirectAttributes redirectAttributes) {
        TicketDetailPO ticketDetailPO = ticketDetailService.findOne(ticketDetailId);
        if (ticketDetailPO.getAvailable()) {
            ticketDetailPO.setAvailable(false);
        }else {
            ticketDetailPO.setAvailable(true);
        }
        ticketDetailService.updateTicketDetail(ticketDetailPO);
        redirectAttributes.addFlashAttribute("msg", "更新成功");
        return "redirect:/backstage/Product/ticket/"+ticketId+"/detail";
    }


    /**
     * 添加票务页面
     * @param model
     * @param request
     * @return
     */
    @RequiresPermissions("product:create")
    @RequestMapping(value = "/ticket/add", method = RequestMethod.GET)
    public String showTicketCreateForm(Model model,HttpServletRequest request) {
        setCommonData(model,request);
        setDiscounts(model);
        return "backstage/Product/ticket/add";
    }

    /**
     * 添加票务
     * @param ticketPO
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:create")
    @RequestMapping(value = "/ticket/add", method = RequestMethod.POST)
    public String ticketCreate(TicketPO ticketPO, RedirectAttributes redirectAttributes) {
        ticketService.createTicket(ticketPO);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/backstage/Product/ticket/index";
    }

    /**
     * 更新票务页面
     * @param model
     * @param ticketId
     * @param request
     * @return
     */
    @RequiresPermissions("product:update")
    @RequestMapping(value = "/ticket/{ticketId}/update", method = RequestMethod.GET)
    public String showTicketUpdateForm(Model model, @PathVariable("ticketId") Long ticketId, HttpServletRequest request) {
        setCommonData(model,request);
        setDiscounts(model);
        model.addAttribute("ticket", ticketService.findOne(ticketId));
        return "backstage/Product/ticket/update";
    }


    /**
     * 更新票务
     * @param ticketPO
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:update")
    @RequestMapping(value = "/ticket/update", method = RequestMethod.POST)
    public String ticketUpdate(TicketPO ticketPO, RedirectAttributes redirectAttributes) {
        ticketService.updateTicket(ticketPO);
        redirectAttributes.addFlashAttribute("msg", "更新成功");
        return "redirect:/backstage/Product/ticket/index";
    }


    /**
     * 删除票务
     * @param ticketId
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("product:do not touch this method")
    @RequestMapping(value = "/ticket/{ticketId}/delete", method = RequestMethod.GET)
    public String deleteTicket(@PathVariable("ticketId") Long ticketId, RedirectAttributes redirectAttributes) {
        ticketService.deleteTicket(ticketId);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/backstage/Product/ticket/index";
    }




    /*
    上传图片
     */



    /**
     * 获取上传图片页面
     * @return
     */
    @RequiresPermissions("product_image:upload")
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadForm() {
        return "backstage/Product/upload";
    }

    /**
     * 上传图片
     * @param request
     * @param file
     * @return
     */
    @RequiresPermissions("product_image:upload")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public boolean upload(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile file) {
        //更改图片名，保证唯一
        String newName = new String(UUID.randomUUID()+"").substring(0,8)+".jpg";
        String imagePath = request.getServletContext().getRealPath("/WEB-INF/jsp/backstage/Product/image");
        String newPath =  request.getServletContext().getRealPath("/WEB-INF/jsp/backstage/Product/image/cut_image")+"/"+newName;
        try {
            if (!file.isEmpty()) {
                logger.info("Process file:{}", file.getOriginalFilename());
                File imageFile = new File(imagePath, newName);
                file.transferTo(imageFile);
                ImageUtil.cutImage(imagePath+"/"+newName,newPath);
            }else {
                return false;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 图片预览
     * @param request
     * @param model
     * @return
     */
    @RequiresPermissions("product_image:view")
    @RequestMapping(value = "/preview", method = RequestMethod.GET)
    public  String picturePreview(HttpServletRequest request, Model model) {
        setCommonData(model,request);
        return "backstage/Product/index";
    }

    /**
     * 图片删除
     * @param request
     * @param picture
     * @return
     */
    @RequiresPermissions("product_image:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public  String pictureDelete(HttpServletRequest request, @RequestParam(value = "picture") String picture) {
        String path = request.getServletContext().getRealPath("/WEB-INF/jsp/backstage/Product/image/cut_image");
        File file = new File(path,picture);
        try{
            if(file.delete()){
                System.out.println(file.getName() + " 文件已被删除！");
            }else{
                System.out.println("文件删除失败！");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "redirect:/backstage/Product/preview";
    }


    private void setCommonData(Model model, HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/WEB-INF/jsp/backstage/Product/image/cut_image");
        model.addAttribute("pictures", TravelFileUtil.travelFile(path));
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
}
