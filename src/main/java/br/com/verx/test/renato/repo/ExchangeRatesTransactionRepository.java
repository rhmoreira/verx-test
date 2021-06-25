package br.com.verx.test.renato.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import br.com.verx.test.renato.ErrorType;
import br.com.verx.test.renato.ExchangeException;
import br.com.verx.test.renato.repo.domain.ExchangeTransaction;

@Repository
public class ExchangeRatesTransactionRepository {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public List<ExchangeTransaction> listByUser(String idUser) {
		return mongoTemplate
				.query(ExchangeTransaction.class)
				.matching(Query.query(Criteria.where("userId").is(idUser)))
				.all();
	}
	
	public ExchangeTransaction saveTransaction(ExchangeTransaction exchangeTransaction) throws ExchangeException {
		try {
			return mongoTemplate.insert(exchangeTransaction);
		} catch (Exception e) {
			throw new ExchangeException("Error saving exchange transaction", ErrorType.UNEXPECTED_ERROR);
		}
	}
}
