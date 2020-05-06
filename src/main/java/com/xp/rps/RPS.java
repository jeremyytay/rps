package com.xp.rps;

public class RPS {
    public static Result play(Throw player1, Throw player2) {
        if(player1 == player2) {
            return Result.DRAW;
        }

        if((Throw.SCISSORS == player1 && Throw.PAPER == player2) ||
                (Throw.ROCK == player1 && Throw.SCISSORS == player2) ||
                (Throw.PAPER == player1 && Throw.ROCK == player2)) {
            return Result.P1_WINS;
        }

        return Result.P2_WINS;
    }
}
