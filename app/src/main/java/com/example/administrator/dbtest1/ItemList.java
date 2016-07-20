package com.example.administrator.dbtest1;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
    Handler mHandler = new Handler();
    String img, name, text;
    ArrayList<mDrink> armDrink;
    mDrink mdrink;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper=new DBHelper(this);

        db=dbHelper.getWritableDatabase();
        setContentView(R.layout.item_list_view);


        armDrink = new ArrayList<mDrink>();
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
        adapter = new MyAdapter(ItemList.this, R.layout.itemlist, armDrink);
        listView=(ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener listViewClickListener= new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", "아이템클릭, postion : " + position +
                        ", id : " + id);
                String toastMessage = "position is " + position + ", and id is " + id;
                Log.e("토스트:",toastMessage);
                Toast.makeText(getApplicationContext(),toastMessage,Toast.LENGTH_SHORT).show();


                String cName = ((TextView)view.findViewById(R.id.tv1)).getText().toString();
                String cContent=((TextView)view.findViewById(R.id.tv2)).getText().toString();
                Intent intent = new Intent(ItemList.this, ItemView.class);
                String sql="SELECT * FROM Drink order by _id DESC;";
                Cursor c = db.rawQuery(sql,null);
                c.moveToFirst();
                for(int i=0; i<position;i++){
                    c.moveToNext();
                }

                String imgUri=c.getString(1);
                Log.e("imgUri:",imgUri);
//                ByteArrayOutputStream bs = new ByteArrayOutputStream();
//                bm.compress(Bitmap.CompressFormat.PNG, 50, bs);

//                intent.putExtra("byteArray", bs.toByteArray());

                intent.putExtra("img",imgUri);

//                intent.putExtra("img",bm);
                intent.putExtra("name",cName);
                intent.putExtra("content",cContent);

                startActivity(intent);

            }
        };

        listView.setOnItemClickListener(listViewClickListener);


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


