package br.com.verx.challenge.renato.service.exchange;

import br.com.verx.challenge.renato.repo.domain.ExchangeTransaction;

public class CurrencyExchange extends ExchangeTransaction{
	
	private Double exchangeAmount;
	
	public CurrencyExchange(ExchangeTransaction exchange) {
		super.setId(exchange.getId());
		super.setUserId(exchange.getUserId());
		super.setSrcCurrency(exchange.getSrcCurrency());
		super.setAmount(exchange.getAmount());
		super.setTargetCurrency(exchange.getTargetCurrency());
		super.setDate(exchange.getDate());
		super.setExchangeRate(exchange.getExchangeRate());
		
		this.exchangeAmount = getAmount() * getExchangeRate();
	}
	
	public CurrencyExchange() {
	}

	public Double getExchangeAmount() {
		return exchangeAmount;
	}
	public void setExchangeAmount(Double exchangeAmount) {
		this.exchangeAmount = exchangeAmount;
	}
	
	public String format() {
		return 
			String
				.format("id[%s], src[%s], target[%s], amnt[%d], rate[%s], exch[%d]", 
					getId().toHexString(),
					getSrcCurrency(),
					getTargetCurrency(),
					getAmount(),
					getExchangeRate(),
					getExchangeAmount()
				);
	}
}
