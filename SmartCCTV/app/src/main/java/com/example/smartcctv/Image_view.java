package com.example.smartcctv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Image_view extends AppCompatActivity {
    String url_passed;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
//        imageView = findViewById(R.id.image_view);
        Intent intent = getIntent();
        url_passed = intent.getStringExtra("url");
//        webView = (WebView) findViewById(R.id.webview);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("https://4f28-2401-4900-499c-a871-58bd-f14b-e358-8efd.in.ngrok.io/img/01-03-2023-12-08-11.png");

        // Find the WebView by its unique ID
        WebView webView = findViewById(R.id.web);

        // loading http://www.google.com url in the WebView.
//        webView.loadUrl("https://4f28-2401-4900-499c-a871-58bd-f14b-e358-8efd.in.ngrok.io/img/01-03-2023-12-08-11.png");
        webView.loadUrl(""+url_passed+".png");

        // this will enable the javascript.
        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
        webView.setWebViewClient(new WebViewClient());
    }
}