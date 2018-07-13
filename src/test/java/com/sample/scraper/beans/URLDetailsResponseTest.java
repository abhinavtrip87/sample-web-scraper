package com.sample.scraper.beans;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;

import com.fasterxml.jackson.databind.ObjectMapper;
public class URLDetailsResponseTest {	

	@Autowired
	private JacksonTester<URLDetailsResponse> json;
	private static final String content = "{\"appName\":\"Instagram\",\"version\":\"50.1.0.43.119\",\"changeLog\":\"Improvements\",\"releaseDate\":\"June 15, 2014\"}";

	@Before
	public void setup() {
		ObjectMapper objectMapper = new ObjectMapper(); 
		// Possibly configure the mapper
		JacksonTester.initFields(this, objectMapper);
	}

	@Test
	public void testSerialize() throws Exception {
		URLDetailsResponse detail = new URLDetailsResponse();
		detail.setAppName("Instagram");
		detail.setChangeLog("Improvements");
		detail.setReleaseDate("June 15, 2014");
		detail.setVersion("50.1.0.43.119");
		assertThat(this.json.write(detail)).isEqualToJson(content);
	}

	@Test
	public void deserializeJson() throws IOException {
		URLDetailsResponse detail = new URLDetailsResponse();
		detail.setAppName("Instagram");
		detail.setChangeLog("Improvements");
		detail.setReleaseDate("June 15, 2014");
		detail.setVersion("50.1.0.43.119");

		assertThat(this.json.parseObject(content).getAppName())
		.isEqualTo(detail.getAppName());
		assertThat(this.json.parseObject(content).getChangeLog())
		.isEqualTo(detail.getChangeLog());
		assertThat(this.json.parseObject(content).getVersion())
		.isEqualTo(detail.getVersion());
		assertThat(this.json.parseObject(content).getReleaseDate())
		.isEqualTo(detail.getReleaseDate());
	}

}
