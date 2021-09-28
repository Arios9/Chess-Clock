package com.example.chess_clock;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;

import java.util.ArrayList;


public class BlankFragment extends Fragment {

    private GridView gridView;
    private View view;

    static final String[] numbers = new String[] {
            "A", "B", "C", "D", "E",
            "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"};

    private ArrayList<Button> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_blank, container, false);
        createButtons();
        return view;
    }

    private void createButtons() {
        gridView = (GridView) view.findViewById(R.id.buttonGridView);

        for (int i=0; i<9; i++){
            int finalI = i;
            Button button= new androidx.appcompat.widget.AppCompatButton(getContext()){
                @Override
                public String toString() {
                    return String.valueOf("Bullet \n"+finalI);
                }
            };
            arrayList.add(button);
        }

        ArrayAdapter<Button> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, arrayList);

        gridView.setAdapter(adapter);
    }

}