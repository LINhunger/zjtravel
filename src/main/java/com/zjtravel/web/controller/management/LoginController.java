package com.zjtravel.web.controller.management;

import com.zjtravel.pojo.dto.RequestResult;
import com.zjtravel.pojo.po.UserPO;
import com.zjtravel.web.bind.annotation.CurrentUser;
import com.zjtravel.web.enums.StatEnum;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hunger on 2017/2/14.
 */
@Controller
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());




    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String getLoginForm() {
        return "frontend/login";
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(HttpServletRequest req, Model model) {
        String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "登录失败：用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "登录失败：用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "登录失败：其他错误：" + exceptionClassName;
        }
        model.addAttribute("error", error);
        return "frontend/login";
    }





}
