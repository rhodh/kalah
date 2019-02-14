package com.rhod.kalah.models;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GameInstanceTests {
	protected Logger logger = Logger.getLogger(GameInstanceTests.class.getName());
	
	ObjectMapper mapper;
	
	@Before
	public void setup() {
		mapper = new ObjectMapper();
	}
	
	@Test
	public void urlBuiltCorrectlyWithoutSlash() {
		final GameInstance board = new GameInstance(52, "www.test.com");
		Assert.assertEquals("www.test.com/52", board.getUrl());
	}
	
	@Test
	public void urlBuiltCorrectlyWithSlash() {
		final GameInstance board = new GameInstance(52, "www.test.com/");
		Assert.assertEquals("www.test.com/52", board.getUrl());
	}

	@Test
	public void validJson() {
		final String expectJson =  "{\"id\":52,\"url\":\"www.test.com/52\","
				+ "\"version\":0,"
				+"\"model\":{\"1\":6,\"2\":6,\"3\":6,\"4\":6,\"5\":6,\"6\":6,\"7\":0,"
				+ "\"8\":6,\"9\":6,\"10\":6,\"11\":6,\"12\":6,\"13\":6,\"14\":0,"
				+ "\"Player\":\"ONE\"}}";

		final GameInstance board = new GameInstance(52, "www.test.com");
		try {
			String result = mapper.writeValueAsString(board);
			Assert.assertEquals(expectJson, result);
		} catch (JsonProcessingException e) {
			Assert.assertTrue(false);
		}

	}
}
