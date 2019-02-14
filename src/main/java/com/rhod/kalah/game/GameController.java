package com.rhod.kalah.game;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhod.kalah.models.GameInstance;
import com.rhod.kalah.models.NewGameResponse;
import com.rhod.kalah.models.ReturnObject;
import com.rhod.kalah.models.UpdatedGameResponse;


@RestController
public class GameController {
	
	protected Logger logger = Logger.getLogger(GameController.class.getName());
	
	@Autowired
	protected GameOrchestrater gameOrchestrater;
	
	public GameController(GameOrchestrater gameOrchestrater) {
		this.gameOrchestrater = gameOrchestrater;
	}

	@RequestMapping(value = "games", method = RequestMethod.POST,  produces = "application/json")
	public ReturnObject<NewGameResponse> createGame(HttpServletRequest request) {
		final String url = request.getRequestURL().toString();
		GameInstance instance = gameOrchestrater.createGame(url);
		return new ReturnObject<NewGameResponse>(new NewGameResponse(instance), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "games/{gameId}/pits/{pitId}", method = RequestMethod.PUT, produces = "application/json")
	public ReturnObject<UpdatedGameResponse> updateGame(@PathVariable("gameId") Integer gameId, @PathVariable("pitId") Integer pitId) {
		GameInstance instance = gameOrchestrater.updateGame(gameId, pitId);
		return new ReturnObject<UpdatedGameResponse>(new UpdatedGameResponse(instance), HttpStatus.OK);
	}
	
}
