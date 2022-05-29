package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {

		Juego j = new Juego();
		
		
		System.out.println("Menu principal: \n" + "1 - opcion 1 \n" + "2 - opcion 2 \n" + "3 - opcion 3 \n"
				+ "4 - opcion 4 \n" + "5 - opcion 5 \n" + "6 - Salir");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputString = null;
		try {
			inputString = input.readLine();
		} catch (IOException e) {

			e.printStackTrace();
		}
		Integer opcion = Integer.parseInt(inputString);

		j.menu(opcion);

	}
}