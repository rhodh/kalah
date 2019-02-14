package com.rhod.kalah.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.BAD_REQUEST, reason = "Game already exists")
public class GameAlreadyExists extends RuntimeException  {

	public GameAlreadyExists(int gameID) {
		super("Game ID " + Integer.toString(gameID) + " already exits in repository");
	}

	private static final long serialVersionUID = 1L;

}