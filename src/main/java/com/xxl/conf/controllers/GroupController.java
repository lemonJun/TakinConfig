package com.xxl.conf.controllers;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.takin.mvc.mvc.Model;
import com.takin.mvc.mvc.annotation.Path;
import com.xxl.conf.mysql.XxlConfGroup;
import com.xxl.conf.mysql.XxlConfGroupDaoImpl;
import com.xxl.conf.mysql.XxlConfNodeDaoImpl;
import com.xxl.conf.util.GuiceDI;
import com.xxl.conf.util.ReturnT;

/**
 * conf group controller
 * @author xuxueli 2016-10-02 20:52:56
 */
@Path("/group")
public class GroupController extends SMBaseController {

    private XxlConfGroupDaoImpl xxlConfGroupDao = GuiceDI.getInstance(XxlConfGroupDaoImpl.class);
    private XxlConfNodeDaoImpl xxlConfNodeDao = GuiceDI.getInstance(XxlConfNodeDaoImpl.class);

    @Path("/index")
    public String index(Model model) {

        List<XxlConfGroup> list = xxlConfGroupDao.findAll();

        model.add("list", list);
        return "group/group.index";
    }

    @Path("/save")
    public ReturnT<String> save(XxlConfGroup xxlConfGroup) {

        // valid
        if (xxlConfGroup.getGroupName() == null || StringUtils.isBlank(xxlConfGroup.getGroupName())) {
            return new ReturnT<String>(500, "请输入GroupName");
        }
        if (xxlConfGroup.getGroupName().length() < 4 || xxlConfGroup.getGroupName().length() > 100) {
            return new ReturnT<String>(500, "GroupName长度限制为4~100");
        }
        if (xxlConfGroup.getGroupTitle() == null || StringUtils.isBlank(xxlConfGroup.getGroupTitle())) {
            return new ReturnT<String>(500, "请输入分组名称");
        }

        // valid repeat
        XxlConfGroup groupOld = xxlConfGroupDao.load(xxlConfGroup.getGroupName());
        if (groupOld != null) {
            return new ReturnT<String>(500, "GroupName对应分组以存在,请勿重复添加");
        }

        int ret = xxlConfGroupDao.save(xxlConfGroup);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Path("/update")
    public ReturnT<String> update(XxlConfGroup xxlConfGroup) {

        // valid
        if (xxlConfGroup.getGroupName() == null || StringUtils.isBlank(xxlConfGroup.getGroupName())) {
            return new ReturnT<String>(500, "请输入GroupName");
        }
        if (xxlConfGroup.getGroupName().length() < 4 || xxlConfGroup.getGroupName().length() > 100) {
            return new ReturnT<String>(500, "GroupName长度限制为4~100");
        }
        if (xxlConfGroup.getGroupTitle() == null || StringUtils.isBlank(xxlConfGroup.getGroupTitle())) {
            return new ReturnT<String>(500, "请输入分组名称");
        }

        int ret = xxlConfGroupDao.update(xxlConfGroup);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

    @Path("/remove")
    public ReturnT<String> remove(String groupName) {

        // valid
        int list_count = xxlConfNodeDao.pageListCount(0, 10, groupName, null);
        if (list_count > 0) {
            return new ReturnT<String>(500, "该分组使用中, 不可删除");
        }

        List<XxlConfGroup> allList = xxlConfGroupDao.findAll();
        if (allList.size() == 1) {
            return new ReturnT<String>(500, "删除失败, 系统需要至少预留一个默认分组");
        }

        int ret = xxlConfGroupDao.remove(groupName);
        return (ret > 0) ? ReturnT.SUCCESS : ReturnT.FAIL;
    }

}
