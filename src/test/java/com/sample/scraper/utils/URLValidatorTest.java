package com.sample.scraper.utils;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import static com.sample.scraper.utils.URLValidator.isValidURL;

public class URLValidatorTest {

	@Test
	public void testValidator() throws Exception {
		assertThat(isValidURL("http://www.amazon.com/Instagram/dp/B00KZP2DTQ/")).isTrue();
		assertThat(isValidURL("https://www.amazon.com/musical-ly-your-video-social-network/dp/B0117U0G3M/")).isTrue();
		assertThat(isValidURL("http://www.amazon.com/Facebook/dp/B0094BB4TW/")).isTrue();
		assertThat(isValidURL("https://www.amazon.com/Twitter-Inc/dp/B004SOR4H6/")).isTrue();
		assertThat(isValidURL("https://www.amazon.com/FORBIS-s-r-o-Video-Call/dp/B01MU9PVWO/")).isTrue();
		assertThat(isValidURL("https://www.amazon.com/Netflix-Inc/dp/B005ZXWMUS/")).isTrue();
		
		assertThat(isValidURL(" ")).isFalse();
		assertThat(isValidURL("/abcd ")).isFalse();
		assertThat(isValidURL(null)).isFalse();
	}
}
