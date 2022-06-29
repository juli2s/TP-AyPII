package excepciones;



public class ContrincantesIncompatibles extends Exception
{
    public ContrincantesIncompatibles() {
		
	}

	public ContrincantesIncompatibles(String message) {
		super(message);
	
	}

	public ContrincantesIncompatibles(Throwable cause) {
		super(cause);
		
	}

	public ContrincantesIncompatibles(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ContrincantesIncompatibles(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

}
