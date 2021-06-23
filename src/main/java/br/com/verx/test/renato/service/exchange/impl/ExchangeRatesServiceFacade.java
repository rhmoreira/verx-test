package br.com.verx.test.renato.service.exchange.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.verx.test.renato.ExchangeException;
import br.com.verx.test.renato.repo.domain.ExchangeTransaction;
import br.com.verx.test.renato.service.exchange.CurrencyExchange;
import br.com.verx.test.renato.service.exchange.ExchangeRatesService;
import br.com.verx.test.renato.service.exchange.client.CurrencySymbolsValidator;

@Service
@Primary
public class ExchangeRatesServiceFacade implements ExchangeRatesService {
	
	private ExchangeRatesService ratesService;
	private CurrencySymbolsValidator currencyValidator;

	@Autowired
	public ExchangeRatesServiceFacade(
			@Qualifier("ExchangeRatesApiClientService") ExchangeRatesService ratesExchangeService,
			CurrencySymbolsValidator currencyValidator) {
		super();
		this.ratesService = ratesExchangeService;
		this.currencyValidator = currencyValidator;
	}

	@Override
	public ExchangeTransaction convert(String srcCurrency, String targetCurrency, Double amount, String idUser) throws ExchangeException {
		
		currencyValidator.validateSymbol(srcCurrency);
		currencyValidator.validateSymbol(targetCurrency);
		
		ExchangeTransaction exchangeTransaction = ratesService.convert(srcCurrency, targetCurrency, amount, idUser);
		//TODO: save transaction
		
		
		return new CurrencyExchange(exchangeTransaction);
	}
}
