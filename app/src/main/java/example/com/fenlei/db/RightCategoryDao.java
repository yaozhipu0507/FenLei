package example.com.fenlei.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import example.com.fenlei.utils.RightCategory;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "RIGHT_CATEGORY".
*/
public class RightCategoryDao extends AbstractDao<RightCategory, Long> {

    public static final String TABLENAME = "RIGHT_CATEGORY";

    /**
     * Properties of entity RightCategory.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Cid = new Property(0, String.class, "cid", false, "CID");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Pcid = new Property(2, Long.class, "pcid", true, "_id");
    }


    public RightCategoryDao(DaoConfig config) {
        super(config);
    }
    
    public RightCategoryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"RIGHT_CATEGORY\" (" + //
                "\"CID\" TEXT," + // 0: cid
                "\"NAME\" TEXT," + // 1: name
                "\"_id\" INTEGER PRIMARY KEY );"); // 2: pcid
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"RIGHT_CATEGORY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, RightCategory entity) {
        stmt.clearBindings();
 
        String cid = entity.getCid();
        if (cid != null) {
            stmt.bindString(1, cid);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Long pcid = entity.getPcid();
        if (pcid != null) {
            stmt.bindLong(3, pcid);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, RightCategory entity) {
        stmt.clearBindings();
 
        String cid = entity.getCid();
        if (cid != null) {
            stmt.bindString(1, cid);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        Long pcid = entity.getPcid();
        if (pcid != null) {
            stmt.bindLong(3, pcid);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2);
    }    

    @Override
    public RightCategory readEntity(Cursor cursor, int offset) {
        RightCategory entity = new RightCategory( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // cid
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // pcid
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, RightCategory entity, int offset) {
        entity.setCid(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPcid(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(RightCategory entity, long rowId) {
        entity.setPcid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(RightCategory entity) {
        if(entity != null) {
            return entity.getPcid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(RightCategory entity) {
        return entity.getPcid() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}