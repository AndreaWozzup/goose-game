package com.bitrock.game.goose;

public class Board {

	public class Result {
		private int position;
		private String description;

		public Result(int position, String description) {
			this.position = position;
			this.description = description;
		}

		public int getPosition() {
			return this.position;
		}

		public String getDescription() {
			return this.description;
		}
	}

	private String rollDescription(String name, Roll roll) {
		return name + " rolls " + roll.getFirst() + ", " + roll.getSecond() + ". ";
	}

	private String moveDescription(String name, int start, Space position) {
		return name + " moves from " + start + " to " + position.getIndex() + ".";
	}

	private String gooseDescription(String name, int start, Space position) {
		return name + " moves from " + start + " to " + position.getIndex() + ", The Goose. ";
	}

	private String moveAgainDescription(String name, int start, Space position) {
		return name + " moves again from " + start + " to " + position.getIndex() + ".";
	}

	private String bridgeDescription(String name, int start) {
		return name + " moves from " + start + " to The Bridge. " + name + " jumps to 12.";
	}

	private String bounceDescription(String name, int start, Space position) {
		return name + " moves from " + start + " to 63. " + name + " bounces! " + name + " returns to "
				+ position.getIndex();
	}

	public Result move(String name, int start, Roll roll, String description) {

		Space position = new Space(start + roll.total());

		if (position.isGoose()) {
			if (description.equals("")) {
				description = rollDescription(name, roll);
				description += gooseDescription(name, start, position);
			} else {
				description += moveAgainDescription(name, start, position);
			}
			return move(name, position.getIndex(), roll, description);
		}

		if (description.equals("")) {
			description = rollDescription(name, roll);
		}

		if (position.isBridge()) {
			position.bridge();
			description = rollDescription(name, roll);
			description += bridgeDescription(name, start);
		} else if (position.isBounce()) {
			position.bounce(roll.total());
			description += bounceDescription(name, start, position);
		} else {
			description += moveDescription(name, start, position);
		}

		if (position.isWinner()) {
			description += " " + name + " wins!!";
		}

		// TODO add prank here

		return new Result(position.getIndex(), description);

	}

}
