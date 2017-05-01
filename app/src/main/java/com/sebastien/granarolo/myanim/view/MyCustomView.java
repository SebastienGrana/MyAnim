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
import com.sebastien.granarolo.myanim.model.MySprite;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Handler;

/**
 * Created by hb on 12/04/2017.
 */
/*
*
* Ceci est une class d'une View Personnaliser
* Apparement ça sert a pouvoir faire des annimations, pouvoir crée a la volé des TextView ImageView etc.. à débatre ... =)
*/
public class MyCustomView extends View implements View.OnTouchListener {

    private static final String TAG = "MyCustomView";
    public static final int myMenuWidth = 50;
    public static final int mMarginPic = 10;
    private int screenWidth;            //Des dimentions pour récup la taille de l'écrans
    private int screenHeight;
    private Paint mPaint;               //Object Paint qui sert a pouvoir afficher des elements, comme : TextView ImageView etc ... c'est cool!
    private Paint mPaint2;

    private Bitmap mBitmapSpriteRed;
    private Bitmap mBitmapSpriteYellow;
    private Bitmap mBitmapSpriteGreen;
    private Bitmap mBitmapSpritePurple;
    private Bitmap mBitmapSpriteBlue;
    private Bitmap mBitmapSpriteTeal;
    private Bitmap mBitmapSpriteOrange;
    private Bitmap mBitmapSpritePink;
    private Bitmap mBitmapDefault;
//    private Bitmap mBitmapIcSpriteMahhieu;

    private Bitmap mBitmapIcShowDebugScreenOn;
    private Bitmap mBitmapIcShowDebugScreenOff;

    private Bitmap mBitmapIcMakeBounceOff;
    private Bitmap mBitmapIcMakeBounceOn;

    private Bitmap mBitmapIcReDrawOff;
    private Bitmap mBitmapIcReDrawOn;

    private Bitmap mBitmapIcAddPicOn;
    private Bitmap mBitmapIcAddPicOff;

    private Bitmap mBitmapIcRemovePicOn;
    private Bitmap mBitmapIcRemovePicOff;

    private Bitmap mBitmapIcClearAllPicOn;
    private Bitmap mBitmapIcClearAllPicOff;

    private Bitmap mBitmapIcSoundOn;
    private Bitmap mBitmapIcSoundOff;

    private Bitmap mBitmapIcShowTextOn;
    private Bitmap mBitmapIcShowTextOff;

    private Bitmap mBitmapIcRdmBounceAngleModOn;
    private Bitmap mBitmapIcRdmBounceAngleModOff;

    private Bitmap mBitmapIcFireworksModOn;
    private Bitmap mBitmapIcFireworksModOff;

    private Bitmap mBitmapIcIncreaseSpeedPicOn;
    private Bitmap mBitmapIcIncreaseSpeedPicOff;

    private Bitmap mBitmapIcDecreaseSpeedPicOn;
    private Bitmap mBitmapIcDecreaseSpeedPicOff;

    private Bitmap mBitmapIcStopPicOn;
    private Bitmap mBitmapIcStopPicOff;

    private Bitmap mBitmapIcRedrawSelectedPicOn;
    private Bitmap mBitmapIcRedrawSelectedPicOff;

    private Bitmap mBitmapIcChangeColorOn;
    private Bitmap mBitmapIcChangeColorOff;


//    private MySprite mPic1;
//    private MySprite mPic2;
//    private MySprite mPic3;
//    private MySprite mPic4;

    private ArrayList<MySprite> tabPics;
    private ArrayList<Bitmap> mBitmapTab;
    private ArrayList<Bitmap> bitmapTabForOnBtn;
    private ArrayList<Bitmap> bitmapTabForOffBtn;
    private ArrayList<Boolean> booleanTabForBtnState;

    private String textNbTouchTotal;
    private String textNbTouchPerSecondes;
    private String textNbPic;
    private String textNbTouchPerSecondesMax;
    private String text5;
    private String text6;
    private String text7;
    private String text8;
    private String text9;

    private Boolean isBounceOn;
    private Boolean isReDrawOn;
    private Boolean isAddPicOn;
    private Boolean isRemovePicOn;
    private Boolean isClearAllPicOn;
    private Boolean isSoundOn;
    private Boolean isTextDebugIsShown;
    private Boolean isRmdBounceAngleModOn;
    private Boolean isMyMenuisShown;
    private Boolean isFireworksModOn;
    private Boolean isIncreaseSpeedOn;
    private Boolean isDecreaseSpeedOn;
    private Boolean isStopPicOff;
    private Boolean isReDrawSelectedSpriteOn;
    private Boolean isChangeColorOn;

    //    private Map<String, Boolean> booleanTabForBtnState ;
    private MyMenu myMenu;
    private MediaPlayer mMediaPlayerForPic1;
    private MediaPlayer mMediaPlayerForPic2;
    private Vibrator mVibrator;
    private Random randomNumber = new Random();
    private android.widget.Toast Toast;
    private Display mDisplay;
    private Point mSize;
    private WindowManager windowMana;
    private Handler mHandler;
    private Runnable runnableUpdateCoor;
    private SensorManager mSensorManager;
    private Sensor mSensor;

    private long starttime;
    private long millis;

    private int seconds;
    private int minutes;
    private int oldSeconds;
    private int newNbCollidePerSecondes;
    private int veryOldSeconds;
    private int compterFrame1;
    private int maxCollidePerSeconds;
    private int compterTouch;
    private int limiteFrame;
    private int nbTouch;
    private int newNbTouch;
    private int intBool1;
    private int nbCollidePerSecondes;

    private MySprite selectedMySprite;

    /**
     * @param context
     */
    public MyCustomView(Context context) {  //Constucteur de la view où on y init toutes les variables, objets et autres dimentions
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public MyCustomView(Context context, AttributeSet attrs) { //2em constructeur (je sais pas encore pourquoi ^^ )
        super(context, attrs);
        init();
    }

    /**
     *
     */
    private void init() {
        super.setOnTouchListener(this);     //Appel l'init du parent

//        mMediaPlayerForPic1 = MediaPlayer.create(getContext(), R.raw.oui);
//        mMediaPlayerForWoW = MediaPlayer.create(getContext(), R.raw.wowguy);   //on crée le player de musique, on donne le context qui sert a savoir où le créer quand il est appeler, puis on lui passe le son a jouer

        mMediaPlayerForPic1 = MediaPlayer.create(getContext(), R.raw.deniah);
        mMediaPlayerForPic2 = MediaPlayer.create(getContext(), R.raw.wowguy);
        mPaint = new Paint();       // init
        mPaint.setTextSize(40);     // on définit la taille de police
        mPaint2 = new Paint();       // Pour le text debug
        mPaint2.setTextSize(25);
        starttime = 0;
        nbCollidePerSecondes = 0;
        compterFrame1 = 0;
        compterTouch = 0;
        limiteFrame = 2;
        maxCollidePerSeconds = 0;
        nbTouch = 0;    //Compteur
        mVibrator = (Vibrator) getContext().getSystemService(Activity.VIBRATOR_SERVICE);
        tabPics = new ArrayList<MySprite>();
        selectedMySprite = null;

        runnableUpdateCoor = new Runnable() {
            public void run() {

                updateCoor();
            }
        };

        //------------------------------Debut Init des Images de L'appli----------------------------------------------
        mBitmapSpriteRed = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_red);
        mBitmapSpriteYellow = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_yellow);
        mBitmapSpriteGreen = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_green);
        mBitmapSpritePurple = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_purple);
        mBitmapSpriteBlue = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_blue);
        mBitmapSpriteTeal = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_teal);
        mBitmapSpriteOrange = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_orange);
        mBitmapSpritePink = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_pink);
        mBitmapDefault = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sprite_default);
//        mBitmapIcSpriteMahhieu = BitmapFactory.decodeResource(getResources(), R.drawable.ic_mahhieu);

        mBitmapTab = new ArrayList<Bitmap>();
        mBitmapTab.add(0, mBitmapSpritePurple);
        mBitmapTab.add(1, mBitmapSpriteRed);
        mBitmapTab.add(2, mBitmapSpriteYellow);
        mBitmapTab.add(3, mBitmapSpriteGreen);
        mBitmapTab.add(4, mBitmapSpriteBlue);
        mBitmapTab.add(5, mBitmapSpriteTeal);
        mBitmapTab.add(6, mBitmapSpriteOrange);
        mBitmapTab.add(7, mBitmapSpritePink);
        mBitmapTab.add(8, mBitmapDefault);
//        mBitmapTab.add(5,mBitmapIcSpriteMahhieu);
        //----------------------------------------------

        //------------------------------------Fin Init des Images de L'appli---------------------------------------------


        //------------------------------------Debut Init des/ du MyMenu de L'appli---------------------------------------

        mBitmapIcShowDebugScreenOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_show_debug_screen_on);
        mBitmapIcShowDebugScreenOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_show_debug_screen_off);

        mBitmapIcMakeBounceOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_make_bounce_off);
        mBitmapIcMakeBounceOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_make_bounce_on);

        mBitmapIcReDrawOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_re_draw_on);
        mBitmapIcReDrawOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_re_draw_off);

        mBitmapIcAddPicOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_add_pic_on);
        mBitmapIcAddPicOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_add_pic_off);

        mBitmapIcRemovePicOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_remove_pic_on);
        mBitmapIcRemovePicOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_remove_pic_off);

        mBitmapIcClearAllPicOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_clear_all_pic_on);
        mBitmapIcClearAllPicOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_clear_all_pic_off);

        mBitmapIcSoundOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sound_on);
        mBitmapIcSoundOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sound_off);

        mBitmapIcShowTextOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_show_text_on);
        mBitmapIcShowTextOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_show_text_off);

        mBitmapIcFireworksModOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_fireworks_mod_on);
        mBitmapIcFireworksModOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_fireworks_mod_off);

        mBitmapIcRdmBounceAngleModOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rdm_bounce_angle_on);
        mBitmapIcRdmBounceAngleModOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_rdm_bounce_angle_off);

        mBitmapIcIncreaseSpeedPicOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_increase_speed_pic_on);
        mBitmapIcIncreaseSpeedPicOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_increase_speed_pic_off);

        mBitmapIcDecreaseSpeedPicOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_decrease_speed_pic_on);
        mBitmapIcDecreaseSpeedPicOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_decrease_speed_pic_off);

        mBitmapIcStopPicOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_stop_all_on);
        mBitmapIcStopPicOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_stop_all_off);

        mBitmapIcRedrawSelectedPicOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_redraw_selected_on);
        mBitmapIcRedrawSelectedPicOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_redraw_selected_off);

        mBitmapIcChangeColorOn = BitmapFactory.decodeResource(getResources(), R.drawable.ic_change_color_on);
        mBitmapIcChangeColorOff = BitmapFactory.decodeResource(getResources(), R.drawable.ic_change_color_off);

        isMyMenuisShown = true;   //Si le menu est afficher
        isBounceOn = false;      //Si l'on acctive les rebonds
        isReDrawOn = false;      //Si on redessine les images lors du rebond
        isAddPicOn = false;
        isRemovePicOn = false;
        isClearAllPicOn = false;
        isSoundOn = false;
        isTextDebugIsShown = false;
        isFireworksModOn = false;
        isRmdBounceAngleModOn = false;
        isIncreaseSpeedOn = false;
        isDecreaseSpeedOn = false;
        isStopPicOff = false;
        isReDrawSelectedSpriteOn = false;
        isChangeColorOn = false;

        bitmapTabForOnBtn = new ArrayList<Bitmap>();
        bitmapTabForOnBtn.add(0, mBitmapIcShowDebugScreenOn);
        bitmapTabForOnBtn.add(1, mBitmapIcMakeBounceOn);
        bitmapTabForOnBtn.add(2, mBitmapIcReDrawOn);
        bitmapTabForOnBtn.add(3, mBitmapIcAddPicOn);
        bitmapTabForOnBtn.add(4, mBitmapIcRemovePicOn);
        bitmapTabForOnBtn.add(5, mBitmapIcClearAllPicOn);
        bitmapTabForOnBtn.add(6, mBitmapIcSoundOn);
        bitmapTabForOnBtn.add(7, mBitmapIcShowTextOn);
        bitmapTabForOnBtn.add(8, mBitmapIcFireworksModOn);
        bitmapTabForOnBtn.add(9, mBitmapIcRdmBounceAngleModOn);
        bitmapTabForOnBtn.add(10, mBitmapIcIncreaseSpeedPicOn);
        bitmapTabForOnBtn.add(11, mBitmapIcDecreaseSpeedPicOn);
        bitmapTabForOnBtn.add(12, mBitmapIcStopPicOn);
        bitmapTabForOnBtn.add(13, mBitmapIcRedrawSelectedPicOn);
        bitmapTabForOnBtn.add(14, mBitmapIcChangeColorOn);

        bitmapTabForOffBtn = new ArrayList<Bitmap>();
        bitmapTabForOffBtn.add(0, mBitmapIcShowDebugScreenOff);
        bitmapTabForOffBtn.add(1, mBitmapIcMakeBounceOff);
        bitmapTabForOffBtn.add(2, mBitmapIcReDrawOff);
        bitmapTabForOffBtn.add(3, mBitmapIcAddPicOff);
        bitmapTabForOffBtn.add(4, mBitmapIcRemovePicOff);
        bitmapTabForOffBtn.add(5, mBitmapIcClearAllPicOff);
        bitmapTabForOffBtn.add(6, mBitmapIcSoundOff);
        bitmapTabForOffBtn.add(7, mBitmapIcShowTextOff);
        bitmapTabForOffBtn.add(8, mBitmapIcFireworksModOff);
        bitmapTabForOffBtn.add(9, mBitmapIcRdmBounceAngleModOff);
        bitmapTabForOffBtn.add(10, mBitmapIcIncreaseSpeedPicOff);
        bitmapTabForOffBtn.add(11, mBitmapIcDecreaseSpeedPicOff);
        bitmapTabForOffBtn.add(12, mBitmapIcStopPicOff);
        bitmapTabForOffBtn.add(13, mBitmapIcRedrawSelectedPicOff);
        bitmapTabForOffBtn.add(14, mBitmapIcChangeColorOff);

        booleanTabForBtnState = new ArrayList<Boolean>();
        booleanTabForBtnState.add(0, isMyMenuisShown);
        booleanTabForBtnState.add(1, isBounceOn);
        booleanTabForBtnState.add(2, isReDrawOn);
        booleanTabForBtnState.add(3, isAddPicOn);
        booleanTabForBtnState.add(4, isRemovePicOn);
        booleanTabForBtnState.add(5, isClearAllPicOn);
        booleanTabForBtnState.add(6, isSoundOn);
        booleanTabForBtnState.add(7, isTextDebugIsShown);
        booleanTabForBtnState.add(8, isFireworksModOn);
        booleanTabForBtnState.add(9, isRmdBounceAngleModOn);
        booleanTabForBtnState.add(10, isIncreaseSpeedOn);
        booleanTabForBtnState.add(11, isDecreaseSpeedOn);
        booleanTabForBtnState.add(12, isStopPicOff);
        booleanTabForBtnState.add(13, isReDrawSelectedSpriteOn);
        booleanTabForBtnState.add(14, isChangeColorOn);

        //Objet menu comportant 3 Tableaux : 2 Tableau de Bitmap et 1 de Boolean
        myMenu = new MyMenu(new ArrayList<Bitmap>(), new ArrayList<Bitmap>(), new ArrayList<Boolean>());

        myMenu.setBitmapTabBtnOn(bitmapTabForOnBtn);
        myMenu.setBitmapTabBtnOff(bitmapTabForOffBtn);
        myMenu.setBooleanTabBtnState(booleanTabForBtnState);

        //------------------------------------Fin Init des/ du MyMenu de L'appli---------------------------------------


//        map= new HashMap<String, Boolean>();
//
//        mSize = new Point();
//        windowMana = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
//        mDisplay = windowMana.getDefaultDisplay();
//        mDisplay.getSize(mSize);
//        screenWidth = mDisplay.getWidth();
//        screenHeight = mDisplay.getHeight();


//        mPic1 = new MySprite();
//        mPic1.setxStart(2);
//        mPic1.setyStart(2);
//        mPic1.setxSpeed(5);
//        mPic1.setySpeed(5);
//        mPic1.setxEnd(mPic1.getxStart() + mBitmapSpriteRed.getWidth());
//        mPic1.setyEnd(mPic1.getyStart() + mBitmapSpriteRed.getHeight());
//        mPic1.setmSpriteWidth(mPic1.getxEnd() - mPic1.getxStart());
//        mPic1.setmSpriteHeight(mPic1.getyEnd() - mPic1.getyStart());
//        mPic1.setPicTouchedOnX(false);
//        mPic1.setPicTouchedOnY(false);
//        mPic1.setmBitmap(mBitmapSpriteRed);
//        mPic1.setmMediaPlayer((new MediaPlayer()).create(getContext(), R.raw.oui));
//
//        mPic2 = new MySprite();
//        mPic2.setxStart(200);
//        mPic2.setyStart(100);
//        mPic2.setxSpeed(2);
//        mPic2.setySpeed(2);
//        mPic2.setxEnd(mPic2.getxStart() + mBitmapSpriteRed.getWidth());
//        mPic2.setyEnd(mPic2.getyStart() + mBitmapSpriteRed.getHeight());
//        mPic2.setmSpriteWidth(mPic2.getxEnd() - mPic2.getxStart());
//        mPic2.setmSpriteHeight(mPic2.getyEnd() - mPic2.getyStart());
//        mPic2.setmBitmap(mBitmapSpriteRed);
//        mPic2.setmMediaPlayer((new MediaPlayer()).create(getContext(), R.raw.oui));
//
//        mPic3 = new MySprite();
//        mPic3.setxStart(400);
//        mPic3.setyStart(200);
//        mPic3.setxSpeed(4);
//        mPic3.setySpeed(4);
//        mPic3.setxEnd(mPic3.getxStart() + mBitmapSpriteRed.getWidth());
//        mPic3.setyEnd(mPic3.getyStart() + mBitmapSpriteRed.getHeight());
//        mPic3.setmSpriteWidth(mPic3.getxEnd() - mPic3.getxStart());
//        mPic3.setmSpriteHeight(mPic3.getyEnd() - mPic3.getyStart());
//        mPic3.setmBitmap(mBitmapSpriteRed);
//        mPic3.setmMediaPlayer((new MediaPlayer()).create(getContext(), R.raw.oui));
//
//        tabPics.add(0, mPic1);
//        tabPics.add(1, mPic2);
//        tabPics.add(2, mPic3);
    }


    @Override
    protected void onDraw(Canvas canvas) {          //La fonction/méthode qui sert a crée des elements (Textview Imageview
        super.onDraw(canvas);

        drawTexts(canvas);
        drawMyMenu(canvas, myMenu);
        drawMySprites(canvas);
//        updateCoor();
        runnableUpdateCoor.run();

        increaseCompterFrame1AndSetDefaultBitmap();
//        if (compterTouch > 60) {
//            compterTouch = 0;
//        }


        invalidate();
//        compterTouch++;
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
                onClickOnMySprite(x, y, motionEvent);
                reDrawMySelectedMySpriteHere(x, y);

            }

            if (myMenu.getBooleanTabBtnState().get(0)) {   //Si le menu de myMenu est afficher

                if (x >= (screenWidth - myMenu.getBitmapTabBtnOff().get(0).getWidth() - mMarginPic * 2)) {


                    onClickOnMyMenuBtn(x, y, myMenu);

                    setScreenValues();


                }

            } else { //Si le myMenu n'est pas afficher
                if ((x >= (screenWidth - myMenu.getBitmapTabBtnOff().get(0).getWidth() - mMarginPic * 2)) &&
                        (y <= (myMenu.getBitmapTabBtnOff().get(0).getHeight()) + mMarginPic * 2)) {


                    onClickOnMyMenuBtn(x, y, myMenu);

                    setScreenValues();

                    if (!myMenu.getBooleanTabBtnState().get(1)) {
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
    public void updateCoor() {

        for (int compterFrame = 0; compterFrame < 15; compterFrame++) {
            if (compterFrame == 14) {
//        if (compterTouch > limiteFrame) {

                if (tabPics.size() > 1) {
                    for (int i = 0; i <= tabPics.size() - 1; i++) {
                        moveImg(tabPics.get(i));
                        makeBoundIfCollideOnWall(tabPics.get(i));

                        if (myMenu.getBooleanTabBtnState().get(1)) {
                            for (int j = (i + 1); j <= tabPics.size() - 1; j++) {
                                makeBoundIfCollideOnObject(tabPics.get(i), tabPics.get(j));
                            }
                        }
                    }
                } else if (tabPics.size() == 1) {
                    moveImg(tabPics.get(0));
                    makeBoundIfCollideOnWall(tabPics.get(0));
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
            playSoundCollideAh();
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
        setRandomBitmapForMSprite(mObject1);
        setRandomSpeedForPicIfActivated(mObject1);
        reDrawPicWhenCollideIfActivated(mObject1);

        setTouchedOnXY(mObject2);
        setRandomBitmapForMSprite(mObject2);
        setRandomSpeedForPicIfActivated(mObject2);
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
    private Boolean doActionWhenClickOnMySprite(int x, int y, int id) {

//                reDrawPicFuther(tabPics.get(i));
//                playSoundCollideWow();
        tabPics.get(id).setmBitmap(getRandomBitmap());
        selectedMySprite = tabPics.get(id);


        return true;


    }


    private Boolean onClickOnMySprite(int x, int y, MotionEvent motionEvent) {

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
                    doActionWhenClickOnMySprite(x, y, i);

                }/* else if (motionEvent.getAction() == MotionEvent.ACTION_BUTTON_PRESS) {
//                    reDrawMySelectedMySpriteHere(x, y);
                }*/
                return true;
            } else {
                setDefaultBitmapForAllPic(tabPics);
            }
        }
        return null;
    }

    private void reDrawMySelectedMySpriteHere(int x, int y) {
        if (selectedMySprite != null && booleanTabForBtnState.get(13)) {
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
    private void increaseCompterFrame1AndSetDefaultBitmap() {
        compterFrame1++;
//        compterTouch++;

        //si le compteur est = à 15  /et/  si le rebond est On  /et/  si le Fireworks mod est On
        if (compterFrame1 == 15 && myMenu.getBooleanTabBtnState().get(8)) {
            setDefaultBitmapForAllPic(tabPics);
            compterFrame1 = 0;
        } else if (compterFrame1 > 15) {
            compterFrame1 = 0;
        }
    }

    /**
     * @param mPic
     * @return
     */
    public Boolean reDrawPicWhenCollideIfActivated(MySprite mPic) {

        if (myMenu.getBooleanTabBtnState().get(2)) {

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
     * @param myPic
     */
    private void makeBoundIfCollideOnWall(MySprite myPic) {

        if (!myPic.isPicTouchedOnX()) {
            if ((myPic.getxStart() >= (screenWidth - myPic.getmSpriteWidth()))) {
                myPic.setPicTouchedOnX(true);
                setRandomBitmapForMSprite(myPic);
                setRandomSpeedForPicIfActivated(myPic);
            }
        } else if (myPic.isPicTouchedOnX()) {
            if ((myPic.getxStart() <= 0)) {
                myPic.setPicTouchedOnX(false);
                setRandomBitmapForMSprite(myPic);
                setRandomSpeedForPicIfActivated(myPic);
            }
        }

        if (!myPic.isPicTouchedOnY()) {
            if ((myPic.getyStart() >= ((screenHeight) - myPic.getmSpriteHeight()))) {
                myPic.setPicTouchedOnY(true);
                setRandomBitmapForMSprite(myPic);
                setRandomSpeedForPicIfActivated(myPic);
            }
        } else if (myPic.isPicTouchedOnY()) {
            if ((myPic.getyStart() <= 0)) {
                myPic.setPicTouchedOnY(false);
                setRandomBitmapForMSprite(myPic);
                setRandomSpeedForPicIfActivated(myPic);
            }
        }
    }

    /**
     * @param myPic
     */
    private void moveImg(MySprite myPic) {

//        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        if (myPic.getxStart() + mBitmapDefault.getWidth() <= screenWidth + 50 && myPic.getyStart() + mBitmapDefault.getHeight() <= screenHeight + 50) {
            if (myPic.getxStart() + mBitmapDefault.getWidth() > 0 - 50 && myPic.getyStart() + mBitmapDefault.getHeight() > 0 - 50) {

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
        } else {
            myPic.setxStart(screenWidth / 2);
            myPic.setyStart(screenHeight / 2);
            playSoundCollideWow();
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
    public void playSoundCollideAh() {
        //on joue la musique
        if (myMenu.getBooleanTabBtnState().get(6)) { //si le son de rebond est acctivé

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
    public void playSoundCollideWow() {

        if (mMediaPlayerForPic2.isPlaying()) {     //si la musique est fini
            mMediaPlayerForPic2.stop();                    //on stop le lecteur
            mMediaPlayerForPic2.reset();                   //on le vide
            mMediaPlayerForPic2 = MediaPlayer.create(getContext(), R.raw.wowguy); // on reremplis
        }

        mMediaPlayerForPic2.start(); // on joue

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
        if (myMenu.getBooleanTabBtnState().get(1)) { //si le rebond sur les object sont acctiver
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
    public void setRandomSpeedForPicIfActivated(MySprite myPic) {
        if (myMenu.getBooleanTabBtnState().get(9)) {//si le bouton est acctiver
            myPic.setySpeed(randomNumber.nextInt(9)); // vitesse en 0 et 8
            myPic.setxSpeed(randomNumber.nextInt(9)); // vitesse en 0 et 8
        }
    }


    /**
     * @param mPic
     * @return
     */
    private Bitmap getOtherBitmap(MySprite mPic) {
        if (!mPic.isPicTouchedOnX() && !mPic.isPicTouchedOnY()) {
            return mBitmapSpriteRed;
        } else if (!mPic.isPicTouchedOnX() && mPic.isPicTouchedOnY()) {
            return mBitmapSpriteYellow;
        } else if (mPic.isPicTouchedOnX() && !mPic.isPicTouchedOnY()) {
            return mBitmapSpriteGreen;
        } else if (mPic.isPicTouchedOnX() && mPic.isPicTouchedOnY()) {
            return mBitmapSpritePurple;
        }
        return mBitmapDefault;
    }


    /**
     * @return
     */
    private Bitmap getRandomBitmap() {

        return mBitmapTab.get(randomNumber.nextInt(mBitmapTab.size() - 1));
    }


    /**
     * @param mSprite
     * @return
     */
    private Boolean setRandomBitmapForMSprite(MySprite mSprite) {
        if (myMenu.getBooleanTabBtnState().get(14)) {
            mSprite.setmBitmap(mBitmapTab.get(randomNumber.nextInt(mBitmapTab.size() - 1)));
        }
        return true;
    }


    /**
     * @param mTabSprite
     * @return
     */
    private Boolean setDefaultBitmapForAllPic(ArrayList<MySprite> mTabSprite) {
        for (int i = 0; i < mTabSprite.size(); i++) {
            if (mTabSprite.get(i) != selectedMySprite) {
                mTabSprite.get(i).setmBitmap(mBitmapTab.get(mBitmapTab.size() - 1));
            }
        }
        return true;
    }


    /**
     * @param startX
     * @param startY
     */
    private void addNewMySpriteOnTabPics(int startX, int startY) {
        tabPics.add(
                new MySprite(
                        startX,  // Position en X
                        startY,  // Position en Y
                        randomNumber.nextInt(5) + 3, // sa Vitesse en X
                        randomNumber.nextInt(5) + 3, // sa Vitesse en Y
                        mBitmapSpriteRed.getWidth(), // sa Largeur en X
                        mBitmapSpriteRed.getHeight(), // sa Hauteur en Y
                        mBitmapTab.get(mBitmapTab.size() - 1), //l'image du sprite (ici la noir)
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
    private void removeLastMySpriteOnTabPics() { //enleve le dernier sprite
        if (tabPics.size() > 0) {
            tabPics.remove(tabPics.size() - 1);
        }
    }


    /**
     *
     */
    private void clearAllOnTabPics() {//enleve tout les sprites
        for (int i = tabPics.size() - 1; i >= 0; i--) {
            tabPics.remove(i);
        }
    }


    /**
     * @param x
     * @param y
     * @param myDebugMenu
     * @return
     */
    private Boolean onClickOnMyMenuBtn(int x, int y, MyMenu myDebugMenu) { //écoute le click et fait une acction pour les boutons du MyMenu

        if (!myDebugMenu.getBooleanTabBtnState().get(0)) {

            doActionForBtn(myDebugMenu, 0);

            return true;

        } else {

            for (int i = 0; i <= myDebugMenu.getBooleanTabBtnState().size() - 1; i++) {

                if (i == 0) {  //Pour le cas 0

                    if ((x >= (screenWidth - myDebugMenu.getBitmapTabBtnOff().get(0).getWidth() - mMarginPic * 2) &&
                            (y >= (myDebugMenu.getBitmapTabBtnOff().get(0).getHeight() - mMarginPic * 1)) &&
                            (y <= (myDebugMenu.getBitmapTabBtnOff().get(0).getHeight() + mMarginPic * 2)))) {

                        doActionForBtn(myDebugMenu, i);
                        return true;
                    }

                } else {

                    if ((x >= (screenWidth - myDebugMenu.getBitmapTabBtnOff().get(i).getWidth() - mMarginPic * 2) &&
                            (y >= (myDebugMenu.getBitmapTabBtnOff().get(i).getHeight() + (mMarginPic * 2)) * i) &&
                            (y <= ((myDebugMenu.getBitmapTabBtnOff().get(i).getHeight() + (mMarginPic * 2)) * i) +
                                    (myDebugMenu.getBitmapTabBtnOff().get(i).getHeight() + (mMarginPic * 2))))) {

                        doActionForBtn(myDebugMenu, i);
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
        if (!myMenu.getBooleanTabBtnState().get(0)) {

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
    private void drawMySprites(Canvas canvas) { // déssine tout les sprites

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
    private void drawTexts(Canvas canvas) { // déssine tout les texts

        textNbTouchTotal = "nombre de colisions total: " + nbTouch + " (+" + newNbTouch + ")";
        textNbTouchPerSecondes = "nombre de colisions en cours: " + newNbTouch + " (" + nbCollidePerSecondes + "/s)";
        textNbPic = "nombre d'images : " + tabPics.size();
        textNbTouchPerSecondesMax = "nombre de colisions par seconds Max : " + maxCollidePerSeconds;
//        textNbTouchPerSecondesMax = "rebond : " + myMenu.getBooleanTabBtnState().get(1);
//        text5 = "isReDrawOn : " + myMenu.getBooleanTabBtnState().get(2);
//            text5 = "isMyMenuisShown : " + myMenu.getBooleanTabBtnState().get(0);
//            text6 = "Old ScreenWidth X: " + getWidth();
//            text7 = "Old ScreenHeight Y: " + getHeight();
//            text8 = "New ScreenWidth X: " + screenWidth;
//            text9 = "New ScreenHeight Y: " + screenHeight;
//            text5 = "" + isASecondIsPassed();

        canvas.drawText(textNbPic, 50, 50, mPaint);

        if (myMenu.getBooleanTabBtnState().get(7)) {

//            canvas.drawColor(Color.GREEN);
            canvas.drawText(textNbTouchPerSecondes, 50, 150, mPaint2);
            canvas.drawText(textNbTouchTotal, 50, 200, mPaint2);
            canvas.drawText(textNbTouchPerSecondesMax, 50, 250, mPaint2);
//            canvas.drawText(textNbTouchPerSecondesMax, 50, 300, mPaint);
//            canvas.drawText(text5, 50, 350, mPaint);
//            canvas.drawText(text6, 50, 330, mPaint);
//            canvas.drawText(text7, 50, 360, mPaint);
//            canvas.drawText(text8, 50, 390, mPaint);
//            canvas.drawText(text9, 50, 420, mPaint);
        }
    }

    /**
     * @return
     */
    public Boolean isASecondIsPassed() { // renvois vrais si une seconde est passer
        oldSeconds = seconds;
        millis = System.currentTimeMillis() - starttime;
        seconds = (int) (millis / 1000);
        minutes = seconds / 60;
        seconds = seconds % 60;
        if (seconds > oldSeconds) {
            return true;
        }
        return false;
    }
//    public Boolean isTowSecondsArePassed() {
//            oldSeconds = seconds;
//
//        millis = System.currentTimeMillis() - starttime;
//        seconds = (int) (millis / 1000);
//        minutes = seconds / 60;
////        seconds = seconds % 60;
//        if (isASecondIsPassed()) {
//            if (isASecondIsPassed()) {
//
//                return true;
//            }
//        }
////        if (seconds - veryOldSeconds ==2) {
////
////        }
//
//        return false;
//    }

    /**
     * @param canvas
     * @param myMenu
     */
    private void drawMyMenu(Canvas canvas, MyMenu myMenu) { // déssine les icones du MyMenu
        if (!myMenu.getBooleanTabBtnState().get(0)) {        //Si le menu debug n'est pas afficher

//            mBitmapIcShowDebugScreenOn
            canvas.drawBitmap(      //Dessine l'icone pour l'ouverture
                    myMenu.getBitmapTabBtnOff().get(0),
                    screenWidth - myMenu.getBitmapTabBtnOff().get(0).getWidth() - mMarginPic,
                    mMarginPic,
                    mPaint);

            canvas.drawLine( // Ligne verticale
                    screenWidth - myMenu.getBitmapTabBtnOff().get(0).getWidth() - mMarginPic * 2,
                    0,
                    screenWidth - myMenu.getBitmapTabBtnOff().get(0).getWidth() - mMarginPic * 2,
                    myMenu.getBitmapTabBtnOff().get(0).getHeight() + mMarginPic * 2,
                    mPaint);

            canvas.drawLine(// Ligne Horizontale
                    screenWidth - myMenu.getBitmapTabBtnOff().get(0).getWidth() - mMarginPic * 2,
                    myMenu.getBitmapTabBtnOff().get(0).getHeight() + mMarginPic * 2,
                    screenWidth,
                    myMenu.getBitmapTabBtnOff().get(0).getHeight() + mMarginPic * 2,
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
//                    myMenu.getBitmapTabBtnOn().get(0),
//                    screenWidth + mMarginPic,
//                    myMenu.getBitmapTabBtnOn().get(0).getHeight() * 0 + mMarginPic,
//                    mPaint);

            for (int i = 0; i < myMenu.getBitmapTabBtnOn().size(); i++) {    //Pour chaque icone a affichier Moins la première

                canvas.drawLine(    //Petite Ligne Horizontale Apres l'image
                        screenWidth,
                        (myMenu.getBitmapTabBtnOn().get(i).getHeight() + (mMarginPic * 2)) * (i + 1),
                        screenWidth + myMenuWidth,
                        (myMenu.getBitmapTabBtnOn().get(i).getHeight() + (mMarginPic * 2)) * (i + 1),
                        mPaint);

                //Selon l'état du de l'icone affiche celle conrespondente
                if (myMenu.getBooleanTabBtnState().get(i)) { //Si le boolean de l'icone est On


                    canvas.drawBitmap(
                            myMenu.getBitmapTabBtnOn().get(i),
                            screenWidth + mMarginPic,
                            ((myMenu.getBitmapTabBtnOff().get(i).getHeight() + (2 * mMarginPic)) * i) + mMarginPic,
                            mPaint);

                } else {    //Si le boolean de l'icone est Off
                    canvas.drawBitmap(
                            myMenu.getBitmapTabBtnOff().get(i),
                            screenWidth + mMarginPic,
                            ((myMenu.getBitmapTabBtnOff().get(i).getHeight() + (2 * mMarginPic)) * i) + mMarginPic,
                            mPaint);
                }

            }
        }
    }

    /**
     * @param myMenu
     * @param idMyBtn
     */
    private void doActionForBtn(MyMenu myMenu, int idMyBtn) {
        switch (idMyBtn) {
            case 0:     //Afficher le menu
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 1:     //Acctiver le rebond
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);

                if (!myMenu.getBooleanTabBtnState().get(1)) {
                    nbTouch = nbTouch + newNbTouch;
                    newNbTouch = 0;
                    nbCollidePerSecondes = 0;
                }
                break;
            case 2:     //Acctiver l'anti Chevochement
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 3:     //Ajouter une image
                addNewMySpriteOnTabPics(randomNumber.nextInt(screenWidth), randomNumber.nextInt(screenHeight));
                maxCollidePerSeconds = 0;
                isAddPicOn = false;
                break;
            case 4:     //Supprimer la dernierre image
                removeLastMySpriteOnTabPics();
                maxCollidePerSeconds = 0;
                isReDrawOn = false;
                break;
            case 5:     //Supprimer toute les images
                clearAllOnTabPics();
                nbCollidePerSecondes = 0;
                newNbTouch = 0;
                maxCollidePerSeconds = 0;
                isClearAllPicOn = false;
                break;
            case 6:     //Acctiver les sons
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 7:     //Afficher les textes
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 8:     //Acctiver le menu Fireworks
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 9:     //Acctiver vitesse aléatoire
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 10:     //Augmenter la vitesse de tout les images
                increaseSpeed(1);
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 11:     //Diminuer la vitesse de tout les images
                decreaseSpeed(1);
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 12:     //Arraiter toute les images
                stopAllPic(tabPics);
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 13:     //Redessiner l'image sélectionné
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
                break;
            case 14:     //Acctiver le changement de couleurs
                booleanTabForBtnState.set(idMyBtn, !booleanTabForBtnState.get(idMyBtn));
                myMenu.setBooleanTabBtnState(booleanTabForBtnState);
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
    private void increaseSpeed(int nbIncrease) {
        for (int i = 0; i <= tabPics.size() - 1; i++) {

            if (tabPics.get(i).getxSpeed() >= 0) {
                tabPics.get(i).setxSpeed(tabPics.get(i).getxSpeed() + nbIncrease);
            }
            if (tabPics.get(i).getySpeed() >= 0) {
                tabPics.get(i).setySpeed(tabPics.get(i).getySpeed() + nbIncrease);
            }
        }
    }

    /**
     * @param nbDecrease
     */
    private void decreaseSpeed(int nbDecrease) {
        for (int i = 0; i <= tabPics.size() - 1; i++) {

            if (tabPics.get(i).getxSpeed() >= nbDecrease) {
                tabPics.get(i).setxSpeed(tabPics.get(i).getxSpeed() - nbDecrease);
            }
            if (tabPics.get(i).getySpeed() >= nbDecrease) {
                tabPics.get(i).setySpeed(tabPics.get(i).getySpeed() - nbDecrease);
            }
        }
    }

    /**
     *
     */
    private class MyMenu {
        private ArrayList<Bitmap> bitmapTabBtnOn;
        private ArrayList<Bitmap> bitmapTabBtnOff;
        private ArrayList<Boolean> booleanTabBtnState;

        private String position;
        private Boolean isActivated;
        private Bitmap myMenuIcOn;
        private Bitmap myMenuIcOff;

        public MyMenu() {
        }

        public MyMenu(ArrayList<Bitmap> bitmapTabBtnOn, ArrayList<Bitmap> bitmapTabBtnOff, ArrayList<Boolean> booleanTabBtnState) {
            this.bitmapTabBtnOn = bitmapTabBtnOn;
            this.bitmapTabBtnOff = bitmapTabBtnOff;
            this.booleanTabBtnState = booleanTabBtnState;
        }

        public MyMenu(ArrayList<Bitmap> bitmapTabBtnOn, ArrayList<Bitmap> bitmapTabBtnOff, ArrayList<Boolean> booleanTabBtnState, String position, Boolean isActivated, Bitmap myMenuIcOn, Bitmap myMenuIcOff) {
            this.bitmapTabBtnOn = bitmapTabBtnOn;
            this.bitmapTabBtnOff = bitmapTabBtnOff;
            this.booleanTabBtnState = booleanTabBtnState;
            this.position = position;
            this.isActivated = isActivated;
            this.myMenuIcOn = myMenuIcOn;
            this.myMenuIcOff = myMenuIcOff;
        }

        public Bitmap getMyMenuIcOn() {
            return myMenuIcOn;
        }

        public Bitmap getMyMenuIcOff() {
            return myMenuIcOff;
        }

        public Boolean getActivated() {
            return isActivated;
        }

        public ArrayList<Boolean> getBooleanTabBtnState() {
            return booleanTabBtnState;
        }

        public String getPosition() {
            return position;
        }

        public ArrayList<Bitmap> getBitmapTabBtnOn() {
            return bitmapTabBtnOn;
        }

        public ArrayList<Bitmap> getBitmapTabBtnOff() {
            return bitmapTabBtnOff;
        }

        public void setBitmapTabBtnOff(ArrayList<Bitmap> bitmapTabBtnOff) {
            this.bitmapTabBtnOff = bitmapTabBtnOff;
        }

        public void setBitmapTabBtnOn(ArrayList<Bitmap> tabOfMyButtonOnBitmap) {
            this.bitmapTabBtnOn = tabOfMyButtonOnBitmap;
        }

        public void setBooleanTabBtnState(ArrayList<Boolean> booleanTabBtnState) {
            this.booleanTabBtnState = booleanTabBtnState;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public void setMyMenuIcOn(Bitmap myMenuIcOn) {
            this.myMenuIcOn = myMenuIcOn;
        }

        public void setMyMenuIcOff(Bitmap myMenuIcOff) {
            this.myMenuIcOff = myMenuIcOff;
        }

        public void setActivated(Boolean activated) {
            isActivated = activated;
        }
    }
}
