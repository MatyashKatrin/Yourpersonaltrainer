package com.example.user.myapplication;

import android.app.Activity;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    Button inspirationsButton;
    Button achivementsButton;
    Button exercisesButton;
    Button newRunButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        exercisesButton = (Button) findViewById(R.id.exercises);
//        achivementsButton = (Button) findViewById(R.id.achievements);
        inspirationsButton = (Button) findViewById(R.id.inspirations);
//        newRunButton = (Button) findViewById(R.id.newRun);




        idzDoClick(exercisesButton, Exercises.class);
        idzDoClick(inspirationsButton, Inspirations.class);
//        idzDoClick(achivementsButton, Achivements.class);
        idzDoClick(exercisesButton, Exercises.class);
//        idzDoClick(newRunButton, RunningActivity.class);
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
