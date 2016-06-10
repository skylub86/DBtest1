package com.example.administrator.dbtest1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

/**
 * Created by Jiyoon on 2016-06-01.
 */

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "test2.db", null, 1);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE Drink(_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"Image TEXT, name TEXT, price INTEGER);");

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Drink");
        onCreate(db);
    }
    public void addDrink(mDrink mdrink){
        ContentValues values = new ContentValues();
    }
}
