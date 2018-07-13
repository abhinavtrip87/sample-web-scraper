package com.sample.scraper;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.sample.scraper.beans.URLDetailsResponse;
import com.sample.scraper.controller.URLProcessingController;
import com.sample.scraper.service.URLScraperService;

@RunWith(SpringRunner.class)
@WebMvcTest(URLProcessingController.class)
public class URLProcessingWebMockTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private URLScraperService service;

	private static final String APP_ENDPOINT = "/amazon/appstore/details";

	@Test
	public void testGetRequestForLanding() throws Exception {
		this.mockMvc.perform(get(APP_ENDPOINT)).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Welcome to Amazon App Store URLWebScraper")));
	}

	@Test
	public void testPostRequestForURLDetails() throws Exception {
		URLDetailsResponse response = new URLDetailsResponse();
		response.setAppName("appName");
		response.setVersion("version");
		response.setChangeLog("changeLog");
		response.setReleaseDate("releaseDate");

		when(service.getURLDetails("http://someDummyUrl/dummy")).thenReturn(response);

		this.mockMvc.perform(post(APP_ENDPOINT)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content("url=http://someDummyUrl/dummy")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Amazon AppStore URL Details")));

	}

	@Test
	public void testEmptyPostRequest() throws Exception {
		this.mockMvc.perform(post(APP_ENDPOINT)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content("url= ")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Invalid URL. Please check URL and try again.")));
	}

	@Test
	public void testNoContenrtPostRequest() throws Exception {
		this.mockMvc.perform(post(APP_ENDPOINT)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.content(" ")).andDo(print()).andExpect(status().isOk())
		.andExpect(content().string(containsString("Invalid URL. Please check URL and try again.")));

	}
}
