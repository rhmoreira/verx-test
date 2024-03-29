package br.com.verx.challenge.renato.service.exchange.client;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.verx.challenge.renato.ErrorType;
import br.com.verx.challenge.renato.ExchangeException;
import br.com.verx.challenge.renato.repo.domain.ExchangeTransaction;
import br.com.verx.challenge.renato.service.exchange.ExchangeCurrenciesService;
import br.com.verx.challenge.renato.service.exchange.ExchangeRatesService;

@Service
@Qualifier("ExchangeRatesApiClientService")
@Scope("singleton")
public class ExchangeRatesApiClientService implements ExchangeRatesService, ExchangeCurrenciesService{
	
	private static Logger log = LoggerFactory.getLogger(ExchangeRatesApiClientService.class);
	
	private static final String API_URL = "http://api.exchangeratesapi.io";
	
	private static final String CURRENCY_URI = "/latest";
	private static final String SYMBOLS_URI = "/symbols";
	
	private static final String ACCESS_KEY = "1f68a5f2ca28a58c0face02f98534e81";
	
	@Override
	public ExchangeTransaction convert(String srcCurrency, String targetCurrency, Double amount, String idUser) throws ExchangeException  {
		log.debug("Converting '{}' to '{}'", srcCurrency, targetCurrency);
		
		if (srcCurrency.equals(targetCurrency) && srcCurrency.equals("EUR")) {
			log.info("Source currency and target currency are both 'EUR'. Returning default exchange");
			return new ExchangeTransaction(idUser, srcCurrency, amount, targetCurrency, 1D, LocalDateTime.now());
		}
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("symbols", srcCurrency.concat(",").concat(targetCurrency));
		
		ExchangeRatesApiResponse response = invokeService(CURRENCY_URI, params, ExchangeRatesApiResponse.class);
		
		Double srcRate = response.getRates().get(srcCurrency);
		Double targetRate = response.getRates().get(targetCurrency);
		
		ExchangeTransaction exchTx = new ExchangeTransaction(idUser, srcCurrency, amount, targetCurrency, targetRate / srcRate);
		return exchTx;
	}
	
	@Override
	public Set<String> listCurrencies() throws ExchangeException {
		log.debug("Listing currency symbols...");
		ExchangeRatesApiSymbolsResponse response = invokeService(SYMBOLS_URI, new HashMap<>(), ExchangeRatesApiSymbolsResponse.class);
		return response.getSymbols().keySet();
	}
	
	private <T extends AbstractExchangeRatesApiResponse> T invokeService(String serviceUri, Map<String, String> queryParams, Class<T> responseClass) throws ExchangeException {
		log.debug("Invoking exchange api service. '{}'", serviceUri);
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(API_URL.concat(serviceUri));
		
		queryParams.put("access_key", ACCESS_KEY);		
		queryParams.forEach((key, value) -> uriBuilder.queryParam(key, value));
		
		ResponseEntity<T> response = new RestTemplate().getForEntity(uriBuilder.build().toString(), responseClass, queryParams);
		
		validateResponse(response);
		
		return response.getBody();
	}
	
	private <T extends AbstractExchangeRatesApiResponse> void validateResponse(ResponseEntity<T> response) throws ExchangeException {
		log.debug("Validating response...");
		if (!response.getStatusCode().equals(HttpStatus.OK))
			throw new ExchangeException("Failed to query for symbols", ErrorType.SERVICE_REQ_FAILURE);
		
		T result = response.getBody();
		if (!result.isSuccess())
			throw new ExchangeException("Error consuming Exchange Rate service", ErrorType.UNEXPECTED_ERROR);
		
		log.debug("Response OK");
	}

}
