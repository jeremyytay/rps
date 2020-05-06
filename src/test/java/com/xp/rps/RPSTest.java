package com.xp.rps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPSTest {

    @Test
    public void rockVsPaper() {
        assertEquals(Result.P2_WINS, RPS.play(Throw.ROCK, Throw.PAPER));
    }

    @Test
    public void rockVsScissors() {
        assertEquals(Result.P1_WINS, RPS.play(Throw.ROCK, Throw.SCISSORS));
    }

    @Test
    public void paperVsScissors() {
        assertEquals(Result.P2_WINS, RPS.play(Throw.PAPER, Throw.SCISSORS));
    }

    @Test
    public void paperVsRock() {
        assertEquals(Result.P1_WINS, RPS.play(Throw.PAPER, Throw.ROCK));
    }

    @Test
    public void scissorsVsRock() {
        assertEquals(Result.P2_WINS, RPS.play(Throw.SCISSORS, Throw.ROCK));
    }

    @Test
    public void scissorsVsPaper() {
        assertEquals(Result.P1_WINS, RPS.play(Throw.SCISSORS, Throw.PAPER));
    }

    @Test
    public void paperVsPaper() {
        assertEquals(Result.DRAW, RPS.play(Throw.PAPER, Throw.PAPER));
    }

    @Test
    public void rockVsRock() {
        assertEquals(Result.DRAW, RPS.play(Throw.ROCK, Throw.ROCK));
    }

    @Test
    public void scissorsVsScissors() {
        assertEquals(Result.DRAW, RPS.play(Throw.SCISSORS, Throw.SCISSORS));
    }
}
