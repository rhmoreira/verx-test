package br.com.verx.challenge.renato.repo.domain;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("exchange_transaction")
public class ExchangeTransaction {

	@Id
	private ObjectId id;
	private String userId;
	private String srcCurrency;
	private Double amount;
	private String targetCurrency;
	private Double exchangeRate;
	private LocalDateTime date;
	
	
	public ExchangeTransaction(String userId, String srcCurrency, Double amount, String targetCurrency,
			Double exchangeRate) {
		this(userId, srcCurrency, amount, targetCurrency, exchangeRate, null);
	}
	
	public ExchangeTransaction(String userId, String srcCurrency, Double amount, String targetCurrency,
			Double exchangeRate, LocalDateTime date) {
		super();
		this.userId = userId;
		this.srcCurrency = srcCurrency;
		this.amount = amount;
		this.targetCurrency = targetCurrency;
		this.exchangeRate = exchangeRate;
		this.date = date;
	}
	
	public ExchangeTransaction() {
	}
	
	public ObjectId getId() {
		return id;
	}
	public void setId(ObjectId id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getSrcCurrency() {
		return srcCurrency;
	}
	public void setSrcCurrency(String srcCurrency) {
		this.srcCurrency = srcCurrency;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getTargetCurrency() {
		return targetCurrency;
	}
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
	public Double getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Double exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public LocalDateTime getDate() {
		return date;
	}
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
}
