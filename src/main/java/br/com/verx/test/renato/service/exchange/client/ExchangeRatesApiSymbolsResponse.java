package br.com.verx.test.renato.service.exchange.client;

import java.util.Map;

public class ExchangeRatesApiSymbolsResponse extends AbstractExchangeRatesApiResponse{

	private Map<String, Object> symbols;

	public Map<String, Object> getSymbols() {
		return symbols;
	}
	public void setSymbols(Map<String, Object> symbols) {
		this.symbols = symbols;
	}
}
