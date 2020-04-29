package com.example.user.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class Inspirations extends AppCompatActivity{

    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;
    ImageView img7;
    ImageView img8;
    ImageView img9;
    ImageView img10;
    ImageView img11;
    ImageView img12;
    ImageView img13;
    ImageView img14;
    ImageView img15;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tapety_layout);

        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        img4 = (ImageView)findViewById(R.id.img4);
        img5 = (ImageView)findViewById(R.id.img5);
        img6 = (ImageView)findViewById(R.id.img6);
        img7 = (ImageView)findViewById(R.id.img7);
        img8 = (ImageView)findViewById(R.id.img8);
        img9 = (ImageView)findViewById(R.id.img9);
        img10 = (ImageView)findViewById(R.id.img10);
        img11 = (ImageView)findViewById(R.id.img11);
        img12 = (ImageView)findViewById(R.id.img12);
        img13 = (ImageView)findViewById(R.id.img13);
        img14 = (ImageView)findViewById(R.id.img14);
        img15 = (ImageView)findViewById(R.id.img15);

        img1.setTag(R.drawable.fajnie);
        img2.setTag(R.drawable.img2);
        img3.setTag(R.drawable.img3);
        img4.setTag(R.drawable.img4);
        img5.setTag(R.drawable.img5);
        img6.setTag(R.drawable.img6);
        img7.setTag(R.drawable.img7);
        img8.setTag(R.drawable.img8);
        img9.setTag(R.drawable.img9);
        img10.setTag(R.drawable.img10);
        img11.setTag(R.drawable.img11);
        img12.setTag(R.drawable.img12);
        img13.setTag(R.drawable.img13);
        img14.setTag(R.drawable.img14);
        img15.setTag(R.drawable.qwe);

        img1.setOnClickListener(getOnClick(img1));
        img2.setOnClickListener(getOnClick(img2));
        img3.setOnClickListener(getOnClick(img3));
        img4.setOnClickListener(getOnClick(img4));
        img5.setOnClickListener(getOnClick(img5));
        img6.setOnClickListener(getOnClick(img6));
        img7.setOnClickListener(getOnClick(img7));
        img8.setOnClickListener(getOnClick(img8));
        img9.setOnClickListener(getOnClick(img9));
        img10.setOnClickListener(getOnClick(img10));
        img11.setOnClickListener(getOnClick(img11));
        img12.setOnClickListener(getOnClick(img12));
        img13.setOnClickListener(getOnClick(img13));
        img14.setOnClickListener(getOnClick(img14));
        img15.setOnClickListener(getOnClick(img15));
    }

    public View.OnClickListener getOnClick(final ImageView imageView){

        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingWallpaperActivity.class);
                intent.putExtra("tapeta", String.valueOf((int) imageView.getTag()));
                startActivity(intent);
            }
        };

    }
}
