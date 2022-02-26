package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean start;
    SeekBar seekBar;
    TextView textFiled;
    CountDownTimer countDownTimer;
    public void updateTimer(int secondsLeft)
    {
        int minutes=(int)secondsLeft/60; 
        int secondes=(int) secondsLeft-minutes*60;
        String secondsString=String.valueOf(secondes);
        if(secondes<=9)
        {
             secondsString="0" + secondsString;
        }

        textFiled.setText(Integer.toString(minutes ) + " : " + secondsString );
    }
    public void buttonStartStop(View view)
{
    Button myButton=(Button) findViewById(R.id.StartStopButton);


    if(start==true)
    {
        start=false;
        myButton.setText("Stop");
        seekBar.setEnabled(false);
        int barProgress=seekBar.getProgress();


         countDownTimer = new CountDownTimer(barProgress*1000 +100, 1000) {
            public void onTick(long Coundown){
                    updateTimer((int) Coundown/1000);

            }

            public void onFinish() {

                textFiled.setText("Finished");

            }
        }.start();

    }else {
        start =true;
        seekBar.setEnabled(true);
        myButton.setText("Start");
        countDownTimer.cancel();


    }


}


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=true;
        seekBar=(SeekBar) findViewById(R.id.seekBar);
        textFiled=(TextView) findViewById(R.id.TimeTextField);
        seekBar.setMax(600);
        seekBar.setProgress(3);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int min =1;


                if(progress<min)
                {
                    seekBar.setProgress(min);
                   // textFiled.setText(progress);
                }
               updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });





    }



}

