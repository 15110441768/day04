package com.example.lenovo.day04.util;

import com.example.lenovo.day04.base.BaseApp;
import com.example.lenovo.day04.ui.zhihu.bean.DailyNewsArticleBean;
import com.example.lenovo.day04.ui.zhihu.dao.DailyNewsArticleBeanDao;
import com.example.lenovo.day04.ui.zhihu.dao.DaoMaster;
import com.example.lenovo.day04.ui.zhihu.dao.DaoSession;

import java.util.List;

public class DbUtil {

    private final DailyNewsArticleBeanDao dailyNewsArticleBeanDao;

    private DbUtil() {
        //1.创建数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(BaseApp.getInstance(), "com.example.lenovo.day04.db");

        //2.设置读写权限
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDatabase());

        //3.获取管理器类
        DaoSession daoSession = daoMaster.newSession();

        //4.获取dao操作对象
        dailyNewsArticleBeanDao = daoSession.getDailyNewsArticleBeanDao();
    }

    private static DbUtil dbUtil;

    public static DbUtil getDbUtil() {
        if (dbUtil == null) {
            synchronized (DbUtil.class) {
                if (dbUtil == null) {
                    dbUtil = new DbUtil();
                }
            }
        }
        return dbUtil;
    }

    public void insert(DailyNewsArticleBean dailyNewsArticleBean){
        DailyNewsArticleBean queryOne = queryOne(dailyNewsArticleBean.getTitle());
        if (queryOne==null){
            dailyNewsArticleBeanDao.insert(dailyNewsArticleBean);
        }
    }

    public void delete(DailyNewsArticleBean dailyNewsArticleBean){
        dailyNewsArticleBeanDao.delete(dailyNewsArticleBean);
    }

    public DailyNewsArticleBean queryOne(String title){
        DailyNewsArticleBean dailyNewsArticleBean = dailyNewsArticleBeanDao.queryBuilder()
                .where(DailyNewsArticleBeanDao.Properties.Title.eq(title))
                .build()
                .unique();
        return dailyNewsArticleBean;
    }

    public List<DailyNewsArticleBean> queryAll(){
        List<DailyNewsArticleBean> dailyNewsArticleBeans = dailyNewsArticleBeanDao.loadAll();
        return dailyNewsArticleBeans;
    }
}
