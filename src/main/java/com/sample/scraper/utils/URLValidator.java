package com.sample.scraper.utils;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Utility for validating incoming URLs
 * @author abhinav_tripathi03
 *
 */
public class URLValidator {

	public static boolean isValidURL(String url) {
		try {
			new URL(url).toURI();
			return true;
		} catch (MalformedURLException | URISyntaxException e) {
			return false;
		}
	}

}
