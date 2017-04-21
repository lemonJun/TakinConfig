package com.takin.config.controllers;

import java.io.File;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.takin.config.core.ZKConfig;
import com.takin.config.util.GuiceDI;
import com.takin.emmet.file.PropertiesHelper;
import com.takin.mvc.mvc.IInit;
import com.takin.mvc.mvc.WF;
import com.takin.mvc.mvc.annotation.Init;

@Init
public class ConfigInit implements IInit {
    private static final Logger logger = Logger.getLogger(ConfigInit.class);

    @Override
    public void init() {
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
