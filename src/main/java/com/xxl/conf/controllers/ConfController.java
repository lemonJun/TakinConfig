package com.xxl.conf.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bj58.wf.mvc.BeatContext.Model;
import com.bj58.wf.mvc.annotation.Path;
import com.xxl.conf.controllers.annotation.PermessionLimit;
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
        model.addAttribute("XxlConfNodeGroup", list);
        return "conf/conf.index";
    }

    @Path("/pageList")
    public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start, @RequestParam(required = false, defaultValue = "10") int length, String nodeGroup, String nodeKey) {
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
