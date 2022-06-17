package excepciones;

public class PerteneceALigaException extends Exception {

	public PerteneceALigaException() {
		
	}

	public PerteneceALigaException(String message) {
		super(message);
	
	}

	public PerteneceALigaException(Throwable cause) {
		super(cause);
		
	}

	public PerteneceALigaException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PerteneceALigaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

}
