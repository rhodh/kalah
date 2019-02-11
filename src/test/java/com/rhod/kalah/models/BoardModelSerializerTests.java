package com.rhod.kalah.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BoardModelSerializerTests {
ObjectMapper mapper;
	
	@Before
	public void setup() {
		mapper = new ObjectMapper();
	}
	
	
	private BoardModel buildModel() {
		List<Integer> state = new ArrayList<>();
		for (int i = 0; i < 14; ++i) {
			state.add(4);
		}
		
		return new BoardModel(state, Player.ONE);
	}
	
	@Test
	public void validJson() {
	final String expectJson =  "{\"1\":4,\"2\":4,\"3\":4,\"4\":4,"
						+ "\"5\":4,\"6\":4,\"7\":4,\"8\":4,"
						+ "\"9\":4,\"10\":4,\"11\":4,\"12\":4,"
						+ "\"13\":4,\"14\":4,\"Player\":\"ONE\"}";
	
	

		final BoardModel board = buildModel();
		try {
			String result = mapper.writeValueAsString(board);
			Assert.assertEquals(expectJson, result);
		} catch (JsonProcessingException e) {
			Assert.assertTrue(false);
		}

	}
}
