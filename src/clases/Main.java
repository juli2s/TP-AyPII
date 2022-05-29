package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		Juego j = new Juego();
		
		
		System.out.println("Menu principal: \n" + "1 - Administración de personajes  \n" + "2 - Administracion de ligas \n" + "3 - Combates \n"
				+ "4 - Reportes \n" + "5 - Salir \n");
		System.out.println("Ingrese la opción elegida: ");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputString = null;
		try {
			inputString = input.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		}
		Integer opcion = Integer.parseInt(inputString);

		try {
			
			j.menu(opcion);
		
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
}