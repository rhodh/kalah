package com.rhod.kalah.models;


/**
 * Enum representing which player
 * @author Rhodri
 *
 */
public enum Player {
	ONE, 
	TWO;
	
	/**
	 * Get the opponent player wrt current player
	 * @return players opponent
	 */
	public Player getOpponent() {
		return this == Player.ONE ? Player.TWO : Player.ONE;
	}
}
