package clases;


import java.io.IOException;


public class Main {

	public static void main(String[] args) {

		try {
			
			Juego j = Juego.getInstance();
		
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		}

	}
}