package com.example.chess_clock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class BlankFragment extends Fragment {

    private GridView gridView;
    private View view;
    private ArrayList<TimeButton> arrayList = new ArrayList<>();

    public static final ArrayList<TimeControl> timeControls = new ArrayList<>(
            Arrays.asList(
                    new TimeControl("Bullet",1,0),
                    new TimeControl("Bullet",2,1),
                    new TimeControl("Blitz",3,0),
                    new TimeControl("Blitz",3,2),
                    new TimeControl("Blitz",5,0),
                    new TimeControl("Blitz",5,3),
                    new TimeControl("Rapid",10,0),
                    new TimeControl("Rapid",10,5),
                    new TimeControl("Rapid",15,10),
                    new TimeControl("Classical",30,0),
                    new TimeControl("Classical",20,20)
            )
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        createButtons();
        return view;
    }

    private void createButtons() {
        gridView = (GridView) view.findViewById(R.id.buttonGridView);

        for (TimeControl timeControl : timeControls){
            TimeButton button= new TimeButton(getContext(), timeControl);
            arrayList.add(button);
        }

        ButtonArrayAdapter adapter = new ButtonArrayAdapter(getContext(), arrayList);

        gridView.setAdapter(adapter);
    }

}