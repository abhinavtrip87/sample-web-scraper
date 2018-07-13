package com.sample.scraper.controller;

import static com.sample.scraper.constants.URLProcessingConstants.APPLICATION_URL_ENCODED;
import static com.sample.scraper.constants.URLProcessingConstants.ERROR;
import static com.sample.scraper.constants.URLProcessingConstants.EXCEPTION;
import static com.sample.scraper.constants.URLProcessingConstants.MESSAGE;
import static com.sample.scraper.constants.URLProcessingConstants.PATH;
import static com.sample.scraper.constants.URLProcessingConstants.STATUS;
import static com.sample.scraper.constants.URLProcessingConstants.TIMESTAMP;
import static com.sample.scraper.constants.URLProcessingConstants.URL_DETAILS;
import static com.sample.scraper.constants.URLProcessingConstants.URL_DETAILS_REQUEST;
import static com.sample.scraper.constants.URLProcessingConstants.URL_ENDPOINT;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sample.scraper.beans.URLDetailsRequest;
import com.sample.scraper.beans.URLDetailsResponse;
import com.sample.scraper.exception.URLProcessingExeption;
import com.sample.scraper.service.URLScraperService;
import com.sample.scraper.utils.URLValidator;

/**
 * Controller for URLProcessing exposing endpoints
 * @author abhinav_tripathi
 *
 */
@Controller
@ComponentScan(value = {"com.sample.scraper"})
public class URLProcessingController {

	private URLScraperService urlScraperService;

	@Autowired
	public void setUrlScraperService(URLScraperService urlScraperService) {
		this.urlScraperService = urlScraperService;
	}

	/**
	 * GET controller mapping for landing page
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = URL_ENDPOINT, method = RequestMethod.GET)
	public String urlDetailsRequest(Model model) {
		model.addAttribute(URL_DETAILS_REQUEST, new URLDetailsRequest());
		return URL_DETAILS_REQUEST;
	}

	/**
	 * POST controller mapping for details page
	 * @param URLDetailsRequest
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = URL_ENDPOINT, method = RequestMethod.POST, consumes = {APPLICATION_URL_ENCODED})
	public String urlDetailsRequestSubmit(URLDetailsRequest urldetailsrequest, Model model) {
		if (!URLValidator.isValidURL(urldetailsrequest.getUrl())){
			return createErrorResponse(model, System.currentTimeMillis(), "Invalid URL. Please check URL and try again.", 400, new URLProcessingExeption("Invalid URL"), urldetailsrequest.getUrl());
		}
		URLDetailsResponse detail = new URLDetailsResponse();
		try {
			detail = urlScraperService.getURLDetails(urldetailsrequest.getUrl());
			detail.setUrl(urldetailsrequest.getUrl());
		} catch (URLProcessingExeption e) {
			return createErrorResponse(model, System.currentTimeMillis(), "There was a problem with parsing." + e.getMessage(), 400, e, urldetailsrequest.getUrl());
		} catch (Exception e) {
			createErrorResponse(model, System.currentTimeMillis(), "Internal Server Error", 500, e, urldetailsrequest.getUrl());
		}
		List<URLDetailsResponse> urldetails = new ArrayList<>();
		urldetails.add(detail);

		model.addAttribute(URL_DETAILS, urldetails);
		return URL_DETAILS;
	}

	private String createErrorResponse (Model model, long timestamp, String errorMessage, int statusCode, Exception exception, String urlPath){
		model.addAttribute(TIMESTAMP, timestamp);
		model.addAttribute(PATH, urlPath);
		model.addAttribute(STATUS, statusCode);
		model.addAttribute(EXCEPTION, exception);
		model.addAttribute(MESSAGE, errorMessage);
		return ERROR;
	}
}
