package com.takin.config.mysql;

import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.takin.emmet.collection.CollectionUtil;

/**
 * 配置
 * @author xuxueli
 */
public class ConfNodeDao {

    private static final Logger logger = LoggerFactory.getLogger(ConfNodeDao.class);

    public List<ConfNode> pageList(int offset, int pagesize, String nodeGroup, String nodeKey) {
        try {
            List<ConfNode> list = BDProvider.getInst().Client().pageListByWhere(ConfNode.class, "", "", offset, pagesize, "");
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
            BDProvider.getInst().Client().deleteByWhere(ConfNode.class, "node_group ='" + nodeGroup + "' and node_key='" + nodeKey + "'");
        } catch (Exception e) {
            logger.error("", e);
        }
        return 1;
    }

    public void insert(ConfNode node) {
        try {
            BDProvider.getInst().Client().insert(node);
        } catch (Exception e) {
            logger.error("", e);
        }
    }

    public ConfNode selectByKey(String nodeGroup, String nodeKey) {
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            List<ConfNode> list = BDProvider.getInst().Client().getListByWhere(ConfNode.class, "*", "", "", "");
            ConfNode bean = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
            return bean;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    public int update(ConfNode node) {
        int id = 0;
        try {
            BDProvider.getInst().Client().upateEntity(node);
        } catch (Exception e) {
            logger.error("", e);
        }
        return id;
    }

}
