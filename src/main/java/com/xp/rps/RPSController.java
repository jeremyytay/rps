package com.xp.rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RPSController {

    private RPSRepository rpsRepository;

    @Autowired
    public RPSController(RPSRepository rpsRepository) {
        this.rpsRepository = rpsRepository;
    }

    @PostMapping("/game")
    public int createGame(@RequestBody Game game) {
        int id = rpsRepository.createGame(game);
        return id;
    }

    @GetMapping("/game/{id}")
    public Game getGame(@PathVariable int id) {
        return rpsRepository.getGame(id);
    }

    @PostMapping("/play/{id}")
    public Round playGame(@PathVariable int id, @RequestBody Round round) {
        Result result = RPS.play(round.getThrow1(), round.getThrow2());
        round.setResult(result);
        GameResult gameResult = rpsRepository.getGameResult(id);
        if(gameResult == null) {
            Game game = rpsRepository.getGame(id);
            gameResult = new GameResult(game);
        }
        gameResult.addRound(round);
        rpsRepository.recordRoundResult(gameResult);
        return round;
    }

    @GetMapping("/result/{id}")
    public GameResult getResult(@PathVariable int id) {
        return rpsRepository.getGameResult(id);
    }
}
