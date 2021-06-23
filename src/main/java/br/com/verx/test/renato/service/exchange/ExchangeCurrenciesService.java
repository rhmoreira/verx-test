package br.com.verx.test.renato.service.exchange;

import java.util.Set;

import br.com.verx.test.renato.ExchangeException;

public interface ExchangeCurrenciesService {

	public Set<String> listCurrencies() throws ExchangeException ;
}
