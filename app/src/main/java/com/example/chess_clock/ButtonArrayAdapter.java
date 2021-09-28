package com.example.chess_clock;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ButtonArrayAdapter extends ArrayAdapter<TimeButton> {
    public ButtonArrayAdapter(@NonNull Context context, ArrayList<TimeButton> resource) {
        super(context,0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.time_button, parent, false);
        }

        TextView time = convertView.findViewById(R.id.timeText);
        TextView type = convertView.findViewById(R.id.typeText);

        TimeButton timeButton = getItem(position);
        TimeControl timeControl = timeButton.getTimeControl();
        time.setText(timeControl.getTime() + " + " +timeControl.getIncrement());
        type.setText(timeControl.getType());

        return convertView;
    }
}
