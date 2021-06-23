package br.com.verx.test.renato.service.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.verx.test.renato.ExchangeException;
import br.com.verx.test.renato.repo.domain.ExchangeTransaction;

@Service
@Primary
public class ExchangeRatesServiceFacade implements ExchangeRatesService {
	
	private ExchangeRatesService currencyExchangeService;

	@Autowired
	public ExchangeRatesServiceFacade(
			@Qualifier("exchangeRatesApiService") ExchangeRatesService currencyExchangeService) {
		super();
		this.currencyExchangeService = currencyExchangeService;
	}

	@Override
	public ExchangeTransaction convert(String srcCurrency, String targetCurrency, Double amount, String idUser) throws ExchangeException {
		ExchangeTransaction exchangeTransaction = currencyExchangeService.convert(srcCurrency, targetCurrency, amount, idUser);
		//TODO: save transaction
		
		
		return new CurrencyExchange(exchangeTransaction);
	}
}
