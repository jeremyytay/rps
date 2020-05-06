package com.xp.rps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RPSController {

    @Autowired
    private RPSRepository rpsRepository;

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
    public Result playGame(@PathVariable int id, @RequestBody Round userInput) {
        Result result = RPS.play(userInput.getThrow1(), userInput.getThrow2());
        userInput.setResult(result);
        rpsRepository.recordRoundResult(id, userInput);
        return result;
    }

    @GetMapping("/result/{id}")
    public GameResult getResult(@PathVariable int id) {
        return rpsRepository.getGameResult(id);
    }
}
