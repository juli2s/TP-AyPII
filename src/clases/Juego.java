package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedList;
import java.util.Scanner;

import excepciones.PerteneceALigaException;

public class Juego {

	List<Personaje> competidores = new LinkedList<Personaje>();
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	Menu menu = new Menu();

	private static Juego instancia;
	private HashMap<String, Personaje> heroes = new HashMap<String, Personaje>();
	private HashMap<String, Personaje> villanos = new HashMap<String, Personaje>();
	private HashMap<String, Competidor> ligaDeHeroes = new HashMap<String, Competidor>();
	private HashMap<String, Competidor> ligaDeVillanos = new HashMap<String, Competidor>();

	public static Juego getInstance() {
		if (instancia == null) {
			instancia = new Juego();
		}
		return instancia;
	}

	/*
	 * post inicializa el juego
	 */

	public HashMap<String, Competidor> getLigaDeHeroes() {
		return ligaDeHeroes;
	}

	public HashMap<String, Competidor> getLigaDeVillanos() {
		return ligaDeVillanos;
	}

	public void cargarPersonajesDesdeArchivo(String path) {
		try {
			FileReader archivo = new FileReader(path);
			BufferedReader lector = new BufferedReader(archivo);
			String linea;
			while ((linea = lector.readLine()) != null) {

				String[] data = linea.split(",");

				if (data[0].equals("Héroe")) {
					Personaje h = new Personaje(data[1], data[2], Integer.parseInt(data[3].trim()),
							Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()),
							Integer.parseInt(data[6].trim()));

					heroes.put(data[1], h);
					this.competidores.add(h);

				}
				if (data[0].equals("Villano")) {
					Personaje v = new Personaje(data[1], data[2], Integer.parseInt(data[3].trim()),
							Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()),
							Integer.parseInt(data[6].trim()));
					villanos.put(data[1], v);
					this.competidores.add(v);
				}

			}
			if (lector != null) {
				lector.close();
			}

		} catch (Exception e) {
			System.err.println(e);
		}

	}

	public void cargarPersonajesManualmente() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner teclado = new Scanner(System.in);

		try {

			System.out.println("\n 1 - Heroe 2 - Villano \n");
			int heroeOVillano = teclado.nextInt();

			System.out.println("Nombre real \n");
			String nombreReal = br.readLine();

			System.out.println("Nombre de personaje \n");
			String nombrePersonaje = br.readLine();

			System.out.println("Velocidad \n");
			int velocidad = teclado.nextInt();

			System.out.println("Fuerza \n");
			int fuerza = teclado.nextInt();

			System.out.println("Resistencia \n");
			int resistencia = teclado.nextInt();

			System.out.println("Destreza \n");
			int destreza = teclado.nextInt();

			if (heroeOVillano == 1) {
				Personaje h = new Personaje(nombreReal, nombrePersonaje, velocidad, fuerza, resistencia, destreza);
				this.heroes.put(nombrePersonaje, h);

			} else if (heroeOVillano == 2) {
				Personaje v = new Personaje(nombreReal, nombrePersonaje, velocidad, fuerza, resistencia, destreza);
				this.villanos.put(nombrePersonaje, v);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void guardarPersonajesEnArchivo() {
		try {
			FileWriter writer = new FileWriter("./src/personajes_out.txt", true);
			Iterator<Map.Entry<String, Personaje>> iteratorHeroes = this.heroes.entrySet().iterator();
			Iterator<Map.Entry<String, Personaje>> iteratorVillanos = this.villanos.entrySet().iterator();
			while (iteratorHeroes.hasNext()) {
				Map.Entry<String, Personaje> entrada = (Entry<String, Personaje>) iteratorHeroes.next();
				Personaje p = entrada.getValue();
				// System.out.println(iterator.next().toString());
				writer.write(p.toString() + "\n");
			}
			while (iteratorVillanos.hasNext()) {
				Map.Entry<String, Personaje> entrada = (Entry<String, Personaje>) iteratorVillanos.next();
				Personaje p = entrada.getValue();
				writer.write(p.toString() + "\n");
			}
			System.out.println("Guardado completo...");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void listarPersonajesPorMultiplesCaracteristicas() {
		Atributo atributo1;
		Atributo atributo2;

		System.out.println("CARACTERISTICAS: listado de caracteristicas");

		try {
			String attr1 = mostrarMensaje("Primer atributo");
			String attr2 = mostrarMensaje("Segundo atributo");

			atributo1 = Atributo.valueOf(attr1.toUpperCase());
			atributo2 = Atributo.valueOf(attr2.toUpperCase());
			if (atributo1 == atributo2) {
				atributo2 = Atributo.valueOf(mostrarMensaje("Debe elegir una caracteristica distinta"));
			}

			Comparator<Personaje> comp = new ComparadorPorAtributo(atributo1, atributo2);

			this.competidores.sort(comp);
		} catch (NumberFormatException | IOException e) {
			System.err.println("Opcion ingresada invalida");
		}

		for (Personaje p : this.competidores) {
			System.out.println(p.toString());
		}
	}

	/*
	 * pre: no se puede crear una liga ya existente ni con personajes que no existan
	 * en el archivo de personajes
	 */
	public void cargarLigaDesdeArchivo(String path) {
		try {
			FileReader archivo = new FileReader(path);
			BufferedReader lector = new BufferedReader(archivo);
			String linea;
			while ((linea = lector.readLine()) != null) {

				String[] data = linea.split(",");

				// reviso si la liga existe, sinó, la creo. Para crearla me fijo si el primero
				// es un heroe, villano u otra liga.
				// si existe le agrego los personajes de esa row

				// chequeo liga de heroes. si existe, le agrego los personajes que vienen en
				// esta linea
				if (ligaDeHeroes.containsKey(data[0])) {
					Liga liga = (Liga) ligaDeHeroes.get(data[0]);
					// aca hay que agregar todos los heroes que vengan en la linea de esta liga
					for (int i = 1; i < data.length; i++) {
						if (!liga.pertenece(heroes.get(data[i]))) {
							liga.agregarCompetidor(heroes.get(data[1]));
						} else {
							throw new PerteneceALigaException("el competidor ya pertenece a la liga");
						}
					}

				}
				// chequeo liga de villanos. si existe, le agrego los personajes que vienen en
				// esta linea
				else if (ligaDeVillanos.containsKey(data[0])) {
					Liga liga = (Liga) ligaDeVillanos.get(data[0]);

					// aca hay que agregar todos los heroes que vengan en la linea de esta liga
					for (int i = 1; i < data.length; i++) {
						if (!liga.pertenece(villanos.get(data[i]))) {
							liga.agregarCompetidor(villanos.get(data[i]));
						} else {
							throw new PerteneceALigaException("el competidor ya pertenece a la liga");
						}
					}

				}
				// si no existe la liga, la creo y agrego todos los personajes que vengan en la
				// row, siempre y cuando no pertenezcan a otra liga existente
				else {

					List<Competidor> listaCompetidores = new LinkedList();
					Liga ligaAux = null;

					// para saber si es de heroes o villaos, veo el primer competidor

					if (heroes.containsKey(data[1])) {
						for (int i = 1; i < data.length; i++) {
							for (String key : ligaDeHeroes.keySet()) {
								if (ligaDeHeroes.get(key).pertenece(heroes.get(data[i]))) {
									listaCompetidores.add(heroes.get(data[i]));
								} else {
									throw new PerteneceALigaException("el competidor ya pertenece a la liga");
								}
							}
						}
						ligaAux = new Liga(data[0], listaCompetidores);
						ligaDeHeroes.put(data[0], ligaAux);

					} else {
						// recorro todos los personajes de la linea, si alguno existe en alguna de las
						// ligas existentes, levanto exception
						for (int i = 1; i < data.length; i++) {
							for (String key : ligaDeVillanos.keySet()) {
								if (ligaDeVillanos.get(key).pertenece(villanos.get(data[i]))) {
									listaCompetidores.add(villanos.get(data[i]));
								} else {
									throw new PerteneceALigaException("el competidor ya pertenece a la liga");
								}
							}

						}
						ligaAux = new Liga(data[0], listaCompetidores);
						ligaDeVillanos.put(data[0], ligaAux);
					}
				}
			}

			if (lector != null) {
				lector.close();
			}
			// mostrar las ligas creadas:
			mostrarLigas();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e);
		}
	}

	public void mostrarLigas() {

		System.out.println("Ligas de heroes en juego" + ligaDeHeroes.keySet().toString());
		System.out.println("Ligas de villanos en juego" + ligaDeVillanos.keySet().toString());
	}

	public void iniciarMenu() throws NumberFormatException, IOException {
		menu.generarMenu(this);
	}

	private String mostrarMensaje(String opciones) throws NumberFormatException, IOException {
		System.out.println(opciones + "\n");
		return input.readLine();
	}

}
