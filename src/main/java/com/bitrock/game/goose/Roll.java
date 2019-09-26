package com.bitrock.game.goose;

import java.util.concurrent.ThreadLocalRandom;

public class Roll {

	private int first;
	private int second;

	public Roll() {
		this.first = ThreadLocalRandom.current().nextInt(1, 7);
		this.second = ThreadLocalRandom.current().nextInt(1, 7);
	}

	public Roll(int first, int second) {
		this.first = first;
		this.second = second;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}

	private boolean isValid(int n) {
		return (n > 0 && n < 7);
	}

	public boolean isValid() {
		return isValid(this.first) && isValid(this.second);
	}

	public int total() {
		return first + second;
	}

	public boolean isDouble() {
		return (first == second);
	}

}
