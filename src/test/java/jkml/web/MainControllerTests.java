package jkml.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testNow() throws Exception {
		mockMvc.perform(get("/now")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testUpload_skipNames_true() throws Exception {
		mockMvc.perform(get("/upload").param("fileType", "master").param("baseName","RPT20220131"))
		.andDo(print()).andExpect(status().isOk());
	}

	@Test
	void testUpload_skipNames_false() throws Exception {
		mockMvc.perform(get("/upload").param("fileType", "master").param("baseName","RPT20220131").param("skipNames", "false"))
		.andDo(print()).andExpect(status().isOk());
	}

}
