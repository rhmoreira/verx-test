package br.com.verx.test.renato;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.verx.test.renato.web.rest.BasicResponse;
import br.com.verx.test.renato.web.rest.ErrorResponse;

@ControllerAdvice
public class ExchangeExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(ExchangeException.class)
	protected ResponseEntity<BasicResponse> handleExchangeException(ExchangeException e) {
		return ResponseEntity
				.status(e.getType().getHttpStatus())
				.body(new ErrorResponse(e.getType().getErrorCode(), e.getMessage()));
	}
}
