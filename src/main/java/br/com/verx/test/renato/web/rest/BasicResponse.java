package br.com.verx.test.renato.web.rest;

public class BasicResponse {

	private String message;
	

	public BasicResponse(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
