package com.sample.scraper.service;

import static com.sample.scraper.constants.URLProcessingConstants.A_LIST_ITEM;
import static com.sample.scraper.constants.URLProcessingConstants.A_ROW;
import static com.sample.scraper.constants.URLProcessingConstants.CONTENT;
import static com.sample.scraper.constants.URLProcessingConstants.MAS_LATEST_UPDATES_ID;
import static com.sample.scraper.constants.URLProcessingConstants.MAS_TECHNICAL_DETAILS_ID;
import static com.sample.scraper.constants.URLProcessingConstants.MAS_TITLE_ID;
import static com.sample.scraper.constants.URLProcessingConstants.ORIGINAL_RELEASE_DT;
import static com.sample.scraper.constants.URLProcessingConstants.PRODUCT_DETAILS_TABLE_ID;
import static com.sample.scraper.constants.URLProcessingConstants.SPAN_TAG;
import static com.sample.scraper.constants.URLProcessingConstants.VERSION;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;
import com.sample.scraper.beans.URLDetailsResponse;
import com.sample.scraper.exception.URLProcessingExeption;

/**
 * Service implementation for processing URL details from web.
 * @author abhinav_tripathi
 *
 */
@Service
public class URLScraperServiceImpl implements URLScraperService {
	final static Logger logger = LoggerFactory.getLogger(URLScraperService.class);


	/**
	 * Method to parse app details from appstore url
	 * @param loadedPage
	 * @return URLDetailsResponse
	 * @throws Exception
	 */
	@Override
	public URLDetailsResponse getURLDetails(String url) throws URLProcessingExeption {
		// Create configuration for JBrowserDriver - Embeddable browser
		JBrowserDriver driver = new JBrowserDriver(Settings.builder().timezone(Timezone.UTC)
				.maxConnections(200)
				.socketTimeout(10000)
				.connectionReqTimeout(10000)
				.connectTimeout(10000).build());
		logger.debug("driver creating successful >>>>>>>>>");

		URLDetailsResponse detail = new URLDetailsResponse();
		try {
			driver.get(url);
			String loadedPage = driver.getPageSource();
			detail = parseWithJsoup(loadedPage);
		} catch (Exception e) {
			throw new URLProcessingExeption(e, "There was a problem in parsing data from app store.");
		} finally{
			driver.quit();
		}
		return detail;
	}

	/**
	 * Method to parse page as DOM objects with JSOUP library
	 * @param loadedPage
	 * @return URLDetailsResponse
	 * @throws Exception
	 */
	private URLDetailsResponse parseWithJsoup(String loadedPage) throws Exception {
		logger.debug("STARTED JSOUP PARSING>>>>>>>>>");
		URLDetailsResponse response = new URLDetailsResponse();
		Document doc = Jsoup.parse(loadedPage);

		String appName = getAppNameDetails(doc);
		String version = getVersionDetails(doc);
		String changeLog = getChangeLogDetails(doc);
		String releaseDate = getReleaseDateDetails(doc);

		response.setAppName(appName);
		response.setVersion(version);
		response.setChangeLog(changeLog);
		response.setReleaseDate(releaseDate);
		logger.debug(URLDetailsResponse.toJson(response));
		return response;
	}

	/**
	 * Method to retrieve release details
	 * @param Document
	 * @return
	 */
	private String getReleaseDateDetails(Document doc) {
		String releaseDate = null;
		Element productDetails = doc.getElementById(PRODUCT_DETAILS_TABLE_ID);
		Elements elements = productDetails.getElementsByClass(CONTENT);
		Elements releaseElements = elements.get(0).getElementsContainingText(ORIGINAL_RELEASE_DT);
		if (!releaseElements.isEmpty() && releaseElements.get(2) != null){
			Element releaseElement = releaseElements.get(2);
			releaseDate = releaseElement.ownText();
		}
		return releaseDate;
	}

	/**
	 * Method to retrieve change log details
	 * @param Document
	 * @return
	 */
	private String getChangeLogDetails(Document doc) {
		String changeLog = null;
		Element latestUpdateElements = doc.getElementById(MAS_LATEST_UPDATES_ID);
		if (latestUpdateElements !=null){
			Elements changeLogElements = latestUpdateElements.getElementsByClass(A_LIST_ITEM);
			if (changeLogElements!= null && !changeLogElements.isEmpty()) {
				changeLog = changeLogElements.get(0).ownText();
			}
		}
		return changeLog;
	}

	/**
	 * Method to retrieve version details
	 * @param Document
	 * @return versionDetails
	 */
	private String getVersionDetails(Document doc) {
		String version = null;
		Element versionElement = doc.getElementById(MAS_TECHNICAL_DETAILS_ID);
		Elements versionElements = versionElement.getElementsContainingText(VERSION);
		for(Element element : versionElements) {
			if (element.ownText().contains(VERSION) && element.parent() != null && element.parent().children() != null){
				Elements versioElements = element.parent().children();
				if (!versioElements.isEmpty()) {
					version = versioElements.get(1).ownText();
				}
			}
		}
		return version;
	}

	/**
	 * Method to retrieve app name details
	 * @param Document
	 * @return appName
	 */
	private String getAppNameDetails(Document doc) {
		String appName = null;
		Element appNameParentElement = doc.getElementById(MAS_TITLE_ID);
		Elements appNameElements = appNameParentElement.getElementsByClass(A_ROW);
		if (!appNameElements.isEmpty() && appNameParentElement.getElementsByTag(SPAN_TAG) != null && appNameParentElement.getElementsByTag(SPAN_TAG).get(0) != null){
			appName = appNameParentElement.getElementsByTag(SPAN_TAG).get(0).ownText();
		}
		return appName;
	}

	@Override
	public List<URLDetailsResponse> getAllURLDetails(List<String> url) {
		// TODO : TO BE IMPLEMENTED IN FUTURE.
		return null;
	}

}
