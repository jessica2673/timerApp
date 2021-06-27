package com.firstapp.timerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    //problem is this line
    //EditText inputTime = (EditText) findViewById(R.id.txtInput);
    //int time = Integer.parseInt(inputTime.getText().toString());

    int time = 300000;
    long startTime = time;

    TextView countDownDisplay;
    Button startPause;
    Button reset, btnEnter;

    long timeLeft = startTime;
    CountDownTimer countDown;
    boolean running;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEnter = findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enter();
            }
        });
        startTime = time;
        timeLeft = startTime;

        countDownDisplay = findViewById(R.id.txtTimeDisplay);
        updateCountDownText();

        startPause = findViewById(R.id.btnStart);
        reset = findViewById(R.id.btnRestart);

        startPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (running) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();
    }


    public void enter(){
        EditText inputTime = findViewById(R.id.txtInput);
        if(inputTime.getText().toString().length() == 0){
            time = 6000;
        }
        else{
            time = Integer.parseInt(inputTime.getText().toString());
        }
        updateCountDownText();
        resetTimer();
    }

    public void startTimer(){
        countDown = new CountDownTimer(timeLeft, 1000){
            @Override
            public void onTick(long millis){
                timeLeft = millis;
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                running = false;
                startPause.setText("Start");
                startPause.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);
            }
        }.start();
        running = true;
        startPause.setText("pause");
        reset.setVisibility(View.INVISIBLE);
    }

    public void pauseTimer(){
        countDown.cancel();
        running = false;
        startPause.setText("Start");
        reset.setVisibility(View.VISIBLE);

    }

    public void resetTimer(){
        timeLeft = startTime;
        updateCountDownText();
        reset.setVisibility(View.INVISIBLE);
        startPause.setVisibility(View.VISIBLE);
    }

    public void updateCountDownText(){
        int mins = (int) (timeLeft/1000)/60;
        int secs = (int) (timeLeft/1000) % 60;

        String timeFormat = String.format(Locale.getDefault(), "%02d:%02d", mins, secs);

        countDownDisplay.setText(timeFormat);
    }

}





















