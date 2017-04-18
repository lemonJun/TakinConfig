package com.xxl.conf.mysql;

import java.util.List;

import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xxl.conf.util.CollectionUtil;

/**
 * Created by xuxueli on 16/10/8.
 */
public class ConfGroupDao {

    private static final Logger logger = LoggerFactory.getLogger(ConfGroupDao.class);

    public List<ConfGroup> findAll() {
        try {
            List<ConfGroup> list = BDProvider.getInst().Client().getListByWhere(ConfGroup.class, "*", "", "", "");
            for (ConfGroup group : list) {
                logger.info("group:" + group.getGroupname() + " titel:" + group.getGrouptitle());
            }
            return list;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
        //        return sqlSessionTemplate.selectList("XxlConfGroupMapper.findAll");
    }

    public int save(ConfGroup xxlJobGroup) {
        int id = 0;
        try {
            Object obj = BDProvider.getInst().Client().insert(xxlJobGroup);
            if (obj != null) {
                id = (int) ((long) obj);
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        return id;
    }

    public int update(ConfGroup xxlJobGroup) {
        int id = 0;
        try {
            BDProvider.getInst().Client().upateEntity(xxlJobGroup);
        } catch (Exception e) {
            logger.error("", e);
        }
        return id;
    }

    public int remove(String groupName) {
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            String sql = "delete from XXL_CONF_GROUP where group_name ='" + groupName + "'";
            BDProvider.getInst().Client().deleteByWhere(ConfGroup.class, "group_name ='" + groupName + "'");
        } catch (Exception e) {
            logger.error("", e);
        }
        return 1;
    }

    public ConfGroup load(String groupName) {
        try {
            StopWatch watch = new StopWatch();
            watch.start();
            String sql = "select * from XXL_CONF_GROUP where group_name ='" + groupName + "'";
            List<ConfGroup> list = BDProvider.getInst().Client().getListByWhere(ConfGroup.class, "*", "group_name ='" + groupName + "'", "", "");
            ConfGroup bean = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
            return bean;
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;

    }
}
