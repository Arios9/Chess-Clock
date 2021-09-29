package com.example.chess_clock;

import android.widget.Button;

public class Game {

    private  TimeControl timeControl;
    private int remainingTime1, remainingTime2;

    public Game(TimeControl timeControl) {
        this.timeControl = timeControl;
        this.remainingTime1 = timeControl.getTime()*(60*10);
        this.remainingTime2 = timeControl.getTime()*(60*10);
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
}
