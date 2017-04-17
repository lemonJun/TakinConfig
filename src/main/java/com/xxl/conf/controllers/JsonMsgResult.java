package com.xxl.conf.controllers;

import com.alibaba.fastjson.JSONObject;

public class JsonMsgResult {

    public static JSONObject buildResult(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json;
    }

    public static JSONObject buildResult(int code, String msg, String title, String url, String mobile) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        json.put("title", title);
        json.put("url", url);
        json.put("mobile", mobile);
        return json;
    }

}
