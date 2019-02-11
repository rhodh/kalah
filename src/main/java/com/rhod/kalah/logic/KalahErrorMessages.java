package com.rhod.kalah.logic;

import com.rhod.kalah.models.Player;

public class KalahErrorMessages {
	public static final String CANNOT_START_ON_KALAH = "Cannot start with move from the Kalah";
	public static final String NOT_NEGATIVE = "Pit ID cannot be negative";
	public static final String TOO_LARGE = "Pit ID is too large";
	public static final String NO_STONES = "No stones in the given Pit ID";
	public static final String NOT_ZERO = "Pit ID cannot be Zero";

	public static String startOnOwnPit(Player player) {
		return "Player " + player.toString().toLowerCase() + " must start on their own pit";
	} 
}
