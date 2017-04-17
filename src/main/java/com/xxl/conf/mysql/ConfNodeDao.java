package com.xxl.conf.mysql;

import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lemonjun.mysql.orm.BDProvider;
import com.xxl.conf.util.CollectionUtil;
import com.xxl.conf.util.GuiceDI;

/**
 * 配置
 * @author xuxueli
 */
public class ConfNodeDao {

    private static final Logger logger = LoggerFactory.getLogger(ConfNodeDao.class);

    public List<ConfNode> pageList(int offset, int pagesize, String nodeGroup, String nodeKey) {
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            String sql = "select * from XXL_CONF_NODE ";
            List<ConfNode> list = BDProvider.getInst().Client().pageListByWhere(ConfNode.class, "", "", offset, pagesize, "", 5000);
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
            //            GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.execBySQL(sql);
        } catch (Exception e) {
            logger.error("", e);
        }
        return 1;
    }

    public void insert(ConfNode node) {
        try {
            //            Object obj = GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.insert(node);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public ConfNode selectByKey(String nodeGroup, String nodeKey) {
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            String sql = "select * from XXL_CONF_NODE where node_group ='" + nodeGroup + "' and node_key='" + nodeKey + "'";
            //            List<ConfNode> list = GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.getListBySQL(ConfNode.class, sql);
            //            ConfNode bean = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
            return null;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public int update(ConfNode node) {
        int id = 0;
        try {
            //            GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.upateEntity(node);
        } catch (Exception e) {
            logger.error("", e);
        }
        return id;
    }

}
