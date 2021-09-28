package com.example.chess_clock;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.NonNull;

public class TimeButton extends androidx.appcompat.widget.AppCompatButton {

    private TimeControl timeControl;

    public TimeButton(@NonNull Context context, TimeControl timeControl) {
        super(context);
        this.timeControl = timeControl;
    }

    public TimeControl getTimeControl() {
        return timeControl;
    }
}
