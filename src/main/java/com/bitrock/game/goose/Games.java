package com.bitrock.game.goose;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Games {

	private HashMap<String, Game> games = new HashMap<String, Game>();

	public String getGame() {

		String gameId = null;

		for (Entry<String, Game> entry : games.entrySet()) {
			if (entry.getValue().isAvailable()) {
				gameId = entry.getKey();
			}
		}

		if (gameId == null) {
			// no game available, let's create a new one
			gameId = UUID.randomUUID().toString();
			games.put(gameId, new Game());
		}

		return gameId;

	}

	public long size() {
		return games.size();
	}

	public Game getGame(String gameId) {
		return games.get(gameId);
	}

	public void removeGame(String gameId) {
		games.remove(gameId);
	}

}
