package com.xp.rps;

public interface RPSRepository {
    int createGame(Game game);
    Game getGame(int id);
}
