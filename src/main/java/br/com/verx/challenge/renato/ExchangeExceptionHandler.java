package br.com.verx.challenge.renato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.verx.challenge.renato.web.rest.BasicResponse;
import br.com.verx.challenge.renato.web.rest.ErrorResponse;

@ControllerAdvice
public class ExchangeExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static Logger log = LoggerFactory.getLogger(ExchangeExceptionHandler.class);

	
	@ExceptionHandler(ExchangeException.class)
	protected ResponseEntity<BasicResponse> handleExchangeException(ExchangeException e) {
		log.error(e.getMessage(), e);
		
		return ResponseEntity
				.status(e.getType().getHttpStatus())
				.body(new ErrorResponse(e.getType().getErrorCode(), e.getMessage()));
	}
}
