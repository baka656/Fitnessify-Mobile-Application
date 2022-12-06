package edu.sjsu.android.fitnessify;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class BMI extends AppCompatActivity {
    // setup the current default value of weight, age, and height
    int int_weight = 55, int_age = 22, current_progress;
    String sample_weight ="55", sample_age ="22", user_type ="0", int_progress ="170";
    TextView current_height, current_weight, current_age;
    RelativeLayout male, female;
    SeekBar bar_height;
    ImageView increment_age, decrement_age, increment_weight, decrement_weight;
    Button calculate_bmi;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        // fragment view field
        increment_weight =findViewById(R.id.increase_wt);
        current_weight =findViewById(R.id.weight_curr);
        current_height =findViewById(R.id.current_ht);
        increment_age =findViewById(R.id.increment_age);
        decrement_age =findViewById(R.id.decrement_age);
        male =findViewById(R.id.male);
        female =findViewById(R.id.female);
        bar_height =findViewById(R.id.ht_seek_bar);
        decrement_weight =findViewById(R.id.decrement_weight);
        calculate_bmi =findViewById(R.id.bmi_calc);
        current_age =findViewById(R.id.curr_age);

        // male and female click listener
        male.setOnClickListener(res -> {
            user_type =getString(R.string.male);
            female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.gendernotfocused));
            male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.genderfocused));
        });

        female.setOnClickListener(res -> {
            male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.gendernotfocused));
            user_type =getString(R.string.female_text);
            female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.genderfocused));
        });


        // set up max height
        bar_height.setMax(300);
        bar_height.setProgress(170);
        bar_height.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seek_bar, int sample_progress, boolean may_be_from_user) {
                current_progress =sample_progress;
                int_progress =String.valueOf(current_progress);
                current_height.setText(int_progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // set the listener
        increment_age.setOnClickListener(res -> {
            int_age = int_age +1;
            sample_age =String.valueOf(int_age);
            current_age.setText(sample_age);
        });

        decrement_age.setOnClickListener(res -> {
            int_age = int_age -1;
            sample_age =String.valueOf(int_age);
            current_age.setText(sample_age);
        });

        increment_weight.setOnClickListener(res -> {
            int_weight = int_weight +1;
            sample_weight =String.valueOf(int_weight);
            current_weight.setText(sample_weight);
        });

        decrement_weight.setOnClickListener(res -> {
            int_weight = int_weight -1;
            sample_weight =String.valueOf(int_weight);
            current_weight.setText(sample_weight);
        });

        calculate_bmi.setOnClickListener(res -> {
            if(user_type.equals("0"))
                Toast.makeText(getApplicationContext(),R.string.gender_select,Toast.LENGTH_SHORT).show();
            else if(int_progress.equals("0"))
                Toast.makeText(getApplicationContext(),R.string.height_select,Toast.LENGTH_SHORT).show();
            else if(int_age ==0 || int_age <0)
                Toast.makeText(getApplicationContext(),R.string.incorrect_age,Toast.LENGTH_SHORT).show();
            else if(int_weight ==0|| int_weight <0)
                Toast.makeText(getApplicationContext(),R.string.incorrect_weight,Toast.LENGTH_SHORT).show();
            else {
                Intent extra = new Intent(BMI.this, BMIResult.class);
                extra.putExtra(getString(R.string.l_gender), user_type);
                extra.putExtra(getString(R.string.l_height), int_progress);
                extra.putExtra(getString(R.string.l_weight), sample_weight);
                extra.putExtra(getString(R.string.l_age), sample_age);
                startActivity(extra);
            }
        });


    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(BMI.this,MainActivity.class));
    }
}