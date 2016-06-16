package com.example.administrator.dbtest1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jiyoon on 2016-06-08.
 */
public class ItemList extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    DBHelper dbHelper;
    MyAdapter adapter;
    BitmapFactory options;
    Handler mHandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = openOrCreateDatabase("menu",MODE_PRIVATE, null);
        dbHelper=new DBHelper(this);

        db=dbHelper.getWritableDatabase();
        setContentView(R.layout.item_list_view);

        String img, name, text;
        ArrayList<mDrink> armDrink;
        armDrink = new ArrayList<mDrink>();
        mDrink mdrink;

        String sql="SELECT * FROM Drink order by _id DESC;";
        //DB에서 불러온 데이터를 어댑터로 리스트에 꽂기
        Cursor cursor = db.rawQuery(sql,null);
        while(cursor.moveToNext()){
            img=cursor.getString(1);
            name=cursor.getString(2);
            text=cursor.getString(3);
            mdrink=new mDrink(img,name,text);
            armDrink.add(mdrink);
            Log.e("이미지: ", img);
            Log.e("이름: ", name);
            Log.e("내용: ", text);


        }
        cursor.close();
        adapter = new MyAdapter(this, R.layout.itemlist, armDrink);
        listView=(ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);


    }
//    Thread countThread = new Thread("Count Thread") {
//        public void run() {
//
//                mHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//
//                        listView.setAdapter(adapter);
//
//                    }
//                });
//
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//
//        }
//    };
}


