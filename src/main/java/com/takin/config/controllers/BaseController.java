package com.takin.config.controllers;

import java.io.File;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.takin.config.core.ZKConfig;
import com.takin.config.util.GuiceDI;
import com.takin.emmet.file.PropertiesHelper;
import com.takin.mvc.mvc.AbstractController;
import com.takin.mvc.mvc.WF;

public class BaseController extends AbstractController {
    private static final Logger logger = Logger.getLogger(BaseController.class);

    static {
        try {
            GuiceDI.init();
        } catch (Exception e) {
            logger.error("InjectorHolder init error", e);
        }

        try {
            ZKConfig config = GuiceDI.getInstance(ZKConfig.class);
            PropertiesHelper pro = new PropertiesHelper(WF.getNamespaceConfigFolder() + File.separator + "zoo.properties");

            config.setZkhosts(pro.getString("zkhosts"));
            logger.info("hosts:" + JSON.toJSONString(config));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
