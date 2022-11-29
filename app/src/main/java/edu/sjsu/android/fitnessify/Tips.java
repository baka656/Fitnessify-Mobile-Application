package edu.sjsu.android.fitnessify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import java.util.Objects;

public class Tips extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        Objects.requireNonNull(getSupportActionBar()).hide();
        webView = findViewById(R.id.webview2);
        webView.loadUrl("https://www.self.com/story/popular-at-home-workout-programs");

    }
}