package com.valkyria.trebusz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Kensaj on 20.10.2017.
 */

public class TrebuszDatabaseAdapter {
    private static final String DEBUG_TAG = "SQLitleDatabaseAdapter";


    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database.db";
    private static final String DB_MAIN_TABLE = "TrebuszPlany";


    public static final String KEY_ID = "_id";
    public static final String ID_OPTIONS = "INTEGER PRIMARY KEY AUTOINCREMENT";
    public static final int ID_COLUMN = 0;

    public static final String KEY_DATE = "date";
    public static final String DATE_OPTIONS = "TEXT NOT NULL";
    public static final int DATE_COLUMN = 1;

    public static final String KEY_DAY = "day";
    public static final String DAY_OPTIONS = "TEXT NOT NULL";
    public static final int DAY_COLUMN = 2;

    public static final String KEY_HOUR = "hour";
    public static final String HOUR_OPTIONS = "TEXT NOT NULL";
    public static final int HOUR_COLUMN = 3;

    public static final String KEY_SUBJECT = "subject";
    public static final String SUBJECT_OPTIONS = "TEXT NOT NULL";
    public static final int SUBJECT_COLUMN = 4;

    public static final String KEY_TYPE = "type";
    public static final String TYPE_OPTIONS = "TEXT NOT NULL";
    public static final int TYPE_COLUMN = 5;

    public static final String KEY_TEACHER = "teacher";
    public static final String TEACHER_OPTIONS = "TEXT NOT NULL";
    public static final int TEACHER_COLUMN = 6;


    public static final String KEY_HALL = "hall";
    public static final String HALL_OPTIONS = "TEXT NOT NULL";
    public static final int HALL_COLUMN = 7;


    private static final String DB_CREATE_MAIN_TABLE =
            "CREATE TABLE " + DB_MAIN_TABLE + "( " +
                    KEY_ID + " " + ID_OPTIONS + ", " +
                    KEY_DATE + " " + DATE_OPTIONS + ", " +
                    KEY_DAY + " " + DAY_OPTIONS + ", " +
                    KEY_HOUR + " " + HOUR_OPTIONS + ", " +
                    KEY_SUBJECT + " " + SUBJECT_OPTIONS + ", " +
                    KEY_TYPE + " " + TYPE_OPTIONS + ", " +
                    KEY_TEACHER + " " + TEACHER_OPTIONS + ", " +
                    KEY_HALL + " " + HALL_OPTIONS +
                    ");";

    private static final String DROP_MAIN_TABLE =
            "DROP TABLE IF EXISTS " + DB_MAIN_TABLE;

    private SQLiteDatabase db;
    private Context context;
    private DatabaseHelper dbHelper;

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE_MAIN_TABLE);

            Log.d(DEBUG_TAG, "Database creating...");
            Log.d(DEBUG_TAG, "Table " + DB_MAIN_TABLE + " ver." + DB_VERSION + " created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(DROP_MAIN_TABLE);

            Log.d(DEBUG_TAG, "Database updating...");
            Log.d(DEBUG_TAG, "Table " + DB_MAIN_TABLE + " updated from ver." + oldVersion + " to ver." + newVersion);
            Log.d(DEBUG_TAG, "All data is lost.");

            onCreate(db);
        }
    }

    public TrebuszDatabaseAdapter(Context context) {
        this.context = context;
    }

    public TrebuszDatabaseAdapter open() {
        dbHelper = new DatabaseHelper(context, DB_NAME, null, DB_VERSION);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLException e) {
            db = dbHelper.getReadableDatabase();
        }
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public long insertTrebuszDatabase(String date, String day, String hour, String hall, String teacher, String subject, String type) {
        ContentValues newMainValues = new ContentValues();
        newMainValues.put(KEY_DATE, date);
        newMainValues.put(KEY_DAY, day);
        newMainValues.put(KEY_HOUR, hour);
        newMainValues.put(KEY_HALL, hall);
        newMainValues.put(KEY_SUBJECT, subject);
        newMainValues.put(KEY_TYPE, type);
        newMainValues.put(KEY_TEACHER, teacher);
        return db.insert(DB_MAIN_TABLE, null, newMainValues);
    }

    public boolean updateTrebuszDatabase(DatabaseInterface record) {
        long id = record.getId();
        String date = record.getDate();
        String day = record.getDay();

        return updateTrebuszDatabase(id, date, day);
    }

    public boolean updateTrebuszDatabase(long id, String date, String day) {
        String where = KEY_ID + "=" + id;
        ContentValues updateTodoValues = new ContentValues();
        updateTodoValues.put(KEY_DATE, date);
        updateTodoValues.put(KEY_DAY, day);
        return db.update(DB_MAIN_TABLE, updateTodoValues, where, null) > 0;
    }

    public boolean deleteTrebuszDatabase(long id) {
        String where = KEY_ID + "=" + id;
        return db.delete(DB_MAIN_TABLE, where, null) > 0;
    }


    public Cursor getAllRecords() {
        String[] columns = {KEY_ID, KEY_DATE, KEY_DAY, KEY_HOUR, KEY_SUBJECT, KEY_TYPE, KEY_TEACHER, KEY_HALL};
        return db.query(DB_MAIN_TABLE, columns, null, null, null, null, null);
    }

    public DatabaseInterface getMainDatabase(long id) {
        String[] columns = {KEY_ID, KEY_DATE, KEY_DAY, KEY_HOUR, KEY_SUBJECT, KEY_TYPE, KEY_TEACHER, KEY_HALL};
        String where = KEY_ID + "=" + id;
        Cursor cursor = db.query(DB_MAIN_TABLE, columns, where, null, null, null, null);
        DatabaseInterface record = null;
        if (cursor != null && cursor.moveToFirst()) {
            String date = cursor.getString(DATE_COLUMN);
            String day = cursor.getString(DAY_COLUMN);
            String hour = cursor.getString(HOUR_COLUMN);
            String subject = cursor.getString(SUBJECT_COLUMN);
            String type = cursor.getString(TYPE_COLUMN);
            String teacher = cursor.getString(TEACHER_COLUMN);
            String hall = cursor.getString(HALL_COLUMN);


            record = new DatabaseInterface(id, date, day, hour, subject, type, teacher, hall);
        }
        return record;
    }


}
