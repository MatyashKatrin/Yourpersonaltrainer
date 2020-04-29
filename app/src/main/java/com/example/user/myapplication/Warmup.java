package com.example.user.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class Warmup extends YouTubeBaseActivity {

    YouTubePlayerView warmupVideo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warmup);

        Log.i("WarmUp", "WasHere");
        warmupVideo = (YouTubePlayerView)findViewById(R.id.warmupVideo);
        warmupVideo.initialize(Youtube.getApiKey(), YouTubeVideoPlayingService.getOnInitListener(
                "Ks-lKvKQ8f4"));


    }
}
