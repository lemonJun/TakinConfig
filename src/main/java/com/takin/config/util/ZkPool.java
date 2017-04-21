package com.takin.config.util;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.github.zkclient.ZkClient;
import com.takin.config.core.ZKConfig;

public class ZkPool {

    private static final Logger logger = Logger.getLogger(ZkPool.class);

    public static ZkClient zkClient = null;

    /**
     * 初始化zk客户 涉及到多线程
     * @return
     */
    public static ZkClient getClient() {
        try {
            if (zkClient == null) {
                ZKConfig config = GuiceDI.getInstance(ZKConfig.class);
                logger.info(JSON.toJSONString(config));
                zkClient = new ZkClient(config.getZkhosts(), 600, 600);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return zkClient;
    }

}
