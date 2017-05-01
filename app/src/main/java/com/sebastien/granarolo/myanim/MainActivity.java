
package com.sebastien.granarolo.myanim;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamField;
import java.io.OutputStream;
import java.util.Random;

//on implémente View.OnClickListener pour pouvoir utiliser onclick etc. à confirmer
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private HomeFragment homeFrag = (new HomeFragment());
    private ImageView mImageView;
    Random randomNumber = new Random();
    private String TAG = "MainActivity";
    private File file;
    private MediaPlayer mMediaPlayerForOui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setFragment(cameraFrag);                //on charge le fragment Home
        setFragment(homeFrag);


    }

    public void setFragment(Fragment frag) {
        getFragmentManager().beginTransaction().replace(R.id.content, frag).commit();   //booon voilà quoi
    }





    public void openActivityOther(View view) {                                          //un autre "setFragment" d'activity pour voir y faire d'autre animation


    }

    @Override
    public void onClick(View v) {           //méthode implémenter de force car on a implément l'interface View.OnClickListener

    }

    @Override
    protected void onStop() {               //idem
        super.onStop();
//        mTransaction.cancel();
    }
}

/*
package com.sebastien.granarolo.myanim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
    }

    */
/**
 * A native method that is implemented by the 'native-lib' native library,
 * which is packaged with this application.
 *//*

    public native String stringFromJNI();
}
*/
