package br.com.verx.challenge.renato;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.verx.challenge.renato.web.rest.ErrorResponse;

@Component
@RestControllerAdvice
public class ExchangeExceptionHandler extends ResponseEntityExceptionHandler {
	
	private static Logger log = LoggerFactory.getLogger(ExchangeExceptionHandler.class);

	
	@ExceptionHandler(ExchangeException.class)
	protected ResponseEntity<ErrorResponse> handleExchangeException(ExchangeException e) {
		log.error(e.getMessage(), e);
		
		return ResponseEntity
				.status(e.getType().getHttpStatus())
				.body(new ErrorResponse(e.getType().getErrorCode(), e.getMessage()));
	}
	
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<ErrorResponse> handleException(RuntimeException e) {
		log.error(e.getMessage(), e);
		
		return ResponseEntity
				.status(ErrorType.UNEXPECTED_ERROR.getHttpStatus())
				.body(new ErrorResponse(ErrorType.UNEXPECTED_ERROR.getErrorCode(), "I'm deeply sorry. 3 hired monkeys are already working out the problem"));
	}
}
