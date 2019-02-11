package com.rhod.kalah.logic;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rhod.kalah.exceptions.InvalidPitNumber;
import com.rhod.kalah.models.BoardModel;
import com.rhod.kalah.models.Player;

import freemarker.core.ReturnInstruction.Return;

/**
 * The board which makes move on the board model.
 * 
 * @author Rhodri
 *
 */
class Board {
	// Subtract by one since we are using arrays index as our key 
	static final int PLAYER_ONE_FIRST_PIT = 0;
	static final int PLAYER_TWO_FIRST_PIT = 7;
    static final int KALAH_ONE = 6;
	static final int KALAH_TWO = 13;
	static final int NO_OF_PITS = 14;
	protected Logger logger = Logger.getLogger(Board.class.getName());
		
	/**
	 * Constructor for the BoardState
	 * @param state the current state of the board pits
	 * @param player the player
	 * @throws InvalidStateException
	 */
	public Board(BoardModel model) {	
		data = model;
	}
	
	final private BoardModel data;

	/**
	 * Get the hashcode of the object
	 * @return the hashcode of the object
	 */
	@Override
	public int hashCode(){
	    return Objects.hash(data);
	}

	/**
	 * Are both object equal?
	 * @return true if equal false otherwise.
	 */
	@Override
	public boolean equals(final Object obj){
		boolean ret = false;
	    if(obj instanceof Board){
	        final Board other = (Board) obj;
	        ret = data.equals(other.data);
	    } 
		
		return ret;
	}
		
	/**
	 * Validates the pit Id passed it verify its a valid move.
	 * @param pitId the pit id to validate
	 */
	public String validatePitID(final int pitID) {
		
		String ret = "";
		if(pitID < 0) {
			ret = KalahErrorMessages.NOT_NEGATIVE;
		} else if(pitID == 0){
			ret = KalahErrorMessages.NOT_ZERO;
		} else if(pitID > data.getPits().size()) {
			ret = KalahErrorMessages.TOO_LARGE;
		} else {
			// Subtract Pit ID since we use the array index as our Id's
			final int adjustedPitID = pitID - 1;
			if(adjustedPitID == KALAH_ONE || adjustedPitID == KALAH_TWO) {
				ret = KalahErrorMessages.CANNOT_START_ON_KALAH;
			} else if(adjustedPitID < KALAH_ONE && getCurrentPlayer() == Player.TWO) {
				ret = KalahErrorMessages.startOnOwnPit(Player.TWO);
			} else if(adjustedPitID > KALAH_ONE && getCurrentPlayer() == Player.ONE) {
				ret = KalahErrorMessages.startOnOwnPit(Player.ONE);
			} else if(data.getPits().get(adjustedPitID) == 0) {
				ret = KalahErrorMessages.NO_STONES;
			}
		}
		
		logger.info("validatePitId() for PitID " + Integer.toString(pitID) + " has return " + ret);
		
		return ret;
	}
	
	/**
	 * For the given start pit ID get the PitIterator to
	 * iterate over all the dropable pits
	 * @param startPitID the starting pitID, note the Iterator will collect the
	 *                   stones from the starting pit and will be ready to drop
	 *                   on the *next* valid pit.
	 * @return the pit iterator
	 */
	PitIterator getIterator(final int startPitID) {
		return new PitIterator(startPitID, this);
	}
	
	@Override
	public String toString() {
		return "Player " + getCurrentPlayer().toString() 
		+ "\nPits [" + data.getPits().stream()
					.map(i -> i.toString())
					.collect(Collectors.joining(", ")) + "]";
	}
	
	/**
	 * Swap players 
	 */
	public void swapPlayers() {
		setCurrentPlayer(getCurrentPlayer().getOpponent());
		logger.info("Player " + getCurrentPlayer().toString() + " next turn");
	}

	/**
	 * Steals the opponents stones from the opposite pit to the given pit and
	 * place the stones in the players Kalah.
	 * @param pit the player pit.
	 */
	public void stealOpponentsStones(PitIterator pit) {
		final int opponentsPit = 12 - pit.getCurrentPitID();
		final int stoneCount = removeStones(opponentsPit);
		addStonesToKalah(getCurrentPlayer(), stoneCount);
	}
	
	/**
	 * Get the Kalah for the given player.
	 * @param player the given player.
	 * @return the given player Kalah.
	 */
	static int getKalah(final Player player) {
		return player == player.ONE ? KALAH_ONE : KALAH_TWO;
	}

	/**
	 * Add given number of stones to the pit.
	 * @param pitId we wish to add to.
	 * @param stoneCount the amount of stones we wish to add.
	 */
	void addStonesToPit(final int pitId, final int stoneCount) {
		data.getPits().set(pitId, data.getPits().get(pitId) + stoneCount);
	}
	
	/**
	 * Remove stones from the pit for a given pit id
	 * @param pitId the given pit id
	 * @return the stones we've removed
	 */
	int removeStones(final int pitId) {
		final int stoneCount = data.getPits().get(pitId);
		data.getPits().set(pitId, 0);
		return stoneCount;
	}
	
	/**
	 * Given a player add the given stone count to the Kalah
	 * @param player the player we wish to add
	 * @param stoneCount the stone count
	 */
	void addStonesToKalah(final Player player, final int stoneCount) {
		addStonesToPit(getKalah(player), stoneCount);
	}

	/**
	 * @return the currentPlayer
	 */
	Player getCurrentPlayer() {
		return data.getCurrentPlayer();
	}

	/**
	 * Get the stone count for a given pit id
	 * @param pitID the given pit id
1	 * @return the stone count
	 */
	Integer getStoneCount(final int pitID) {
		return data.getPits().get(pitID);
	}
	
	
	/**
	 * Is this pit the opponents Kalah? 
	 * @return true if this pit is the opponents Kalah
	 */
	boolean isOpponentKalah(final long pitID) {
		return getKalah(getCurrentPlayer().getOpponent()) == pitID;
	}

	/**
	 * Has the given player run out of stones in their pits.
	 * @param player the given player
	 * @return true if the player has run out of stones
	 */
	boolean hasRunOutOfStones(Player player) {
		boolean ret = true;

		for(int i = getFirstPit(player); ret && i < getKalah(player); ++ i) {
			ret &= getStoneCount(i) == 0;
		}
		
		return ret;
	}

	/**
	 * Gather all the remaining stones in the players pits and add them
	 * to the the players Kalah.
	 * @param player the given player
	 */
	void gatherRemainingStones(Player player) {
		for(int i = getFirstPit(player); i < getKalah(player); ++ i) {
			final int stones = removeStones(i);
			addStonesToKalah(player, stones);
		}
	}
	
	/**
	 * @param currentPlayer the currentPlayer to set
	 */
	private void setCurrentPlayer(Player currentPlayer) {
		this.data.setCurrentPlayer(currentPlayer);
	}
	

	/**
	 * Get the first Pit id for the given player
	 * @param player the given player
	 * @return the first pit id
	 */
	private int getFirstPit(Player player) {
		return player == Player.ONE ? Board.PLAYER_ONE_FIRST_PIT : Board.PLAYER_TWO_FIRST_PIT;
	}

	BoardModel getCurrentState() {
		return data;
	}

}
