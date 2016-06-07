package com.example.administrator.dbtest1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Jiyoon on 2016-06-07.
 */
public class Dao {
    private Context context;
    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private ContentValues row;
    public Dao(Context context){
        this.context=context;
        dbHelper = new DBHelper(context);

    }
    public ArrayList<Contents> getContents(){
        db=dbHelper.getReadableDatabase();
        ArrayList<Contents> contents= new ArrayList<>();
        String img,name,text;
        String sql = "SELECT * FROM Drink;";
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            img=cursor.getString(1);
            name=cursor.getString(2);
            text=cursor.getString(3);

        }
        cursor.close();
        return contents;
    }
}
