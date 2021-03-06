package com.medical.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.medical.greendao.Students;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table STUDENTS.
*/
public class StudentsDao extends AbstractDao<Students, Long> {

    public static final String TABLENAME = "STUDENTS";

    /**
     * Properties of entity Students.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property StuName = new Property(1, String.class, "stuName", false, "STU_NAME");
        public final static Property StuSex = new Property(2, String.class, "stuSex", false, "STU_SEX");
    };

    private DaoSession daoSession;


    public StudentsDao(DaoConfig config) {
        super(config);
    }
    
    public StudentsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'STUDENTS' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'STU_NAME' TEXT," + // 1: stuName
                "'STU_SEX' TEXT);"); // 2: stuSex
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'STUDENTS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Students entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String stuName = entity.getStuName();
        if (stuName != null) {
            stmt.bindString(2, stuName);
        }
 
        String stuSex = entity.getStuSex();
        if (stuSex != null) {
            stmt.bindString(3, stuSex);
        }
    }

    @Override
    protected void attachEntity(Students entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Students readEntity(Cursor cursor, int offset) {
        Students entity = new Students( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // stuName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // stuSex
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Students entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setStuName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setStuSex(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Students entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Students entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
