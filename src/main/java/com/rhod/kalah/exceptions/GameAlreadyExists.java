package com.rhod.kalah.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GameAlreadyExists extends Exception {

	public GameAlreadyExists(int gameID) {
		super("Game ID " + Integer.toString(gameID) + " already exits in repository");
	}

	private static final long serialVersionUID = 1L;

}