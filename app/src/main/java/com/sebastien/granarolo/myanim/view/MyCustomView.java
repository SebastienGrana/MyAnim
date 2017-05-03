package com.sebastien.granarolo.myanim.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.sebastien.granarolo.myanim.R;
import com.sebastien.granarolo.myanim.model.MyBtn;
import com.sebastien.granarolo.myanim.model.MyBtnData;
import com.sebastien.granarolo.myanim.model.MyMenu;
import com.sebastien.granarolo.myanim.model.MySprite;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;

/**
 * Created by seb on 12/04/2017.
 */


//TODO faire objet Plateau de jeu, pour pouvoir avoir d'autre "Images" que celle de base ainsi que d'autres Menu
//TODO Tableaux a deux dimention. Une ligne pour chaque tableau de "MySprites"
//TODO 2em Tableau: une ligne pour chaque Object MyMenu
//TODO ex: plateau.getTabsSprites(0,0).getXStart
//TODO Mettre Graviter, effets pour le plateau

//TODO Faire le Controller dans Le mvC !!
//TODO Ne pas utiliser de variable de classe, tout passer en parrametre de la fonction !!!
//TODO implémenter: le nouveau system de détection de colision, le nouveau system de déplacement, les angles des mySprite, la vitesse en float et le poid

//TODO refaire les méthodes de drawMyMenu et onClickOnMyMenu de simplement
//TODO faire plain de variable de function pour une meilleur compréhention  du oode

//TODO JAVADOC

public class MyCustomView extends View implements View.OnTouchListener {

    private static final String TAG = "MyCustomView";
    public static final int myMenuWidth = 50;
    public static final int mMarginPic = 10;
    private int screenWidth;
    private int screenHeight;
    private Paint mPaint;
    private Paint mPaint2;

    private Bitmap mBitmapSpriteRed;
    private Bitmap mBitmapSpriteYellow;
    private Bitmap mBitmapSpriteGreen;
    private Bitmap mBitmapSpritePurple;
    private Bitmap mBitmapSpriteBlue;
    private Bitmap mBitmapSpriteTeal;
    private Bitmap mBitmapSpriteOrange;
    private Bitmap mBitmapSpritePink;
    private Bitmap mBitmapSpriteDefault;
    //    private Bitmap mBitmapIcSpriteMahhieu;

    private MyBtn showMyMenu;
    private MyBtnData makeBounce;
    private MyBtn reDraw;
    private MyBtn addPic;
    private MyBtn removePic;
    private MyBtn clearAllPic;
    private MyBtn doSound;
    private MyBtn showText;
    private MyBtn rdmBounceAngleMod;
    private MyBtn fireworksMod;
    private MyBtn increaseSpeedPic;
    private MyBtn decreaseSpeedPic;
    private MyBtn stopPic;
    private MyBtn redrawSelectedPic;
    private MyBtn changeColor;

    private ArrayList<MySprite> tabPics;
    private ArrayList<Bitmap> tabMBitmap;
    private ArrayList<String> tabTextString;
    private ArrayList<MyBtn> myBtnTab;
    private ArrayList dataTabMyBtnBounce;

    private int newNbCollidePerSecondes;
    private int maxCollidePerSeconds;

    private int nbCollidePerSecondes;
    private int nbTouch;
    private int newNbTouch;
    private int intBool1;
    private int compterFrame1;

    //        private Map<String, Boolean> booleanTabForBtnState ;
    private MyMenu myMenuRight;
    private MediaPlayer mMediaPlayerForPic1;
    private MediaPlayer mMediaPlayerForPic2;
    private Random randomNumber = new Random();
    private Vibrator mVibrator;
    private Runnable runnableUpdateCoor;
//    private android.widget.Toast Toast;
//    private Display mDisplay;
//    private Point mSize;
//    private WindowManager windowMana;
//    private Handler mHandler;
//    private SensorManager mSensorManager;
//    private Sensor mSensor;


    private MySprite selectedMySprite;

    /**
     * @param context
     */
    public MyCustomView(Context context) {  //Constucteur de la view où on y init toutes les variables, objets et autres dimentions
        super(context);
        init();
        initMyBtn();
        initMySprite();
    }

    /**
     * @param context
     * @param attrs
     */
    public MyCustomView(Context context, AttributeSet attrs) { //2em constructeur (je sais pas encore pourquoi ^^ )
        super(context, attrs);
        init();
        initMyBtn();
        initMySprite();
    }

    /**
     *
     */
    private void init() {
        super.setOnTouchListener(this);     //Appel l'init du parent

//        mMediaPlayerForPic1 = MediaPlayer.create(getContext(), R.raw.oui);
//        mMediaPlayerForWoW = MediaPlayer.create(getContext(), R.raw.wowguy);
        mMediaPlayerForPic1 = MediaPlayer.create(getContext(), R.raw.deniah);
        mMediaPlayerForPic2 = MediaPlayer.create(getContext(), R.raw.wowguy);
        mPaint = new Paint();       // init
        mPaint.setTextSize(40);     // on définit la taille de police
        mPaint2 = new Paint();       // Pour le text debug
        mPaint2.setTextSize(25);
        nbTouch = 0;    //Compteur
        nbCollidePerSecondes = 0;
        maxCollidePerSeconds = 0;
        newNbTouch = 0;
        nbCollidePerSecondes = 0;
        compterFrame1 = 0;

        mVibrator = (Vibrator) getContext().getSystemService(Activity.VIBRATOR_SERVICE);

        tabPics = new ArrayList<MySprite>();
        tabTextString = new ArrayList<String>();
        selectedMySprite = null;

        runnableUpdateCoor = new Runnable() {
            public void run() {

                updateCoor(tabPics);
            }
        };



    }

    private void initMySprite() {
        mBitmapSpriteRed = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_red);
        mBitmapSpriteYellow = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_yellow);
        mBitmapSpriteGreen = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_green);
        mBitmapSpritePurple = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_purple);
        mBitmapSpriteBlue = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_blue);
        mBitmapSpriteTeal = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_teal);
        mBitmapSpriteOrange = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_orange);
        mBitmapSpritePink = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_pink);
        mBitmapSpriteDefault = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_default);
//        mBitmapIcSpriteMahhieu = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mahhieu);

        tabMBitmap = new ArrayList<Bitmap>();
        tabMBitmap.add(0, mBitmapSpritePurple);
        tabMBitmap.add(1, mBitmapSpriteRed);
        tabMBitmap.add(2, mBitmapSpriteYellow);
        tabMBitmap.add(3, mBitmapSpriteGreen);
        tabMBitmap.add(4, mBitmapSpriteBlue);
        tabMBitmap.add(5, mBitmapSpriteTeal);
        tabMBitmap.add(6, mBitmapSpriteOrange);
        tabMBitmap.add(7, mBitmapSpritePink);
        tabMBitmap.add(8, mBitmapSpriteDefault);
//        tabMBitmap.add(5,mBitmapIcSpriteMahhieu);
    }

    private void initMyBtn() {
        dataTabMyBtnBounce = new ArrayList();
        dataTabMyBtnBounce.add(0, nbTouch);
        dataTabMyBtnBounce.add(1, newNbTouch);
        dataTabMyBtnBounce.add(2, nbCollidePerSecondes);
        dataTabMyBtnBounce.add(3, maxCollidePerSeconds);

        showMyMenu = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_show_debug_screen_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_show_debug_screen_off),
                true,
                "Show Menu",
                0);

        makeBounce = new MyBtnData(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_make_bounce_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_make_bounce_off),
                false,
                "Bounce",
                2,
                dataTabMyBtnBounce);
        reDraw = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_re_draw_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_re_draw_off),
                false,
                "Debug",
                2);
        addPic = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_add_pic_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_add_pic_off),
                false,
                "Add Pic",
                1);
        removePic = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_remove_pic_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_remove_pic_off),
                false,
                "Del Pic",
                1);
        clearAllPic = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_clear_all_pic_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_clear_all_pic_off),
                false,
                "Clear",
                1);
        doSound = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_sound_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_sound_off),
                false,
                "doSound",
                2);
        showText = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_show_text_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_show_text_off),
                false,
                "Text",
                2);
        rdmBounceAngleMod = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_fireworks_mod_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_fireworks_mod_off),
                false,
                "Fireworks Mod",
                2);
        fireworksMod = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_rdm_bounce_angle_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_rdm_bounce_angle_off),
                false,
                "Rmd Bounce Mod",
                2);
        increaseSpeedPic = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_increase_speed_pic_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_increase_speed_pic_off),
                false,
                "+ Speed",
                1);
        decreaseSpeedPic = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_decrease_speed_pic_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_decrease_speed_pic_off),
                false,
                "- Speed",
                1);
        stopPic = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_stop_all_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_stop_all_off),
                false,
                "Stop",
                1);
        redrawSelectedPic = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_redraw_selected_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_redraw_selected_off),
                false,
                "Draw Mod",
                2);
        changeColor = new MyBtn(
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_change_color_on),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_change_color_off),
                false,
                "Color Mod",
                2);


        myBtnTab = new ArrayList<MyBtn>();
        myBtnTab.add(0, showMyMenu);
        myBtnTab.add(1, makeBounce);
        myBtnTab.add(2, reDraw);
        myBtnTab.add(3, addPic);
        myBtnTab.add(4, removePic);
        myBtnTab.add(5, clearAllPic);
        myBtnTab.add(6, doSound);
        myBtnTab.add(7, showText);
        myBtnTab.add(8, rdmBounceAngleMod);
        myBtnTab.add(9, fireworksMod);
        myBtnTab.add(10, increaseSpeedPic);
        myBtnTab.add(11, decreaseSpeedPic);
        myBtnTab.add(12, stopPic);
        myBtnTab.add(13, redrawSelectedPic);
        myBtnTab.add(14, changeColor);
        //Objet menu comportant 3 Tableaux : 2 Tableau de Bitmap et 1 de Boolean
        myMenuRight = new MyMenu(myBtnTab, "right");
    }


    @Override
    protected void onDraw(Canvas canvas) {          //La fonction/méthode qui sert a crée des elements (Textview Imageview
        super.onDraw(canvas);

        drawTexts(canvas, tabTextString, mPaint, mPaint2);
        drawMyMenu(canvas, myMenuRight, mPaint, screenWidth, screenHeight);
        drawMySprites(canvas, tabPics, mPaint);
        reDrawLostPic(tabPics, tabMBitmap, screenWidth, screenHeight);
//        updateCoor();
        runnableUpdateCoor.run();

        increaseCompterFrame1AndSetDefaultBitmap(tabPics, tabMBitmap, selectedMySprite);
        invalidate();
    }


    /**
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {    // pour l'animation d'une image
        super.onLayout(changed, left, top, right, bottom);
        setScreenValues();
    }

    /**
     * @param view
     * @param motionEvent
     * @return
     */
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {        //méthode qui est appeler lorsque que l'on touche lécrans
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {


//
            if (x < screenWidth) {
//            String myText = String.format("X = %d et Y = %d", x, y);
//            Toast.makeText(view.getContext(), myText, Toast.LENGTH_SHORT).show();
//                addNewMySpriteOnTabPics(x, y);
//                setDefaultBitmapForAllPic(tabPics);
                onClickOnMySprite(x, y, motionEvent, tabPics);
                reDrawMySelectedMySpriteHere(x, y, selectedMySprite);

            }

            if (myMenuRight.getMyBtnTab().get(0).getState()) {   //Si le menu de myMenuRight est afficher

                if (x >= (screenWidth - myMenuRight.getMyBtnTab().get(0).getPicOff().getWidth() - mMarginPic * 2)) {


                    onClickOnMyMenuBtn(x, y, myMenuRight);

                    setScreenValues();


                }

            } else { //Si le myMenuRight n'est pas afficher
                if ((x >= (screenWidth - myMenuRight.getMyBtnTab().get(0).getPicOff().getWidth() - mMarginPic * 2)) &&
                        (y <= (myMenuRight.getMyBtnTab().get(0).getPicOff().getHeight()) + mMarginPic * 2)) {


                    onClickOnMyMenuBtn(x, y, myMenuRight);

                    setScreenValues();

                    if (!myMenuRight.getMyBtnTab().get(1).getState()) {
                        nbTouch = newNbTouch;
                        newNbTouch = 0;
                    }
                }
            }

        } else {
            return false;
        }

        return false;
    }

    /**
     *
     */
    public void updateCoor(ArrayList<MySprite> tabPics) {

        for (int compterFrame = 0; compterFrame < 15; compterFrame++) {
            if (compterFrame == 14) {
//        if (compterTouch > limiteFrame) {

                if (tabPics.size() > 1) {
                    for (int i = 0; i <= tabPics.size() - 1; i++) {
                        moveImg(tabPics.get(i));
                        makeBoundIfCollideOnWall(tabPics.get(i), screenWidth, screenHeight);

                        if (myMenuRight.getMyBtnTab().get(1).getState()) {
                            for (int j = (i + 1); j <= tabPics.size() - 1; j++) {
                                makeBoundIfCollideOnObject(tabPics.get(i), tabPics.get(j));
                            }
                        }
                    }
                } else if (tabPics.size() == 1) {
                    moveImg(tabPics.get(0));
                    makeBoundIfCollideOnWall(tabPics.get(0), screenWidth, screenHeight);
                }
//        compterTouch = 0;
//            }

//                if(isTowSecondsArePassed()){
//                    setDefaultBitmapForAllPic(tabPics);
//                }
            }
        }
    }

    /**
     * @param mObject1
     * @param mObject2
     * @return
     */
    private Boolean makeBoundIfCollideOnObject(MySprite mObject1, MySprite mObject2) {

        if (isObjectCollided(mObject1, mObject2)) {

            doActionsWhenCollide(mObject1, mObject2);
            playSoundCollideAh(mMediaPlayerForPic1);
            return true;

        } else {
            return false;
        }

    }

    /**
     * @param mObject1
     * @param mObject2
     */
    private void doActionsWhenCollide(MySprite mObject1, MySprite mObject2) {

        increaseNbCollideIfBouceOn();

        setTouchedOnXY(mObject1);
        setRandomBitmapForMSprite(mObject1, tabMBitmap, randomNumber);
        setRandomSpeedForPicIfActivated(mObject1, randomNumber);
        reDrawPicWhenCollideIfActivated(mObject1);

        setTouchedOnXY(mObject2);
        setRandomBitmapForMSprite(mObject2, tabMBitmap, randomNumber);
        setRandomSpeedForPicIfActivated(mObject2, randomNumber);
        reDrawPicWhenCollideIfActivated(mObject2);

    }

    /**
     * @param mObject1
     */
    private void setTouchedOnXY(MySprite mObject1) {

        if (!mObject1.isPicTouchedOnX() && !mObject1.isPicTouchedOnY()) {

            mObject1.setPicTouchedOnX(true);
            mObject1.setPicTouchedOnY(true);


        } else if (!mObject1.isPicTouchedOnX() && mObject1.isPicTouchedOnY()) {

            mObject1.setPicTouchedOnX(true);
            mObject1.setPicTouchedOnY(false);


        } else if (mObject1.isPicTouchedOnX() && !mObject1.isPicTouchedOnY()) {

            mObject1.setPicTouchedOnX(false);
            mObject1.setPicTouchedOnY(true);


        } else if (mObject1.isPicTouchedOnX() && mObject1.isPicTouchedOnY()) {

            mObject1.setPicTouchedOnX(false);
            mObject1.setPicTouchedOnY(false);


        }
    }


    /**
     * @param x
     * @param y
     * @return
     */
    private Boolean doActionWhenClickOnMySprite(int x, int y, int id, ArrayList<MySprite> tabPics, MySprite selectedMySprite) {

//                reDrawPicFuther(tabPics.get(i));
//                playSoundCollideWow();
        tabPics.get(id).setmBitmap(getRandomBitmap(tabMBitmap, randomNumber));
        selectedMySprite = tabPics.get(id);


        return true;


    }


    private Boolean onClickOnMySprite(int x, int y, MotionEvent motionEvent, ArrayList<MySprite> tabPics) {

        int grosDoigts = 20;
        int mSpriteX;
        int mSpriteEndX;
        int mSpriteY;
        int mSpriteEndY;

        for (int i = 0; i <= tabPics.size() - 1; i++) {

            mSpriteX = tabPics.get(i).getxStart();
            mSpriteEndX = mSpriteX + tabPics.get(i).getmSpriteWidth();
            mSpriteY = tabPics.get(i).getyStart();
            mSpriteEndY = mSpriteY + tabPics.get(i).getmSpriteHeight();

            if ((x > mSpriteX - grosDoigts) && (x <= mSpriteEndX + grosDoigts) &&
                    (y > mSpriteY - grosDoigts) && (y <= mSpriteEndY + grosDoigts)) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    doActionWhenClickOnMySprite(x, y, i, tabPics, selectedMySprite);

                }/* else if (motionEvent.getAction() == MotionEvent.ACTION_BUTTON_PRESS) {
//                    reDrawMySelectedMySpriteHere(x, y);
                }*/
                return true;
            } else {
                setDefaultBitmapForAllPic(tabPics, tabMBitmap, selectedMySprite);
            }
        }
        return null;
    }

    private void reDrawMySelectedMySpriteHere(int x, int y, MySprite selectedMySprite) {
        if (selectedMySprite != null && myMenuRight.getMyBtnTab().get(13).getState()) {
            selectedMySprite.setxStart(x);
            selectedMySprite.setyStart(y);
            selectedMySprite.setxSpeed(0);
            selectedMySprite.setySpeed(0);
        }
    }

    /**
     * @param mObject1
     * @param mObject2
     * @return
     */
    private boolean isObjectCollided(MySprite mObject1, MySprite mObject2) {
        //Si la distance entre les deux image (en X et en Y) /est inférieur/  à la largeur/hauteur de l'image
        if ((setAbsolute(mObject1.getxStart() - mObject2.getxStart()) < (mObject1.getmSpriteWidth())) &&
                (setAbsolute(mObject1.getyStart() - mObject2.getyStart()) < (mObject1.getmSpriteHeight()))) {
            return true;
        }
        return false;
    }


    /**
     *
     */
    private void increaseCompterFrame1AndSetDefaultBitmap(ArrayList<MySprite> tabPics, ArrayList<Bitmap> tabMBitmap, MySprite selectedMySprite) {

        //si le compteur est = à 15  /et/  si le rebond est On  /et/  si le Fireworks mod est On
        if (compterFrame1 == 15 && myMenuRight.getMyBtnTab().get(8).getState()) {
            setDefaultBitmapForAllPic(tabPics, tabMBitmap, selectedMySprite);
            compterFrame1 = 0;
        } else if (compterFrame1 > 15) {
            compterFrame1 = 0;
        } else {
            compterFrame1 ++;
        }
    }

    /**
     * @param mPic
     * @return
     */
    public Boolean reDrawPicWhenCollideIfActivated(MySprite mPic) {

        if (myMenuRight.getMyBtnTab().get(2).getState()) {

            if (!mPic.isPicTouchedOnX() && !mPic.isPicTouchedOnY()) {
                mPic.setxStart(mPic.getxStart() + 17);
                mPic.setyStart(mPic.getyStart() + 17);

            } else if (!mPic.isPicTouchedOnX() && mPic.isPicTouchedOnY()) {
                mPic.setxStart(mPic.getxStart() + 17);
                mPic.setyStart(mPic.getyStart() - 17);

            } else if (mPic.isPicTouchedOnX() && !mPic.isPicTouchedOnY()) {
                mPic.setxStart(mPic.getxStart() - 17);
                mPic.setyStart(mPic.getyStart() + 17);

            } else if (mPic.isPicTouchedOnX() && mPic.isPicTouchedOnY()) {
                mPic.setxStart(mPic.getxStart() - 17);
                mPic.setyStart(mPic.getyStart() - 17);

            }
            return true;
        }
        return false;
    }


    /**
     * @param mPic
     * @return
     */
    public Boolean reDrawPicFuther(MySprite mPic) {

        if (!mPic.isPicTouchedOnX() && !mPic.isPicTouchedOnY()) {
            mPic.setxStart(mPic.getxStart() + 17);
            mPic.setyStart(mPic.getyStart() + 17);
            return true;

        } else if (!mPic.isPicTouchedOnX() && mPic.isPicTouchedOnY()) {
            mPic.setxStart(mPic.getxStart() + 17);
            mPic.setyStart(mPic.getyStart() - 17);
            return true;

        } else if (mPic.isPicTouchedOnX() && !mPic.isPicTouchedOnY()) {
            mPic.setxStart(mPic.getxStart() - 17);
            mPic.setyStart(mPic.getyStart() + 17);
            return true;

        } else if (mPic.isPicTouchedOnX() && mPic.isPicTouchedOnY()) {
            mPic.setxStart(mPic.getxStart() - 17);
            mPic.setyStart(mPic.getyStart() - 17);
            return true;

        }
        return false;

    }

    /**
     *
     */
    private void reDrawLostPic(ArrayList<MySprite> tabPics, ArrayList<Bitmap> tabMBitmap, int screenWidth, int screenHeight) {
        MySprite myPic;
        int defaultPic = tabMBitmap.size() - 1;
        for (int i = 0; i < tabPics.size(); i++) {
            myPic = tabPics.get(i);

            if ((myPic.getxStart() + tabMBitmap.get(defaultPic).getWidth() > screenWidth + 50 && myPic.getyStart() + tabMBitmap.get(defaultPic).getHeight() > screenHeight + 50) ||
                    (myPic.getxStart() + tabMBitmap.get(defaultPic).getWidth() < 0 - 50 && myPic.getyStart() + tabMBitmap.get(defaultPic).getHeight() < 0 - 50)) {

                myPic.setxStart(screenWidth / 2);
                myPic.setyStart(screenHeight / 2);
                playSoundCollideWow(mMediaPlayerForPic2);

            }
        }

    }

    /**
     * @param myPic
     */
    private void makeBoundIfCollideOnWall(MySprite myPic, int screenWidth, int screenHeight) {

        if (!myPic.isPicTouchedOnX()) {
            if ((myPic.getxStart() >= (screenWidth - myPic.getmSpriteWidth()))) {
                myPic.setPicTouchedOnX(true);
                setRandomBitmapForMSprite(myPic, tabMBitmap, randomNumber);
                setRandomSpeedForPicIfActivated(myPic, randomNumber);
            }
        } else if (myPic.isPicTouchedOnX()) {
            if ((myPic.getxStart() <= 0)) {
                myPic.setPicTouchedOnX(false);
                setRandomBitmapForMSprite(myPic, tabMBitmap, randomNumber);
                setRandomSpeedForPicIfActivated(myPic, randomNumber);
            }
        }

        if (!myPic.isPicTouchedOnY()) {
            if ((myPic.getyStart() >= ((screenHeight) - myPic.getmSpriteHeight()))) {
                myPic.setPicTouchedOnY(true);
                setRandomBitmapForMSprite(myPic, tabMBitmap, randomNumber);
                setRandomSpeedForPicIfActivated(myPic, randomNumber);
            }
        } else if (myPic.isPicTouchedOnY()) {
            if ((myPic.getyStart() <= 0)) {
                myPic.setPicTouchedOnY(false);
                setRandomBitmapForMSprite(myPic, tabMBitmap, randomNumber);
                setRandomSpeedForPicIfActivated(myPic, randomNumber);
            }
        }
    }

    /**
     * @param myPic
     */
    private void moveImg(MySprite myPic) {

//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);

        if (!myPic.isPicTouchedOnX()) {
            myPic.setxStart(myPic.getxStart() + myPic.getxSpeed());

        } else if (myPic.isPicTouchedOnX()) {
            myPic.setxStart(myPic.getxStart() - myPic.getxSpeed());
        }

        if (!myPic.isPicTouchedOnY()) {
            myPic.setyStart(myPic.getyStart() + myPic.getySpeed());
        } else if (myPic.isPicTouchedOnY()) {
            myPic.setyStart(myPic.getyStart() - myPic.getySpeed());
        }
    }


    /**
     * @param number
     * @return
     */
    private int setAbsolute(int number) {
        if (number < 0) {
            number = number * -1;
            return number;
        }
        return number;
    }

    /**
     *
     */
    public void playSoundCollideAh(MediaPlayer mMediaPlayerForPic1) {
        //on joue la musique
        if (myMenuRight.getMyBtnTab().get(6).getState()) { //si le son de rebond est acctivé

            if (mMediaPlayerForPic1.isPlaying()) {     //si la musique est fini
                mMediaPlayerForPic1.stop();                    //on stop le lecteur
                mMediaPlayerForPic1.reset();                   //on le vide
                mMediaPlayerForPic1 = MediaPlayer.create(getContext(), R.raw.deniah); // on reremplis
            }

            mMediaPlayerForPic1.start(); // on joue
        }
    }

    /**
     *
     */
    public void playSoundCollideWow(MediaPlayer mMediaPlayerForPic2) {

        if (myMenuRight.getMyBtnTab().get(6).getState()) { //si le son de rebond est acctivé

            if (mMediaPlayerForPic2.isPlaying()) {     //si la musique est fini
                mMediaPlayerForPic2.stop();                    //on stop le lecteur
                mMediaPlayerForPic2.reset();                   //on le vide
                mMediaPlayerForPic2 = MediaPlayer.create(getContext(), R.raw.wowguy); // on reremplis
            }

            mMediaPlayerForPic2.start(); // on joue
        }
    }

    /**
     * Don't use it if your mSprite don't have a mediaplayer
     *
     * @param mPic
     */
    public void playSoundMySprite(MySprite mPic) {

        mPic.getmMediaPlayer().start();
        if (!mPic.getmMediaPlayer().isPlaying()) {
            mPic.getmMediaPlayer().stop();
            mPic.getmMediaPlayer().reset();
        }
    }


    /**
     *
     */
    private void increaseNbCollideIfBouceOn() {
//Todo tableau
        int compterTouch = 0;
        if (myMenuRight.getMyBtnTab().get(1).getState()) { //si le rebond sur les object sont acctiver
            compterTouch++;
            newNbTouch = newNbTouch + compterTouch;
            newNbCollidePerSecondes = newNbCollidePerSecondes + compterTouch;
            compterTouch = 0;
        }

        if (isASecondIsPassed()) {
            nbCollidePerSecondes = newNbCollidePerSecondes;
            newNbCollidePerSecondes = 0;


            if (nbCollidePerSecondes > maxCollidePerSeconds) {

                maxCollidePerSeconds = nbCollidePerSecondes;
            }
        } else if (isASecondIsPassed() && newNbTouch == 0) {

            nbCollidePerSecondes = 0;
        }
    }


    /**
     * @param myPic
     */
    public void setRandomSpeedForPicIfActivated(MySprite myPic, Random randomNumber) {
        if (myMenuRight.getMyBtnTab().get(9).getState()) {//si le bouton est acctiver
            myPic.setySpeed(randomNumber.nextInt(9)); // vitesse en 0 et 8
            myPic.setxSpeed(randomNumber.nextInt(9)); // vitesse en 0 et 8
        }
    }


    /**
     * @param mPic
     * @return
     */
    private Bitmap getOtherBitmap(MySprite mPic, ArrayList<Bitmap> tabMBitmap) {
        int defaultPic = tabMBitmap.size() - 1;

        if (!mPic.isPicTouchedOnX() && !mPic.isPicTouchedOnY()) {
            return tabMBitmap.get(0);
        } else if (!mPic.isPicTouchedOnX() && mPic.isPicTouchedOnY()) {
            return tabMBitmap.get(1);
        } else if (mPic.isPicTouchedOnX() && !mPic.isPicTouchedOnY()) {
            return tabMBitmap.get(2);
        } else if (mPic.isPicTouchedOnX() && mPic.isPicTouchedOnY()) {
            return tabMBitmap.get(3);
        }
        return tabMBitmap.get(defaultPic);
    }


    /**
     * @return
     */
    private Bitmap getRandomBitmap(ArrayList<Bitmap> tabMBitmap, Random randomNumber) {

        return tabMBitmap.get(randomNumber.nextInt(tabMBitmap.size() - 1));
    }


    /**
     * @param mSprite
     * @return
     */
    private Boolean setRandomBitmapForMSprite(MySprite mSprite, ArrayList<Bitmap> tabMBitmap, Random randomNumber) {
        if (myMenuRight.getMyBtnTab().get(14).getState()) {
            mSprite.setmBitmap(tabMBitmap.get(randomNumber.nextInt(tabMBitmap.size() - 1)));
        }
        return true;
    }


    /**
     * @param mTabSprite
     * @return
     */
    private Boolean setDefaultBitmapForAllPic(ArrayList<MySprite> mTabSprite, ArrayList<Bitmap> tabMBitmap, MySprite selectedMySprite) {
        for (int i = 0; i < mTabSprite.size(); i++) {
            if (mTabSprite.get(i) != selectedMySprite) {
                mTabSprite.get(i).setmBitmap(tabMBitmap.get(tabMBitmap.size() - 1));
            }
        }
        return true;
    }


    /**
     * @param startX
     * @param startY
     */
    private void addNewMySpriteOnTabPics(int startX, int startY, ArrayList<MySprite> tabPics, Random randomNumber, ArrayList<Bitmap> tabMBitmap) {
        int defaultPic = tabMBitmap.size() - 1;
        tabPics.add(
                new MySprite(
                        startX,  // Position en X
                        startY,  // Position en Y
                        randomNumber.nextInt(5) + 3, // sa Vitesse en X
                        randomNumber.nextInt(5) + 3, // sa Vitesse en Y
                        tabMBitmap.get(defaultPic).getWidth(), // sa Largeur en X
                        tabMBitmap.get(defaultPic).getHeight(), // sa Hauteur en Y
                        tabMBitmap.get(defaultPic), //l'image du sprite (ici la noir)
                        getRandomBool(),    //sence de déplacement en X (0 droite, 1 gauche)
                        getRandomBool()    //sence de déplacement en Y (0 haut, 1 bas)
                       /*, (new MediaPlayer()).create(
                                getContext(),
                                R.raw.coolpoc)*/
                )
        );
    }


    /**
     * @return
     */
    private Boolean getRandomBool() {
        intBool1 = randomNumber.nextInt(2); // génere un int entre 0 et 1 (2 est exclu)
        if (intBool1 == 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     *
     */
    private void removeLastMySpriteOnTabPics(ArrayList<MySprite> tabPics) { //enleve le dernier sprite
        if (tabPics.size() > 0) {
            tabPics.remove(tabPics.size() - 1);
        }
    }


    /**
     *
     */
    private void clearAllOnTabPics(ArrayList<MySprite> tabPics) {//enleve tout les sprites
        for (int i = tabPics.size() - 1; i >= 0; i--) {
            tabPics.remove(i);
        }
    }


    /**
     * @param x
     * @param y
     * @param myMenu
     * @return
     */
    private Boolean onClickOnMyMenuBtn(int x, int y, MyMenu myMenu) { //écoute le click et fait une acction pour les boutons du MyMenu

        if (!this.myMenuRight.getMyBtnTab().get(0).getState()) {

            doActionForBtn(myMenu.getMyBtnTab().get(0), 0, tabPics);

            return true;

        } else {

            for (int i = 0; i <= myMenu.getMyBtnTab().size() - 1; i++) {

                if (i == 0) {  //Pour le cas 0

                    if ((x >= (screenWidth - myMenu.getMyBtnTab().get(0).getPicOff().getWidth() - mMarginPic * 2) &&
                            (y >= (myMenu.getMyBtnTab().get(0).getPicOff().getWidth() - mMarginPic * 1)) &&
                            (y <= (myMenu.getMyBtnTab().get(0).getPicOff().getWidth() + mMarginPic * 2)))) {

                        doActionForBtn(myMenu.getMyBtnTab().get(i), i, tabPics);
                        return true;
                    }

                } else {

                    if ((x >= (screenWidth - myMenu.getMyBtnTab().get(i).getPicOff().getWidth() - mMarginPic * 2) &&
                            (y >= (myMenu.getMyBtnTab().get(i).getPicOff().getHeight() + (mMarginPic * 2)) * i) &&
                            (y <= ((myMenu.getMyBtnTab().get(i).getPicOff().getHeight() + (mMarginPic * 2)) * i) +
                                    (myMenu.getMyBtnTab().get(i).getPicOff().getHeight() + (mMarginPic * 2))))) {

                        doActionForBtn(myMenu.getMyBtnTab().get(i), i, tabPics);
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     *
     */
    private void setScreenValues() { // récupere la hauteur et la largeur de lécrans si le MyMenu est ouvert ou non
        if (!myMenuRight.getMyBtnTab().get(0).getState()) {

//            windowMana.getDefaultDisplay().getRealSize(mSize);
//            screenWidth = mSize.x;
//            screenHeight = mSize.y/*-256 */;
            screenWidth = getWidth();
            screenHeight = getHeight();
        } else {
//            windowMana.getDefaultDisplay().getRealSize(mSize);
//            screenWidth = mSize.x /*-40*/;
//            screenHeight = mSize.y/*-256*/;
            screenWidth = getWidth() - myMenuWidth;
            screenHeight = getHeight();

        }
    }


    /**
     * @param canvas
     */
    private void drawMySprites(Canvas canvas, ArrayList<MySprite> tabPics, Paint mPaint) { // déssine tout les sprites

        for (int i = 0; i <= tabPics.size() - 1; i++) {

            canvas.drawBitmap(tabPics.get(i).getmBitmap(),
                    tabPics.get(i).getxStart(),
                    tabPics.get(i).getyStart(),
                    mPaint
            );


        }
    }

    /**
     * @param canvas
     */
    private void drawTexts(Canvas canvas, ArrayList<String> tabTextString, Paint mPaint, Paint mPaint2) { // déssine tout les texts
//TODO Tableeau pour le dessins des textes
        int x = 50;
        int y = 50;

        setText(tabTextString);
        for (int i = 0; i < tabTextString.size(); i++) {
            if (i == 0) {
                canvas.drawText(tabTextString.get(i), x, y, mPaint);
                y = y + 50;
            } else if (myMenuRight.getMyBtnTab().get(7).getState()) {
                y = y + 50;
                canvas.drawText(tabTextString.get(i), x, y, mPaint2);
            }
        }
    }

    private void setText(ArrayList<String> tabTextString) {
        tabTextString.removeAll(tabTextString);
        tabTextString.add(0, "nombre d'images : " + tabPics.size());
        tabTextString.add(1, "nombre de colisions total: " + nbTouch + " (+" + newNbTouch + ")");
        tabTextString.add(2, "nombre de colisions en cours: " + newNbTouch + " (" + nbCollidePerSecondes + "/s)");
        tabTextString.add(3, "nombre de colisions par seconds Max : " + maxCollidePerSeconds);
//        tabTextString.add(4, "Une seconde est passé: " + isASecondIsPassed());
    }

    /**
     * @return
     */
    public Boolean isASecondIsPassed() { // renvois vrais si une seconde est passer

        int seconds = 0;
        long starttime = 0;
        int oldSeconds;
        long millis;

        oldSeconds = seconds;
        millis = System.currentTimeMillis() - starttime;
        seconds = (int) (millis / 1000);
        seconds = seconds % 60;
        if (seconds > oldSeconds) {
            return true;
        }
        return false;
    }

    /**
     * @param canvas
     * @param myMenu
     */
    private void drawMyMenu(Canvas canvas, MyMenu myMenu, Paint mPaint, int screenWidth, int screenHeight) { // déssine les icones du MyMenu
        if (!myMenu.getMyBtnTab().get(0).getState()) {        //Si le menu debug n'est pas afficher

//            mBitmapIcShowDebugScreenOn
            canvas.drawBitmap(      //Dessine l'icone pour l'ouverture
                    myMenu.getMyBtnTab().get(0).getPicOff(),
                    screenWidth - myMenu.getMyBtnTab().get(0).getPicOff().getWidth() - mMarginPic,
                    mMarginPic,
                    mPaint);

            canvas.drawLine( // Ligne verticale
                    screenWidth - myMenu.getMyBtnTab().get(0).getPicOff().getWidth() - mMarginPic * 2,
                    0,
                    screenWidth - myMenu.getMyBtnTab().get(0).getPicOff().getWidth() - mMarginPic * 2,
                    myMenu.getMyBtnTab().get(0).getPicOff().getHeight() + mMarginPic * 2,
                    mPaint);

            canvas.drawLine(// Ligne Horizontale
                    screenWidth - myMenu.getMyBtnTab().get(0).getPicOff().getWidth() - mMarginPic * 2,
                    myMenu.getMyBtnTab().get(0).getPicOff().getHeight() + mMarginPic * 2,
                    screenWidth,
                    myMenu.getMyBtnTab().get(0).getPicOff().getHeight() + mMarginPic * 2,
                    mPaint);

        } else {    //Si le menu debug c'est afficher
            canvas.drawLine( //Grande Ligne Verticale
                    screenWidth,        //Dessine l'icone pour fermeture
                    0,
                    screenWidth,
                    screenHeight,
                    mPaint);
            canvas.drawLine(    //Petite Ligne Horizontale
                    screenWidth,
                    0,
                    screenWidth + myMenuWidth,
                    0,
                    mPaint);
//            canvas.drawBitmap(
//                    myMenuRight.getBitmapTabBtnOn().get(0),
//                    screenWidth + mMarginPic,
//                    myMenuRight.getBitmapTabBtnOn().get(0).getHeight() * 0 + mMarginPic,
//                    mPaint);

            for (int i = 0; i < myMenu.getMyBtnTab().size(); i++) {    //Pour chaque icone a affichier Moins la première

                canvas.drawLine(    //Petite Ligne Horizontale Apres l'image
                        screenWidth,
                        (myMenu.getMyBtnTab().get(i).getPicOff().getHeight() + (mMarginPic * 2)) * (i + 1),
                        screenWidth + myMenuWidth,
                        (myMenu.getMyBtnTab().get(i).getPicOff().getHeight() + (mMarginPic * 2)) * (i + 1),
                        mPaint);

                //Selon l'état du de l'icone affiche celle conrespondente
                if (myMenu.getMyBtnTab().get(i).getState()) { //Si le boolean de l'icone est On


                    canvas.drawBitmap(
                            myMenu.getMyBtnTab().get(i).getPicOn(),
                            screenWidth + mMarginPic,
                            ((myMenu.getMyBtnTab().get(i).getPicOn().getHeight() + (2 * mMarginPic)) * i) + mMarginPic,
                            mPaint);

                } else {    //Si le boolean de l'icone est Off
                    canvas.drawBitmap(
                            myMenu.getMyBtnTab().get(i).getPicOff(),
                            screenWidth + mMarginPic,
                            ((myMenu.getMyBtnTab().get(i).getPicOff().getHeight() + (2 * mMarginPic)) * i) + mMarginPic,
                            mPaint);
                }

            }
        }
    }

    /**
     * @param myBtn
     * @param idMyBtn
     */
    private void doActionForBtn(MyBtn myBtn, int idMyBtn, ArrayList<MySprite> tabPics) {
//TODO ne plus utiliser, si possible, de variables local (ex :les regrouper dans un tableau)
        switch (idMyBtn) {
            case 0:     //Afficher le menu
                myBtn.setState(!myBtn.getState());
                break;
            case 1:     //Acctiver le rebond
                myBtn.setState(!myBtn.getState());
                if (!myBtn.getState()) {
                    nbTouch = nbTouch + newNbTouch;
                    newNbTouch = 0;
                    nbCollidePerSecondes = 0;
                }
                break;
            case 2:     //Acctiver l'anti Chevochement
                myBtn.setState(!myBtn.getState());
                break;
            case 3:     //Ajouter une image
                addNewMySpriteOnTabPics(randomNumber.nextInt(screenWidth), randomNumber.nextInt(screenHeight), tabPics, randomNumber, tabMBitmap);
                maxCollidePerSeconds = 0;
                myBtn.setState(!myBtn.getState());
                break;
            case 4:     //Supprimer la dernierre image
                removeLastMySpriteOnTabPics(tabPics);
                maxCollidePerSeconds = 0;
                myBtn.setState(!myBtn.getState());
                break;
            case 5:     //Supprimer toute les images
                clearAllOnTabPics(tabPics);
                nbCollidePerSecondes = 0;
                newNbTouch = 0;
                maxCollidePerSeconds = 0;
                myBtn.setState(!myBtn.getState());
                break;
            case 6:     //Acctiver les sons
                myBtn.setState(!myBtn.getState());
                break;
            case 7:     //Afficher les textes
                myBtn.setState(!myBtn.getState());
                break;
            case 8:     //Acctiver le menu Fireworks
                myBtn.setState(!myBtn.getState());
                break;
            case 9:
                myBtn.setState(!myBtn.getState());
                break;
            case 10:     //Augmenter la vitesse de tout les images
                increaseSpeed(1, tabPics);
                myBtn.setState(!myBtn.getState());
                break;
            case 11:     //Diminuer la vitesse de tout les images
                decreaseSpeed(1, tabPics);
                myBtn.setState(!myBtn.getState());
                break;
            case 12:     //Arraiter toute les images
                stopAllPic(tabPics);
                myBtn.setState(!myBtn.getState());
                break;
            case 13:     //Redessiner l'image sélectionné
                myBtn.setState(!myBtn.getState());
                break;
            case 14:     //Acctiver le changement de couleurs
                myBtn.setState(!myBtn.getState());
                break;
        }

    }

    /**
     * @param tabPics
     */
    private void stopAllPic(ArrayList<MySprite> tabPics) {
        for (int i = 0; i <= tabPics.size() - 1; i++) {


            tabPics.get(i).setxSpeed(0);
            tabPics.get(i).setySpeed(0);

        }
    }

    /**
     * @param nbIncrease
     */
    private void increaseSpeed(int nbIncrease, ArrayList<MySprite> tabPics) {
        MySprite mySprite;
        for (int i = 0; i <= tabPics.size() - 1; i++) {

            mySprite = tabPics.get(i);

            if (mySprite.getxSpeed() >= 0) {
                mySprite.setxSpeed(mySprite.getxSpeed() + nbIncrease);
            }
            if (mySprite.getySpeed() >= 0) {
                mySprite.setySpeed(mySprite.getySpeed() + nbIncrease);
            }
        }
    }

    /**
     * @param nbDecrease
     */
    private void decreaseSpeed(int nbDecrease, ArrayList<MySprite> tabPics) {
        MySprite mySprite;
        for (int i = 0; i <= tabPics.size() - 1; i++) {

            mySprite = tabPics.get(i);
            if (mySprite.getxSpeed() >= nbDecrease) {
                mySprite.setxSpeed(mySprite.getxSpeed() - nbDecrease);
            }
            if (mySprite.getySpeed() >= nbDecrease) {
                mySprite.setySpeed(mySprite.getySpeed() - nbDecrease);
            }
        }
    }


}
