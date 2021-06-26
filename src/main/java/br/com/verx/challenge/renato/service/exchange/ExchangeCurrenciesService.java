package br.com.verx.challenge.renato.service.exchange;

import java.util.Set;

import br.com.verx.challenge.renato.ExchangeException;

public interface ExchangeCurrenciesService {

	public Set<String> listCurrencies() throws ExchangeException ;
}
