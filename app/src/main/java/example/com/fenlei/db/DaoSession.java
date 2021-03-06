package example.com.fenlei.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import example.com.fenlei.utils.LeftCategory;
import example.com.fenlei.utils.RightCategory;
import example.com.fenlei.utils.SecondProduct;

import example.com.fenlei.db.LeftCategoryDao;
import example.com.fenlei.db.RightCategoryDao;
import example.com.fenlei.db.SecondProductDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig leftCategoryDaoConfig;
    private final DaoConfig rightCategoryDaoConfig;
    private final DaoConfig secondProductDaoConfig;

    private final LeftCategoryDao leftCategoryDao;
    private final RightCategoryDao rightCategoryDao;
    private final SecondProductDao secondProductDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        leftCategoryDaoConfig = daoConfigMap.get(LeftCategoryDao.class).clone();
        leftCategoryDaoConfig.initIdentityScope(type);

        rightCategoryDaoConfig = daoConfigMap.get(RightCategoryDao.class).clone();
        rightCategoryDaoConfig.initIdentityScope(type);

        secondProductDaoConfig = daoConfigMap.get(SecondProductDao.class).clone();
        secondProductDaoConfig.initIdentityScope(type);

        leftCategoryDao = new LeftCategoryDao(leftCategoryDaoConfig, this);
        rightCategoryDao = new RightCategoryDao(rightCategoryDaoConfig, this);
        secondProductDao = new SecondProductDao(secondProductDaoConfig, this);

        registerDao(LeftCategory.class, leftCategoryDao);
        registerDao(RightCategory.class, rightCategoryDao);
        registerDao(SecondProduct.class, secondProductDao);
    }
    
    public void clear() {
        leftCategoryDaoConfig.clearIdentityScope();
        rightCategoryDaoConfig.clearIdentityScope();
        secondProductDaoConfig.clearIdentityScope();
    }

    public LeftCategoryDao getLeftCategoryDao() {
        return leftCategoryDao;
    }

    public RightCategoryDao getRightCategoryDao() {
        return rightCategoryDao;
    }

    public SecondProductDao getSecondProductDao() {
        return secondProductDao;
    }

}
