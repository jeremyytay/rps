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
}
