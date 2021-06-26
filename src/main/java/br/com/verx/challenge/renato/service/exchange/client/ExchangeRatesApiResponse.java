package br.com.verx.challenge.renato.service.exchange.client;

import java.util.Date;
import java.util.Map;

public class ExchangeRatesApiResponse extends AbstractExchangeRatesApiResponse {

	private long timestamp;
	private String base;
	private Date date;
	private Map<String, Double> rates;
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Map<String, Double> getRates() {
		return rates;
	}
	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}
}
