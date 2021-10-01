package com.example.chess_clock;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.function.Consumer;

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
            game.createPlayers();
        }
    }

    private void addMenuButtonListeners() {
        homeButton.setOnClickListener(v -> createQuitDialog());
        startPauseButton.setOnClickListener(v -> game.startPauseAction(startPauseButton));
        restartButton.setOnClickListener(v -> createRestartDialog());
    }

    private void createRestartDialog() {
        createDialog(() -> {
            if(game.getTimer() != null) game.getTimer().cancel();
            disableButton(game.getActivatePlayer().getPlayersButton());
            createNewGame();
            startPauseButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_72);
        }, "Do you want to restart?");
    }

    private void createQuitDialog() {
        createDialog(() -> finish(), "Do you want to quit?");
    }

    private void createDialog(Runnable runnable, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setPositiveButton("Yes", (dialog, id) -> runnable.run());
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

    public void enableButton(Button button) {
        button.setBackgroundColor(Color.BLUE);
        button.setTextColor(Color.WHITE);
    }

    public void disableButton(Button button) {
        button.setBackgroundColor(Color.LTGRAY);
        button.setTextColor(Color.BLACK);
    }

}