package br.com.verx.challenge.renato.service.exchange.client;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.verx.challenge.renato.ErrorType;
import br.com.verx.challenge.renato.ExchangeException;
import br.com.verx.challenge.renato.service.exchange.ExchangeCurrenciesService;

@Component
public class CurrencySymbolsValidator implements InitializingBean{
	
	private static Logger log = LoggerFactory.getLogger(CurrencySymbolsValidator.class);

	private ExchangeCurrenciesService currenciesService;
	
	private Set<String> symbols;
	
	@Autowired
	public CurrencySymbolsValidator(ExchangeCurrenciesService currenciesService) {
		super();
		this.currenciesService = currenciesService;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.symbols = currenciesService.listCurrencies();
		log.info("{} currency symbols loaded", this.symbols.size());
		
	}
	
	public void validateSymbol(String symbol) throws ExchangeException {
		log.debug("Validating currency symbol '{}'", symbol);
		
		if (!this.symbols.contains(symbol))
			throw new ExchangeException("Symbol '" + symbol + "' not supported", ErrorType.INVALID_CURRENCY);
	}
	
}
