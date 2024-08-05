package com.acem.bca6thsem.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.acem.bca6thsem.data.MyData;

public class MyDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todoDb";

    public MyDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE mytable(id INTEGER PRIMARY KEY, name TEXT, address TEXT)";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }

    // Insert data
    public void insertData(MyData data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", data.getId());
        contentValues.put("name", data.getName());
        contentValues.put("address", data.getAddress());

        db.insert("mytable", null, contentValues);
        db.close();
    }

    // Read data
    public Cursor selectData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM mytable";
        return db.rawQuery(query, null);
    }

    // Delete data
    public void deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("mytable", "id=?", new String[]{id});
    }

    // Update data
    public void updateData(MyData data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", data.getName());
        contentValues.put("address", data.getAddress());

        db.update("mytable", contentValues, "id=?", new String[]{String.valueOf(data.getId())});
        db.close();
    }
}
