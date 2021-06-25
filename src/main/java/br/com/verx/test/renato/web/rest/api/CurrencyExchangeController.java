package br.com.verx.test.renato.web.rest.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.verx.test.renato.ErrorType;
import br.com.verx.test.renato.ExchangeException;
import br.com.verx.test.renato.repo.ExchangeRatesTransactionRepository;
import br.com.verx.test.renato.repo.domain.ExchangeTransaction;
import br.com.verx.test.renato.service.exchange.CurrencyExchange;
import br.com.verx.test.renato.service.exchange.ExchangeRatesService;

@RestController
@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE)
public class CurrencyExchangeController {

	private ExchangeRatesService exchangeService;
	private ExchangeRatesTransactionRepository transactionRepository;

	@Autowired
	public CurrencyExchangeController(
			ExchangeRatesService exchangeService,
			ExchangeRatesTransactionRepository transactionRepository) {
		super();
		this.exchangeService = exchangeService;
		this.transactionRepository = transactionRepository;
	}



	@GetMapping("/convert/{src}/{trgt}/{amount}/{idUser}")
	public ExchangeTransaction convert(
			@PathVariable(name = "src") Optional<String> src,
			@PathVariable(name = "trgt") String target,
			@PathVariable(name = "amount") Double amount,
			@PathVariable(name = "idUser") String idUser) throws ExchangeException {
		
		ExchangeTransaction result = exchangeService.convert(src.orElse("EUR"), target, amount, idUser);
		return result;
	}
	
	@GetMapping("/transaction/{idUser}")
	public List<ExchangeTransaction> listTransactions(
			@PathVariable(name = "idUser") String idUser) throws ExchangeException {
		
		List<ExchangeTransaction> userTransactions = 
				transactionRepository
					.listByUser(idUser)
					.stream()
					.map(CurrencyExchange::new)
					.collect(Collectors.toList());
		
		if (userTransactions.isEmpty())
			throw new ExchangeException("No transactions found for user [" + idUser + "]", ErrorType.TRANSACTIONS_NOT_FOUND);
		else
			return userTransactions;
	}
}
