package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.PriorityQueue;


public class Juego {
	List<Personaje> competidores = new LinkedList<Personaje>();
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	private HashMap<TipoPersonaje,HashMap<String,Personaje>> contrincantes = new HashMap<TipoPersonaje,HashMap<String,Personaje>>();
	private HashMap<String,Personaje> heroes = new HashMap<String,Personaje>();
	private HashMap<String,Personaje> villanos = new HashMap<String,Personaje>();
	
	
	/*
	   Ojo que no estoy usando el patron de diseño pero necesitaba inicializar el map
	 */
	public Juego()
	{
		contrincantes.put(TipoPersonaje.HEROE, this.heroes);
		contrincantes.put(TipoPersonaje.VILLANO, this.villanos);
	}

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
					Personaje h = new Personaje(data[1], data[2], Integer.parseInt(data[3].trim()),
							Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()),
							Integer.parseInt(data[6].trim()));
					
					heroes.put(data[1],h);
					this.competidores.add(h);
					
					
				}
				if (data[0].equals("Villano")) {
					Personaje v = new Personaje(data[1], data[2], Integer.parseInt(data[3].trim()),
							Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()),
							Integer.parseInt(data[6].trim()));
					villanos.put(data[1],v);
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
			while (iterator.hasNext()) {
				// System.out.println(iterator.next().toString());
				writer.write(iterator.next().toString() + "\n");
			}
			System.out.println("Guardado completo...");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public boolean esGanador(Atributo atributo, Personaje contrincante1, Personaje contrincante2) {
		//habria que chequear que los personajes pertenezcan al juego?
		boolean esGanador = false;
		List<Personaje> competidores = new ArrayList<Personaje>();
		competidores.add(contrincante1);
		competidores.add(contrincante2);
		
		
		Collections.sort(competidores, new ComparadorPorAtributo(atributo));
		
		
		if (competidores.get(1).getNombrePersonaje().equals(contrincante1.getNombrePersonaje()))
		{
			esGanador = true;
		}
		
		return esGanador;
	}

	public void menu() throws NumberFormatException, IOException {
		boolean salir = true;
		int opc = 0;

		do {

			switch (mostrarSubMenu(("\n"+"Menu principal: \n" + "1 - Administración de personajes  \n"
					+ "2 - Administracion de ligas \n" + "3 - Combates \n" + "4 - Reportes \n" + "5- Salir \n"))) {
			case 1:
				while (opc != 5) {

					switch (mostrarSubMenu("\n"+"1- Cargar personajes desde archivo \n" + "2- Cargar personaje manualmente \n"
							+ "3- Listar personajes \n" + "4- Guardar personajes en archivo\n" + "5- atrás\n")) {

					case 1:
						cargarPersonajesDesdeArchivo();
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
					case 5:
						opc = 5;
						menu();
					}
				}
				opc = 0;
				break;
			case 2:

				while (opc != 5) {
					switch (mostrarSubMenu("\n"+"1- Cargar ligas desde archivo \n" + "2- Cargar Ligas manualmente \n"
							+ "3- Listar Ligas \n" + "4- Guardar Ligas en archivo \n" + "5- atrás\n")) {

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
					case 5:
						opc = 5;
						menu();
					}
				}
				opc = 0;
				break;
			case 3:
				while (opc != 3) {
					switch (mostrarSubMenu("\n"+"1- Combate Personaje contra liga \n" + "2- Combate Liga contra liga \n" + "3- atrás\n")) {

					case 1:
						System.out.println("metodo para combate  entre personaje y Ligas");
						if(competidores.get(0).compareTo(competidores.get(1)) == 1)
							System.out.println("Gana heroe");
						else if(competidores.get(0).compareTo(competidores.get(1)) == -1)
							System.out.println("Gana villano");
						break;
					case 2:
						System.out.println("metodo para combate entre Ligas");
						break;
					case 3:
						opc = 3;
						menu();
					}
				}
				opc = 0;
				break;
			case 4:
				while (opc != 3) {
					switch (mostrarSubMenu("\n"+"1- Todos los personajes o ligas que venzan a un personaje dado para cierta caract\n"
						+ " 2- Listado ordenado de personajes por múltiples características \n"+"3- atrás\n")) {
					case 1:
						System.out.println("metodo para reporte de personajes o ligas que venzan a otro");
						break;
					case 2:
						System.out.println("metodo para reporte de personajes por multiples caracteristicas");
						break;
					case 3:
						opc = 3;
						menu();
					}
				}
				opc = 0;
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
