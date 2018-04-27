package example.com.fenlei.db;

import android.content.Context;

/**
 * Created by lenovo on 2018/4/20.
 */

public class DBManager {
    private static volatile DBManager instance;
    private static final String DB_NAME = "complex.db";
    private final DaoSession daoSession;


    private DBManager(Context context) {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public static DBManager getInstance(Context context) {
        if (instance == null) {
            synchronized (DBManager.class) {
                if (null == instance) {
                    instance = new DBManager(context);
                }
            }
        }
        return instance;
    }

    public LeftCategoryDao getLeftCategoryDao() {
        return daoSession.getLeftCategoryDao();
    }

    public RightCategoryDao getRightCategoryDao() {
        return daoSession.getRightCategoryDao();
    }

    public SecondProductDao getSecondProductDao() {
        return daoSession.getSecondProductDao();
    }
}
