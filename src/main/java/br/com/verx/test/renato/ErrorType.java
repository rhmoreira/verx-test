package br.com.verx.test.renato;

import org.springframework.http.HttpStatus;

public enum ErrorType {

	SERVICE_REQ_FAILURE("client_request_error", HttpStatus.INTERNAL_SERVER_ERROR),
	
	USER_NOT_FOUND("user_not_found", HttpStatus.NOT_FOUND),
	TRANSACTIONS_NOT_FOUND("transactions_not_found", HttpStatus.NOT_FOUND),
	
	INVALID_CURRENCY("invalid_currency", HttpStatus.BAD_REQUEST),
	
	UNEXPECTED_ERROR("unexpected_error", HttpStatus.INTERNAL_SERVER_ERROR),
	;
	
	private String errorCode;
	private HttpStatus httpStatus;
	
	private ErrorType(String errorCode, HttpStatus httpStatusCode) {
		this.errorCode = errorCode;
		this.httpStatus = httpStatusCode;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
