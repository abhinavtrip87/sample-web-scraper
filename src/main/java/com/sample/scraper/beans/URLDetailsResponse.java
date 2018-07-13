package com.sample.scraper.beans;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Placeholder for response object
 * @author abhinav_tripathi
 *
 */
public class URLDetailsResponse {

	String appName;
	String version;
	String changeLog;
	String releaseDate;
	String url;

	public String getAppName() {
		return appName;
	}
	
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getChangeLog() {
		return changeLog;
	}
	public void setChangeLog(String changeLog) {
		this.changeLog = changeLog;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public static String toJson(URLDetailsResponse detail) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(detail);
	}

}
