package com.example.smartcctv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video_view extends AppCompatActivity {
    String url_passed;
    String videoUrl = "https://4f28-2401-4900-499c-a871-58bd-f14b-e358-8efd.in.ngrok.io/videos/14-03-2023-16-18-45.mp4";
//    String videoUrl = "https://media.geeksforgeeks.org/wp-content/uploads/20201217192146/Screenrecorder-2020-12-17-19-17-36-828.mp4?_=1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);
        VideoView videoView = findViewById(R.id.videoView);
        Intent intent = getIntent();
        url_passed = intent.getStringExtra("url");

        // Uri object to refer the
        // resource from the videoUrl
        Uri uri = Uri.parse(url_passed+".mp4");

        // sets the resource from the
        // videoUrl to the videoView
        videoView.setVideoURI(uri);

        // creating object of
        // media controller class
        MediaController mediaController = new MediaController(this);

        // sets the anchor view
        // anchor view for the videoView
        mediaController.setAnchorView(videoView);

        // sets the media player to the videoView
        mediaController.setMediaPlayer(videoView);

        // sets the media controller to the videoView
        videoView.setMediaController(mediaController);

        // starts the video
        videoView.start();
    }
}