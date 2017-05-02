package com.sebastien.granarolo.myanim.model;

import android.graphics.Bitmap;
import java.util.ArrayList;

/**
 * Created by seb on 02/05/17.
 */

public class MyBtn {

    private Bitmap picOn;
    private Bitmap picOff;
    private Boolean state;
    private String shortDescriptif;
    private int category;
    private ArrayList mData;

    public MyBtn(Bitmap picOn, Bitmap picOff, Boolean state, String shortDescriptif, int category) {
        this.picOn = picOn;
        this.picOff = picOff;
        this.state = state;
        this.shortDescriptif = shortDescriptif;
        this.category = category;
    }

    public MyBtn(Bitmap picOn, Bitmap picOff, Boolean state, String shortDescriptif, int category, ArrayList mData) {
        this.picOn = picOn;
        this.picOff = picOff;
        this.state = state;
        this.shortDescriptif = shortDescriptif;
        this.category = category;
        this.mData = mData;
    }

    public Bitmap getPicOn() {
        return picOn;
    }

    public void setPicOn(Bitmap picOn) {
        this.picOn = picOn;
    }

    public Bitmap getPicOff() {
        return picOff;
    }

    public void setPicOff(Bitmap picOff) {
        this.picOff = picOff;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getShortDescriptif() {
        return shortDescriptif;
    }

    public void setShortDescriptif(String shortDescriptif) {
        this.shortDescriptif = shortDescriptif;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public ArrayList getmData() {
        return mData;
    }

    public void setmData(ArrayList mData) {
        this.mData = mData;
    }
}
