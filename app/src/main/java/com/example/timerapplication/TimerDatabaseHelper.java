package com.example.timerapplication;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class TimerDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "timerHistory.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "history";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DURATION = "duration";
    private static final String COLUMN_END_TIME = "end_time";

    public TimerDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_DURATION + " TEXT, "
                + COLUMN_END_TIME + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addTimer(String duration, String endTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DURATION, duration);
        values.put(COLUMN_END_TIME, endTime);
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<TimerModel> getAllTimers() {
        List<TimerModel> timerList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COLUMN_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                String duration = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DURATION));
                String endTime = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_END_TIME));
                timerList.add(new TimerModel(duration, endTime));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return timerList;
    }
}

