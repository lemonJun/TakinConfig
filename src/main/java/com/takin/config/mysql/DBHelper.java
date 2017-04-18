package com.takin.config.mysql;
//package com.xxl.conf.mysql;
//
//import java.io.File;
//
//import org.apache.log4j.Logger;
//
//import com.google.inject.Singleton;
//import com.takin.mvc.mvc.WF;
//
///**
// * 这个类应该是给调用方使用的 不应该包含在这个客户端中
// *
// * @author WangYazhou
// * @date 2016年6月8日 上午10:59:19
// * @see
// */
//@Singleton
//public class DBHelper {
//
//    private static final Logger logger = Logger.getLogger(DBHelper.class);
//
//    private DAOHelper daoHelper = null;
//
//    public DBHelper() {
//        try {
//            String dbpath = WF.getConfigFolder() + WF.getNamespace() + File.separator + "db.properties";
//            logger.info(dbpath);
//            daoHelper = DAOBase.createIntrance(dbpath);
//            logger.info("");
//        } catch (Exception e) {
//            logger.error("init db error", e);
//        }
//    }
//
//    public DAOHelper getDAOHelper() {
//        return daoHelper;
//    }
//
//}
