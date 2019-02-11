package com.rhod.kalah.logic;

import org.junit.Assert;
import org.junit.Test;

import com.rhod.kalah.models.Player;

public class PlayerTests {

	@Test
	public void playerOneOpponentIsPlayerTwo() {
		Assert.assertEquals(Player.TWO, Player.ONE.getOpponent());
	}
	
	@Test
	public void playerTwoOpponentIsPlayerOne() {
		Assert.assertEquals(Player.ONE, Player.TWO.getOpponent());
	}
}
