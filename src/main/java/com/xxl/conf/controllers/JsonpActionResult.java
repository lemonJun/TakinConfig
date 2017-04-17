package com.xxl.conf.controllers;

import javax.servlet.http.HttpServletResponse;

import com.bj58.wf.mvc.ActionResult;
import com.bj58.wf.mvc.BeatContext;

public class JsonpActionResult extends ActionResult {
    private String content;
    private String type;

    public JsonpActionResult(String content, String callback) {
        super();
        this.content = callback + "(" + content + ")";
        this.type = "html";
    }

    public JsonpActionResult(String content, String type, String callback) {
        super();
        this.content = callback + "(" + content + ")";
        this.type = type;
    }

    @Override
    public void render(BeatContext beat) throws Exception {
        HttpServletResponse response = beat.getResponse();
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/" + type + "; charset=utf-8");
        response.getWriter().print(content);
    }
}