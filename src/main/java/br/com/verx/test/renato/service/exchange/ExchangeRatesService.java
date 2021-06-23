package br.com.verx.test.renato.service.exchange;

import br.com.verx.test.renato.ExchangeException;
import br.com.verx.test.renato.repo.domain.ExchangeTransaction;

public interface ExchangeRatesService {

	public ExchangeTransaction convert(String srcCurrency, String targetCurrency, Double amount, String idUser) throws ExchangeException ;
	
}
