package com.xxl.conf.controllers;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.takin.mvc.mvc.ActionResult;
import com.takin.mvc.mvc.annotation.Controller;
import com.takin.mvc.mvc.annotation.Path;
import com.takin.mvc.util.ParamUtil;
import com.xxl.conf.mysql.ConfGroup;
import com.xxl.conf.mysql.ConfGroupDao;
import com.xxl.conf.mysql.ConfNode;
import com.xxl.conf.mysql.ConfNodeDao;
import com.xxl.conf.util.GuiceDI;

/**
 * 配置管理
 * @author xuxueli
 */
@Path("")
@Controller
public class ConfController extends SMBaseController {

    @Path("/conf.html")
    public ActionResult index() {
        try {
            List<ConfNode> list = GuiceDI.getInstance(ConfNodeDao.class).pageList(1, 10, "", "");
            beat.getModel().add("list", list);

            List<ConfGroup> glist = GuiceDI.getInstance(ConfGroupDao.class).findAll();
            beat.getModel().add("glist", glist);

            beat.getModel().add("nodeKey", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.view("conf");
    }

    @Path("/conf/pageList")
    public ActionResult pageList() {
        JSONObject json = new JSONObject();
        try {
            String nodeGroup = ParamUtil.getString(beat.getRequest(), "nodeGroup", "");
            String nodeKey = ParamUtil.getString(beat.getRequest(), "nodeKey", "");
            int start = ParamUtil.getInt(beat.getRequest(), "start", 1);
            int length = ParamUtil.getInt(beat.getRequest(), "length", 10);

            GuiceDI.getInstance(ConfNodeDao.class).deleteByKey(nodeGroup, nodeKey);

            List<ConfNode> list = GuiceDI.getInstance(ConfNodeDao.class).pageList(start+1, length, nodeGroup, nodeKey);
            beat.getModel().add("list", list);

            json = JsonMsgResult.buildResult(500, "success", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonActionResult(json.toJSONString());
    }

    @Path("/delete")
    public ActionResult delete() {
        JSONObject json = new JSONObject();
        try {
            String nodeGroup = ParamUtil.getString(beat.getRequest(), "nodeGroup", "");
            String nodeKey = ParamUtil.getString(beat.getRequest(), "nodeKey", "");

            GuiceDI.getInstance(ConfNodeDao.class).deleteByKey(nodeGroup, nodeKey);
            json = JsonMsgResult.buildResult(500, "success", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonActionResult(json.toJSONString());
    }

    /**
     * create/update
     * @return
     */
    @Path("/add")
    public ActionResult add() {
        JSONObject json = new JSONObject();
        try {
            ConfNode confNode = new ConfNode();
            GuiceDI.getInstance(ConfNodeDao.class).insert(confNode);
            json = JsonMsgResult.buildResult(500, "success", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonActionResult(json.toJSONString());
    }

    /**
     * create/update
     * @return
     */
    @Path("/update")
    public ActionResult update() {
        JSONObject json = new JSONObject();
        try {
            ConfNode confNode = new ConfNode();
            GuiceDI.getInstance(ConfNodeDao.class).update(confNode);
            json = JsonMsgResult.buildResult(500, "success", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonActionResult(json.toJSONString());
    }

}
