package com.sebastien.granarolo.myanim.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by seb on 03/05/17.
 */

public class MyBtnData extends MyBtn {
 private  ArrayList mData;

    public MyBtnData(Bitmap picOn, Bitmap picOff, Boolean state, String shortDescriptif, int category, ArrayList mData) {
        super(picOn, picOff, state, shortDescriptif, category);
        this.mData = mData;
    }

    public ArrayList getmData() {
        return mData;
    }

    public void setmData(ArrayList mData) {
        this.mData = mData;
    }
}
