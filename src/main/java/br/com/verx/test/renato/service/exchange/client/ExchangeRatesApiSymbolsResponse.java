package br.com.verx.test.renato.service.exchange.client;

import java.util.Map;

public class ExchangeRatesApiSymbolsResponse {

	private Map<String, String> symbols;

	public Map<String, String> getSymbols() {
		return symbols;
	}
	public void setSymbols(Map<String, String> symbols) {
		this.symbols = symbols;
	}
}
