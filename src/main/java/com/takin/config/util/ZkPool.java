package com.takin.config.util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.log4j.Logger;

import com.takin.config.core.ZKConfig;
import com.takin.mvc.mvc.inject.GuiceDI;

public class ZkPool {

    private static final Logger logger = Logger.getLogger(ZkPool.class);

    public static final RetryPolicy policy = new ExponentialBackoffRetry(100, 2);
    public static CuratorFramework client = null;

    /**
     * 初始化zk客户 涉及到多线程
     * @return
     */
    public static CuratorFramework getClient() {
        try {
            if (client == null) {
                logger.info(GuiceDI.getInstance(ZKConfig.class));
                client = CuratorFrameworkFactory.builder().connectString("localhost:2181").sessionTimeoutMs(1000).retryPolicy(policy).build();
                client.start();
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return client;
    }

    /**
     * 使用新的zk配置
     */
    public static void distroy() {
        client = null;
    }

}
