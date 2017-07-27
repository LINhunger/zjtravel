package com.zjtravel.web.controller.management;

import com.zjtravel.pojo.po.RolePO;
import com.zjtravel.service.management.ResourceService;
import com.zjtravel.service.management.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by hunger on 2017/2/14.
 */
@Controller
@RequestMapping("/backstage/Role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ResourceService resourceService;

    @RequiresPermissions("role:view")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("roleList", roleService.findAll());
        return "backstage/Role/index";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showCreateForm(Model model) {
        setGroupData(model);
        model.addAttribute("role", new RolePO());
        return "backstage/Role/add";
    }

    @RequiresPermissions("role:create")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String create(RolePO role, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        roleService.createRole(role);
        redirectAttributes.addFlashAttribute("msg", "新增成功");
        return "redirect:/backstage/Role/index";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        setGroupData(model);
        model.addAttribute("role", roleService.findOne(id));
        return "backstage/Role/edit";
    }

    @RequiresPermissions("role:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(RolePO role, RedirectAttributes redirectAttributes) {
        roleService.updateRole(role);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/backstage/Role/index";
    }


    @RequiresPermissions("role:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        roleService.deleteRole(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/backstage/Role/index";
    }

    private void setCommonData(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
    }

    private void setGroupData(Model model) {
        model.addAttribute("resourceList", resourceService.groupResource(resourceService.findAll()));
    }
}
