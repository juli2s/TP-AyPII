package excepciones;


public class FormatoCamposIncorrecto extends Exception
{
    public FormatoCamposIncorrecto() {
		
	}

	public FormatoCamposIncorrecto(String message) {
		super(message);
	
	}

	public FormatoCamposIncorrecto(Throwable cause) {
		super(cause);
		
	}

	public FormatoCamposIncorrecto(String message, Throwable cause) {
		super(message, cause);
		
	}

	public FormatoCamposIncorrecto(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

}

