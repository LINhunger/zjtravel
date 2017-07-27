package com.zjtravel.web.controller.management;

import com.zjtravel.pojo.po.ResourcePO;
import com.zjtravel.pojo.po.UserPO;
import com.zjtravel.service.management.ResourceService;
import com.zjtravel.service.management.UserService;
import com.zjtravel.web.bind.annotation.CurrentUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Set;

/**
 * Created by hunger on 2017/2/20.
 */
@Controller
@RequestMapping("/backstage")
public class BackIndexController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;

    @RequiresPermissions("backstage:view")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@CurrentUser UserPO loginUser, Model model) {

        Set<String> permissions = userService.findPermissions(loginUser.getUsername());
        List<ResourcePO> menus = resourceService.findMenus(permissions);
        model.addAttribute("menus", menus);
        return "backstage/index";
    }

}
