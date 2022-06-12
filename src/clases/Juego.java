package clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.LinkedList;
import java.util.Scanner;


public class Juego {
	List<Personaje> competidores = new LinkedList<Personaje>();
	BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	Menu menu = new Menu();
	
	private static Juego instancia;
	private HashMap<String,Personaje> heroes = new HashMap<String,Personaje>();
	private HashMap<String,Personaje> villanos = new HashMap<String,Personaje>();
	
	
	
	public static Juego getInstance(){
		if (instancia == null)
		{
			instancia = new Juego();
		}
		return instancia;
	}
	
	/*
	 * post inicializa el juego
	 */
	
	private Juego () {
		try {
			menu.generarMenu(this);
		} catch (NumberFormatException | IOException e) {
			
			e.printStackTrace();
		}
	}
    
	 void cargarPersonajesDesdeArchivo() {
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
	
     void cargarPersonajesManualmente() {
		
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
				this.heroes.put(nombrePersonaje,h);

			} else if (heroeOVillano == 2) {
				Personaje v = new Personaje(nombreReal, nombrePersonaje, velocidad, fuerza, resistencia,
						destreza);
				this.villanos.put(nombrePersonaje,v);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	 void guardarPersonajesEnArchivo() {
		try {
			FileWriter writer = new FileWriter("./src/personajes_out.txt", true);
			Iterator<Map.Entry<String, Personaje>> iteratorHeroes = this.heroes.entrySet().iterator();
			Iterator<Map.Entry<String, Personaje>> iteratorVillanos = this.villanos.entrySet().iterator();
			while (iteratorHeroes.hasNext()) {
				Map.Entry<String,Personaje> entrada = (Entry<String, Personaje>) iteratorHeroes.next();
				Personaje p = entrada.getValue();
				// System.out.println(iterator.next().toString());
				writer.write(p.toString() + "\n");
			}
			while(iteratorVillanos.hasNext()) {
				Map.Entry<String,Personaje> entrada = (Entry<String, Personaje>) iteratorVillanos.next();
				Personaje p = entrada.getValue();
				writer.write(p.toString() + "\n");
			}
			System.out.println("Guardado completo...");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	

	
	
}
