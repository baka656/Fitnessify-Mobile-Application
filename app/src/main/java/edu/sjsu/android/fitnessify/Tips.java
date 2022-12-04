package edu.sjsu.android.fitnessify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class Tips extends AppCompatActivity {

    WebView home_workout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        // For the tips, when user click on tips button, it will take them to nytimes website.
        home_workout = findViewById(R.id.tips_web_view);
        // load the url
        home_workout.loadUrl(getString(R.string.tips_url));
    }
}