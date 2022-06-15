package clases;


import java.io.IOException;
import excepciones.CompetidorNoPerteneceAlJuego;

public class Main {

	public static void main(String[] args) throws CompetidorNoPerteneceAlJuego{

		try {
			
			Juego j = Juego.getInstance();
			j.iniciarMenu();
		} catch (NumberFormatException | IOException e) {
			
			e.printStackTrace();
		}

	}
}