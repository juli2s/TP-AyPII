package clases;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import excepciones.CompetidorNoPerteneceAlJuego;

public class Menu {

	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public void generarMenu(Juego juego) throws NumberFormatException, IOException, CompetidorNoPerteneceAlJuego {
		boolean salir = true;
		int opc = 0;

		do {
			try {
				switch (mostrarSubMenu(("\n" + "Menu principal: \n" + "1 - Administración de personajes  \n"
						+ "2 - Administracion de ligas \n" + "3 - Combates \n" + "4 - Reportes \n" + "5- Salir \n"))) {
				case 1:
					while (opc != 5) {

						switch (mostrarSubMenu("\n" + "1- Cargar personajes desde archivo \n"
								+ "2- Cargar personaje manualmente \n" + "3- Listar personajes \n"
								+ "4- Guardar personajes en archivo\n" + "5- atrás\n")) {

						case 1:
							juego.cargarPersonajesDesdeArchivo("./src/personajes.txt");
							break;
						case 2:
							juego.cargarPersonajesManualmente();
							break;
						case 3:
							juego.guardarPersonajesEnArchivo(true);
							break;
						case 4:
							juego.guardarPersonajesEnArchivo(false);
							break;
						case 5:
							opc = 5;
							generarMenu(juego);
						}
					}
					opc = 0;
					break;
				case 2:

					while (opc != 5) {
						switch (mostrarSubMenu(
								"\n" + "1- Cargar ligas desde archivo \n" + "2- Cargar Ligas manualmente \n"
										+ "3- Listar Ligas \n" + "4- Guardar Ligas en archivo \n" + "5- atrás\n")) {

						case 1:
							juego.cargarLigaDesdeArchivo("./src/ligas.txt");
							break;
						case 2:
							System.out.println("metodo para cargar Ligas manualmente");
							break;
						case 3:
							juego.guardarLigasEnArchivo(true);
							break;
						case 4:
							juego.guardarLigasEnArchivo(false);
							break;
						case 5:
							opc = 5;
							generarMenu(juego);
						}
					}
					opc = 0;
					break;
				case 3:
					while (opc != 3) {
						switch (mostrarSubMenu(
								"\n" + "1- Pelear \n" + "2- Combate Liga contra liga \n" + "3- atrás\n")) {

						case 1:
							try {
								System.out.println(juego.pelear());
							} catch (Exception CompetidorNoPerteneceAlJuego) {
								System.out.println("El personaje no existe");
							}
							break;
						case 2:
							System.out.println("metodo para combate entre Ligas");
							break;
						case 3:
							opc = 3;
							generarMenu(juego);
						}
					}
					opc = 0;
					break;
				case 4:
					while (opc != 3) {
						switch (mostrarSubMenu("\n"
								+ "1- Todos los personajes o ligas que venzan a un personaje dado para cierta caract\n"
								+ " 2- Listado ordenado de personajes por múltiples características \n"
								+ "3- atrás\n")) {
						case 1:
							System.out.println("metodo para reporte de personajes o ligas que venzan a otro");
							break;
						case 2:
							juego.listarPersonajesPorMultiplesCaracteristicas();
							break;
						case 3:
							opc = 3;
							generarMenu(juego);
						}
					}
					opc = 0;
					break;
				case 5:
					salir = !salir;

				}
			} catch (Exception NumberFormatException) {
				System.out.println("Opcion invalida");
				generarMenu(juego);
			}
		} while (!salir);
	}

	/*
	 * post retorna el valor ingresado para el sub menu
	 */
	private int mostrarSubMenu(String opciones) throws NumberFormatException, IOException {
		System.out.println(opciones + "\n");
		return Integer.parseInt(input.readLine());
	}

}
