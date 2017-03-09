package com.task.vasskob.customtab;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.OrientationListener;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.home)
    ImageButton homeBtn;
    @Bind(R.id.notification)
    ImageButton notificationBtn;
    @Bind(R.id.search)
    ImageButton searchBtn;
    @Bind(R.id.profile)
    ImageButton profileBtn;
    @Bind(R.id.camera)
    ImageButton cameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main);
        lockScreenOrientation();
        ButterKnife.bind(this);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraBtn.setSelected(!cameraBtn.isSelected());
            }
        });
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setSelected(!homeBtn.isSelected());
            }
        });
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBtn.setSelected(!searchBtn.isSelected());
            }
        });
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notificationBtn.setSelected(!notificationBtn.isSelected());
            }
        });
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileBtn.setSelected(!profileBtn.isSelected());
            }
        });

//        SensorManager sensorMan = (SensorManager) getSystemService(SENSOR_SERVICE);
//        Sensor sensor = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
//        sensorMan.registerListener(new  );
    }

    public void lockScreenOrientation() {
        switch (((WindowManager) getSystemService(WINDOW_SERVICE))
                .getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_90:
                setContentView(R.layout.activity_main);
                break;
            case Surface.ROTATION_180:
                setContentView(R.layout.activity_main);
                break;
            case Surface.ROTATION_270:
                setContentView(R.layout.activity_main_reverse);
                break;
            default :
                setContentView(R.layout.activity_main);
        }
    }
}
