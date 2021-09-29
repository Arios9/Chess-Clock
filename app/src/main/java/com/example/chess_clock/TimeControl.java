package com.example.chess_clock;

import android.os.Parcel;
import android.os.Parcelable;

public class TimeControl implements Parcelable {

    private String type;
    private int time;
    private int increment;

    public TimeControl(String type, int time, int increment) {
        this.type = type;
        this.time = time;
        this.increment = increment;
    }

    protected TimeControl(Parcel in) {
        type = in.readString();
        time = in.readInt();
        increment = in.readInt();
    }

    public static final Creator<TimeControl> CREATOR = new Creator<TimeControl>() {
        @Override
        public TimeControl createFromParcel(Parcel in) {
            return new TimeControl(in);
        }

        @Override
        public TimeControl[] newArray(int size) {
            return new TimeControl[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeInt(time);
        dest.writeInt(increment);
    }
}
