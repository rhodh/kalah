package com.rhod.kalah.repository;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.rhod.kalah.exceptions.GameAlreadyExists;
import com.rhod.kalah.exceptions.GameNotFound;
import com.rhod.kalah.exceptions.UpdateVersionOutOfDate;
import com.rhod.kalah.models.GameInstance;

public class InMemoryRepositoryTests {
	
	private InMemoryRepository repo;

	@Before
	public void setup() {
		this.repo = new InMemoryRepository();
	}

	@Test
	public void getUniqueID() {
		Assert.assertEquals(Integer.valueOf(0), repo.getNextId());
		Assert.assertEquals(Integer.valueOf(1), repo.getNextId());
		Assert.assertEquals(Integer.valueOf(2), repo.getNextId());
		Assert.assertEquals(Integer.valueOf(3), repo.getNextId());
		Assert.assertEquals(Integer.valueOf(4), repo.getNextId());
	}
	
	@Test
	public void addNewInstance() {
		GameInstance instance = mock(GameInstance.class);
		when(instance.getId()).thenReturn(6);
		
		try {
			repo.add(instance);
			Assert.assertTrue(true);
		} catch (GameAlreadyExists e) {
			Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void attemptToAddExistingInstance() {
		GameInstance instance = mock(GameInstance.class);
		when(instance.getId()).thenReturn(6);
		
		try {
			repo.add(instance);
			repo.add(instance);
			Assert.assertTrue(false);
		} catch (GameAlreadyExists e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void attemptToGetAnInstanceThatExists() {
		GameInstance instance = mock(GameInstance.class);
		when(instance.getId()).thenReturn(6);
		
		try {
			repo.add(instance);
		} catch (GameAlreadyExists e) {
			Assert.assertEquals("", e.getMessage());
		}
		
		try {
			repo.get(6);
			Assert.assertTrue(true);
		} catch (GameNotFound e) {
			Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void attemptToGetAnInstanceThatDoesntExists() {	
		try {
			repo.get(6);
			Assert.assertTrue(false);
		} catch (GameNotFound e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void attemptToUpdateAnInstanceThatExistsAndIsANewerVersion() {
		GameInstance instance = mock(GameInstance.class);
		when(instance.getId()).thenReturn(6);
		when(instance.getVersion()).thenReturn(2);
		
		try {
			repo.add(instance);
		} catch (GameAlreadyExists e) {
			Assert.assertEquals("", e.getMessage());
		}
		
		GameInstance updatedInstance = mock(GameInstance.class);
		when(updatedInstance.getId()).thenReturn(6);
		when(updatedInstance.getVersion()).thenReturn(3);
		
		try {
			repo.update(updatedInstance);
			Assert.assertTrue(true);
		} catch (GameNotFound e) {
			Assert.assertEquals("", e.getMessage());
		} catch(UpdateVersionOutOfDate e) {
			Assert.assertEquals("", e.getMessage());
		}
	}
	
	@Test
	public void attemptToUpdateAnInstanceThatExistsAndIsTheSameVersion() {
		GameInstance instance = mock(GameInstance.class);
		when(instance.getId()).thenReturn(6);
		when(instance.getVersion()).thenReturn(2);
		
		try {
			repo.add(instance);
		} catch (GameAlreadyExists e) {
			Assert.assertEquals("", e.getMessage());
		}
		
		try {
			repo.update(instance);
			Assert.assertTrue(false);
		} catch (GameNotFound e) {
			Assert.assertEquals("", e.getMessage());
		} catch(UpdateVersionOutOfDate e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void attemptToUpdateAnInstanceThatExistsAndIsAnOlderVersion() {
		GameInstance instance = mock(GameInstance.class);
		when(instance.getId()).thenReturn(6);
		when(instance.getVersion()).thenReturn(2);
		
		try {
			repo.add(instance);
		} catch (GameAlreadyExists e) {
			Assert.assertEquals("", e.getMessage());
		}
		
		GameInstance updatedInstance = mock(GameInstance.class);
		when(updatedInstance.getId()).thenReturn(6);
		when(updatedInstance.getVersion()).thenReturn(1);
		
		try {
			repo.update(updatedInstance);
			Assert.assertTrue(false);
		} catch (GameNotFound e) {
			Assert.assertEquals("", e.getMessage());
		} catch(UpdateVersionOutOfDate e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void attemptToUpdateAnInstanceThatDoesntExist() {
		
		GameInstance updatedInstance = mock(GameInstance.class);
		when(updatedInstance.getId()).thenReturn(6);
		when(updatedInstance.getVersion()).thenReturn(1);
		
		try {
			repo.update(updatedInstance);
			Assert.assertTrue(false);
		} catch (GameNotFound e) {
			Assert.assertTrue(true);
		} catch(UpdateVersionOutOfDate e) {
			Assert.assertEquals("", e.getMessage());
		}
	}
}
