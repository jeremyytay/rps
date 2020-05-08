package com.xp.rps;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JdbcRepoTest {

    private JdbcRepo jdbcRepo;

    private JdbcTemplate jdbcTemplate;

    private Game game;

    private GameResult gameResult;

    private Round round;

    private List<Round> roundList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://arjuna.db.elephantsql.com:5432/flvfvxyx?user=flvfvxyx&password=WWHAAcZbzFWunAX89aIS1YHMtf1hopui");
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcRepo = new JdbcRepo(jdbcTemplate);

        game = new Game(1, "Ben", "Tom", 3);
        round = new Round(1, Throw.ROCK, Throw.SCISSORS);
        roundList.add(round);
        gameResult = new GameResult(game, roundList);
    }

    @Test
    @Disabled
    public void testCreateGame() {
        int gameId = jdbcRepo.createGame(game);
        assertTrue(gameId > 0);
    }

    @Test
    @Disabled
    public void testGetGame() {
        int gameId = 1;
        game = jdbcRepo.getGame(gameId);
        assertEquals(gameId, game.getId());
        assertEquals("RAYMOND", game.getPlayer1());
        assertEquals("RAE", game.getPlayer2());
        assertEquals(2, game.getRound());
    }

    @Test
    @Disabled
    public void testRecordRoundResult() {
        int gameId = jdbcRepo.recordRoundResult(gameResult);
        assertEquals(1,gameId);
    }

    @Test
    @Disabled
    public void testGetGameResult() {
        int gameId = 1;
        gameResult = jdbcRepo.getGameResult(gameId);
        assertEquals(gameId, gameResult.getGame().getId());
        assertEquals(Result.P1_WINS, gameResult.getResult());
    }
}
