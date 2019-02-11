package com.rhod.kalah.logic;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;

import com.rhod.kalah.models.Player;

public class PitIteratorTests {

	@Test
	public void testPitIterationCreation() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.removeStones(anyInt())).thenReturn(6);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.ONE);
		
		PitIterator it = new PitIterator(1, mockBoard);
		Assert.assertNotNull(it);
		Assert.assertEquals(1, it.getCurrentPitID());
	}
	
	@Test
	public void testNextWithValidStoneCount() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.removeStones(anyInt())).thenReturn(6);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.ONE);
		
		PitIterator it = new PitIterator(1, mockBoard);
		PitIterator next = it.next();
		Assert.assertEquals(2, next.getCurrentPitID());
	}
	
	@Test
	public void testNextWithZeroStoneCount() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.removeStones(anyInt())).thenReturn(0);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.ONE);
		
		PitIterator it = new PitIterator(1, mockBoard);
		PitIterator next = it.next();
		Assert.assertNull(next);
	}
	
	@Test
	public void testNextPlayerOneSkipOpponentsKalah() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.removeStones(anyInt())).thenReturn(6);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.ONE);
		
		PitIterator it = new PitIterator(13, mockBoard);
		PitIterator next = it.next();
		Assert.assertEquals(0, next.getCurrentPitID());
	}
	
	@Test
	public void testNextPlayerTwoSkipOpponentsKalah() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.removeStones(anyInt())).thenReturn(6);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.TWO);
		
		PitIterator it = new PitIterator(6, mockBoard);
		PitIterator next = it.next();
		Assert.assertEquals(7, next.getCurrentPitID());
	}
	
	@Test
	public void testDropStone() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.removeStones(anyInt())).thenReturn(6);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.TWO);
		
		PitIterator it = new PitIterator(1, mockBoard);
		it.dropStone();
		verify(mockBoard).addStonesToPit(ArgumentMatchers.eq(1), ArgumentMatchers.eq(1));
	}
	
	@Test
	public void testGetStoneCount() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.TWO);
		
		PitIterator it = new PitIterator(1, mockBoard);
		it.getStoneCount();
		verify(mockBoard).getStoneCount(ArgumentMatchers.eq(1));
	}
	
	@Test
	public void isOwnPitTruePlayerOne() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.ONE);
		
		PitIterator it = new PitIterator(1, mockBoard);
		Assert.assertTrue(it.isOwnPit());
	}
	
	@Test
	public void isOwnPitFalsePlayerOne() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.ONE);
		
		PitIterator it = new PitIterator(8, mockBoard);
		Assert.assertFalse(it.isOwnPit());
	}
	
	@Test
	public void isOwnPitTruePlayerTwo() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.TWO);
		
		PitIterator it = new PitIterator(8, mockBoard);
		Assert.assertTrue(it.isOwnPit());
	}
	
	@Test
	public void isOwnPitFalsePlayerTwo() {
		Board mockBoard = mock(Board.class);
		when(mockBoard.getCurrentPlayer()).thenReturn(Player.TWO);
		
		PitIterator it = new PitIterator(3, mockBoard);
		Assert.assertFalse(it.isOwnPit());
	}
}
