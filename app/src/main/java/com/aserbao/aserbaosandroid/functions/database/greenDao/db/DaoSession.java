package com.aserbao.aserbaosandroid.functions.database.greenDao.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.benq.demofashion.model.Student;
import com.benq.demofashion.model.IdCard;
import com.benq.demofashion.model.CreditCard;

import com.aserbao.aserbaosandroid.functions.database.greenDao.db.StudentDao;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.IdCardDao;
import com.aserbao.aserbaosandroid.functions.database.greenDao.db.CreditCardDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig studentDaoConfig;
    private final DaoConfig idCardDaoConfig;
    private final DaoConfig creditCardDaoConfig;

    private final StudentDao studentDao;
    private final IdCardDao idCardDao;
    private final CreditCardDao creditCardDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        studentDaoConfig = daoConfigMap.get(StudentDao.class).clone();
        studentDaoConfig.initIdentityScope(type);

        idCardDaoConfig = daoConfigMap.get(IdCardDao.class).clone();
        idCardDaoConfig.initIdentityScope(type);

        creditCardDaoConfig = daoConfigMap.get(CreditCardDao.class).clone();
        creditCardDaoConfig.initIdentityScope(type);

        studentDao = new StudentDao(studentDaoConfig, this);
        idCardDao = new IdCardDao(idCardDaoConfig, this);
        creditCardDao = new CreditCardDao(creditCardDaoConfig, this);

        registerDao(Student.class, studentDao);
        registerDao(IdCard.class, idCardDao);
        registerDao(CreditCard.class, creditCardDao);
    }
    
    public void clear() {
        studentDaoConfig.clearIdentityScope();
        idCardDaoConfig.clearIdentityScope();
        creditCardDaoConfig.clearIdentityScope();
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public IdCardDao getIdCardDao() {
        return idCardDao;
    }

    public CreditCardDao getCreditCardDao() {
        return creditCardDao;
    }

}
