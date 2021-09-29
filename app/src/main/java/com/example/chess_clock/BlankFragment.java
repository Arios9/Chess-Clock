package com.example.chess_clock;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


public class BlankFragment extends Fragment {

    private GridView gridView;
    private View view;
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
                    new TimeControl("Classical",30,20)
            )
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        createGridViewItems();
        return view;
    }

    private void createGridViewItems() {
        gridView = view.findViewById(R.id.timeControlsGridView);
        TimeControlArrayAdapter adapter = new TimeControlArrayAdapter(getContext(), timeControls);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> createNewGame(parent.getItemAtPosition(position)));
    }

    private void createNewGame(Object itemAtPosition) {
        Intent intent = new Intent(getContext(), GameActivity.class);
        startActivity(intent);
    }


}