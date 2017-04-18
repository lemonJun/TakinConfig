package com.xxl.conf.controllers;

import javax.servlet.http.HttpServletResponse;

import com.takin.mvc.mvc.ActionResult;
import com.takin.mvc.mvc.BeatContext;

/**
* 按照帮帮接入要求返回JSON格式数据
* @author Administrator
*/
public class JsonActionResult extends ActionResult {
    private String result = "";

    /**
     * 创建对象并初始化
     * @param resultcode 0表示成功，小于0暂定失败，大于0暂定其他成功情况[一期不考虑]
     * @param resultmsg 成功或失败文字提示语，一般指失败
     * @param t 需要转成json的结果集
     */
    public JsonActionResult(String msg) {
        this.result = msg;
    }

    @Override
    public void render(BeatContext beat) throws Exception {
        HttpServletResponse response = beat.getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String msg = this.result;
        response.getWriter().print(msg);
        response.getWriter().flush();
        response.getWriter().close();
    }

}
