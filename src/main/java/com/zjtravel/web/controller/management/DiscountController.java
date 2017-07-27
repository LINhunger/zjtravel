package com.zjtravel.web.controller.management;

import com.zjtravel.pojo.po.DiscountPO;
import com.zjtravel.pojo.po.GroupTourPO;
import com.zjtravel.pojo.po.UserPO;
import com.zjtravel.service.show.DiscountService;
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

import java.util.List;

/**
 * Created by hunger on 2017/3/19.
 */
@Controller
@RequestMapping(value = "/backstage/Discount")
public class DiscountController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DiscountService discountService;

    @RequiresPermissions("discount:view")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model model) {
        List<DiscountPO> discountPOList = discountService.findAll();
        model.addAttribute("discounts",discountPOList);
        return "backstage/Discount/index";
    }

    @RequiresPermissions("discount:create")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showCreateForm() {
        return "backstage/Discount/add";
    }

    @RequiresPermissions("discount:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String create(DiscountPO discountPO, RedirectAttributes redirectAttributes) {
        discountService.createDiscount(discountPO);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/backstage/Discount/index";
    }

    @RequiresPermissions("discount:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("discount", discountService.findOne(id));
        return "backstage/Discount/update";
    }

    @RequiresPermissions("discount:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(DiscountPO discountPO, RedirectAttributes redirectAttributes) {
        discountService.updateDiscount(discountPO);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/backstage/Discount/index";
    }

    @RequiresPermissions("discount:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        discountService.deleteDiscount(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/backstage/Discount/index";
    }


}
