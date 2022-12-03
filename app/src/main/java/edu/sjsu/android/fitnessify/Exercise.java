package edu.sjsu.android.fitnessify;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Exercise extends AppCompatActivity {
    CardView cd1,cd2,cd3,cd4,cd5,cd6,cd7,cd8;

    private void navigate_to_uri(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        cd1 = findViewById(R.id.push_ups_card);
        cd2 = findViewById(R.id.pull_ups_card);
        cd3 = findViewById(R.id.squats_card);
        cd4 = findViewById(R.id.t_plank_card);
        cd5 = findViewById(R.id.dead_bug_card);
        cd6 = findViewById(R.id.skipping_card);
        cd7 = findViewById(R.id.heavy_squat);
        cd8 = findViewById(R.id.split_jump_card);

        cd1.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri("https://www.youtube.com/watch?v=IODxDxX7oi4");
        });

        cd2.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri("https://www.youtube.com/watch?v=eGo4IYlbE5g");
        });

        cd3.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri("https://www.youtube.com/watch?v=YaXPRqUwItQ");
        });

        cd4.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri("https://www.youtube.com/watch?v=rTY5mqJ1HNo");
        });

        cd5.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri("https://www.youtube.com/watch?v=4XLEnwUr1d8");
        });

        cd6.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri("https://www.youtube.com/watch?v=u3zgHI8QnqE");
        });

        cd7.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri("https://www.youtube.com/watch?v=Uv_DKDl7EjA");
        });

        cd8.setOnClickListener(res -> {
            Toast.makeText(Exercise.this, R.string.opening_youtube, Toast.LENGTH_SHORT).show();
            navigate_to_uri("https://www.youtube.com/watch?v=qsF1gYTWTrQ");
        });
    }
}
