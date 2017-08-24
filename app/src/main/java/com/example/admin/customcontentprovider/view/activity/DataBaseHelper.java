package com.example.admin.customcontentprovider.view.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

/**
 * Created by Admin on 8/23/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "DataBase";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "Cars";
    public static final String CONTACT_ID = "_id";
    public static final String CONTACT_MODEL = "Model";
    public static final String CONTACT_BRAND ="Brand";
    public static final String CONTACT_YEAR ="Year";
    public static final String CONTACT_COLOR ="Color";
    public static final String CONTACT_DESCRIPTION ="Description";
    public static final String TAG = "SQLTAG";
    String[] args = null;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("+
                CONTACT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CONTACT_MODEL + " TEXT," +
                CONTACT_BRAND + " TEXT," +
                CONTACT_YEAR + " INTEGER," +
                CONTACT_COLOR + " TEXT,"+
                CONTACT_DESCRIPTION + " TEXT "+
                ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public Cursor getCarsSql(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder sqliteQueryBuilder = new SQLiteQueryBuilder();
        sqliteQueryBuilder.setTables(TABLE_NAME);

        if(id != null) {
            sqliteQueryBuilder.appendWhere(CONTACT_ID + " = " + id);
        }

        if(sortOrder == null || sortOrder == "") {
            sortOrder = CONTACT_MODEL;
        }
        Cursor cursor = sqliteQueryBuilder.query(getReadableDatabase(),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);
        return cursor;
    }
    public long addNewImage(ContentValues values) throws SQLException {
        long id = getWritableDatabase().insert(TABLE_NAME, "", values);
        if(id <=0 ) {
            throw new SQLException("Failed to add an image");
        }

        return id;
    }
}
