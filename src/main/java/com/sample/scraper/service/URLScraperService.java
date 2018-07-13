package com.sample.scraper.service;

import java.util.List;

import com.sample.scraper.beans.URLDetailsResponse;
import com.sample.scraper.exception.URLProcessingExeption;

/**
 * Service for processing URL details from web.
 * @author abhinav_tripathi
 *
 */
public interface URLScraperService {
	
	/**
	 * Method to parse app details from appstore url
	 * @param url
	 * @return
	 * @throws Exception
	 */
	URLDetailsResponse getURLDetails (String url) throws URLProcessingExeption;
	
	/**
	 * Method to parse app details for multiple appstore urls
	 * @param url
	 * @return list of URLDetailsResponses
	 */
	List<URLDetailsResponse> getAllURLDetails (List<String> url);

}
