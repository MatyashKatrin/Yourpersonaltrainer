package com.example.user.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Arms extends YouTubeBaseActivity {

    YouTubePlayerView ArmsVideo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warmup);

        Log.i("WarmUp", "WasHere");
        ArmsVideo = (YouTubePlayerView)findViewById(R.id.warmupVideo);
        ArmsVideo.initialize(Youtube.getApiKey(), YouTubeVideoPlayingService.getOnInitListener(
                "BPYnqtI0ZAM"));


    }
}
