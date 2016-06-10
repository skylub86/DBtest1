package com.example.administrator.dbtest1;

/**
 * Created by Jiyoon on 2016-06-01.
 */
public class mDrink {
    int cImg;
    String cImage;
    String name;
    String cont;

    mDrink(String acImage, String aname, String acont){
        cImage = acImage;
        name=aname;
        cont=acont;

    }
    mDrink(int acImg, String aname, String acont){
        cImg=acImg;
        name=aname;
        cont=acont;
    }


}
