package com.example.webservice.shared.web;

import java.util.HashMap;

/**
 * @author George Otieno
 *
 */
public interface StandardJsonResponse {

	static String DEFAULT_MSG_NAME_FIELD = "message";
	static String DEFAULT_MSG_TITLE_FIELD = "title";
	static String DEFAULT_MSG_TITLE_VALUE = "Internal Server Error";
	static String DEFAULT_MSG_NAME_VALUE = "The server encountered an unexpected condition which prevented it from fulfilling the request.";
	static String RESOURCE_NOT_FOUND_MSG = "The resource requested is not found. Please check your resource ID.";
		
	public void setSuccess(boolean success);

	public void setSuccess(boolean success, String title, String msg);

	/**
	 * @return the success
	 */
	public boolean isSuccess();

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(HashMap<String, String> messages);

	/**
	 * @return the messages
	 */
	public HashMap<String, String> getMessages();
	/**
	 * @param errors
	 *            the errors to set
	 */
	public void setErrors(HashMap<String, String> errors);

	/**
	 * @return the errors
	 */
	public HashMap<String, String> getErrors();

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(HashMap<String, Object> data);

	/**
	 * @return the data
	 */
	public HashMap<String, Object> getData();

	/**
	 * @param code
	 *            the http response code
	 */
	public void setHttpResponseCode(int code);

	/**
	 * @return the http response code
	 */
	public int getHttpResponseCode();
}