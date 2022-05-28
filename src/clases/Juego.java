package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Juego {

	/*
	 * post inicializa el juego
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Menu principal: \n" 
				+ "1 - opcion 1 \n"
				+ "2 - opcion 2 \n"
				+ "3 - opcion 3 \n"
				+ "4 - opcion 4 \n"
				+ "5 - opcion 5 \n"
				+ "6 - Salir");
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String inputString = null;
		try {
			inputString = input.readLine();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		Integer opcion = Integer.parseInt(inputString);
		
		menu(opcion);
	}

	private static void menu(Integer opc) {
		boolean salir = true;
		
		/*
		 * en este menu irían los metodos que estan listados en el tp: 
		 * 
		 * Para los sub menu, creo que podríamos hacer un metodo que liste un menu que pasamos por parametro para no repetir tanto codigo.
		 * 
		 * El menú principal deberá permitir:
			> Administración de Personajes
				- Carga desde archivo
				- Creación
				- Listado
				- Guardar en archivo todos los personajes
			> Administración de Ligas
				- Carga desde archivo
				- Creación
				- Listado
				- Guardar en archivo todas las ligas
			> Realización de combates
				- Personaje contra Liga (definiendo característica)A
				- Liga contra Liga (definiendo característica)
			> Reportes
				- Todos los personajes o ligas que venzan a un personaje dado para cierta
			característica
			 - Listado ordenado de personajes por múltiples características
		 * */
		
		do {
			System.out.println("Menu principal: \n" 
					+ "1 - opcion 1 \n"
					+ "2 - opcion 2 \n"
					+ "3 - opcion 3 \n"
					+ "4 - opcion 4 \n"
					+ "5 - opcion 5 \n"
					+ "6 - Salir");
			switch (opc) {
			case 1:
				System.out.println("Case 1"); 
				break;
			case 2:
				System.out.println("Case 2");
				break;
			case 3:
				System.out.println("Case 3");
				break;
			case 4:
				System.out.println("Case 4");
				break;
			case 5:
				System.out.println("Case 5");
				break;
			case 6:
				salir = !salir;
			}
		} while (!salir);
	}
}
