package com.rhod.kalah.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.rhod.kalah.logic.Board;
import com.rhod.kalah.models.BoardModel;
import com.rhod.kalah.models.Player;

public class BoardTests {
	private Board buildGameState(Player player, int ... args) {
		List<Integer> pits = new ArrayList<>();
        for(int x: args){
        	pits.add(x);
        }

		return new Board(new BoardModel(pits, player));
	}

	
	@Test
	public void equalStatesAreEqual() {
		Board lhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Board rhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Assert.assertTrue(lhs.equals(rhs));
	}
	
	@Test
	public void equalStatesHaveSameHash() {
		Board lhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Board rhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Assert.assertEquals(lhs.hashCode(), rhs.hashCode());
	}
	
	@Test
	public void differentStatesAreNotEqual() {
		Board lhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Board rhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 11, 12, 13, 14);
		Assert.assertFalse(lhs.equals(rhs));
	}
	
	@Test
	public void differentStatesHaveDifferentHash() {
		Board lhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Board rhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 11, 12, 13, 14);
		Assert.assertNotEquals(lhs.hashCode(), rhs.hashCode());
	}
	
	@Test
	public void differentPlayersAreNotEqual() {
		Board lhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Board rhs = buildGameState(Player.TWO, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Assert.assertFalse(lhs.equals(rhs));
	}
	
	@Test
	public void differentPlayersHaveDifferentHash() {
		Board lhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Board rhs = buildGameState(Player.TWO, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Assert.assertNotEquals(lhs.hashCode(), rhs.hashCode());
	}

	@SuppressWarnings("unlikely-arg-type")
	@Test
	public void differentObjectAreNotEqual() {	
		Board lhs = buildGameState(Player.ONE, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14);
		Assert.assertFalse(lhs.equals(new String("Bob")));
	}
	

	@Test
	public void validatePitIDNegativePipId() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		Assert.assertEquals(KalahErrorMessages.NOT_NEGATIVE, state.validatePitID(-1));
	}
	
	@Test
	public void validatePitIDZeroPipId() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		Assert.assertEquals(KalahErrorMessages.NOT_ZERO, state.validatePitID(0));
	}
	
	@Test
	public void validatePitIDLargePipId() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		Assert.assertEquals(KalahErrorMessages.TOO_LARGE, state.validatePitID(15));
	}
	
	@Test
	public void validatePitIDPlayerOneKalahId() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		Assert.assertEquals(KalahErrorMessages.CANNOT_START_ON_KALAH, state.validatePitID(7));
	}
	
	@Test
	public void validatePitIDPlayerTwoKalahId() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		Assert.assertEquals(KalahErrorMessages.CANNOT_START_ON_KALAH,state.validatePitID(14));
	}
	
	@Test
	public void validatePitIDPlayerOneWrongPit() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		Assert.assertEquals(KalahErrorMessages.startOnOwnPit(Player.ONE), state.validatePitID(13));
	}
	
	@Test
	public void validatePitIDPlayerTwoWrongPit() {
	    Board state = buildGameState(Player.TWO, 6, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		Assert.assertEquals(KalahErrorMessages.startOnOwnPit(Player.TWO), state.validatePitID(6));
	}
	
	@Test
	public void validatePitIDPlayerOneNoStones() {
	    Board state = buildGameState(Player.ONE, 0, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		Assert.assertEquals(KalahErrorMessages.NO_STONES, state.validatePitID(1));
	}
	
	@Test
	public void validateSwapPlayer() {
	    Board state = buildGameState(Player.ONE, 0, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		Board expected = buildGameState(Player.TWO, 0, 6, 6, 6, 6, 6, 0, 6, 6, 6, 6, 6, 6, 0);
		
		state.swapPlayers();
		Assert.assertEquals(expected, state);
	}
	
	@Test
	public void validatePlayerOneStealOpponentStones() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Board expected = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 4, 4, 4, 4, 0, 4, 4, 0);

		PitIterator itMock = mock(PitIterator.class);
		when(itMock.getCurrentPitID()).thenReturn(2);
		
		state.stealOpponentsStones(itMock);
		
		Assert.assertEquals(expected, state);
	}
	
	@Test
	public void validatePlayerTwoStealingOpponentStones() {
	    Board state = buildGameState(Player.TWO, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Board expected = buildGameState(Player.TWO, 6, 6, 6, 0, 6, 6, 0, 4, 4, 4, 4, 4, 4, 6);

		PitIterator itMock = mock(PitIterator.class);
		when(itMock.getCurrentPitID()).thenReturn(9);
		
		state.stealOpponentsStones(itMock);
		
		Assert.assertEquals(expected, state);
	}
	
	@Test
	public void getKalahForPlayerOne() {	
		Assert.assertEquals(Board.KALAH_ONE, Board.getKalah(Player.ONE));
	}
	
	@Test
	public void getKalahForPlayerTwo() {	
		Assert.assertEquals(Board.KALAH_TWO, Board.getKalah(Player.TWO));
	}
	
	@Test
	public void validateAddStonesToPit() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Board expected = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 8, 0, 4, 4, 4, 4, 4, 4, 0);

		state.addStonesToPit(5, 2);
		Assert.assertEquals(expected, state);
	}
	
	
	@Test
	public void validateRemoveStones() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Board expected = buildGameState(Player.ONE, 6, 0, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		
		final int stones = state.removeStones(1);
		
		Assert.assertEquals(expected, state);
		Assert.assertEquals(6, stones);
	}
	
	@Test
	public void validateGetStoneCount() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		
		final int stones = state.getStoneCount(1);
		
		Assert.assertEquals(6, stones);
	}
	
	@Test
	public void validateAddStonesToKalahPlayerOne() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Board expected = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 3, 4, 4, 4, 4, 4, 4, 0);
		
		state.addStonesToKalah(Player.ONE, 3);
		
		Assert.assertEquals(expected, state);
	}
	
	@Test
	public void validateAddStonesToKalahPlayerTwo() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Board expected = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 3);
		
		state.addStonesToKalah(Player.TWO, 3);
		
		Assert.assertEquals(expected, state);
	}
	
	@Test
	public void validatePlayerOneIsOpponentsKalahIncorrectValue() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Assert.assertFalse(state.isOpponentKalah(6));
	}
	
	@Test
	public void validatePlayerOneIsOpponentsKalahCorrectValue() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Assert.assertTrue(state.isOpponentKalah(13));
	}
	
	@Test
	public void validatePlayerTwoIsOpponentsKalahIncorrectValue() {
	    Board state = buildGameState(Player.TWO, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Assert.assertFalse(state.isOpponentKalah(13));
	}
	
	@Test
	public void validatePlayerTwoIsOpponentsKalahCorrectValue() {
	    Board state = buildGameState(Player.TWO, 6, 6, 6, 6, 6, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Assert.assertTrue(state.isOpponentKalah(6));
	}
	
	@Test
	public void validatePlayerOneHasRunOutOfStonesFalse() {	
	    Board state = buildGameState(Player.ONE, 0, 0, 0, 0, 0, 6, 0, 4, 4, 4, 4, 4, 4, 0);
		Assert.assertFalse(state.hasRunOutOfStones(Player.ONE));
	}
	
	@Test
	public void validatePlayerOneHasRunOutOfStonesFalseFirstPit() {
	    Board state = buildGameState(Player.ONE, 1, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 0);
		Assert.assertFalse(state.hasRunOutOfStones(Player.ONE));
	}
	
	@Test
	public void validatePlayerOneHasRunOutOfStonesTrue() {
	    Board state = buildGameState(Player.ONE, 0, 0, 0, 0, 0, 0, 3, 4, 4, 4, 4, 4, 4, 0);
		Assert.assertTrue(state.hasRunOutOfStones(Player.ONE));
	}
	
	@Test
	public void validatePlayerTwoHasRunOutOfStonesFalse() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 3, 0);
		Assert.assertFalse(state.hasRunOutOfStones(Player.TWO));
	}
	
	@Test
	public void validatePlayerTwoHasRunOutOfStonesFalseFirstPit() {		
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 4, 0, 0, 0, 0, 0, 0);
		Assert.assertFalse(state.hasRunOutOfStones(Player.TWO));
	}
	
	@Test
	public void validatePlayerTwoHasRunOutOfStonesTrue() {
	    Board state = buildGameState(Player.ONE, 6, 6, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0, 0, 2);
		Assert.assertTrue(state.hasRunOutOfStones(Player.TWO));
	}
}
