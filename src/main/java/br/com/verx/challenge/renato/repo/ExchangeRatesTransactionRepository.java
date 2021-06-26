package br.com.verx.challenge.renato.repo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.verx.challenge.renato.ErrorType;
import br.com.verx.challenge.renato.ExchangeException;
import br.com.verx.challenge.renato.repo.domain.ExchangeTransaction;

@Repository
public class ExchangeRatesTransactionRepository {
	
	private static Logger log = LoggerFactory.getLogger(ExchangeRatesTransactionRepository.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<ExchangeTransaction> listByUser(String idUser) {
		log.debug("Querying transactions for user={}", idUser);
		
		return mongoTemplate
				.query(ExchangeTransaction.class)
				.matching(Query.query(Criteria.where("userId").is(idUser)))
				.all();
	}
	
	public ExchangeTransaction saveTransaction(ExchangeTransaction exchangeTransaction) throws ExchangeException {
		log.info("Persisting new Exchange Transaction for user={}", exchangeTransaction.getUserId());
		
		try {
			return mongoTemplate.insert(exchangeTransaction);
		} catch (Exception e) {
			throw new ExchangeException("Error saving exchange transaction", ErrorType.UNEXPECTED_ERROR);
		}
	}
}
