package com.xp.rps;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

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
        GameResult gameResult = new GameResult();
        gameResult.setGame(game);
        gameResultRepo.put(id, gameResult);
        return id;
    }

    @Override
    public Game getGame(int id) {
        return gameRepo.get(id);
    }

    @Override
    public GameResult recordRoundResult(int id, Round round) {
        GameResult gameResult = gameResultRepo.get(id);
        Game game = gameResult.getGame();
        List<Round> roundList = gameResult.getRoundList();
        if(roundList.size() < game.getRound()) {
            roundList.add(round);
            Result finalResult = gameResult.getResult();
            gameResult.setFinalResult(finalResult);
            gameResult.setRoundList(roundList);
            gameResultRepo.put(id, gameResult);
        }
        return gameResult;
    }

    @Override
    public GameResult getGameResult(int id) {
        return gameResultRepo.get(id);
    }
}
