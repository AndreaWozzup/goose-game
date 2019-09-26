package com.bitrock.game.goose;

import java.util.HashMap;
import java.util.Map;

import com.bitrock.game.goose.Board.Result;

public class Game {

	static final int MAX_PLAYER_NUMBER = 8;

	enum Status {
		AVAILABLE, IN_PROGRESS, ENDED
	}

	private Status status = Status.AVAILABLE;
	private String winner;
	private Board board;

	public Game() {
		this.status = Status.AVAILABLE;
		this.winner = "";
		this.players = new HashMap<String, Integer>();
		this.board = new Board();
	}

	private Map<String, Integer> players;

	public boolean isAvailable() {
		return (status.equals(Status.AVAILABLE));
	}

	public void addPlayer(String name) {
		players.put(name, 1);
		if (players.size() == MAX_PLAYER_NUMBER) {
			this.status = Status.IN_PROGRESS;
		}
	}

	public String movePlayer(String name, Roll roll) {
		Result result = board.move(name, players.get(name), roll, "");
		players.put(name, result.getPosition());
		if (result.getPosition() == 63) {
			this.status = Status.ENDED;
		}
		return result.getDescription();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

}
