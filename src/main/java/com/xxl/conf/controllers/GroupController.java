package com.xxl.conf.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.takin.mvc.mvc.ActionResult;
import com.takin.mvc.mvc.annotation.Controller;
import com.takin.mvc.mvc.annotation.Path;
import com.takin.mvc.util.ParamUtil;
import com.xxl.conf.mysql.XxlConfGroup;
import com.xxl.conf.mysql.XxlConfGroupDaoImpl;
import com.xxl.conf.mysql.XxlConfNodeDaoImpl;
import com.xxl.conf.util.GuiceDI;

/**
 * conf group controller
 * @author xuxueli 2016-10-02 20:52:56
 */
@Path("/group")
@Controller
public class GroupController extends SMBaseController {

    private XxlConfGroupDaoImpl xxlConfGroupDao = GuiceDI.getInstance(XxlConfGroupDaoImpl.class);
    private XxlConfNodeDaoImpl xxlConfNodeDao = GuiceDI.getInstance(XxlConfNodeDaoImpl.class);

    @Path("/index")
    public ActionResult index() {
        List<XxlConfGroup> list = xxlConfGroupDao.findAll();
        beat.getModel().add("list", list);
        return ActionResult.view("group");
    }

    @Path("/save")
    public ActionResult save() {
        JSONObject json = new JSONObject();

        XxlConfGroup xxlConfGroup = new XxlConfGroup();

        // valid
        if (xxlConfGroup.getGroupName() == null || StringUtils.isBlank(xxlConfGroup.getGroupName())) {
            json = JsonMsgResult.buildResult(500, "请输入GroupName");
        }
        if (xxlConfGroup.getGroupName().length() < 4 || xxlConfGroup.getGroupName().length() > 100) {
            json = JsonMsgResult.buildResult(500, "GroupName长度限制为4~100");

        }
        if (xxlConfGroup.getGroupTitle() == null || StringUtils.isBlank(xxlConfGroup.getGroupTitle())) {
            json = JsonMsgResult.buildResult(500, "请输入分组名称");

        }

        // valid repeat
        XxlConfGroup groupOld = xxlConfGroupDao.load(xxlConfGroup.getGroupName());
        if (groupOld != null) {
            json = JsonMsgResult.buildResult(500, "GroupName对应分组以存在,请勿重复添加");
        }
        int ret = xxlConfGroupDao.save(xxlConfGroup);
        json = JsonMsgResult.buildResult(500, "success", "", "", "");
        return new JsonActionResult(json.toJSONString());
    }

    @Path("/update")
    public ActionResult update() {
        JSONObject json = new JSONObject();
        XxlConfGroup xxlConfGroup = new XxlConfGroup();
        // valid
        if (xxlConfGroup.getGroupName() == null || StringUtils.isBlank(xxlConfGroup.getGroupName())) {
            json = JsonMsgResult.buildResult(500, "请输入GroupName");

        }
        if (xxlConfGroup.getGroupName().length() < 4 || xxlConfGroup.getGroupName().length() > 100) {
            json = JsonMsgResult.buildResult(500, "GroupName长度限制为4~100");
        }
        if (xxlConfGroup.getGroupTitle() == null || StringUtils.isBlank(xxlConfGroup.getGroupTitle())) {
            json = JsonMsgResult.buildResult(500, "请输入分组名称");
        }
        int ret = xxlConfGroupDao.update(xxlConfGroup);
        json = JsonMsgResult.buildResult(500, "success", "", "", "");
        return new JsonActionResult(json.toJSONString());
    }

    @Path("/remove")
    public ActionResult remove() {
        JSONObject json = new JSONObject();

        String groupName = ParamUtil.getString(beat.getRequest(), "groupName", "");

        // valid
        int list_count = xxlConfNodeDao.pageListCount(0, 10, groupName, null);
        if (list_count > 0) {
            json = JsonMsgResult.buildResult(500, "该分组使用中, 不可删除");
        }

        List<XxlConfGroup> allList = xxlConfGroupDao.findAll();
        if (allList.size() == 1) {
            json = JsonMsgResult.buildResult(500, "删除失败, 系统需要至少预留一个默认分组");
        }

        int ret = xxlConfGroupDao.remove(groupName);
        json = JsonMsgResult.buildResult(500, "success", "", "", "");
        return new JsonActionResult(json.toJSONString());
    }

}
