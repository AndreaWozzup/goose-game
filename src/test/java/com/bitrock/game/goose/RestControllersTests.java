package com.bitrock.game.goose;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllersTests {
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private Games games;

	@Before
	public void init() throws Exception {
		this.mockMvc.perform(post("/add?name=Pippo"));
	}

	@Test
	public void addPlayer() throws Exception {
		this.mockMvc.perform(post("/add?name=Pluto")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Pluto")));
	}

	@Test
	public void addExistingPlayer() throws Exception {
		this.mockMvc.perform(post("/add?name=Pippo")).andDo(print()).andExpect(status().isBadRequest())
				.andExpect(content().string(containsString("Pippo: already existing player")));
	}

	@Test
	public void addMultiplePlayers() throws Exception {

		long initialSize = games.size();

		// add 80 players, maximum allowed number of players for each game is 8
		for (int i = 0; i < 79; ++i) {
			this.mockMvc.perform(post("/add?name=Player" + i));
		}

		// we expect to have 10 more active games
		assertEquals(games.size(), initialSize + 10);
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void movePlayer() throws Exception {

		Roll roll = new Roll(1, 2);

		mockMvc.perform(MockMvcRequestBuilders.post("/move?name=Pippo").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(roll))).andExpect(status().isOk())
				.andExpect(content().string(containsString("Pippo rolls 1, 2. Pippo moves from 1 to 4.")));

	}

	@Test
	public void moveNonExistingPlayer() throws Exception {

		Roll roll = new Roll(1, 2);

		mockMvc.perform(MockMvcRequestBuilders.post("/move?name=Paperino").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(roll))).andExpect(status().isBadRequest());

	}

	@Test
	public void invalidRoll() throws Exception {

		Roll roll = new Roll(7, 7);

		mockMvc.perform(MockMvcRequestBuilders.post("/move?name=Paperino").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(roll))).andExpect(status().isBadRequest());

	}

}
