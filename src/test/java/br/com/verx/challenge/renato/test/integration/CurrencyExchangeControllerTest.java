package br.com.verx.challenge.renato.test.integration;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.verx.challenge.renato.ErrorType;
import br.com.verx.challenge.renato.test.AbstractChallengeTest;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CurrencyExchangeControllerTest extends AbstractChallengeTest{
	
	private static final String INVALID_CURRENCY = "XPTO";
	
	private static final String SRC_CURRENCY = "GBP";
	private static final String TARGET_CURRENCY = "BRL";
	private static final String ID_USER = "renato";
	private static final Double AMOUNT = 5D;

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void a_convertFromReaisToPounds() throws Exception {
		mockMvc.perform(
			MockMvcRequestBuilders
				.get("/convert/{src}/{trgt}/{amount}/{idUser}", SRC_CURRENCY, TARGET_CURRENCY, AMOUNT, ID_USER)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id", notNullValue()))
		.andExpect(jsonPath("$.exchangeAmount", notNullValue()) );
		
	}
	
	@Test
	public void a_convertInvalidCurrency() throws Exception {
		mockMvc.perform(
			MockMvcRequestBuilders
				.get("/convert/{src}/{trgt}/{amount}/{idUser}", INVALID_CURRENCY, TARGET_CURRENCY, AMOUNT, ID_USER)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().is4xxClientError())
		.andExpect(jsonPath("$.errorCode", is(ErrorType.INVALID_CURRENCY.getErrorCode())));
		
	}
	
	@Test
	public void b_listUserTransactions() throws Exception {
		mockMvc.perform(
					MockMvcRequestBuilders
						.get("/transaction/{idUser}", ID_USER)
						.accept(MediaType.APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$").isArray())
			   .andExpect(jsonPath("$[0].srcCurrency", is(SRC_CURRENCY)))
			   .andExpect(jsonPath("$[0].targetCurrency", is(TARGET_CURRENCY)))
			   .andExpect(jsonPath("$[0].amount", is(AMOUNT)));		
	}
	
	
}
