package com.bitrock.game.goose;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoGame {
	@Autowired
	private MockMvc mockMvc;

	@Before
	public void addPlayers() throws Exception {
		this.mockMvc.perform(post("/add?name=Pippo")).andExpect(status().isOk());
		this.mockMvc.perform(post("/add?name=Pluto")).andExpect(status().isOk());
		this.mockMvc.perform(post("/add?name=Paperino")).andExpect(status().isOk());
	}

	private boolean isWinner(String name) throws Exception {
		MvcResult response = this.mockMvc.perform(post("/move?name=" + name)).andExpect(status().isOk()).andReturn();
		String description = response.getResponse().getContentAsString();
		System.out.println(description);
		Thread.sleep(1000);
		return description.contains("wins");
	}

	@Test
	public void demoGame() throws Exception {
		for (;;) {
			if (isWinner("Pippo"))
				break;
			if (isWinner("Pluto"))
				break;
			if (isWinner("Paperino"))
				break;
		}
	}

}
