package com.example.user.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class Exercises extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);

        Button armsButton = (Button) findViewById(R.id.arms);
        Button chestButton = (Button) findViewById(R.id.chest);
        Button absButton = (Button) findViewById(R.id.abs);
        Button backButton = (Button) findViewById(R.id.back);
        Button warmButton = (Button) findViewById(R.id.warmup);
        Button legsButton = (Button) findViewById(R.id.legs);
        Button bumsButton = (Button) findViewById(R.id.bums);
        Button strechButton = (Button) findViewById(R.id.strech);


        idzDoClick(armsButton, Arms.class);
        idzDoClick(chestButton, Chest.class);
        idzDoClick(absButton, Abs.class);
        idzDoClick(backButton, Back.class);
        idzDoClick(warmButton, Warmup.class);
        idzDoClick(legsButton, Legs.class);
        idzDoClick(bumsButton, Bums.class);
        idzDoClick(strechButton, Strech.class);
    }

    public void idzDoClick (Button button, final Class javaClass){
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goTo(javaClass);
            }
        });
    }
    public void goTo (Class javaClass){
        Intent idzDoBack = new Intent(getApplicationContext(), javaClass);
        startActivity(idzDoBack);
    }
}