package excepciones;


public class LigaVacia extends Exception
{
    public LigaVacia() {
		
	}

	public LigaVacia(String message) {
		super(message);
	
	}

	public LigaVacia(Throwable cause) {
		super(cause);
		
	}

	public LigaVacia(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LigaVacia(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

}


