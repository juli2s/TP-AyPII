package excepciones;

public class LigaYaExiste extends Exception {
	public LigaYaExiste() {
		
	}

	public LigaYaExiste(String message) {
		super(message);
	
	}

	public LigaYaExiste(Throwable cause) {
		super(cause);
		
	}

	public LigaYaExiste(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LigaYaExiste(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

}
