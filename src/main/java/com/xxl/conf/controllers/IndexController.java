package com.xxl.conf.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bj58.wf.mvc.BeatContext.Model;
import com.bj58.wf.mvc.annotation.Path;
import com.xxl.conf.controllers.annotation.PermessionLimit;
import com.xxl.conf.util.ReturnT;

/**
 * Created by xuxueli on 16/7/30.
 */
public class IndexController extends SMBaseController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class.getName());

    @Path("/")
    public String index(Model model, HttpServletRequest request) {
        //        if (!PermissionInterceptor.ifLogin(request)) {
        //            return "redirect:/toLogin";
        //        }
        return "redirect:/conf";
    }

    @Path("/toLogin")
    public String toLogin(Model model, HttpServletRequest request) {
        //        if (PermissionInterceptor.ifLogin(request)) {
        //            return "redirect:/";
        //        }
        return "login";
    }

    //    @Path("/login")
    //    public ReturnT<String> loginDo(HttpServletRequest request, HttpServletResponse response, String userName, String password, String ifRemember) {
    //        //        if (!PermissionInterceptor.ifLogin(request)) {
    //        Properties prop = PropertiesUtil.loadProperties("config.properties");
    //        if (StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password) && PropertiesUtil.getString(prop, "login.username").equals(userName) && PropertiesUtil.getString(prop, "login.password").equals(password)) {
    //            boolean ifRem = false;
    //            if (StringUtils.isNotBlank(ifRemember) && "on".equals(ifRemember)) {
    //                ifRem = true;
    //            }
    //            //                PermissionInterceptor.login(response, ifRem);
    //        } else {
    //            return new ReturnT<String>(500, "账号或密码错误");
    //        }
    //        //        }return ReturnT.SUCCESS;
    //
    //    }

    @Path("/logout")
    public ReturnT<String> logout(HttpServletRequest request, HttpServletResponse response) {
        //        if (PermissionInterceptor.ifLogin(request)) {
        //            PermissionInterceptor.logout(request, response);
        //        }
        return ReturnT.SUCCESS;
    }

    @RequestMapping("/help")
    @PermessionLimit
    public String help() {
        return "help";
    }

}
