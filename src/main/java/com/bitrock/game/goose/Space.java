package com.bitrock.game.goose;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Space {

	private int index;

	public static final int BRIDGE_START_SPACE = 6;
	public static final int BRIDGE_END_SPACE = 12;
	public static final Set<Integer> GOOSE_SPACES = new HashSet<>(Arrays.asList(5, 9, 14, 18, 23, 27));
	public static final int FINAL_SPACE = 63;

	public Space(int index) {
		this.index = index;
	}

	public int getIndex() {
		return this.index;
	}

	public boolean isBridge() {
		return (index == BRIDGE_START_SPACE);
	}

	public void bridge() {
		this.index = BRIDGE_END_SPACE;
	}

	public boolean isGoose() {
		return GOOSE_SPACES.contains(this.index);
	}

	public boolean isBounce() {
		return this.index > FINAL_SPACE;
	}

	public void bounce(int roll) {
		int startIndex = this.index - roll;
		int bounce = roll - (FINAL_SPACE - startIndex);
		this.index = FINAL_SPACE - bounce;
	}

	public boolean isWinner() {
		return (index == FINAL_SPACE);
	}

}
