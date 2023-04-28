package com.example.smartcctv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

public class Livestream extends AppCompatActivity {
    VideoView videoView;
    MediaController mediaController;
    ProgressBar progressBar;
//    Intent intent = getIntent();

//    String url = intent.getStringExtra("url");
//    WebView webView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livestream);
        WebView webView = findViewById(R.id.live_stream);
//
//        // loading http://www.google.com url in the WebView.
        webView.loadUrl("http://192.168.14.163:8080/");

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());

    }
}