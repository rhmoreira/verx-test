package br.com.verx.challenge.renato;

public class ExchangeException extends Exception {

	private static final long serialVersionUID = -3460977006788317103L;
	
	private ErrorType type;

	public ExchangeException() {
		super();
	}

	public ExchangeException(String message, ErrorType type, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.type = type;
	}

	public ExchangeException(String message, ErrorType type, Throwable cause) {
		this(message, type, cause, false, true);
	}

	public ExchangeException(String message, ErrorType type) {
		this(message, type, null);
	}

	public ExchangeException(Throwable cause, ErrorType type) {
		this(null, type, cause);
	}

	public ErrorType getType() {
		return type;
	}

}
