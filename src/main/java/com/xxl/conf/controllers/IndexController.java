package com.xxl.conf.controllers;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.takin.mvc.mvc.ActionResult;
import com.takin.mvc.mvc.Model;
import com.takin.mvc.mvc.annotation.Controller;
import com.takin.mvc.mvc.annotation.Path;
import com.xxl.conf.util.ReturnT;

/**
 * Created by xuxueli on 16/7/30.
 */
@Controller
public class IndexController extends SMBaseController {
    private static Logger logger = LoggerFactory.getLogger(IndexController.class.getName());

    @Path("/")
    public ActionResult index(Model model, HttpServletRequest request) {
        //        if (!PermissionInterceptor.ifLogin(request)) {
        //            return "redirect:/toLogin";
        //        }
        return ActionResult.view("redirect:/conf");
    }

    @Path("/toLogin")
    public ActionResult toLogin(Model model, HttpServletRequest request) {
        //        if (PermissionInterceptor.ifLogin(request)) {
        //            return "redirect:/";
        //        }
        return ActionResult.view("login");
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
    public ReturnT<String> logout() {
        //        if (PermissionInterceptor.ifLogin(request)) {
        //            PermissionInterceptor.logout(request, response);
        //        }
        return ReturnT.SUCCESS;
    }

    @Path("/help")
    public ActionResult help() {
        return ActionResult.view("help");
    }

}
