package com.rb.parser;

import java.io.File;
import java.io.FileInputStream;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rb.parser.controller.ParserController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParserControllerTest {

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new ParserController()).build();
	}

	@Test
	public void processReportTestWithText() throws Exception {
		FileInputStream fis = new FileInputStream(new File("test-files/fail-test.txt"));
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "fail-test.txt", MediaType.TEXT_PLAIN_VALUE,
				fis);
		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/processReport").file(mockMultipartFile))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.nullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status.status", Matchers.comparesEqualTo(400)));
		fis.close();
	}
	
	@Test
	public void processReportTestWithXml() throws Exception {
		FileInputStream fis = new FileInputStream(new File("test-files/records.xml"));
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.xml", MediaType.TEXT_PLAIN_VALUE,
				fis);
		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/processReport").file(mockMultipartFile))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status.status", Matchers.comparesEqualTo(200)));

	}
	
	@Test
	public void processReportTestWithCsv() throws Exception {
		FileInputStream fis = new FileInputStream(new File("test-files/records.csv"));
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.csv", MediaType.TEXT_PLAIN_VALUE,
				fis);
		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/processReport").file(mockMultipartFile))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.notNullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status.status", Matchers.comparesEqualTo(200)));

	}
	
	@Test
	public void processReportTestWithXmlNoFails() throws Exception {
		FileInputStream fis = new FileInputStream(new File("test-files/records-no-fails.xml"));
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records-no-fails.xml", MediaType.TEXT_PLAIN_VALUE,
				fis);
		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/processReport").file(mockMultipartFile))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.nullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status.status", Matchers.comparesEqualTo(200)));

	}
	
	@Test
	public void processReportTestWithCsvNoFails() throws Exception {
		FileInputStream fis = new FileInputStream(new File("test-files/records-no-fails.csv"));
		MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records-no-fails.csv", MediaType.TEXT_PLAIN_VALUE,
				fis);
		this.mockMvc.perform(MockMvcRequestBuilders.multipart("/processReport").file(mockMultipartFile))
				.andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.data", Matchers.nullValue()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status.status", Matchers.comparesEqualTo(200)));

	}
}
