package com.bitrock.game.goose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bitrock.game.goose.Game.Status;

@RestController
public class RestControllers {

	@Autowired
	Players players;
	@Autowired
	Games games;

	@RequestMapping("/")
	public String index() {
		return "Greetings!";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addPlayer(@RequestParam String name) {

		if (players.playerExists(name)) {
			return new ResponseEntity<>(name + ": already existing player", HttpStatus.BAD_REQUEST);
		}

		players.add(name);
		String gameId = players.getGameIdForPlayer(name);

		String playerNames = "";
		for (String s : players.getPlayersForGameId(gameId)) {
			playerNames += s + " ";
		}

		return new ResponseEntity<>("players: " + playerNames, HttpStatus.OK);

	}

	@RequestMapping(value = "/move", method = RequestMethod.POST)
	public ResponseEntity<Object> movePlayer(@RequestParam String name, @RequestBody(required = false) Roll roll) {

		if (!players.playerExists(name)) {
			return new ResponseEntity<>(name + ": player does not exist", HttpStatus.BAD_REQUEST);
		}

		if (roll == null) {
			roll = new Roll();
		} else {
			if (!roll.isValid()) {
				return new ResponseEntity<>(roll.getFirst() + "," + roll.getSecond() + ": invalid roll",
						HttpStatus.BAD_REQUEST);
			}
		}

		String gameId = players.getGameIdForPlayer(name);
		Game game = games.getGame(gameId);

		String result = game.movePlayer(name, roll);

		if (game.getStatus().equals(Status.ENDED)) {
			games.removeGame(gameId);
			players.remove(name);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);

	}

}
