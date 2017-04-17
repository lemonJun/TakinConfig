package com.xxl.conf.mysql;

import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxl.conf.util.CollectionUtil;
import com.xxl.conf.util.GuiceDI;

/**
 * 配置
 * @author xuxueli
 */
public class XxlConfNodeDaoImpl {

    private static final Logger logger = LoggerFactory.getLogger(XxlConfNodeDaoImpl.class);

    public List<XxlConfNode> pageList(int offset, int pagesize, String nodeGroup, String nodeKey) {
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            String sql = "select * from XXL_CONF_NODE ";
            List<XxlConfNode> list = GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.getListBySQL(XxlConfNode.class, sql);
            return list;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public int pageListCount(int offset, int pagesize, String nodeGroup, String nodeKey) {
        return 2;
    }

    public int deleteByKey(String nodeGroup, String nodeKey) {
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            String sql = "delete from XXL_CONF_NODE where node_group ='" + nodeGroup + "' and node_key='" + nodeKey + "'";
            GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.execBySQL(sql);
        } catch (Exception e) {
            logger.error("", e);
        }
        return 1;
    }

    public void insert(XxlConfNode node) {
        try {
            Object obj = GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.insert(node);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public XxlConfNode selectByKey(String nodeGroup, String nodeKey) {
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            String sql = "select * from XXL_CONF_NODE where node_group ='" + nodeGroup + "' and node_key='" + nodeKey + "'";
            List<XxlConfNode> list = GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.getListBySQL(XxlConfNode.class, sql);
            XxlConfNode bean = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
            return bean;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public int update(XxlConfNode node) {
        int id = 0;
        try {
            GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.upateEntity(node);
        } catch (Exception e) {
            logger.error("", e);
        }
        return id;
    }

}
