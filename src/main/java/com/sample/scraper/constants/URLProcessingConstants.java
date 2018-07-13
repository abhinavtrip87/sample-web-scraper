package com.sample.scraper.constants;

/**
 * Constants for URL Processing application
 * @author abhinav_tripathi
 *
 */
public final class URLProcessingConstants {

	//Endpoint constants
	public static final String URL_ENDPOINT = "/amazon/appstore/details";

	//Encoding constants
	public static final String APPLICATION_URL_ENCODED = "application/x-www-form-urlencoded";

	//Controller constants
	public static final String URL_DETAILS = "urldetails";
	public static final String URL_DETAILS_REQUEST = "urldetailsrequest";
	public static final String ERROR = "error";

	//Error Message constants
	public static final String TIMESTAMP = "timestamp";
	public static final String PATH = "path";
	public static final String STATUS = "status";
	public static final String EXCEPTION = "exception";
	public static final String MESSAGE = "message";

	//Element IDs
	public static final String PRODUCT_DETAILS_TABLE_ID = "productDetailsTable";
	public static final String MAS_TITLE_ID = "mas-title";
	public static final String MAS_TECHNICAL_DETAILS_ID = "masTechnicalDetails-btf";
	public static final String MAS_LATEST_UPDATES_ID = "mas-latest-updates";

	//Element Class
	public static final String CONTENT = "content";
	public static final String A_ROW = "a-row";
	public static final String A_LIST_ITEM = "a-list-item";

	//Tags
	public static final String SPAN_TAG = "span";

	//Search Constants
	public static final String ORIGINAL_RELEASE_DT = "Original Release Date";
	public static final String VERSION = "Version";
}
