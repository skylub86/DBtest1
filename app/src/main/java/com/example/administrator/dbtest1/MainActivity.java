package com.example.administrator.dbtest1;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase db;
    private String name_str;
    private Button btn1, btn2, btn3;
    private String ed1, ed2;
    Handler mHandler = new Handler();
    ListView listView;
    MyAdapter adapter;
//    private DisplayImageOptions options = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this);
        db = openOrCreateDatabase("Drink", MODE_PRIVATE, null);
        db=dbHelper.getWritableDatabase();
        setContentView(R.layout.activity_main);
//        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
//
//            }
//        }


//        this.initImageLoader(getApplicationContext());


        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
//        EditText et1 =(EditText)findViewByID(R.id.editText);   에디트 텍스트 값받아오기




        //사진 불러오기 버튼
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
            }
        });

//        BackThread thread = new BackThread();
//        thread.setDaemon(true);
//        thread.start();
        //등록버튼
        btn1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText et1 = (EditText) findViewById(R.id.editText);
                EditText et2 = (EditText) findViewById(R.id.editText2);
                ed1 = et1.getText().toString();
                ed2 = et2.getText().toString();
                db = dbHelper.getReadableDatabase();

//                db = openOrCreateDatabase("Drink",MODE_PRIVATE, null);

//                db=helper.getReadableDatabase();


                db.execSQL("INSERT INTO Drink VALUES(null,'" + name_str + "','" + ed1 + "','" + ed2 + "');");


//                uri = Uri.fromFile(new File(name_str));       //uri 경로 따기
//                image.setImageURI(uri);

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
        //리스트 조회버튼
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //목록조회 버튼
                Intent i = new Intent(MainActivity.this, ItemList.class);
                startActivity(i);
            }


        });

    }

                /// UIL 사용
//    private void initImageLoader(Context context)
//
//    {
//
//        // This configuration tuning is custom. You can tune every option, you may tune some of them,
//
//        // or you can create default configuration by
//
//        //  ImageLoaderConfiguration.createDefault(this);
//
//        // method.
//
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
//
//                .threadPriority(Thread.NORM_PRIORITY - 2)
//
//                .denyCacheImageMultipleSizesInMemory()
//
//                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
//
//                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
//
//                .tasksProcessingOrder(QueueProcessingType.FIFO)
//
////			.writeDebugLogs() // Remove for release app
//
//                .build();
//
//
//
//        ImageLoader.getInstance().init(config);
//
//    }
//
//
//
//
//    public DisplayImageOptions getDisplayImageOptions()
//
//    {
//
//        if(this.options == null)
//
//        {
//
//            this.options = new DisplayImageOptions.Builder()
//
//                    .showImageOnLoading(R.drawable.no_image)
//
//                    .showImageForEmptyUri(R.drawable.no_detail_img)
//
//                    .showImageOnFail(R.drawable.error_1)
//
////			.resetViewBeforeLoading(false)
//
////			.delayBeforeLoading(1000)
//
////			.preProcessor(...)
//
////			.postProcessor(...)
//
////			.extraForDownloader(...)
//
////			.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
//
////			.bitmapConfig(Bitmap.Config.ARGB_8888)
//
////			.decodingOptions(...)
//
////			.displayer(new SimpleBitmapDisplayer())
//
////			.handler(new Handler())
//
//                    .cacheInMemory(true)
//
//                    .cacheOnDisk(true)
//
//                    .considerExifParams(true)
//
//                    .build();
//
//        }
//
//
//
//        return this.options;
//
//    }
//
//
//
//    public void removeFromCache(String imageUri)
//
//    {
//
//        DiskCacheUtils.removeFromCache(imageUri, ImageLoader.getInstance().getDiskCache());
//
//
//
//        MemoryCacheUtils.removeFromCache(imageUri, ImageLoader.getInstance().getMemoryCache());
//
//    }



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



