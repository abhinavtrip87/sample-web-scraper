package com.sample.scraper.exception;

/**
 * User-Defined exception for URLProcessing web scraping application
 * @author abhinav_tripathi
 *
 */
public class URLProcessingExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;

	public URLProcessingExeption(String message) {
		this.message = message;
	}

	public URLProcessingExeption(Throwable cause, String message) {
		super(message);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
