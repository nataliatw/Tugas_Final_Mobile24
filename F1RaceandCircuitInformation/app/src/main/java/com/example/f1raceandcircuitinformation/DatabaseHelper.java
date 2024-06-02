package com.example.f1raceandcircuitinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "F1Database.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_HISTORY = "history";
    private static final String COLUMN_HISTORY_ID = "history_id";
    private static final String COLUMN_ITEM_ID = "item_id";
    private static final String COLUMN_ITEM_TYPE = "item_type";
    private static final String COLUMN_LAST_SEEN = "last_seen";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Metode getter untuk COLUMN_HISTORY_ID
    public static String getColumnHistoryId() {
        return COLUMN_HISTORY_ID;
    }

    // Metode getter untuk COLUMN_ITEM_ID
    public static String getColumnItemId() {
        return COLUMN_ITEM_ID;
    }

    // Metode getter untuk COLUMN_ITEM_TYPE
    public static String getColumnItemType() {
        return COLUMN_ITEM_TYPE;
    }

    // Metode getter untuk COLUMN_LAST_SEEN
    public static String getColumnLastSeen() {
        return COLUMN_LAST_SEEN;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createHistoryTable = "CREATE TABLE " + TABLE_HISTORY + "("
                + COLUMN_HISTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_ITEM_ID + " TEXT,"
                + COLUMN_ITEM_TYPE + " TEXT,"
                + COLUMN_LAST_SEEN + " TEXT" + ")";
        db.execSQL(createHistoryTable);
    }

    public void insertHistory(String itemId, String itemType, String lastSeen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_ID, itemId);
        values.put(COLUMN_ITEM_TYPE, itemType);
        values.put(COLUMN_LAST_SEEN, lastSeen);
        db.insert(TABLE_HISTORY, null, values);
    }

    public Cursor getAllHistory() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_HISTORY, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HISTORY);
        onCreate(db);
    }
}
