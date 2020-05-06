package com.xp.rps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RPSRepositoryTest {
    private RPSRepository rpsRepository;

    private Game game;

    @BeforeEach
    public void setUp() {
        rpsRepository = new InMemoryRepo();
        game = new Game("player1", "player2", 3);
    }

    @Test
    public void testCreateGame() {
        int id = rpsRepository.createGame(game);
        assertEquals(1, id);
    }

    @Test
    public void testGetGame() {
        rpsRepository.createGame(game);
        Game result = rpsRepository.getGame(1);
        assertEquals(1, result.getId());
        assertEquals("player1", result.getPlayer1());
        assertEquals("player2", result.getPlayer2());
    }
}
