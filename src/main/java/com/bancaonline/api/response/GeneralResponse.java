package com.bancaonline.api.response;

/**
 * The type General response.
 */
public class GeneralResponse {

	private boolean success;
	private String message;
	private int code;

	/**
	 * Instantiates a new General response.
	 *
	 * @param success the success
	 * @param code    the code
	 * @param message the message
	 */
	public GeneralResponse(boolean success,  int code, String message) {
		this.success = success;
		this.message = message;
		this.code = code;
	}

	/**
	 * Is success boolean.
	 *
	 * @return the boolean
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Sets success.
	 *
	 * @param success the success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Gets message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets message.
	 *
	 * @param message the message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets code.
	 *
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets code.
	 *
	 * @param code the code
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	
	

}
