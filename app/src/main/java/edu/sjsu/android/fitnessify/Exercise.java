package edu.sjsu.android.fitnessify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Exercise extends AppCompatActivity {
    CardView card1, card2, card3, card4, card5, card6, card7, card8;

    private void navigate_to_uri(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        card1 = findViewById(R.id.push_ups_card);
        card2 = findViewById(R.id.pull_ups_card);
        card3 = findViewById(R.id.squats_card);
        card4 = findViewById(R.id.t_plank_card);
        card5 = findViewById(R.id.dead_bug_card);
        card6 = findViewById(R.id.skipping_card);
        card7 = findViewById(R.id.heavy_squat);
        card8 = findViewById(R.id.split_jump_card);


        card1.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri(getString(R.string.pushup_url));
        });

        card2.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri(getString(R.string.pullups_url));
        });

        card3.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri(getString(R.string.squats_url));
        });

        card4.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri(getString(R.string.t_plank_url));
        });

        card5.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri(getString(R.string.dead_bug_url));
        });

        card6.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri(getString(R.string.skipping_url));
        });

        card7.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri(getString(R.string.heavysquat_url));
        });

        card8.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri(getString(R.string.split_jump_url));
        });

    }
}
