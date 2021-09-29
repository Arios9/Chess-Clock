package com.example.chess_clock;

import android.widget.Button;
import android.widget.ImageButton;

public class Game {

    private  TimeControl timeControl;
    private int remainingTime1, remainingTime2;
    private boolean whiteToPlay = true;
    private boolean gameIsOn = false;

    public Game(TimeControl timeControl) {
        this.timeControl = timeControl;
        this.remainingTime1 = timeControl.getTime()*(60*10);
        this.remainingTime2 = timeControl.getTime()*(60*10);
    }

    public boolean isGameIsOn() {
        return gameIsOn;
    }

    public void setGameIsOn(boolean gameIsOn) {
        this.gameIsOn = gameIsOn;
    }

    public TimeControl getTimeControl() {
        return timeControl;
    }

    public void setTimeControl(TimeControl timeControl) {
        this.timeControl = timeControl;
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


    public void setTimeTextOnTheButton(Button button, int remainingTime) {
        int timeToSeconds = remainingTime/10;
        String minutes = String.valueOf(timeToSeconds/60);
        String seconds = String.valueOf(timeToSeconds%60/10) + String.valueOf(timeToSeconds%10);
        String finalString = minutes + ":" + seconds + "." + remainingTime % 10;
        button.setText(finalString);
    }

    public void startPauseAction(ImageButton startPauseButton) {
        if(gameIsOn){
            startPauseButton.setImageResource(R.drawable.ic_baseline_pause_circle_filled_72);
        }else {
            startPauseButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_72);
        }
        gameIsOn = !gameIsOn;
    }
}
