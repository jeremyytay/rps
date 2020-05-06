package com.xp.rps;

public interface RPSRepository {
    int createGame(Game game);
    Game getGame(int id);
    GameResult recordRoundResult(int id, Round round);
    GameResult getGameResult(int id);
}
