package clases;


import java.io.IOException;


public class Main {

	public static void main(String[] args) {

		Juego j = new Juego();
		
		/*
		 * System.out.println("Ingrese la opción elegida: "); BufferedReader input = new
		 * BufferedReader(new InputStreamReader(System.in)); String inputString = null;
		 * try { inputString = input.readLine(); } catch (IOException e) {
		 * 
		 * e.printStackTrace(); } Integer opcion = Integer.parseInt(inputString);
		 */

		try {
			
			j.menu();
		
		} catch (NumberFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

	}
}