package br.com.verx.challenge.renato.service.exchange.client;

import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.verx.challenge.renato.ErrorType;
import br.com.verx.challenge.renato.ExchangeException;
import br.com.verx.challenge.renato.service.exchange.ExchangeCurrenciesService;

@Component
public class CurrencySymbolsValidator implements InitializingBean{

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
	}
	
	public void validateSymbol(String symbol) throws ExchangeException {
		if (!this.symbols.contains(symbol))
			throw new ExchangeException("Symbol '" + symbol + "' not supported", ErrorType.INVALID_CURRENCY);
	}
	
}
