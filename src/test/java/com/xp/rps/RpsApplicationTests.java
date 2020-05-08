package com.xp.rps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RpsApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void createGame() {
		ResponseEntity<Integer> response = restTemplate.postForEntity("/game",
				new Game("player1", "player2", 3), Integer.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody() > 0);
	}

	@Test
	public void getGame() {
		Game game = new Game("player1", "player2", 3);
		ResponseEntity<Integer> createGameResponse = restTemplate.postForEntity("/game", game, Integer.class);

		ResponseEntity<Game> response = restTemplate.getForEntity("/game/1", Game.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(createGameResponse.getBody(), response.getBody().getId());
		assertEquals(game.getPlayer1(), response.getBody().getPlayer1());
		assertEquals(game.getPlayer2(), response.getBody().getPlayer2());
	}

	@Test
	public void playGame() {
		Game game = new Game("player1", "player2", 3);
		ResponseEntity<Integer> createGameResponse = restTemplate.postForEntity("/game", game, Integer.class);

		Round round = new Round(Throw.PAPER, Throw.ROCK);
		ResponseEntity<Round> response = restTemplate.postForEntity("/play/1", round, Round.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(Result.P1_WINS, response.getBody().getResult());
		assertEquals(Throw.PAPER, response.getBody().getThrow1());
		assertEquals(Throw.ROCK, response.getBody().getThrow2());
	}

	@Test
	public void getGameResult() {
		Game game = new Game("player1", "player2", 3);
		ResponseEntity<Integer> createGameResponse = restTemplate.postForEntity("/game", game, Integer.class);
		Round round = new Round(Throw.PAPER, Throw.ROCK);
		ResponseEntity<Round> playResponse = restTemplate.postForEntity("/play/1", round, Round.class);

		ResponseEntity<GameResult> response = restTemplate.getForEntity("/result/1", GameResult.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(Result.P1_WINS, response.getBody().getResult());
	}

}
