package br.com.verx.challenge.renato.test.unit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.apache.commons.compress.utils.Sets;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.verx.challenge.renato.ErrorType;
import br.com.verx.challenge.renato.ExchangeException;
import br.com.verx.challenge.renato.repo.ExchangeRatesTransactionRepository;
import br.com.verx.challenge.renato.repo.domain.ExchangeTransaction;
import br.com.verx.challenge.renato.service.exchange.CurrencyExchange;
import br.com.verx.challenge.renato.service.exchange.ExchangeRatesService;
import br.com.verx.challenge.renato.service.exchange.client.CurrencySymbolsValidator;
import br.com.verx.challenge.renato.service.exchange.client.ExchangeRatesApiClientService;
import br.com.verx.challenge.renato.service.exchange.impl.ExchangeRatesServiceFacade;
import br.com.verx.challenge.renato.test.AbstractChallengeTest;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExchangeRatesServiceFacadeTest extends AbstractChallengeTest {
	
	private static final String SRC_CURRENCY = "USD";
	private static final String TARGET_CURRENCY = "BRL";
	private static final String ID_USER = "renato";
	private static final Double AMOUNT = 5D;
	private static final Double EXCHANGE_RATE = 1.5D;

	@MockBean
	private ExchangeRatesApiClientService exchangeService;
	
	@Autowired
	private ExchangeRatesTransactionRepository transactionRepo;
	
	@Autowired
	private CurrencySymbolsValidator currencyValidator;
	
	private ExchangeRatesService exchangeServiceFacade;
	
	private boolean setupDone;
	
	@Before
	public void setupExchangeRatesCurrencyServiceMock() throws Exception {
		if(!setupDone) {
			// Mocking currency service api client
			when(exchangeService.listCurrencies())
				.thenReturn(Sets.newHashSet("USD", "BRL", "JPY", "EUR"));
			
			when(exchangeService.convert(anyString(), anyString(), anyDouble(), anyString()))
				.thenReturn(new ExchangeTransaction(ID_USER, SRC_CURRENCY, AMOUNT, TARGET_CURRENCY, EXCHANGE_RATE));
			
			currencyValidator.afterPropertiesSet();
			this.exchangeServiceFacade = new ExchangeRatesServiceFacade(exchangeService, currencyValidator, transactionRepo);
			setupDone = true;
		}
	}
	
	@Test
	public void a_convertSuccessfully() throws ExchangeException {
		ExchangeTransaction transaction = exchangeServiceFacade.convert(SRC_CURRENCY, TARGET_CURRENCY, AMOUNT, ID_USER);
		
		assertNotNull(transaction.getId());
		assertEquals( ((CurrencyExchange)transaction).getExchangeAmount(), AMOUNT * EXCHANGE_RATE );
	}
	
	@Test
	public void b_convertInvalidCurrency() throws ExchangeException {
		ExchangeException exchangeException = assertThrows(ExchangeException.class, () -> exchangeServiceFacade.convert("CAD", TARGET_CURRENCY, AMOUNT, ID_USER));
		
		assertEquals(exchangeException.getType(), ErrorType.INVALID_CURRENCY);
	}
	
	@Test
	public void c_persistedExchangeTransaction() throws ExchangeException {
		List<ExchangeTransaction> transactions = transactionRepo.listByUser(ID_USER);
		
		assertTrue(!transactions.isEmpty());
		assertEquals(transactions.size(), 1);
		
		ExchangeTransaction transaction = transactions.get(0);
		
		assertEquals(transaction.getAmount(), AMOUNT);
		assertEquals(transaction.getSrcCurrency(), SRC_CURRENCY);
		assertEquals(transaction.getTargetCurrency(), TARGET_CURRENCY);
		assertEquals(transaction.getUserId(), ID_USER);
	}
}
