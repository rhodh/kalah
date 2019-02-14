package com.rhod.kalah.logic;

import com.rhod.kalah.models.Player;

/**
 * Iterator class which controls the pit iteration over the
 * pits, won't land on your opponents pit. The iterator starts
 * on the first dropable pit.
 * 
 * Can also interact with current pit.
 * @author Rhodri
 *
 */
class PitIterator {
	int currentPitID;
	int stoneCount;
	final Board board;
	PitIterator(final int startPitID, final Board board) {
		this.board = board;
		// Since we're using arrays index as our pit id negate by one
		final int modifiedStartPit = startPitID - 1;
		this.stoneCount = board.removeStones(modifiedStartPit);
		this.currentPitID = calcNextPit(modifiedStartPit); 
	}
	
	private PitIterator(final int pitID, PitIterator other) {
		this.currentPitID = pitID; 
		this.board = other.board;
		this.stoneCount = other.stoneCount;
	}
	
	/**
	 * Give a pit id calculate the next pit id. 
	 * @param pitId the given pitID 
	 * @return the calculated pipID.
	 */
	private int calcNextPit(final int pitId) {
		return pitId != Board.KALAH_TWO ? pitId + 1 : 0;
	}
	
	/**
	 * Get the next pit. Skips over the opponents Kalah.
	 * @return null if we're out of stones otherwise the next pit
	 */
	public PitIterator next() {
		PitIterator ret = null;
		if(stoneCount > 0) {
			int newPitId = calcNextPit(currentPitID);
			
			if(board.isOpponentKalah(newPitId)) {
				newPitId = calcNextPit(newPitId);
			}
			
			ret = new PitIterator(newPitId, this);
		}
		
		return ret;
	}
	
	/**
	 * Is this pit the players own Kalah?
	 * @return true if its the players Kalah
	 */
	public boolean isOwnKalah() {
		return Board.getKalah(board.getCurrentPlayer()) == currentPitID;
	}
	
	/**
	 * Drop a stone in the pit
	 * @info decrements our stone count
	 */
	public void dropStone() {
		board.addStonesToPit(currentPitID, 1);
		stoneCount--;
	}
		
	/**
	 * Get the stone count of the pit
	 * @return the stone count
	 */
	public Integer getStoneCount() {
		return board.getStoneCount(currentPitID);
	}
	
	/**
	 * Does the player own the pit
	 * @return true if they own the pit
	 */
	public boolean isOwnPit() {
		return (board.getCurrentPlayer() == Player.ONE && currentPitID <= Board.KALAH_ONE)
				|| (board.getCurrentPlayer() == Player.TWO && currentPitID > Board.KALAH_ONE && currentPitID <= Board.KALAH_TWO);
	}
	
	/** 
	 * Move whatever stone we have in the pit to our Kalah
	 */
	public void moveStonesToKalah() {
		final int stones = board.removeStones(currentPitID);
		board.addStonesToKalah(board.getCurrentPlayer(), stones);
	}

	
	/** 
	 * Get the current pitID we're iterating over
	 * @return the current pit id
	 */
	int getCurrentPitID() {
		return currentPitID;
	}
}