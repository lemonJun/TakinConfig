package com.xxl.conf.controllers;

import com.takin.mvc.mvc.annotation.Controller;
import com.takin.mvc.mvc.annotation.Path;
import com.xxl.conf.mysql.ConfGroupDao;
import com.xxl.conf.provider.XxlConfNodeServiceImpl;
import com.xxl.conf.util.GuiceDI;

/**
 * 配置管理
 * @author xuxueli
 */
@Path("/conf")
@Controller
public class ConfController extends SMBaseController {

    private ConfGroupDao xxlConfGroupDao = GuiceDI.getInstance(ConfGroupDao.class);
    private XxlConfNodeServiceImpl xxlConfNodeService = GuiceDI.getInstance(XxlConfNodeServiceImpl.class);

}
