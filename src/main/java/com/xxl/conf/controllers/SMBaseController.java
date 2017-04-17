package com.xxl.conf.controllers;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.bj58.spat.scf.client.SCFInit;
import com.takin.mvc.mvc.AbstractController;
import com.takin.mvc.mvc.Model;
import com.takin.mvc.mvc.WF;
import com.xxl.conf.util.GuiceDI;

public class SMBaseController extends AbstractController {
    private static final Logger logger = Logger.getLogger(SMBaseController.class);

    static {
        String path = WF.getConfigFolder() + WF.getNamespace();
        try {
            SCFInit.init(path + File.separator + "scf.config");
            System.out.println("===========bizconfigurebuilder===========" + path + File.separator + "business.xml");
        } catch (Exception e) {
            logger.error("BaseController.static.init");
        }
        try {
            GuiceDI.init();
        } catch (Exception e) {
            logger.error("InjectorHolder init error", e);
        }
    }

    public HttpServletRequest getRequest() {
        return beat.getRequest();
    }

    public HttpServletResponse getResponse() {
        return beat.getResponse();
    }

    public Model getModel() {
        return beat.getModel();
    }

    protected String getStringParameter(String key) {
        return getStringParameter(key, null);
    }

    protected String getStringParameter(String key, String defaultValue) {
        String value = getRequest().getParameter(key);
        if (value == null)
            return defaultValue;
        return value;
    }

    protected int getIntParameter(String key) {
        return getIntParameter(key, 0);
    }

    protected int getIntParameter(String key, int defaultValue) {
        String value = getRequest().getParameter(key);
        if (value == null)
            return defaultValue;
        int result = defaultValue;
        try {
            result = Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            result = defaultValue;
        }
        return result;
    }

    protected long getLongParameter(String key, long defaultValue) {
        String value = getRequest().getParameter(key);
        if (value == null)
            return defaultValue;
        long result = defaultValue;
        try {
            result = Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            result = defaultValue;
        }
        return result;
    }

    protected boolean getBooleanParameter(String key) {
        String value = getRequest().getParameter(key);
        if (value == null)
            return false;
        return Boolean.parseBoolean(value);
    }
}
