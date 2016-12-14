package com.example.hirurg.contacts;

/**
 * Created by hirurg on 05.11.16.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {

    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FN = "firstName";
    public static final String COLUMN_LN = "lastName";
    public static final String COLUMN_PHONE = "phoneNumber";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_BIRTH = "birthdate";
    public static final String COLUMN_SN = "socialNetworkId";
    public static final String COLUMN_IMAGE_PATH = "imagePath";
    public static final String COLUMN_IMAGE_ID = "inageId";

    private static final String DB_CREATE = "create table " + TABLE_CONTACTS
            + "(" + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_FN + " text not null, " + COLUMN_LN + " text not null, "
            + COLUMN_PHONE + " text, " + COLUMN_EMAIL + " text, "
            + COLUMN_BIRTH + " text, " + COLUMN_SN + " text, "
            + COLUMN_IMAGE_PATH + " text, " + COLUMN_IMAGE_ID + " integer);";

    private final Context mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context ctx) {
        mCtx = ctx;
    }

    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper != null)
            mDBHelper.close();
    }

    public Cursor getAllData() {
        String orderByColumn = Properties.ORDER_BY == Properties.FIRST_NAME ? COLUMN_FN : COLUMN_LN;
        return mDB.query(TABLE_CONTACTS, null, null, null, null, null, orderByColumn);
    }

    public Cursor getLine(long row_id) {
        return mDB.rawQuery("SELECT * FROM 'contacts' WHERE _id = ?",
                new String[] { String.valueOf(row_id) });
    }

    public void addOrUpd(Boolean update, String fn, String ln, String phone,
                         String email, String birthdate, String social, String imagePath,
                         long imageID, long row_id) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FN, fn);
        cv.put(COLUMN_LN, ln);
        cv.put(COLUMN_PHONE, phone);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_BIRTH, birthdate);
        cv.put(COLUMN_SN, social);
        cv.put(COLUMN_IMAGE_PATH, imagePath);
        cv.put(COLUMN_IMAGE_ID, imageID);
        if (update) {
            mDB.update(TABLE_CONTACTS, cv, "_id = ?",
                    new String[] { String.valueOf(row_id) });
        } else {
            mDB.insert(TABLE_CONTACTS, null, cv);
        }
    }

    public Cursor getAgregatedData(String[] query) {
        return mDB.query(TABLE_CONTACTS, query, null, null, null, null, null);
    }

    public void delRec(long id) {
        mDB.delete(TABLE_CONTACTS, COLUMN_ID + " = " + id, null);
    }

    public void deleteAll(){
        mDB.delete(TABLE_CONTACTS, null, null);
    }

    public Cursor findRec(String query) {
        return mDB.query(true, TABLE_CONTACTS, new String[] { COLUMN_FN,
                        COLUMN_LN }, COLUMN_FN + " LIKE" + "'%" + query + "%'", null,
                null, null, null, null);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}

