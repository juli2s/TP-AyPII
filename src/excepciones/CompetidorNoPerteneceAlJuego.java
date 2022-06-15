package excepciones;

public class CompetidorNoPerteneceAlJuego extends Exception
{
    public CompetidorNoPerteneceAlJuego() {
		
	}

	public CompetidorNoPerteneceAlJuego(String message) {
		super(message);
	
	}

	public CompetidorNoPerteneceAlJuego(Throwable cause) {
		super(cause);
		
	}

	public CompetidorNoPerteneceAlJuego(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CompetidorNoPerteneceAlJuego(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

}
