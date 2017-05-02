package com.sebastien.granarolo.myanim.model;

import android.graphics.Bitmap;
import android.media.MediaPlayer;

/**
 * Created by hb on 16/04/2017.
 */

public class MySprite {

    private int xStart;
    private int yStart;
    private int xSpeed;
    private int ySpeed;
    private int mSpriteWidth;
    private int mSpriteHeight;
    private Bitmap mBitmap;

    private float angle;
    private float mass;
    private float speed;



    private boolean isPicTouchedOnX;
    private boolean isPicTouchedOnY;
    private MediaPlayer mMediaPlayer;


    //    private MediaPlayer mMedia1;

    public MySprite() {
    }

    public MySprite(int xStart, int yStart, int xSpeed, int ySpeed, int mSpriteWidth, int mSpriteHeight, Bitmap mBitmap, boolean isPicTouchedOnX, boolean isPicTouchedOnY) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.mSpriteWidth = mSpriteWidth;
        this.mSpriteHeight = mSpriteHeight;
        this.mBitmap = mBitmap;
        this.isPicTouchedOnX = isPicTouchedOnX;
        this.isPicTouchedOnY = isPicTouchedOnY;
    }

    public MySprite(int xStart, int yStart, int xSpeed, int ySpeed, int mSpriteWidth, int mSpriteHeight, Bitmap mBitmap, boolean isPicTouchedOnX, boolean isPicTouchedOnY, MediaPlayer mMediaPlayer) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.mSpriteWidth = mSpriteWidth;
        this.mSpriteHeight = mSpriteHeight;
        this.mBitmap = mBitmap;
        this.isPicTouchedOnX = isPicTouchedOnX;
        this.isPicTouchedOnY = isPicTouchedOnY;
        this.mMediaPlayer = mMediaPlayer;
    }

    public MediaPlayer getmMediaPlayer() {
        return mMediaPlayer;
    }

    public void setmMediaPlayer(MediaPlayer mMediaPlayer) {
        this.mMediaPlayer = mMediaPlayer;
    }

    public Bitmap getmBitmap() {
        return mBitmap;
    }

    public void setmBitmap(Bitmap mBitmap) {
        this.mBitmap = mBitmap;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public int getySpeed() {
        return ySpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public int getmSpriteWidth() {
        return mSpriteWidth;
    }

    public void setmSpriteWidth(int mSpriteWidth) {
        this.mSpriteWidth = mSpriteWidth;
    }

    public int getmSpriteHeight() {
        return mSpriteHeight;
    }

    public void setmSpriteHeight(int mSpriteHeight) {
        this.mSpriteHeight = mSpriteHeight;
    }

    public boolean isPicTouchedOnX() {
        return isPicTouchedOnX;
    }

    public void setPicTouchedOnX(boolean picTouchedOnX) {
        isPicTouchedOnX = picTouchedOnX;
    }

    public boolean isPicTouchedOnY() {
        return isPicTouchedOnY;
    }

    public void setPicTouchedOnY(boolean picTouchedOnY) {
        isPicTouchedOnY = picTouchedOnY;
    }
}
