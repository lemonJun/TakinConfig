package com.takin.config.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.takin.config.util.ZkPool;
import com.takin.mvc.mvc.ActionResult;
import com.takin.mvc.mvc.BeatContext;
import com.takin.mvc.mvc.annotation.Controller;
import com.takin.mvc.mvc.annotation.Path;

@Controller
@Path("")
public class ZKController extends BaseController {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Path("/search")
    public ActionResult search() {
        JSONObject json = new JSONObject();
        BeatContext beat = beat();
        String path = beat.getRequest().getParameter("path");
        try {
            CuratorFramework client = ZkPool.getClient();
            Stat stat = new Stat();
            byte[] bs = client.getData().storingStatIn(stat).forPath(path);
            JSONObject obj = new JSONObject();
            obj.put("id", 1);
            obj.put("pid", 0);
            obj.put("name", path);
            obj.put("hasClick", false);
            obj.put("isParent", false);

            json.put("data", bs == null || bs.length < 1 ? "NA" : new String(bs));
            json.put("obj", obj);
            json.put("stat", toStat(stat));
            json.put("succ", 1);
        } catch (Exception e) {
            e.printStackTrace();
            json.put("succ", 0);
        }
        System.out.println(json.toString());
        return new JsonActionResult(json.toString());
    }

    @Path("/getChildren")
    public ActionResult getChildren() {
        JSONObject json = new JSONObject();
        BeatContext beat = beat();
        String ppath = beat.getRequest().getParameter("ppath");
        String pid = beat.getRequest().getParameter("pid");
        try {
            CuratorFramework client = ZkPool.getClient();
            List<String> children = client.getChildren().forPath(ppath);
            JSONArray array = new JSONArray();
            for (int i = 1; i < children.size(); i++) {
                String str = children.get(i);
                JSONObject obj = new JSONObject();
                obj.put("id", pid + "" + i);
                obj.put("pid", pid);
                String name;
                if (ppath.endsWith("/")) {
                    name = ppath + str;
                } else {
                    name = ppath + "/" + str;
                }
                obj.put("name", name);
                obj.put("hasClick", false);
                obj.put("isParent", false);
                array.add(obj);
            }

            Stat stat = new Stat();
            byte[] bs = client.getData().storingStatIn(stat).forPath(ppath);

            json.put("childs", array.toArray());
            json.put("data", bs == null || bs.length < 1 ? "NA" : new String(bs));
            json.put("stat", toStat(stat));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonActionResult(json.toString());
    }

    public String toStat(Stat stat) {
        StringBuilder sb = new StringBuilder();
        sb.append("czxid").append("：：&nbsp;&nbsp;").append(stat.getCzxid()).append("</br>");
        sb.append("mzxid").append("：：&nbsp;&nbsp;").append(stat.getMzxid()).append("</br>");
        sb.append("pzxid").append("：：&nbsp;&nbsp;").append(stat.getPzxid()).append("</br>");
        sb.append("numChildren").append("：：&nbsp;&nbsp;").append(stat.getNumChildren()).append("</br>");
        sb.append("version").append("：：&nbsp;&nbsp;").append(stat.getVersion()).append("</br>");
        sb.append("cversion").append("：：&nbsp;&nbsp;").append(stat.getCversion()).append("</br>");
        sb.append("aversion").append("：：&nbsp;&nbsp;").append(stat.getAversion()).append("</br>");
        sb.append("ctime").append("：：&nbsp;&nbsp;").append(sdf.format(new Date(stat.getCtime()))).append("</br>");
        sb.append("mtime").append("：：&nbsp;&nbsp;").append(sdf.format(new Date(stat.getMtime()))).append("</br>");
        return sb.toString();
    }

    @Path("/modifyNodeData")
    public ActionResult modifyNodeData() {
        JSONObject json = new JSONObject();
        BeatContext beat = beat();
        String path = beat.getRequest().getParameter("path");
        String id = beat.getRequest().getParameter("id");
        String value = beat.getRequest().getParameter("value");
        if ("1".equals(id)) {
            path = "/";
        }
        if (value == null || value.length() < 1) {
            return null;
        }

        try {
            CuratorFramework client = ZkPool.getClient();
            client.setData().forPath(path, value.getBytes());

            Stat stat = new Stat();
            byte[] bs = client.getData().storingStatIn(stat).forPath(path);

            json.put("data", bs == null ? "NA" : new String(bs));
            json.put("issuccess", true);
            json.put("stat", stat.toString());
        } catch (Exception e) {
            e.printStackTrace();
            json.put("issuccess", false);
        }
        return new JsonActionResult(json.toString());
    }

    @Path("/telnet")
    public ActionResult telnetFourCMD() {
        JSONObject json = new JSONObject();
        BeatContext beat = beat();
        String path = beat.getRequest().getParameter("path");
        String id = beat.getRequest().getParameter("id");
        String value = beat.getRequest().getParameter("value");
        if ("1".equals(id)) {
            path = "/";
        }
        if (value == null || value.length() < 1) {
            return null;
        }

        try {
            CuratorFramework client = ZkPool.getClient();
            client.setData().forPath(path, value.getBytes());

            Stat stat = new Stat();
            byte[] bs = client.getData().storingStatIn(stat).forPath(path);

            json.put("data", bs == null ? "NA" : new String(bs));
            json.put("issuccess", true);
            json.put("stat", stat.toString());
        } catch (Exception e) {
            e.printStackTrace();
            json.put("issuccess", false);
        }
        return new JsonActionResult(json.toString());
    }
}
