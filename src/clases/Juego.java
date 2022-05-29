package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Juego {
	ArrayList<Personaje> competidores = new ArrayList<Personaje>();
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	/*
	 * post inicializa el juego
	 */

	private void cargarPersonajesDesdeArchivo() {
		try {
			FileReader archivo = new FileReader("./src/personajes.txt");
			BufferedReader lector = new BufferedReader(archivo);
			String linea;
			while ((linea = lector.readLine()) != null) {

				String[] data = linea.split(",");

				if (data[0].equals("Héroe")) {
					Heroe h = new Heroe(data[1], data[2], Integer.parseInt(data[3].trim()),
							Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()),
							Integer.parseInt(data[6].trim()));
					this.competidores.add(h);
				}
				if (data[0].equals("Villano")) {
					Villano v = new Villano(data[1], data[2], Integer.parseInt(data[3].trim()),
							Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()),
							Integer.parseInt(data[6].trim()));
					this.competidores.add(v);
				}

			}
			if (lector != null) {
				lector.close();
			}

			for (Personaje p : this.competidores) {
				System.out.println(p.getNombreReal());
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}
	private void guardarPersonajesEnArchivo() {
		try {
			FileWriter writer = new FileWriter("./src/personajes_out.txt", true);
			Iterator<Personaje> iterator = this.competidores.iterator();
			while( iterator.hasNext()) {
		//		System.out.println(iterator.next().toString());
				writer.write(iterator.next().toString() + "\n");
			}
			System.out.println("Guardado completo...");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public void menu(Integer opc) throws NumberFormatException, IOException {
		boolean salir = true;

		/*
		 * en este menu irían los metodos que estan listados en el tp:
		 * 
		 * Para los sub menu, creo que podríamos hacer un metodo que liste un menu que
		 * pasamos por parametro para no repetir tanto codigo.
		 * 
		 * El menú principal deberá permitir: > Administración de Personajes - Carga
		 * desde archivo - Creación - Listado - Guardar en archivo todos los personajes
		 * > Administración de Ligas - Carga desde archivo - Creación - Listado -
		 * Guardar en archivo todas las ligas > Realización de combates - Personaje
		 * contra Liga (definiendo característica)A - Liga contra Liga (definiendo
		 * característica) > Reportes - Todos los personajes o ligas que venzan a un
		 * personaje dado para cierta característica - Listado ordenado de personajes
		 * por múltiples características
		 */

		do {

			switch (opc) {
			case 1:
				switch (mostrarSubMenu("1- Cargar personajes desde archivo \n" + "2- Cargar personaje manualmente \n"
						+ "3- Listar personajes \n" + "4- Guardar personajes en archivo")) {

				case 1:
					cargarPersonajesDesdeArchivo();
					menu(opc);
					break;
				case 2:
					System.out.println("metodo para cargar personajes manualmente");
					break;
				case 3:
					System.out.println("metodo para listar personajes");
					break;
				case 4:
					guardarPersonajesEnArchivo();
					break;
				}
				break;
			case 2:
				switch (mostrarSubMenu("1- Cargar ligas desde archivo \n" + "2- Cargar Ligas manualmente \n"
						+ "3- Listar Ligas \n" + "4- Guardar Ligas en archivo")) {

				case 1:
					System.out.println("Metodo para cargar Ligas desde archivo");
					break;
				case 2:
					System.out.println("metodo para cargar Ligas manualmente");
					break;
				case 3:
					System.out.println("metodo para listar Ligas");
					break;
				case 4:
					System.out.println("metodo para guardar Ligas en un archivo");
					break;
				}
				break;
			case 3:
				switch (mostrarSubMenu("1- Combate Personaje contra liga \n" + "2- Combate Liga contra liga \n")) {

				case 1:
					System.out.println("metodo para combate  entre personaje y Ligas");
					break;
				case 2:
					System.out.println("metodo para combate entre Ligas ");
					break;
				}

				break;
			case 4:
				switch (mostrarSubMenu("1- Todos los personajes o ligas que venzan a un personaje dado para cierta caract\n" + " 2- Listado ordenado de personajes por múltiples características \n")) {
				case 1:
					System.out.println("metodo para reporte de personajes o ligas que venzan a otro");
					break;
				case 2: 
					System.out.println("metodo para reporte de personajes por multiples caracteristicas");
					break;
				}
				break;
			case 5:
				salir = !salir;
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
