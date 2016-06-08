package com.example.administrator.dbtest1;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    Cursor cursor;
    Dao dao;
    private String name_str;
    private Button btn1, btn2,btn3;
    private View view1,view2,view3;
    private String ed1,ed2;
    ArrayList<mDrink> armDrink;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper=new DBHelper(this);
        db = openOrCreateDatabase("Drink",MODE_PRIVATE, null);
        btn1=(Button)findViewById(R.id.button);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
//        EditText et1 =(EditText)findViewByID(R.id.editText);   에디트 텍스트 값받아오기
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult( intent, 100 );
            }
        });

        btn1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                EditText et1 =(EditText)findViewById(R.id.editText);
                EditText et2 =(EditText)findViewById(R.id.editText2);
                ed1= et1.getText().toString();
                ed2= et2.getText().toString();
                db=helper.getReadableDatabase();

//                db = openOrCreateDatabase("Drink",MODE_PRIVATE, null);

//                db=helper.getReadableDatabase();

                db.execSQL("INSERT INTO Drink VALUES(null,'"+name_str+"','"+ed1+"','"+ed2+"');" );

//                armDrink = new ArrayList<mDrink>();
//                mDrink mdrink;
//                mdrink = new mDrink("abcdef",ed1,ed2);
//                mDrink mdrink = new mDrink(name_str,ed1,ed2);


//                MyAdapter adapter = new MyAdapter(this, R.layout.itemlist, armDrink);
//                listView=(ListView)findViewById(R.id.listView);
//                listView.setAdapter(adapter);


//                armDrink = new ArrayList<mDrink>();
//                mDrink mdrink;
//                mdrink= new mDrink(name_str,ed1,ed2);

                Intent i = new Intent(MainActivity.this, ItemList.class);
                startActivity(i);
            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //목록조회 버튼
                Intent i = new Intent(MainActivity.this, ItemList.class);
                startActivity(i);
            }





        });

    }

    // 실제 전체 이미지 주소 가져오기
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data )
    {
        super.onActivityResult( requestCode, resultCode, data );

        if( requestCode == 100  )
        {
            if( resultCode == RESULT_OK )
            {
                try
                {
                    name_str = getRealPathFromURI(data.getData());
                    Bitmap image_bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),data.getData());
                    ImageView image = (ImageView)findViewById(R.id.imageView);
                    image.setImageBitmap(image_bitmap);
//                    Bitmap img = MediaStore.Images.Media.getBitmap( getContentResolver(), data.getData() );
//                    ImageView image=(ImageView)findViewById(R.id.imageView);
//                    image.setImageBitmap(img);


                    Log.e("이미지주소:",name_str);
                }
                catch( Exception e )
                {
                    Log.e( "Picture", e.toString( ) );
                }


            }
        }
    }

    public String getRealPathFromURI(Uri contentUri){
        String res= null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri,proj,null,null,null);
        if(cursor.moveToFirst()){
            int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res=cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }


}
//
//class MyAdapter extends BaseAdapter{
//    Context con;
//    int resource;
//    ArrayList<mDrink> li;
//    LayoutInflater inflater;
//    int ct;
//    View.OnClickListener oc;
//    MyAdapter(Context context, int act, ArrayList<mDrink> ali) {
//        con = context;
//        ct = act;
//        li = ali;
//        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        Log.v("생성자확인", "콜");
//    }
//
//
//
//
//    @Override
//    public int getCount(){
//        Log.v("어댑터","카운터값"+li.size());
//        return li.size();
//
//    }
//    @Override
//    public Object getItem(int position){
//
//        return li.get(position).name;
//    }
//
//    @Override
//    public long getItemId(int position){
//        return position;
//    }
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        Log.v("확인","겟뷰");
//        if(convertView==null){
//            convertView=inflater.inflate(ct, parent, false);
//        }
//
////        ImageView img = (ImageView) convertView.findViewById(R.id.imageView);
////        img.setImageResource(li.get(position).cImage);
////
////        TextView txt1 = (TextView)convertView.findViewById(R.id.textView1);
////        txt1.setText(li.get(position).name);
////        TextView txt2= (TextView)convertView.findViewById(R.id.textView2);
////        txt2.setText(li.get(position).cont);
//
//        return convertView;
//    }
//
//}



