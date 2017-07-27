package com.zjtravel.web.controller.management;

import com.zjtravel.pojo.po.ResourcePO;
import com.zjtravel.service.management.ResourceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by hunger on 2017/2/14.
 */
@Controller
@RequestMapping("/backstage/Resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    @ModelAttribute("types")
    public ResourcePO.ResourceType[] resourceTypes() {
        return ResourcePO.ResourceType.values();
    }

    @RequiresPermissions("resource:view")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("resourceList", resourceService.findAll());
        return "backstage/Resource/index";
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.GET)
    public String showAppendChildForm(@PathVariable("parentId") Long parentId, Model model) {
        ResourcePO parent = resourceService.findOne(parentId);
        model.addAttribute("parent", parent);
        ResourcePO child = new ResourcePO();
        child.setParentId(parentId);
        child.setParentIds(parent.makeSelfAsParentIds());
        model.addAttribute("resource", child);
        return "backstage/Resource/add";
    }

    @RequiresPermissions("resource:create")
    @RequestMapping(value = "/{parentId}/appendChild", method = RequestMethod.POST)
    public String create(ResourcePO resource, RedirectAttributes redirectAttributes) {
        resourceService.createResource(resource);
        redirectAttributes.addFlashAttribute("msg", "新增子节点成功");
        return "redirect:/backstage/Resource/index";
    }

    /**
     * 获取修改资源页面
     * @param id
     * @param model
     * @return
     */
    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("resource", resourceService.findOne(id));
        model.addAttribute("parentName",resourceService.findOne(resourceService.findOne(id).getParentId()).getName());
        return "backstage/Resource/edit";
    }

    /**
     * 修改资源
     * @param resource
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("resource:update")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String update(ResourcePO resource, RedirectAttributes redirectAttributes) {
        resourceService.updateResource(resource);
        redirectAttributes.addFlashAttribute("msg", "修改成功");
        return "redirect:/backstage/Resource/index";
    }

    /**
     * 删除资源
     * @param id
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions("resource:delete")
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        resourceService.deleteResource(id);
        redirectAttributes.addFlashAttribute("msg", "删除成功");
        return "redirect:/backstage/Resource/index";
    }


}
