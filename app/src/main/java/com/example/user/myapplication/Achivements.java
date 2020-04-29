package com.example.user.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class Achivements extends AppCompatActivity{

    ArrayList<Rekord> rekordsArray = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achivements);

        RekordsAdapter rekordsAdapter = new RekordsAdapter(rekordsArray);


        RecyclerView rekordsView = (RecyclerView)findViewById(R.id.recordsListView);

        rekordsView.setAdapter(rekordsAdapter);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rekordsView.setLayoutManager(mLayoutManager);

        DBHelperTapeta dbHelperTapeta = new DBHelperTapeta(getApplicationContext());
        for(int i = 0; i < dbHelperTapeta.getAllRekords().size(); i++){
            rekordsArray.add(0, dbHelperTapeta.getAllRekords().get(i));
        }

        rekordsAdapter.notifyDataSetChanged();

    }
}
