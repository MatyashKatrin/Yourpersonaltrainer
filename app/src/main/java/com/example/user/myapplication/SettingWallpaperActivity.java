package com.example.user.myapplication;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.io.IOException;

public class SettingWallpaperActivity extends AppCompatActivity {

    ImageView tapetaImg;
    ImageView acceptTapeta;
    ImageView cancelTapeta;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ustawienia_tapety);
        tapetaImg = (ImageView)findViewById(R.id.tapetaImg);
        acceptTapeta = (ImageView)findViewById(R.id.acceptTapeta);
        cancelTapeta = (ImageView)findViewById(R.id.cancelTapeta);

        tapetaImg.setImageResource(Integer.parseInt(getIntent().getStringExtra("tapeta")));

        acceptTapeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WallpaperManager myWallpaperManager
                        = WallpaperManager.getInstance(getApplicationContext());



                try {
                    myWallpaperManager.setResource(Integer.parseInt(getIntent().getStringExtra("tapeta")));
                } catch (IOException e) {

                    e.printStackTrace();
                }

                finish();
            }
        });
        cancelTapeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
