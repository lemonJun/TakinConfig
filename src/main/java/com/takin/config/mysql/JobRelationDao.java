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
//import com.bj58.chr.bigjob.util.CollectionUtil;
//import com.bj58.chr.bigjob.util.GuiceDI;
//import com.bj58.chr.bigjob.util.StringUtil;
//import com.bj58.chr.bigjob.util.ThreadLocalLog;
//import com.google.inject.Singleton;
//
//@Singleton
//public class JobRelationDao {
//
//    private static final Logger logger = LoggerFactory.getLogger(JobRelationDao.class);
//
//    public void insert(JobRelBean jobrel) {
//        try {
//            GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.insert(jobrel);
//            loglog(String.format("save job relation:%s", JSON.toJSONString(jobrel)));
//        } catch (Exception e) {
//            logger.error("", e);
//        }
//    }
//
//    private void loglog(String log) {
//        GuiceDI.getInstance(ThreadLocalLog.class).getLocalsb().get().append(log).append("\n");
//    }
//
//    public JobRelBean getByMvJobID(String mvjobid) {
//        try {
//            StopWatch watch = new StopWatch();
//            watch.start();
//            String sql = "select * from t_mvtochr_job where mvjobid ='" + mvjobid + "'";
//            List<JobRelBean> list = GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.getListBySQL(JobRelBean.class, sql);
//            JobRelBean bean = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
//            loglog(String.format("check mvjob result:%s use:%s sql:%s", list.size(), watch.getTime(), sql));
//            return bean;
//        } catch (Exception e) {
//            logger.error("", e);
//        }
//        return null;
//    }
//
//    public JobRelBean getByMvComJob(String chrcomid, String mvjobid, String jobname, int city) {
//        try {
//            if (StringUtil.isNullOrEmpty(chrcomid)) {
//                loglog(String.format("com not exist formvjob:%s ", mvjobid));
//                return null;
//            }
//            StopWatch watch = new StopWatch();
//            watch.start();
//            String sql = "select * from t_mvtochr_job where chrcomid ='" + chrcomid + "' and city=" + city + " and jobname='" + jobname + "'";
//            List<JobRelBean> list = GuiceDI.getInstance(DBHelper.class).getDAOHelper().sql.getListBySQL(JobRelBean.class, sql);
//            JobRelBean bean = CollectionUtil.isNotEmpty(list) ? list.get(0) : null;
//            loglog(String.format("check mvjob result:%s use:%s sql:%s", list.size(), watch.getTime(), sql));
//            return bean;
//        } catch (Exception e) {
//            logger.error("", e);
//        }
//        return null;
//    }
//
//}
