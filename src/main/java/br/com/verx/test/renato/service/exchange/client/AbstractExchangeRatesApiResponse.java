package br.com.verx.test.renato.service.exchange.client;

import java.util.Map;

@SuppressWarnings("unchecked")
abstract class AbstractExchangeRatesApiResponse {

	private boolean success;
	private Map<String, Object> error;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public Integer getErrorCode() {
		if (!this.isSuccess())
			return (Integer) ((Map<String, Object>)error.get("error")).get("code");
		else
			return -1;
	}
	
}
