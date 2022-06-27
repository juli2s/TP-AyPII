package excepciones;

public class FormatoArchivoIncorrecto extends Exception
{
    public FormatoArchivoIncorrecto() {
		
	}

	public FormatoArchivoIncorrecto(String message) {
		super(message);
	
	}

	public FormatoArchivoIncorrecto(Throwable cause) {
		super(cause);
		
	}

	public FormatoArchivoIncorrecto(String message, Throwable cause) {
		super(message, cause);
		
	}

	public FormatoArchivoIncorrecto(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

}

