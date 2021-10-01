package com.example.chess_clock;

import android.graphics.Color;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Timer;
import java.util.TimerTask;

public class Game {

    private final TimeControl timeControl;
    private Player activatePlayer;
    private boolean gameIsOn = false;
    private boolean gameOver = false;
    private Timer timer;
    private final GameActivity gameActivity;

    public Game(GameActivity gameActivity, TimeControl timeControl) {
        this.gameActivity = gameActivity;
        this.timeControl = timeControl;
    }

    private void createTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                activatePlayer.timeCalculate();
            }
        },0, 100);
    }

    public Timer getTimer() {
        return timer;
    }

    public boolean isGameIsOn() {
        return gameIsOn;
    }

    public TimeControl getTimeControl() {
        return timeControl;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public Player getActivatePlayer() {
        return activatePlayer;
    }

    public void setActivatePlayer(Player activatePlayer) {
        this.activatePlayer = activatePlayer;
    }

    public GameActivity getGameActivity() {
        return gameActivity;
    }

    public void startPauseAction(ImageButton startPauseButton) {
        if(gameOver) return;
        if(gameIsOn){
            gameActivity.disableButton(activatePlayer.getPlayersButton());
            timer.cancel();
            startPauseButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_72);
        }else {
            gameActivity.enableButton(activatePlayer.getPlayersButton());
            createTimer();
            startPauseButton.setImageResource(R.drawable.ic_baseline_pause_circle_filled_72);
        }
        gameIsOn = !gameIsOn;
    }

    public void createPlayers() {
        Player player1 = new Player(this, gameActivity.getButton1());
        Player player2 = new Player(this, gameActivity.getButton2());
        player1.setOpponent(player2);
        player2.setOpponent(player1);
        player1.addButtonListener();
        player2.addButtonListener();
        activatePlayer = player1;
    }

    public void gameOver() {
        gameOver = true;
        timer.cancel();
    }
}
