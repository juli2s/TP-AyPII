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
import excepciones.CompetidorNoPerteneceAlJuego;
import excepciones.LigaYaExiste;

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
	
	public void resetearJugadores(){
		 heroes = new HashMap<String, Personaje>();
		 villanos = new HashMap<String, Personaje>();
		 ligaDeHeroes = new HashMap<String, Competidor>();
		 ligaDeVillanos = new HashMap<String, Competidor>();
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
					Personaje h = new Personaje(data[1].trim(), data[2].trim(), Integer.parseInt(data[3].trim()),
							Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()),
							Integer.parseInt(data[6].trim()));

					heroes.put(data[2].trim(), h); //creo que la key tiene que ser el nmbre de personaje porque es es el que se pasa en ligas.in
					this.competidores.add(h);

				}
				if (data[0].equals("Villano")) {
					Personaje v = new Personaje(data[1].trim(), data[2].trim(), Integer.parseInt(data[3].trim()),
							Integer.parseInt(data[4].trim()), Integer.parseInt(data[5].trim()),
							Integer.parseInt(data[6].trim()));
					
					//System.out.println(data[2]);
					villanos.put(data[2].trim(), v);
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
    
	public HashMap<String, Personaje> getHeroes() {
		return heroes;
	}

	public HashMap<String, Personaje> getVillanos() {
		return villanos;
	}

	/*
	 * pre: no se puede crear una liga ya existente ni con personajes que no existan
	 * en el archivo de personajes
	 */
	private void cargarLigas(String[] data)throws PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste
	{
		String nombreLiga = data[0].trim();
		HashMap<String, Competidor> ligaAAgregar = null;
		HashMap<String, Personaje> tipoAchequear = null;
		List<Competidor> listaCompetidores = new LinkedList();
		Liga liga;
		Competidor competidor = null;
		String nombreCompetidor = data[1].trim();
		
		if ( !(ligaDeHeroes.containsKey(nombreLiga)) && !(ligaDeVillanos.containsKey(nombreLiga)) ) {
			
			if(heroes.containsKey(nombreCompetidor) || ligaDeHeroes.containsKey(nombreCompetidor))
			{
				ligaAAgregar = ligaDeHeroes;
				tipoAchequear = heroes; 
			}
			if(villanos.containsKey(nombreCompetidor) || ligaDeVillanos.containsKey(nombreCompetidor))
			{   
				//System.out.println(nombreCompetidor + " ligavillano");
				ligaAAgregar = ligaDeVillanos;
				tipoAchequear = villanos;
			}
			
			if (ligaAAgregar == null) throw new CompetidorNoPerteneceAlJuego("El competidor no pertenece al Juego");
			/** Agrega todos los heroes que vengan en la linea de esta liga**/
			for (int i = 1; i < data.length; i++) {
				
				nombreCompetidor = data[i].trim();
			
				
				if(tipoAchequear.containsKey(nombreCompetidor)) {
					competidor = tipoAchequear.get(nombreCompetidor);
					for (String key : ligaAAgregar.keySet()) {
						if (ligaAAgregar.get(key).pertenece(tipoAchequear.get(nombreCompetidor))){
							Liga ligaM = (Liga) ligaAAgregar.get(key);
							
							throw new PerteneceALigaException("El competidor ya pertenece a una liga");
							
						}
					}
				}
					
				if(ligaAAgregar.containsKey(nombreCompetidor)) 
					competidor = ligaAAgregar.get(nombreCompetidor); //SI es una liga, tiene que estar ya cargada tambien
			
				if (competidor == null) throw new CompetidorNoPerteneceAlJuego("El competidor no pertenece al Juego"); 
				listaCompetidores.add(competidor);
			}
			
			liga = new Liga(nombreLiga, listaCompetidores);
			ligaAAgregar.put(nombreLiga, liga);
		}else
		{
			throw new LigaYaExiste("La liga ya existe"); 
		}

	}
	public void cargarLigaDesdeArchivo(String path) throws IOException {
		FileReader archivo = new FileReader(path);
		BufferedReader lector = new BufferedReader(archivo);
		
		try {
			String linea;
			
			while ((linea = lector.readLine()) != null) {
				String[] data = linea.split(",");
				cargarLigas(data);
			}
			    // mostrar las ligas creadas:
			    mostrarLigas();
			    
		} catch (Exception e) {
			   //e.printStackTrace();
			   System.err.println(e);
		}

		finally{
			if (lector != null)
				lector.close();
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
