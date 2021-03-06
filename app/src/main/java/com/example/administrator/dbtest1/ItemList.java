package com.example.administrator.dbtest1;

/**
 * Created by Administrator on 2016-06-10.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ItemList extends AppCompatActivity {
    SQLiteDatabase db;
    ListView listView;
    DBHelper dbHelper;
    BitmapFactory options;
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



        MyAdapter adapter = new MyAdapter(this, R.layout.itemlist, armDrink);
        listView=(ListView)findViewById(R.id.listView2);
        listView.setAdapter(adapter);


    }

}
class MyAdapter extends BaseAdapter {

    Context con;
    int resource;
    ArrayList<mDrink> li;
    LayoutInflater inflater;
    int ct;
    View.OnClickListener oc;
    MyAdapter(Context context, int act, ArrayList<mDrink> ali) {
        con = context;
        ct = act;
        li = ali;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Log.v("생성자확인", "콜");
    }


    @Override
    public int getCount(){
        Log.v("어댑터","카운터값"+li.size());
        return li.size();

    }
    @Override
    public Object getItem(int position){

        return li.get(position).name;
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    //            itemLayout = inflater.inflate(R.layout.item_list_view,null);
//            viewHolder = new ViewHolder();
//            viewHolder.imgVH = (ImageView) itemLayout.findViewById(R.id.imageView2);
//            viewHolder.nameVH = (TextView) itemLayout.findViewById(R.id.tv1);
//            viewHolder.textVH = (TextView) itemLayout.findViewById(R.id.tv2);
//            itemLayout.setTag(viewHolder);
    //ver.3 뷰홀더

    public class ViewHolder{
        ImageView imgVH;
        TextView nameVH;
        TextView textVH;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("확인","겟뷰");
        ViewHolder viewHolder = null;

        if(convertView==null){
            convertView=inflater.inflate(ct,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.imgVH = (ImageView)convertView.findViewById(R.id.imageView2);
            viewHolder.nameVH = (TextView)convertView.findViewById(R.id.tv1);
            viewHolder.textVH = (TextView)convertView.findViewById(R.id.tv2);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        //Bitmap 1/4 resize


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;

        Bitmap selectedImage = BitmapFactory.decodeFile(li.get(position).cImage, options);

        viewHolder.imgVH.setImageBitmap(null);
        viewHolder.nameVH.setText(li.get(position).name);
        viewHolder.textVH.setText(li.get(position).cont);

//            Bitmap resized = Bitmap.createScaledBitmap(selectedImage, selectedImage.getWidth(), selectedImage.getHeight(), true);
        viewHolder.imgVH.setImageBitmap(selectedImage);
        viewHolder.nameVH.setText(li.get(position).name);
        viewHolder.textVH.setText(li.get(position).cont);



        return convertView;
    }


    //ver.2 비트맵 리사이즈
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.v("확인","겟뷰");
//
//        if(convertView==null){
//            convertView=inflater.inflate(ct,parent,false);
//
//        }
//
//        //Bitmap 1/4 resize
//        ImageView imgView=(ImageView)convertView.findViewById(R.id.imageView2);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize=2;
//        Bitmap selectedImage = BitmapFactory.decodeFile(li.get(position).cImage,options);
//        Bitmap resized = Bitmap.createScaledBitmap(selectedImage, selectedImage.getWidth(),selectedImage.getHeight(),true );
//
//
//        imgView.setImageBitmap(resized);
//
//
//        TextView txt1 = (TextView)convertView.findViewById(R.id.tv1);
//        TextView txt2= (TextView)convertView.findViewById(R.id.tv2);
//
//        txt1.setText(li.get(position).name);
//        txt2.setText(li.get(position).cont);
//
//        return convertView;
//    }


    //ver.1
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.v("확인","겟뷰");
//        if(convertView==null){
//            convertView=inflater.inflate(ct, parent, false);
//        }
//        //Bitmap 1/4 resize
//        ImageView imgView=(ImageView)convertView.findViewById(R.id.imageView2);
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize=2;
//        Bitmap selectedImage = BitmapFactory.decodeFile(li.get(position).cImage,options);
//        Bitmap resized = Bitmap.createScaledBitmap(selectedImage, selectedImage.getWidth(),selectedImage.getHeight(),true );
//
//
//        imgView.setImageBitmap(resized);
//
//
//        TextView txt1 = (TextView)convertView.findViewById(R.id.tv1);
//        TextView txt2= (TextView)convertView.findViewById(R.id.tv2);
//
//        txt1.setText(li.get(position).name);
//        txt2.setText(li.get(position).cont);
//
//        return convertView;
//    }

}