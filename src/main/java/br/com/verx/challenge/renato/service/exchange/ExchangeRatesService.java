package br.com.verx.challenge.renato.service.exchange;

import br.com.verx.challenge.renato.ExchangeException;
import br.com.verx.challenge.renato.repo.domain.ExchangeTransaction;

public interface ExchangeRatesService {

	public ExchangeTransaction convert(String srcCurrency, String targetCurrency, Double amount, String idUser) throws ExchangeException ;
	
}
