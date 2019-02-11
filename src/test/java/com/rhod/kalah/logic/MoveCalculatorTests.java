package com.rhod.kalah.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.rhod.kalah.models.BoardModel;
import com.rhod.kalah.models.Player;

public class MoveCalculatorTests {

	private BoardModel buildGameState(Player player, int ... args) {
		List<Integer> pits = new ArrayList<>();
        for(int x: args){
        	pits.add(x);
        }
        
		return new BoardModel(pits, player);
	}
	
	@Test
	public void makeMovePlayerBasic() {
		try {
			final BoardModel board = buildGameState(Player.ONE, 2, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
			final BoardModel expectedResult = buildGameState(Player.TWO, 0, 7, 7, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
			MoveCalculator.makeMove(board, 1); 
			
			Assert.assertEquals(expectedResult, board);
		} 
		catch(Exception e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	public void makeMovePlayerFinishInKalah() {
		try {
			final BoardModel board = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
			final BoardModel expectedResult = buildGameState(Player.ONE, 0, 7, 7, 7, 7, 7, 1, 6, 6, 6, 6, 6, 6, 0);
			MoveCalculator.makeMove(board, 1); 
			
			Assert.assertEquals(expectedResult, board);
		} 
		catch(Exception e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	public void makeMovePlayerSkipOpponentsKalah() {
		try {
			final BoardModel board = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 8, 0, 6, 6, 6, 6, 6, 6, 0);
			final BoardModel expectedResult = buildGameState(Player.TWO, 7, 6, 6, 6, 6, 0, 1, 7, 7, 7, 7, 7, 7, 0);
		    MoveCalculator.makeMove(board, 6); 
			
			Assert.assertEquals(expectedResult, board);
		} 
		catch(Exception e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	public void makeMovePlayerStealOppositesStones() {
		try {
			final BoardModel board = buildGameState(Player.ONE, 3, 6, 6, 0, 6, 8, 0, 6, 6, 6, 6, 6, 6, 0);
			final BoardModel expectedResult = buildGameState(Player.TWO, 0, 7, 7, 0, 6, 8, 7, 6, 6, 0, 6, 6, 6, 0);
			MoveCalculator.makeMove(board, 1); 
			
			Assert.assertEquals(expectedResult, board);
		} 
		catch(Exception e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	public void playerOneMakesAMoveAndFinshes() {
		try {
			final BoardModel board = buildGameState(Player.ONE, 0, 0, 0, 0, 0, 1, 21, 3, 2, 5, 1, 7, 2, 0);
			final BoardModel expectedResult = buildGameState(Player.ONE, 0, 0, 0, 0, 0, 0, 22, 0, 0, 0, 0, 0, 0, 20);
			MoveCalculator.makeMove(board, 6); 
			
			Assert.assertEquals(expectedResult, board);
		} 
		catch(Exception e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	public void playerOneMakesAMoveAndRemovesAllOfPlayer2() {
		try {
			final BoardModel board = buildGameState(Player.ONE, 0, 2, 3, 5, 1, 0, 10, 3, 0, 0, 0, 0, 0, 30);
			final BoardModel expectedResult = buildGameState(Player.ONE, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 30);
			MoveCalculator.makeMove(board, 5); 
			
			Assert.assertEquals(expectedResult, board);
		} 
		catch(Exception e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	public void playerTwoMakesAMoveAndFinshes() {
		try {
			final BoardModel board = buildGameState(Player.TWO, 0, 2, 3, 5, 5, 1, 10, 0, 0, 0, 0, 0, 1, 25);
			final BoardModel expectedResult = buildGameState(Player.TWO, 0, 0, 0, 0, 0, 0, 26, 0, 0, 0, 0, 0, 0, 26);
			MoveCalculator.makeMove(board, 13); 
			
			Assert.assertEquals(expectedResult, board);
		} 
		catch(Exception e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}
	
	@Test
	public void playerTwoMakesAMoveAndRemovesAllOfPlayerOne() {
		try {
			final BoardModel board = buildGameState(Player.TWO, 2, 0, 0, 0, 0, 0, 24, 3, 5, 4, 1, 1, 0, 14);
			final BoardModel expectedResult = buildGameState(Player.TWO, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 30);
			MoveCalculator.makeMove(board, 12); 
			
			Assert.assertEquals(expectedResult, board);
		} 
		catch(Exception e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}
}
