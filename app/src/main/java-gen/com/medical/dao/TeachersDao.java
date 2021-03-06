package com.medical.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.medical.greendao.Teachers;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table TEACHERS.
*/
public class TeachersDao extends AbstractDao<Teachers, Long> {

    public static final String TABLENAME = "TEACHERS";

    /**
     * Properties of entity Teachers.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property TeaName = new Property(1, String.class, "teaName", false, "TEA_NAME");
        public final static Property TeaSex = new Property(2, String.class, "teaSex", false, "TEA_SEX");
    };

    private DaoSession daoSession;


    public TeachersDao(DaoConfig config) {
        super(config);
    }
    
    public TeachersDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'TEACHERS' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'TEA_NAME' TEXT," + // 1: teaName
                "'TEA_SEX' TEXT);"); // 2: teaSex
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'TEACHERS'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Teachers entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String teaName = entity.getTeaName();
        if (teaName != null) {
            stmt.bindString(2, teaName);
        }
 
        String teaSex = entity.getTeaSex();
        if (teaSex != null) {
            stmt.bindString(3, teaSex);
        }
    }

    @Override
    protected void attachEntity(Teachers entity) {
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
    public Teachers readEntity(Cursor cursor, int offset) {
        Teachers entity = new Teachers( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // teaName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // teaSex
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Teachers entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTeaName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setTeaSex(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Teachers entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Teachers entity) {
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
