package br.com.verx.test.renato.service.exchange.client;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.verx.test.renato.ErrorType;
import br.com.verx.test.renato.ExchangeException;
import br.com.verx.test.renato.repo.domain.ExchangeTransaction;
import br.com.verx.test.renato.service.exchange.ExchangeCurrenciesService;
import br.com.verx.test.renato.service.exchange.ExchangeRatesService;

@Service("exchangeRatesApiService")
public class ExchangeRatesApiService implements ExchangeRatesService, ExchangeCurrenciesService{
	
	private static final String API_URL = "http://api.exchangeratesapi.io";
	
	private static final String CURRENCY_URI = "/latest";
	private static final String SYMBOLS_URI = "/symbols";
	
	private static final String ACCESS_KEY = "871e2333c0ca71edbbcee3c972c7dd09";

	@Override
	public ExchangeTransaction convert(String srcCurrency, String targetCurrency, Double amount, String idUser) throws ExchangeException  {
		return null;
	}
	
	@Override
	public Set<String> listCurrencies() throws ExchangeException {
		ResponseEntity<ExchangeRatesApiSymbolsResponse> response = invokeService(SYMBOLS_URI, new HashMap<>(), ExchangeRatesApiSymbolsResponse.class);
		return response.getBody().getSymbols().keySet();
	}
	
	private <T> ResponseEntity<T> invokeService(String serviceUri, Map<String, String> queryParams, Class<T> responseClass) throws ExchangeException {
		queryParams.put("access_key", ACCESS_KEY);
		
		ResponseEntity<T> response = new RestTemplate().getForEntity(API_URL.concat(serviceUri), responseClass, queryParams);
		if (!response.getStatusCode().equals(HttpStatus.OK))
			throw new ExchangeException("Failed to query for symbols", ErrorType.SERVICE_REQ_FAILURE);
		
		return response;
	}

}
