package br.com.verx.challenge.renato.test.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Set;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.verx.challenge.renato.Bootstrap;
import br.com.verx.challenge.renato.ExchangeException;
import br.com.verx.challenge.renato.repo.domain.ExchangeTransaction;
import br.com.verx.challenge.renato.service.exchange.ExchangeCurrenciesService;
import br.com.verx.challenge.renato.service.exchange.ExchangeRatesService;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes=Bootstrap.class)
public class ExchangeRatesApiClientServiceTest {
	
	private static final String SRC_CURRENCY = "GBP";
	private static final String TARGET_CURRENCY = "BRL";
	private static final String ID_USER = "renato";
	private static final Double AMOUNT = 10D;

	
	@Autowired
	@Qualifier("ExchangeRatesApiClientService")
	private ExchangeRatesService exchangeServiceFacade;
	
	@Test
	public void a_listCurrencies() throws ExchangeException {
		Set<String> currencies = assertDoesNotThrow(() -> ((ExchangeCurrenciesService)exchangeServiceFacade).listCurrencies());
		
		assertTrue(currencies.contains(SRC_CURRENCY));
	}
	
	@Test
	public void b_convertFromReaisToPounds() throws ExchangeException {
		ExchangeTransaction exchange = assertDoesNotThrow(() -> exchangeServiceFacade.convert(SRC_CURRENCY, TARGET_CURRENCY, AMOUNT, ID_USER));
		
		assertNotNull(exchange.getExchangeRate());
	}
	
}
