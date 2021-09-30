package com.example.chess_clock;

import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private final TimeControl timeControl;
    private int remainingTime1, remainingTime2;
    private boolean whiteToPlay = true;
    private boolean gameIsOn = false;
    private boolean gameOver = false;
    private Timer timer;
    private final GameActivity gameActivity;

    public Game(GameActivity gameActivity, TimeControl timeControl) {
        this.gameActivity = gameActivity;
        this.timeControl = timeControl;
        this.remainingTime1 = timeControl.getTime()*(60*10);
        this.remainingTime2 = timeControl.getTime()*(60*10);
    }

    private void createTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                timeCalculate();
            }
        },0, 100);
    }

    private void timeCalculate() {
        if(whiteToPlay){
            remainingTime1--;
            setTimeTextOnTheButton(gameActivity.getButton1(), remainingTime1);
            if(remainingTime1==0){
                gameOver = true;
                timer.cancel();
                gameActivity.getButton1().setBackgroundColor(Color.RED);
            }
        }else{
            remainingTime2--;
            setTimeTextOnTheButton(gameActivity.getButton2(), remainingTime2);
            if(remainingTime2==0){
                gameOver = true;
                timer.cancel();
                gameActivity.getButton2().setBackgroundColor(Color.RED);
            }
        }
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean isGameIsOn() {
        return gameIsOn;
    }

    public TimeControl getTimeControl() {
        return timeControl;
    }

    public int getRemainingTime1() {
        return remainingTime1;
    }

    public void setRemainingTime1(int remainingTime1) {
        this.remainingTime1 = remainingTime1;
    }

    public int getRemainingTime2() {
        return remainingTime2;
    }

    public void setRemainingTime2(int remainingTime2) {
        this.remainingTime2 = remainingTime2;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isWhiteToPlay() {
        return whiteToPlay;
    }

    public void setWhiteToPlay(boolean whiteToPlay) {
        this.whiteToPlay = whiteToPlay;
    }

    public void setTimeTextOnTheButton(Button button, int remainingTime) {
        int timeToSeconds = remainingTime/10;
        String minutes = String.valueOf(timeToSeconds/60);
        String seconds = String.valueOf(timeToSeconds%60/10) + String.valueOf(timeToSeconds%10);
        String finalString = minutes + ":" + seconds + "." + remainingTime % 10;
        button.setText(finalString);
    }

    public void startPauseAction(ImageButton startPauseButton) {
        if(gameIsOn){
            gameActivity.getButton1().setBackgroundColor(Color.LTGRAY);
            gameActivity.getButton2().setBackgroundColor(Color.LTGRAY);
            timer.cancel();
            startPauseButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_72);
        }else {
            if(isWhiteToPlay()) gameActivity.getButton1().setBackgroundColor(Color.GREEN);
            else gameActivity.getButton2().setBackgroundColor(Color.GREEN);
            createTimer();
            startPauseButton.setImageResource(R.drawable.ic_baseline_pause_circle_filled_72);
        }
        gameIsOn = !gameIsOn;
    }
}
