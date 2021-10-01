package com.example.chess_clock;

import android.graphics.Color;
import android.widget.Button;

public class Player {

    private Game game;
    private Button playersButton;
    private Player opponent;
    private int remainingTime;

    public Player(Game game, Button playersButton) {
        this.game = game;
        this.playersButton = playersButton;
        this.remainingTime = game.getTimeControl().getTime()*(60*10);
    }

    public void setTimeTextOnTheButton() {
        int timeToSeconds = remainingTime/10;
        String minutes = String.valueOf(timeToSeconds/60);
        String seconds = String.valueOf(timeToSeconds%60/10) + String.valueOf(timeToSeconds%10);
        String finalString = minutes + ":" + seconds + "." + remainingTime % 10;
        playersButton.setText(finalString);
    }

    public void timeCalculate() {
        remainingTime--;
        setTimeTextOnTheButton();
        if(remainingTime == 0){
            game.gameOver();
            playersButton.setBackgroundColor(Color.RED);
        }
    }

    public Button getPlayersButton() {
        return playersButton;
    }

    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    public void addButtonListener() {
        playersButton.setOnClickListener(v -> {
            if(game.isGameOver())return;
            if(!game.isGameIsOn())return;
            if(!game.getActivatePlayer().equals(this))return;
            game.setActivatePlayer(opponent);
            remainingTime += 10*game.getTimeControl().getIncrement();
            setTimeTextOnTheButton();
            opponent.getPlayersButton().setBackgroundColor(Color.GREEN);
            playersButton.setBackgroundColor(Color.LTGRAY);
        });
        setTimeTextOnTheButton();
    }
}
