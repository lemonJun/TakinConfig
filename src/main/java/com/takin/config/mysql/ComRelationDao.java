package com.takin.config.mysql;
//package com.xxl.conf.mysql;
//
//import java.util.List;
//
//import org.apache.commons.lang.time.StopWatch;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.alibaba.fastjson.JSON;
//import com.google.inject.Singleton;
//import com.xxl.conf.util.CollectionUtil;
//import com.xxl.conf.util.GuiceDI;
//
//@Singleton
//public class ComRelationDao {
//
//    private static final Logger logger = LoggerFactory.getLogger(ComRelationDao.class);
//
//    public void insert(ComRelBean comrel) {
//        try {
//            GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.insert(comrel);
//            loglog(String.format("save com relation:%s", JSON.toJSONString(comrel)));
//        } catch (Exception e) {
//            logger.error("", e);
//        }
//    }
//
//    private void loglog(String log) {
//    }
//
//    public ComRelBean isComExist(String mvcomid, String comname) {
//        ComRelBean bean = getByMVComID(mvcomid);
//        if (bean != null) {
//            return bean;
//        }
//        bean = getByComName(comname);
//        return bean;
//    }
//
//    public ComRelBean getByMVComID(String mvcomid) {
//        try {
//            StopWatch watch = new StopWatch();
//            watch.start();
//            String sql = "select * from t_mvtochr_com where mvcomid ='" + mvcomid + "'";
//            List<ComRelBean> list = GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.getListBySQL(ComRelBean.class, sql);
//            ComRelBean bean = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
//            loglog(String.format("check mvcom result:%s use:%s sql:%s", list.size(), watch.getTime(), sql));
//            return bean;
//        } catch (Exception e) {
//            logger.error("", e);
//        }
//        return null;
//    }
//
//    //一个来源中会存在多个企业名称相同的
//    public ComRelBean getByComName(String comname) {
//        try {
//            StopWatch watch = new StopWatch();
//            watch.start();
//            String sql = "select * from t_mvtochr_com where comname ='" + comname + "'";
//            List<ComRelBean> list = GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.getListBySQL(ComRelBean.class, sql);
//            ComRelBean bean = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
//            loglog(String.format("check mvcom result:%s use:%s sql:%s", list.size(), watch.getTime(), sql));
//            return bean;
//        } catch (Exception e) {
//            logger.error("", e);
//        }
//        return null;
//    }
//
//}
