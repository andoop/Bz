package cn.bmob.imdemo.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import cn.bmob.imdemo.db.NewFriend;
import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "newfriend".
*/
public class NewFriendDao extends AbstractDao<NewFriend, Long> {

    public static final String TABLENAME = "newfriend";

    /**
     * Properties of entity NewFriend.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Uid = new Property(1, String.class, "uid", false, "UID");
        public final static Property Msg = new Property(2, String.class, "msg", false, "MSG");
        public final static Property Name = new Property(3, String.class, "name", false, "NAME");
        public final static Property Avatar = new Property(4, String.class, "avatar", false, "AVATAR");
        public final static Property Status = new Property(5, Integer.class, "status", false, "STATUS");
        public final static Property Time = new Property(6, Long.class, "time", false, "TIME");
    };


    public NewFriendDao(DaoConfig config) {
        super(config);
    }
    
    public NewFriendDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"newfriend\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"UID\" TEXT," + // 1: uid
                "\"MSG\" TEXT," + // 2: msg
                "\"NAME\" TEXT," + // 3: name
                "\"AVATAR\" TEXT," + // 4: avatar
                "\"STATUS\" INTEGER," + // 5: status
                "\"TIME\" INTEGER);"); // 6: time
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"newfriend\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, NewFriend entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String uid = entity.getUid();
        if (uid != null) {
            stmt.bindString(2, uid);
        }
 
        String msg = entity.getMsg();
        if (msg != null) {
            stmt.bindString(3, msg);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(4, name);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(5, avatar);
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(6, status);
        }
 
        Long time = entity.getTime();
        if (time != null) {
            stmt.bindLong(7, time);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public NewFriend readEntity(Cursor cursor, int offset) {
        NewFriend entity = new NewFriend( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // uid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // msg
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // name
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // avatar
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // status
            cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6) // time
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, NewFriend entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setMsg(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setAvatar(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setStatus(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setTime(cursor.isNull(offset + 6) ? null : cursor.getLong(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(NewFriend entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(NewFriend entity) {
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
