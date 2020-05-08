package com.xp.rps;

import java.util.ArrayList;
import java.util.List;

public class GameResult {
    private Game game;
    private List<Round> roundList = new ArrayList<>();
    private Result finalResult;

    public GameResult() {
    }

    public GameResult(Game game) {
        this.game = game;
    }

    public Result getResult() {
        Result result = Result.DRAW;
        int p1 = 0;
        int p2 = 0;
        for(Round round : roundList) {
            if(round.getResult() == Result.P1_WINS) {
                p1++;
            } else if(round.getResult() == Result.P2_WINS) {
                p2++;
            }
        }
        if(p1 > p2) {
            result = Result.P1_WINS;
        } else if(p2 > p1) {
            result = Result.P2_WINS;
        }
        return result;
    }

    public void addRound(Round round) {
        roundList.add(round);
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public List<Round> getRoundList() {
        return roundList;
    }

    public void setRoundList(List<Round> roundList) {
        this.roundList = roundList;
    }

    public void setFinalResult(Result finalResult) {
        this.finalResult = finalResult;
    }
}
