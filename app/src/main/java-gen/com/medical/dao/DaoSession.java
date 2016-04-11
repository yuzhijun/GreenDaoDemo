package com.medical.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.AbstractDaoSession;
import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

import com.medical.greendao.Users;
import com.medical.greendao.infoType;
import com.medical.greendao.infos;
import com.medical.greendao.Areas;
import com.medical.greendao.Students;
import com.medical.greendao.Teachers;
import com.medical.greendao.stuAndtea;

import com.medical.dao.UsersDao;
import com.medical.dao.infoTypeDao;
import com.medical.dao.infosDao;
import com.medical.dao.AreasDao;
import com.medical.dao.StudentsDao;
import com.medical.dao.TeachersDao;
import com.medical.dao.stuAndteaDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig usersDaoConfig;
    private final DaoConfig infoTypeDaoConfig;
    private final DaoConfig infosDaoConfig;
    private final DaoConfig areasDaoConfig;
    private final DaoConfig studentsDaoConfig;
    private final DaoConfig teachersDaoConfig;
    private final DaoConfig stuAndteaDaoConfig;

    private final UsersDao usersDao;
    private final infoTypeDao infoTypeDao;
    private final infosDao infosDao;
    private final AreasDao areasDao;
    private final StudentsDao studentsDao;
    private final TeachersDao teachersDao;
    private final stuAndteaDao stuAndteaDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        usersDaoConfig = daoConfigMap.get(UsersDao.class).clone();
        usersDaoConfig.initIdentityScope(type);

        infoTypeDaoConfig = daoConfigMap.get(infoTypeDao.class).clone();
        infoTypeDaoConfig.initIdentityScope(type);

        infosDaoConfig = daoConfigMap.get(infosDao.class).clone();
        infosDaoConfig.initIdentityScope(type);

        areasDaoConfig = daoConfigMap.get(AreasDao.class).clone();
        areasDaoConfig.initIdentityScope(type);

        studentsDaoConfig = daoConfigMap.get(StudentsDao.class).clone();
        studentsDaoConfig.initIdentityScope(type);

        teachersDaoConfig = daoConfigMap.get(TeachersDao.class).clone();
        teachersDaoConfig.initIdentityScope(type);

        stuAndteaDaoConfig = daoConfigMap.get(stuAndteaDao.class).clone();
        stuAndteaDaoConfig.initIdentityScope(type);

        usersDao = new UsersDao(usersDaoConfig, this);
        infoTypeDao = new infoTypeDao(infoTypeDaoConfig, this);
        infosDao = new infosDao(infosDaoConfig, this);
        areasDao = new AreasDao(areasDaoConfig, this);
        studentsDao = new StudentsDao(studentsDaoConfig, this);
        teachersDao = new TeachersDao(teachersDaoConfig, this);
        stuAndteaDao = new stuAndteaDao(stuAndteaDaoConfig, this);

        registerDao(Users.class, usersDao);
        registerDao(infoType.class, infoTypeDao);
        registerDao(infos.class, infosDao);
        registerDao(Areas.class, areasDao);
        registerDao(Students.class, studentsDao);
        registerDao(Teachers.class, teachersDao);
        registerDao(stuAndtea.class, stuAndteaDao);
    }
    
    public void clear() {
        usersDaoConfig.getIdentityScope().clear();
        infoTypeDaoConfig.getIdentityScope().clear();
        infosDaoConfig.getIdentityScope().clear();
        areasDaoConfig.getIdentityScope().clear();
        studentsDaoConfig.getIdentityScope().clear();
        teachersDaoConfig.getIdentityScope().clear();
        stuAndteaDaoConfig.getIdentityScope().clear();
    }

    public UsersDao getUsersDao() {
        return usersDao;
    }

    public infoTypeDao getInfoTypeDao() {
        return infoTypeDao;
    }

    public infosDao getInfosDao() {
        return infosDao;
    }

    public AreasDao getAreasDao() {
        return areasDao;
    }

    public StudentsDao getStudentsDao() {
        return studentsDao;
    }

    public TeachersDao getTeachersDao() {
        return teachersDao;
    }

    public stuAndteaDao getStuAndteaDao() {
        return stuAndteaDao;
    }

}
