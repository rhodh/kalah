package com.rhod.kalah.logic;

import com.rhod.kalah.exceptions.InvalidPitData;
import com.rhod.kalah.exceptions.InvalidPitNumber;
import com.rhod.kalah.models.BoardModel;
import com.rhod.kalah.models.Player;

/**
 * Stateless Class that implement the Kalah rules on the board.
 * @author Rhodri
 *
 */
public class MoveCalculator {
	
	/**
	 * Make a move given a pit id
	 * @param currentBoard the current state of the board.
	 * @param pitId The pit we wish to start the move from.
	 * @return the new state of the model
	 * @throws InvalidPitNumber 
	 * @throws InvalidPitData 
	 * @throws InvalidStateException 
	 */
	static BoardModel makeMove(final BoardModel currentModel, final int pitID) throws InvalidPitNumber, InvalidPitData {
		
		if(currentModel.getPits().size() != Board.NO_OF_PITS) {
			throw new InvalidPitData(currentModel.getPits().size());
		}
		
		Board currentBoard = new Board(currentModel);
		final String error = currentBoard.validatePitID(pitID);
		
		if(!error.isEmpty()) {
			throw new InvalidPitNumber(error);
		}
	
		PitIterator lastPit = null;
		for(PitIterator it = currentBoard.getIterator(pitID); it != null; it = it.next()) {
			// drop a stone
			it.dropStone();
			
			// save our last pit
			lastPit = it;
		}
		
		// If the last pit is our own and only has one stone we get to
		// steal our opponents stones and move our stone to our Kalah.
		if(lastPit.getStoneCount() == 1 && lastPit.isOwnPit()) {
			currentBoard.stealOpponentsStones(lastPit);
			lastPit.moveStonesToKalah();
		}
		
		if(currentBoard.hasRunOutOfStones(Player.ONE)) {
			currentBoard.gatherRemainingStones(Player.TWO);
		} else if(currentBoard.hasRunOutOfStones(Player.TWO)) {
			currentBoard.gatherRemainingStones(Player.ONE);
		} else if(!lastPit.isOwnKalah()) {
			currentBoard.swapPlayers();
		}
		
		return currentBoard.getCurrentState();
	}
}
