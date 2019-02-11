package com.rhod.kalah.models;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonDeserialize(using = BoardModelDeserializer.class)
@JsonSerialize(using = BoardModelSerializer.class)
public class BoardModel {
	private List<Integer> pits;
	private Player currentPlayer;

	public BoardModel(List<Integer> pits, Player player) {
		this.pits = pits;
		this.currentPlayer = player;
	}

	public List<Integer> getPits() {
		return pits;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
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
}