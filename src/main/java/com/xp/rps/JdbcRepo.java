package com.xp.rps;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class JdbcRepo implements RPSRepository{

    private JdbcTemplate jdbcTemplate;

    private final String SQL_GET_GAME = "SELECT * FROM GAME WHERE ID=?";
    private final String SQL_CREATE_GAME = "INSERT INTO GAME (PLAYER1, PLAYER2, ROUND) VALUES (?,?,?)";

    private final String SQL_GET_ROUND = "SELECT * FROM ROUND WHERE GAME_ID=?";
    private final String SQL_CREATE_ROUND = "INSERT INTO ROUND (GAME_ID, THROW1, THROW2, RESULT) VALUES (?,?,?,?)";

    public JdbcRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int createGame(Game game) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_CREATE_GAME, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, game.getPlayer1());
            ps.setString(2, game.getPlayer2());
            ps.setInt(3, game.getRound());
            return ps;
        }, keyHolder);
        return (int)keyHolder.getKeys().get("id");
    }

    @Override
    public Game getGame(int id) {
        Game game = jdbcTemplate.queryForObject(SQL_GET_GAME, new Object[]{id}, ((rs, rowNum) ->
                new Game(rs.getInt("id"),
                        rs.getString("player1"),
                        rs.getString("player2"),
                        rs.getInt("round"))
        ));
        return game;
    }

    @Override
    public int recordRoundResult(GameResult gameResult) {
        for(int i = 0; i < gameResult.getRoundList().size(); i++) {
            Round round = gameResult.getRoundList().get(i);
            if(round.getRoundId() == 0) {
                jdbcTemplate.update(SQL_CREATE_ROUND,
                        gameResult.getGame().getId(),
                        round.getThrow1().toString(),
                        round.getThrow2().toString(),
                        round.getResult().toString());
            }
        }
        return gameResult.getGame().getId();
    }

    @Override
    public GameResult getGameResult(int id) {
        Game game = this.getGame(id);
        List<Round> roundList = jdbcTemplate.query(SQL_GET_ROUND, new Object[]{id}, ((rs, rowNum) ->
                new Round(rs.getInt("id"),
                        Throw.valueOf(rs.getString("throw1")),
                        Throw.valueOf(rs.getString("throw2")),
                        Result.valueOf(rs.getString("result")))
        ));
        GameResult gameResult = new GameResult();
        gameResult.setGame(game);
        gameResult.setRoundList(roundList);
        return gameResult;
    }
}
