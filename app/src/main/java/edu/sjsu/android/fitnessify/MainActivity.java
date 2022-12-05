package edu.sjsu.android.fitnessify;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;
import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {
    // declare variable
    private static final int TIME = 2000, ACTIVITY = 123456;
    private long bp;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, ACTIVITY);
        }
        changeFragment(new Home());
        SharedPreferences preferences_shared = getSharedPreferences(getString(R.string.consumer), MODE_PRIVATE);
        String s1 = preferences_shared.getString(getString(R.string.email_id), "");
        SmoothBottomBar bar = findViewById(R.id.navigation_bar);
        bar.setOnItemSelectedListener((OnItemSelectedListener) res -> {
            // switch condition for home, fitness, login fragment and profile
            switch (res){
                case 0:
                    changeFragment(new Home());
                    break;

                case 1:
                    changeFragment(new Fitness());
                    break;

                case 2:
                    if(s1.equals(""))
                        changeFragment(new LoginFragment());
                    else
                        changeFragment(new Profile());
                    break;
            }
            return true;
        });
    }

    // changeFragment method
    public void changeFragment(Fragment f) {
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frame,f);
        t.commit();
    }

    // onBackPressed method
    @Override
    public void onBackPressed(){
        if(bp + TIME > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            return;
        }
        else{
            Toast.makeText(getBaseContext(),R.string.press_back,Toast.LENGTH_SHORT).show();
        }
        bp = System.currentTimeMillis();
    }
}