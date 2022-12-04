package edu.sjsu.android.fitnessify;

import android.os.Bundle;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;


public class Diet extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet);
        webView = findViewById(R.id.diet_web_view);
        webView.loadUrl(getString(R.string.diet_page));
    }
}