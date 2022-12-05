package edu.sjsu.android.fitnessify;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Home extends Fragment implements SensorEventListener {
    // home page
    // declare card, text view and enable the sensor manager to count the steps
    boolean isRunning = false;
    CardView cv;
    TextView tv, number_of_steps;
    SensorManager sensor;

    public Home() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // fragment view field
        View fragment_view = inflater.inflate(R.layout.fragment_home, container, false);
        sensor = (SensorManager) requireActivity().getSystemService( Context.SENSOR_SERVICE);
        tv = fragment_view.findViewById(R.id.sliding_text);
        number_of_steps = fragment_view.findViewById(R.id.steps_chart_count);
        cv = fragment_view.findViewById(R.id.gym_workout);
        tv.setSelected(true);
        cv.setOnClickListener(res -> startActivity(new Intent(getActivity(),Exercise.class)));
        return fragment_view;
    }


    // onResume method
    @Override
    public void onResume() {
        super.onResume ();
        isRunning = true;
        Sensor count = sensor.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        // warning if sensor not found
        if(count== null)
            Toast.makeText ( getActivity(),R.string.no_sensor,Toast.LENGTH_SHORT ).show();
        else
            sensor.registerListener ( this,count,SensorManager.SENSOR_DELAY_UI );
    }

    // onPause method
    @Override
    public void onPause() {
        super.onPause ();
        isRunning = false;
    }
    // Sensor changed event if running
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isRunning)
            number_of_steps.setText(String.valueOf(event.values[0]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}