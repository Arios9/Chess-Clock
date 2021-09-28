package com.example.chess_clock;

public class TimeControl {

    private String type;
    private int time;
    private int increment;

    public TimeControl(String type, int time, int increment) {
        this.type = type;
        this.time = time;
        this.increment = increment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }
}
