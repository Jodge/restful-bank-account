package com.tala.webservice.shared.web;

import java.util.HashMap;

public interface StandardJsonResponse {

	static String DEFAULT_MSG_NAME_FIELD = "message";
	static String DEFAULT_MSG_TITLE_FIELD = "title";
	static String DEFAULT_MSG_TITLE_VALUE = "Internal Server Error";
	static String DEFAULT_MSG_NAME_VALUE = "The server encountered an unexpected condition which prevented it from fulfilling the request.";
	static String VERSIONING_ERROR_MSG = "There is an error with versioning. If you are performing an update, please ensure that the version number has been incremented.";
	static String SERVER_SIDE_ERROR_MSG = "A server side error has occurred. If the error persists, please reach out to Google to follow-up.";
	static String RESOURCE_TO_INSERT_ALREADY_EXISTS_MSG = "The resource to insert already exists. Please check your resource ID.";
	static String RESOURCE_NOT_FOUND_MSG = "The resource requested is not found. Please check your resource ID.";
	static String UNAUTHORISED_REQUEST_MSG = "You do not have permissions to access the resource. Make sure that you are logged in using an account that has permissions to perform the operation.";
	static String INVALID_ARGUMENT_MSG = "One or more of the arguments provided is invalid.";
		
	public void setSuccess(boolean success);

	public void setSuccess(boolean success, String title, String msg);

	public void addMessage(String message);

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
	 * returns message put in by setMessage
	 * 
	 * @return
	 */
	public String getMessage();

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