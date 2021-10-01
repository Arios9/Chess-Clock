package com.example.chess_clock;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.chess_clock.TimeControlFragment.TIME_CONTROL_EXTRA_TEXT;

public class GameActivity extends AppCompatActivity {

    private ImageButton homeButton, startPauseButton, restartButton;
    private Button button1, button2;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setOnSystemUiVisibilityChangeListener();
        setButtons();
        createNewGame();
        addMenuButtonListeners();
        playButtonListeners();
    }

    private void playButtonListeners() {
        button1.setOnClickListener(v -> {
            if(game.isGameOver())return;
            if(!game.isGameIsOn())return;
            if(!game.getActivatedButton().equals(button1))return;
            game.setActivatedButton(button2);
            game.setRemainingTime1(game.getRemainingTime1()+10*game.getTimeControl().getIncrement());
            game.setTimeTextOnTheButton(button1, game.getRemainingTime1());
            button2.setBackgroundColor(Color.GREEN);
            button1.setBackgroundColor(Color.LTGRAY);
        });
        button2.setOnClickListener(v -> {
            if(game.isGameOver())return;
            if(!game.isGameIsOn())return;
            if(!game.getActivatedButton().equals(button2))return;
            game.setActivatedButton(button1);
            game.setRemainingTime2(game.getRemainingTime2()+10*game.getTimeControl().getIncrement());
            game.setTimeTextOnTheButton(button2, game.getRemainingTime2());
            button1.setBackgroundColor(Color.GREEN);
            button2.setBackgroundColor(Color.LTGRAY);
        });
    }

    private void setButtons() {
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        homeButton = findViewById(R.id.imageButtonHome);
        startPauseButton = findViewById(R.id.startPauseButton);
        restartButton = findViewById(R.id.imageButtonRestart);
    }

    public Button getButton1() {
        return button1;
    }

    public Button getButton2() {
        return button2;
    }

    private void createNewGame() {
        if(getIntent().hasExtra(TIME_CONTROL_EXTRA_TEXT)){
            TimeControl timeControl = getIntent().getParcelableExtra(TIME_CONTROL_EXTRA_TEXT);
            game = new Game(this, timeControl);
            game.setTimeTextOnTheButton(button1, game.getRemainingTime1());
            game.setTimeTextOnTheButton(button2, game.getRemainingTime2());
        }
    }

    private void addMenuButtonListeners() {
        homeButton.setOnClickListener(v -> createQuitDialog());
        startPauseButton.setOnClickListener(v -> game.startPauseAction(startPauseButton));
        restartButton.setOnClickListener(v -> createRestartDialog());
    }

    private void createRestartDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to restart?");
        builder.setPositiveButton("Yes", (dialog, id) -> {
            if(game.getTimer() != null) game.getTimer().cancel();
            createNewGame();
            startPauseButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_72);
            button1.setBackgroundColor(Color.LTGRAY);
            button2.setBackgroundColor(Color.LTGRAY);
        });
        builder.setNegativeButton("No", (dialog, id) -> {});
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void createQuitDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Do you want to quit?");
        builder.setPositiveButton("Yes", (dialog, id) -> finish());
        builder.setNegativeButton("No", (dialog, id) -> {});
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setOnSystemUiVisibilityChangeListener() {
        getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(visibility -> {
            if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0)
                new Handler().postDelayed(() -> hideSystemUI(), 1000);
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) hideSystemUI();
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


}