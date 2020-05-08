package com.xp.rps;

public class Game {
    private int id;
    private String player1;
    private String player2;
    private int round;

    public Game() {
    }

    public Game(int id, String player1, String player2, int round) {
        this.id = id;
        this.player1 = player1;
        this.player2 = player2;
        this.round = round;
    }

    public Game(String player1, String player2, int round) {
        this.player1 = player1;
        this.player2 = player2;
        this.round = round;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
