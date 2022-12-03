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
        home_workout = findViewById(R.id.tips_web_view);
        home_workout.loadUrl("https://www.nytimes.com/guides/well/strength-training-plyometrics");
    }
}