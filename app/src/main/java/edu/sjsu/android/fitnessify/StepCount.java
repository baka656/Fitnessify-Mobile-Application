package edu.sjsu.android.fitnessify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class StepCount extends AppCompatActivity implements SensorEventListener {
    private static final int PHYSICAL_ACTIVITY = 123456;
    TextView tv_steps;

    SensorManager sensorManager;
    Sensor sensor;
    boolean running = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_step_count );
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACTIVITY_RECOGNITION) == PackageManager.PERMISSION_DENIED){
            //ask for permission
            requestPermissions(new String[]{Manifest.permission.ACTIVITY_RECOGNITION}, PHYSICAL_ACTIVITY);
        }
        tv_steps = (TextView) findViewById ( R.id.stepsinpiechart );

        sensorManager = (SensorManager) getSystemService ( Context.SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        super.onResume ();
        running = true;
        Sensor countSensor = sensorManager.getDefaultSensor ( sensor.TYPE_STEP_COUNTER );
        if(countSensor!= null){
            sensorManager.registerListener ( this,countSensor,SensorManager.SENSOR_DELAY_UI );
        }else {
            Toast.makeText ( this,"SENSOR NOT FOUND",Toast.LENGTH_SHORT ).show ();
        }
    }

    @Override
    protected void onPause() {
        super.onPause ();
        running = false;
        //if you unregister the hardware will stop detecting steps
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (running){
            tv_steps.setText ( String.valueOf ( event.values[0] ) );
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}