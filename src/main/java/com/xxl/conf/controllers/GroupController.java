package com.xxl.conf.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.takin.mvc.mvc.ActionResult;
import com.takin.mvc.mvc.annotation.Controller;
import com.takin.mvc.mvc.annotation.Path;
import com.takin.mvc.util.ParamUtil;
import com.xxl.conf.mysql.ConfGroup;
import com.xxl.conf.mysql.ConfGroupDao;
import com.xxl.conf.mysql.ConfNodeDao;
import com.xxl.conf.util.GuiceDI;

/**
 * conf group controller
 * @author xuxueli 2016-10-02 20:52:56
 */
@Path("/group")
@Controller
public class GroupController extends SMBaseController {

    private static final Logger logger = LoggerFactory.getLogger(GroupController.class);

    @Path("/index")
    public ActionResult index() {
        try {
            List<ConfGroup> list = GuiceDI.getInstance(ConfGroupDao.class).findAll();
            beat.getModel().add("list", list);
            for (ConfGroup group : list) {
                logger.info("group:" + group.getGroupname() + " titel:" + group.getGrouptitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ActionResult.view("views/group");
    }

    @Path("/save")
    public ActionResult save() {
        JSONObject json = new JSONObject();

        ConfGroup xxlConfGroup = new ConfGroup();

        // valid
        if (xxlConfGroup.getGroupname() == null || StringUtils.isBlank(xxlConfGroup.getGroupname())) {
            json = JsonMsgResult.buildResult(500, "请输入GroupName");
        }
        if (xxlConfGroup.getGroupname().length() < 4 || xxlConfGroup.getGroupname().length() > 100) {
            json = JsonMsgResult.buildResult(500, "GroupName长度限制为4~100");

        }
        if (xxlConfGroup.getGrouptitle() == null || StringUtils.isBlank(xxlConfGroup.getGrouptitle())) {
            json = JsonMsgResult.buildResult(500, "请输入分组名称");

        }

        // valid repeat
        ConfGroup groupOld = GuiceDI.getInstance(ConfGroupDao.class).load(xxlConfGroup.getGroupname());
        if (groupOld != null) {
            json = JsonMsgResult.buildResult(500, "GroupName对应分组以存在,请勿重复添加");
        }
        int ret = GuiceDI.getInstance(ConfGroupDao.class).save(xxlConfGroup);
        json = JsonMsgResult.buildResult(500, "success", "", "", "");
        return new JsonActionResult(json.toJSONString());
    }

    @Path("/update")
    public ActionResult update() {
        JSONObject json = new JSONObject();
        ConfGroup xxlConfGroup = new ConfGroup();
        // valid
        if (xxlConfGroup.getGroupname() == null || StringUtils.isBlank(xxlConfGroup.getGroupname())) {
            json = JsonMsgResult.buildResult(500, "请输入GroupName");

        }
        if (xxlConfGroup.getGroupname().length() < 4 || xxlConfGroup.getGroupname().length() > 100) {
            json = JsonMsgResult.buildResult(500, "GroupName长度限制为4~100");
        }
        if (xxlConfGroup.getGrouptitle() == null || StringUtils.isBlank(xxlConfGroup.getGrouptitle())) {
            json = JsonMsgResult.buildResult(500, "请输入分组名称");
        }
        int ret = GuiceDI.getInstance(ConfGroupDao.class).update(xxlConfGroup);
        json = JsonMsgResult.buildResult(500, "success", "", "", "");
        return new JsonActionResult(json.toJSONString());
    }

    @Path("/remove")
    public ActionResult remove() {
        JSONObject json = new JSONObject();

        String groupName = ParamUtil.getString(beat.getRequest(), "groupName", "");

        // valid
        int list_count = GuiceDI.getInstance(ConfNodeDao.class).pageListCount(0, 10, groupName, null);
        if (list_count > 0) {
            json = JsonMsgResult.buildResult(500, "该分组使用中, 不可删除");
        }

        List<ConfGroup> allList = GuiceDI.getInstance(ConfGroupDao.class).findAll();
        if (allList.size() == 1) {
            json = JsonMsgResult.buildResult(500, "删除失败, 系统需要至少预留一个默认分组");
        }

        int ret = GuiceDI.getInstance(ConfGroupDao.class).remove(groupName);
        json = JsonMsgResult.buildResult(500, "success", "", "", "");
        return new JsonActionResult(json.toJSONString());
    }

}
