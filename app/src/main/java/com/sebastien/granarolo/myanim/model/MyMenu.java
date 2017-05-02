package com.sebastien.granarolo.myanim.model;

/**
 * Created by seb on 02/05/17.
 */

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 *
 */
public class MyMenu {
    private ArrayList<MyBtn> myBtnTab;
    private String position;

    public MyMenu() {
    }

    public MyMenu(ArrayList<MyBtn> myBtnTab, String position) {
        this.myBtnTab = myBtnTab;
        this.position = position;
    }

    public ArrayList<MyBtn> getMyBtnTab() {
        return myBtnTab;
    }

    public void setMyBtnTab(ArrayList<MyBtn> myBtnTab) {
        this.myBtnTab = myBtnTab;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}