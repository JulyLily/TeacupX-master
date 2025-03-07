package com.lily.teacupx.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.lily.teacupx.db.StarBeanInfo;

import com.lily.teacupx.db.StarBeanInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig starBeanInfoDaoConfig;

    private final StarBeanInfoDao starBeanInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        starBeanInfoDaoConfig = daoConfigMap.get(StarBeanInfoDao.class).clone();
        starBeanInfoDaoConfig.initIdentityScope(type);

        starBeanInfoDao = new StarBeanInfoDao(starBeanInfoDaoConfig, this);

        registerDao(StarBeanInfo.class, starBeanInfoDao);
    }
    
    public void clear() {
        starBeanInfoDaoConfig.clearIdentityScope();
    }

    public StarBeanInfoDao getStarBeanInfoDao() {
        return starBeanInfoDao;
    }

}
