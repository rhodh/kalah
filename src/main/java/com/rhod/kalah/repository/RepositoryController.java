package com.rhod.kalah.repository;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rhod.kalah.exceptions.GameAlreadyExists;
import com.rhod.kalah.exceptions.GameNotFound;
import com.rhod.kalah.exceptions.UpdateVersionOutOfDate;
import com.rhod.kalah.models.GameInstance;

@RestController
public class RepositoryController {
	
	protected Logger logger = Logger.getLogger(RepositoryController.class.getName());
	
	@Autowired
	private InMemoryRepository repository;

	@RequestMapping(value = "create", method = RequestMethod.POST, produces = "application/json")
	public GameInstance create(@RequestBody String url) throws GameAlreadyExists  {
		logger.log(Level.INFO, "Creating game");

		final int newID = repository.getNextId();
		GameInstance instance = new GameInstance(newID, url);
		repository.add(instance);
		
		logger.log(Level.INFO, "Created game with ID: " + Integer.toString(newID));
		return instance;
	}
	
	@RequestMapping(value = "get/{gameID}" , method = RequestMethod.GET, produces = "application/json")
	public GameInstance get(@PathVariable("gameID") int gameID) throws GameNotFound {
		return repository.get(gameID);
	}
	
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = "application/json")
	public Boolean update(@RequestBody GameInstance game) throws UpdateVersionOutOfDate, GameNotFound{
		repository.update(game);
		return true;
	}
}
