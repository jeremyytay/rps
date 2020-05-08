package com.xp.rps;

import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
public class InMemoryRepo implements RPSRepository {

    HashMap<Integer, Game> gameRepo = new HashMap<>();
    HashMap<Integer, GameResult> gameResultRepo = new HashMap<>();
    int sequence = 0;

    @Override
    public int createGame(Game game) {
        int id = ++sequence;
        game.setId(id);
        gameRepo.put(id, game);
        return id;
    }

    @Override
    public Game getGame(int id) {
        return gameRepo.get(id);
    }

    @Override
    public int recordRoundResult(GameResult gameResult) {
        gameResultRepo.put(gameResult.getGame().getId(), gameResult);
        return gameResult.getGame().getId();
    }

    @Override
    public GameResult getGameResult(int id) {
        return gameResultRepo.get(id);
    }
}
