package com.rhod.kalah.models;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhod.kalah.models.BoardModel;
import com.rhod.kalah.models.Player;

public class BoardModelDeserializerTests {

	ObjectMapper mapper;
	
	@Before
	public void setup() {
		mapper = new ObjectMapper();
	}
	
	
	private BoardModel validState() {
		List<Integer> state = new ArrayList<>();
		for (int i = 0; i < 14; ++i) {
			state.add(4);
		}
		
		return new BoardModel(state, Player.ONE);
	}
	
	@Test
	public void validJson() {
		final String json =  "{ \n\"1\": 4, \n\"2\":4, \n\"3\":4, \n\"4\":4, "
							+ "\n\"5\":4, \n\"6\":4,\n\"7\":4,\n\"8\":4, "
							+ "\n\"9\":4, \n\"10\":4, \n\"11\":4, \n\"12\":4, "
							+ "\n\"13\":4,\n\"14\":4 \n, \n\"Player\": \"one\" \n}";
		
		try {
			final BoardModel result = mapper.readValue(json, BoardModel.class);
			Assert.assertEquals(validState(), result);
		} catch(Exception e){
			Assert.fail("Expected a valid parse: " + e.getMessage());
		}
	}
	
	@Test
	public void notEnoughKeysErrors() {
		final String json =  "{ \n\"1\": 4, \n\"2\":2}";
		try {
			mapper.readValue(json, BoardModel.class);
			Assert.fail("Expected an JsonProcessingException");
		} catch (JsonProcessingException e) {
			Assert.assertTrue(true);
		} catch(Exception e){
			Assert.fail("Expected an JsonProcessingException: " + e.getMessage());
		}
	}
	
	
	@Test
	public void stringAsAValueErrors() {
		final String json =  "{ \n\"1\": 4, \n\"2\":\"Bob\", \n\"3\":4, \n\"4\":4, "
				+ "\n\"5\":4, \n\"6\":4,\n\"7\":4,\n\"8\":4, "
				+ "\n\"9\":4, \n\"10\":4, \n\"11\":4, \n\"12\":4, "
				+ "\n\"13\":4,\n\"14\":4 \n}";

		try {
			mapper.readValue(json, BoardModel.class);
			Assert.fail("Expected an JsonProcessingException");
		} catch (JsonProcessingException e) {
			Assert.assertTrue(true);
		} catch(Exception e){
			Assert.fail("Expected an JsonProcessingException: " + e.getMessage());
		}
	}
	
	@Test
	public void jsonAsAValueErrors() {
		final String json =  "{ \n\"1\": 4, \n\"2\":{\n\"Bob\": 2 \n}, \n\"3\":4, \n\"4\":4, "
				+ "\n\"5\":4, \n\"6\":4,\n\"7\":4,\n\"8\":4, "
				+ "\n\"9\":4, \n\"10\":4, \n\"11\":4, \n\"12\":4, "
				+ "\n\"13\":4,\n\"14\":4 \n}";

		try {
			mapper.readValue(json, BoardModel.class);
			Assert.fail("Expected an JsonProcessingException");
		} catch (JsonProcessingException e) {
			Assert.assertTrue(true);
		} catch(Exception e){
			Assert.fail("Expected an JsonProcessingException: " + e.getMessage());
		}
	}
	
	@Test
	public void negativeAsAValueErrors() {
		final String json =  "{ \n\"1\": 4, \n\"2\":4, \n\"3\":-4, \n\"4\":4, "
				+ "\n\"5\":4, \n\"6\":4,\n\"7\":4,\n\"8\":4, "
				+ "\n\"9\":4, \n\"10\":4, \n\"11\":4, \n\"12\":4, "
				+ "\n\"13\":4,\n\"14\":4 \n}";

		try {
			mapper.readValue(json, BoardModel.class);
			Assert.fail("Expected an JsonProcessingException");
		} catch (JsonProcessingException e) {
			Assert.assertTrue(true);
		} catch(Exception e){
			Assert.fail("Expected an JsonProcessingException: " + e.getMessage());
		}
	}

	
}
