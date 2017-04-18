package com.xxl.conf.controllers;

import com.takin.mvc.mvc.ActionResult;
import com.takin.mvc.mvc.annotation.Controller;
import com.takin.mvc.mvc.annotation.Path;

/**
 * 只提供微信基础服务接口 业务相关的接口放到其它类中
 *
 * @author WangYazhou
 * @date 2015年11月14日 上午11:09:29
 * @see
 */
@Path("")
@Controller
public class ConsoleController extends SMBaseController {

    @Path("/index.html")
    public ActionResult index() {
        return ActionResult.view("index");
    }

    @Path("/admin")
    public ActionResult main() {
        System.out.println("goto baido");
        return ActionResult.view("main");
    }

    @Path("/tmpmgr")
    public ActionResult tmpmgr() {
        return ActionResult.view("tmpmgr");
    }

    @Path("/znode.html")
    public ActionResult znode() {
        return view("znode");
    }

    @Path("/fcmd")
    public ActionResult fourCmd() {
        return view("fcmd");
    }

    @Path("/cluster.html")
    public ActionResult cluster() {
        return view("cluster");
    }

    @Path("/auth.html")
    public ActionResult auth() {
        return view("auth");
    }

}
