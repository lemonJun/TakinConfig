package com.xxl.conf.controllers;

import java.util.List;
import java.util.Map;

import com.takin.mvc.mvc.Model;
import com.takin.mvc.mvc.annotation.Path;
import com.xxl.conf.mysql.XxlConfGroup;
import com.xxl.conf.mysql.XxlConfGroupDaoImpl;
import com.xxl.conf.mysql.XxlConfNode;
import com.xxl.conf.provider.XxlConfNodeServiceImpl;
import com.xxl.conf.util.GuiceDI;
import com.xxl.conf.util.ReturnT;

/**
 * 配置管理
 * @author xuxueli
 */
@Path("/conf")
public class ConfController extends SMBaseController {

    private XxlConfGroupDaoImpl xxlConfGroupDao = GuiceDI.getInstance(XxlConfGroupDaoImpl.class);
    private XxlConfNodeServiceImpl xxlConfNodeService = GuiceDI.getInstance(XxlConfNodeServiceImpl.class);

    @Path("")
    public String index(Model model, String znodeKey) {

        List<XxlConfGroup> list = xxlConfGroupDao.findAll();
        model.add("XxlConfNodeGroup", list);
        return "conf/conf.index";
    }

    @Path("/pageList")
    public Map<String, Object> pageList() {
        int start = 1;
        int length = 10;
        String nodeGroup = "";
        String nodeKey = "";
        return xxlConfNodeService.pageList(start, length, nodeGroup, nodeKey);
    }

    /**
     * get
     * @return
     */
    @Path("/delete")
    public ReturnT<String> delete(String nodeGroup, String nodeKey) {
        return xxlConfNodeService.deleteByKey(nodeGroup, nodeKey);
    }

    /**
     * create/update
     * @return
     */
    @Path("/add")
    public ReturnT<String> add(XxlConfNode xxlConfNode) {
        return xxlConfNodeService.add(xxlConfNode);
    }

    /**
     * create/update
     * @return
     */
    @Path("/update")
    public ReturnT<String> update(XxlConfNode xxlConfNode) {
        return xxlConfNodeService.update(xxlConfNode);
    }

}
