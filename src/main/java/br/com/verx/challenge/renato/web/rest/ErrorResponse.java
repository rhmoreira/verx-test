package br.com.verx.challenge.renato.web.rest;

public class ErrorResponse extends BasicResponse {

	private String errorCode;

	public ErrorResponse(String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
}
