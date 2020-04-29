package com.example.user.myapplication;

import android.util.Log;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public class YouTubeVideoPlayingService {

    public static YouTubePlayer.OnInitializedListener getOnInitListener(final String link){
        return new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(link);
                Log.i("Yutubsik", "WasHere");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };
    }

}
