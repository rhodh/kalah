package com.rhod.kalah.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Rhodri
 *
 */
@JsonDeserialize(using = BoardModelDeserializer.class)
@JsonSerialize(using = BoardModelSerializer.class)
public class BoardModel {
	public static BoardModel initalBoard() {
		List<Integer> pits = new ArrayList<>(Arrays.asList(6,6,6,6,6,6,0,6,6,6,6,6,6,0));
		return new BoardModel(pits, Player.ONE);
	}
	private List<Integer> pits;

	private Player currentPlayer;

	/**
	 * Default class
	 */
	public BoardModel() {
	}

	/**
	 * Construct a board thats already in play
	 * @param pits the current state of the pits
	 * @param player the current player
	 */
	public BoardModel(List<Integer> pits, Player player) {
		this.pits = pits;
		this.currentPlayer = player;
	}

	/**
	 * Are both object equal?
	 * @return true if equal false otherwise.
	 */
	@Override
	public boolean equals(final Object obj){
		boolean ret = false;
	    if(obj instanceof BoardModel){
	        final BoardModel other = (BoardModel) obj;
	        ret = Objects.equals(getPits(), other.getPits()) 
	        		&& Objects.equals(getCurrentPlayer(), other.getCurrentPlayer());
	    } 
		
		return ret;
	}

	/**
	 * Get the current player
	 * @return the current player
	 */
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Get the current pits
	 * @return the current pits
	 */
	public List<Integer> getPits() {
		return pits;
	}

	/**
	 * Get the hashcode of the object
	 * @return the hashcode of the object
	 */
	@Override
	public int hashCode(){
	    return Objects.hash(getPits(), getCurrentPlayer());
	}
	
	/**
	 * Set the current player
	 * @param currentPlayer the player we wish to set
	 */
	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Clean up any data we don't wish to return to the user
	 */
	public void prepareForUser() {
		currentPlayer = null;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BoardModel [pits=" + pits + ", currentPlayer=" + currentPlayer + "]";
	}
}