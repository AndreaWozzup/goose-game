package com.bitrock.game.goose;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bitrock.game.goose.Board.Result;

public class BoardTests {

	@Test
	public void testVictory() {
		Board board = new Board();

		Roll roll = new Roll(1, 2);
		Result result = board.move("Pippo", 60, roll, "");

		assertEquals(result.getPosition(), 63);

	}

	@Test
	public void testBounce() {
		Board board = new Board();

		Roll roll = new Roll(3, 2);
		Result result = board.move("Pippo", 60, roll, "");

		assertEquals(result.getPosition(), 61);

	}

	@Test
	public void testDiceRoll() {
		Board board = new Board();

		Roll roll = new Roll(1, 2);
		Result result = board.move("Pippo", 4, roll, "");

		assertEquals(result.getPosition(), 7);

	}

	@Test
	public void testBridge() {
		Board board = new Board();

		Roll roll = new Roll(1, 1);
		Result result = board.move("Pippo", 4, roll, "");

		assertEquals(result.getPosition(), 12);

	}

	@Test
	public void testGooseSingleJump() {
		Board board = new Board();

		Roll roll = new Roll(1, 1);
		Result result = board.move("Pippo", 3, roll, "");

		assertEquals(result.getPosition(), 7);

	}

	@Test
	public void testGooseMultipleJump() {
		Board board = new Board();

		Roll roll = new Roll(2, 2);
		Result result = board.move("Pippo", 10, roll, "");

		assertEquals(result.getPosition(), 22);

	}

}
