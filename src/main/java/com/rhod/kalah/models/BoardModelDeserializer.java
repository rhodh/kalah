package com.rhod.kalah.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Custom deserializer used to deserialize JSON object to the game state
 * 
 * @note Since we want to store the game state as array we need to customise 
 * 
 * @author Rhodri
 *
 */
public class BoardModelDeserializer extends StdDeserializer<BoardModel> {

	protected BoardModelDeserializer(Class<?> vc) {
		super(vc);
	}
	
	public BoardModelDeserializer() {
		this(null);
	}

	static final int INVALID_VALUE = -1;

	private JsonNode getJsonValueNode(final String jsonKey, final JsonNode node, final JsonParser p) 
			throws JsonParseException {
		final JsonNode jsonValue = node.get(jsonKey);
		
		if (jsonValue == null) {
			throw new JsonParseException(p, "Json Key: " + jsonKey + " doesn't exist");
		}
		
		if (!jsonValue.isValueNode()) {
			throw new JsonParseException(p, "Json Key: " + jsonKey + " isn't a value");
		}
		
		return jsonValue;
	}

	@Override
	public BoardModel deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		final JsonNode node = p.getCodec().readTree(p);
		
		List<Integer> pits = new ArrayList<>();
		
		// Get the all the pit ids
		for(int i = 1; i < 15; ++i) {
			final String jsonKey = Integer.toString(i);
			
			final JsonNode jsonValue = getJsonValueNode(jsonKey, node, p);
			
			final int id = jsonValue.asInt(INVALID_VALUE);
			
			if (id < 0) {
				throw new JsonParseException(p, "Json Key: " + jsonKey + " has invalid value " + node.asText());
			}
			
			pits.add(id);
		}
		
		final JsonNode expectedPlayerNode = getJsonValueNode("Player", node, p);
		
		final Player expectedPlayer;
		try {
			expectedPlayer = Player.valueOf(expectedPlayerNode.asText().toUpperCase());
		} 
		catch (IllegalArgumentException e) {
			throw new JsonParseException(p, e.getMessage());
		}
		

		return new BoardModel(pits, expectedPlayer);
	}
}
