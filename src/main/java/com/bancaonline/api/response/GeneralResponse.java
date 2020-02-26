package com.bancaonline.api.response;

public class GeneralResponse {
	
	
	private boolean success;
	private String message;
	private int code;
	
	
	
	
	public GeneralResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeneralResponse(boolean success,  int code, String message) {
		super();
		this.success = success;
		this.message = message;
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	

}
