package com.rhod.kalah.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.rhod.kalah.exceptions.GameAlreadyExists;
import com.rhod.kalah.exceptions.GameNotFound;
import com.rhod.kalah.exceptions.UpdateVersionOutOfDate;
import com.rhod.kalah.models.GameInstance;

@Component("repository")
public class InMemoryRepository {
	Map<Integer, GameInstance> repository = new HashMap<>();
	Integer uniqueId = 0;

	Integer getNextId() {
		return uniqueId++;
	}

	/**
	 * Add the game instance to the repository
	 * @param instance the game instance we wish to add
	 * @throws GameAlreadyExists
	 */
	public void add(GameInstance instance) throws GameAlreadyExists {
		if(repository.containsKey(instance.getId())) {
			throw new GameAlreadyExists(instance.getId());
		}
		
		repository.put(instance.getId(), instance);
	}

	/**
	 * Get the game for the given game id
	 * @param gameID the given game id
	 * @return game instance related to the game id
	 * @throws GameNotFound
	 */
	public GameInstance get(int gameID) throws GameNotFound {
		if(!repository.containsKey(gameID)) {
			throw new GameNotFound(gameID);
		}
		
		return repository.get(gameID);
	}


	/**
	 * Update the current repository with the new version
	 * @param game
	 * @throws UpdateVersionOutOfDate 
	 * @throws GameNotFound 
	 */
	public void update(GameInstance game) throws UpdateVersionOutOfDate, GameNotFound {
		final Integer currentVersion = get(game.getId()).getVersion();
		if(game.getVersion() <= currentVersion) {
			throw new UpdateVersionOutOfDate(game.getId(), game.getVersion(), currentVersion);
		}
		
		repository.put(game.getId(), game);
	}
	
}
