package com.bitrock.game.goose;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Players {

	@Autowired
	Games games;
	// this map associates player name and his game id
	private Map<String, String> players = new HashMap<String, String>();

	public boolean playerExists(String name) {
		return players.containsKey(name);
	}

	public void add(String name) {
		String gameId = games.getGame();
		games.getGame(gameId).addPlayer(name);
		players.put(name, gameId);
	}

	public void remove(String name) {
		players.remove(name);
	}

	public String getGameIdForPlayer(String name) {
		return players.get(name);
	}

	public Set<String> getPlayersForGameId(String gameId) {
		Set<String> playersForGameId = new HashSet<>();
		for (Entry<String, String> entry : players.entrySet()) {
			if (entry.getValue().equals(gameId)) {
				playersForGameId.add(entry.getKey());
			}
		}
		return playersForGameId;
	}

}
