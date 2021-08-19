package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Chronometer cronometroj;
    private boolean running;
    private long pauseoffset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        cronometroj = (Chronometer) findViewById(R.id.cronometro);
        cronometroj.setFormat("Time: %");
        cronometroj.setBase(SystemClock.elapsedRealtime());

        cronometroj.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if((SystemClock.elapsedRealtime()- cronometroj.getBase()) >= 60000){
                    cronometroj.setBase(SystemClock.elapsedRealtime());
                    Toast.makeText(MainActivity.this, "bing!",Toast.LENGTH_SHORT);

                }
            }
        });

    }


    public void starChronometer (View s){
        if (!running){
            cronometroj.setBase(SystemClock.elapsedRealtime()-pauseoffset);
            cronometroj.start();
            running = true;
        }
    }

    public void pauseChronometer (View s){
        if(running){
            cronometroj.stop();
            pauseoffset=SystemClock.elapsedRealtime() - cronometroj.getBase();
            running=false;
        }
    }

    public void stopChronometer (View s){
        cronometroj.setBase(SystemClock.elapsedRealtime());
        pauseoffset = 0;
    }

    @Override
    public void onClick(View v) {

    }
}
