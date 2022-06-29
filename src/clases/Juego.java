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

import excepciones.ContrincantesIncompatibles;
import excepciones.FormatoArchivoIncorrecto;
import excepciones.FormatoCamposIncorrecto;
import excepciones.LigaVacia;
import excepciones.PerteneceALigaException;
import excepciones.CompetidorNoPerteneceAlJuego;
import excepciones.LigaYaExiste;

public class Juego {


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

	public HashMap<String, Personaje> getHeroes() {
		return heroes;
	}

	public HashMap<String, Personaje> getVillanos() {
		return villanos;
	}

	
	public void cargarPersonajesDesdeArchivo(String path) throws FormatoArchivoIncorrecto {
		try {
			FileReader archivo = new FileReader(path);
			BufferedReader lector = new BufferedReader(archivo);
			String linea = null;
			linea = lector.readLine();
			
			if (linea == null )
			{
				throw new FormatoArchivoIncorrecto("El formato de archivo no es correcto");
			}
			
				
			while (linea != null) {
				String[] data = linea.split(",");
				try {
					String tipoHeroe = data[0].trim();
				
					if (!tipoHeroe.equals("Héroe") && !tipoHeroe.equals("Villano") )
					{
						throw new FormatoCamposIncorrecto("El formato del campo no es correcto");
					}
					String nombreReal = data[1].trim();				
					String nombrePersonaje = data[2].trim();
				
					if (nombreReal.equals("") || nombrePersonaje.equals("")|| nombreReal == null || nombrePersonaje == null )
					{
						throw new FormatoCamposIncorrecto("El formato del campo no es correcto");
					}
				
					Integer velocidad = Integer.parseInt(data[3].trim());
					Integer fuerza = Integer.parseInt(data[4].trim());
					Integer resistencia = Integer.parseInt(data[5].trim());
					Integer destreza = Integer.parseInt(data[6].trim());

					if (tipoHeroe.equals("Héroe")) {
						Personaje h = new Personaje(nombreReal, nombrePersonaje,velocidad,
								fuerza, resistencia,
								destreza);

						heroes.put(nombrePersonaje.trim(), h); //creo que la key tiene que ser el nmbre de personaje porque es es el que se pasa en ligas.in
						

					}
					if (tipoHeroe.equals("Villano")) {
						Personaje v = new Personaje(nombreReal, nombrePersonaje,velocidad,
								fuerza, resistencia,
								destreza);
					
					
						villanos.put(nombrePersonaje, v);
						
					}

				}catch (Exception e){
					System.err.println(e);
				}
				
				
				linea = lector.readLine();

			}
			if (lector != null) {
				System.out.println("Personajes cargados...");
				lector.close();
			}

		} catch (IOException e) {
			
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

	public void guardarPersonajesEnArchivo(boolean mostrar) {
		try {
			FileWriter writer = new FileWriter("./src/personajes_out.txt", true);
			Iterator<Map.Entry<String, Personaje>> iteratorHeroes = this.heroes.entrySet().iterator();
			Iterator<Map.Entry<String, Personaje>> iteratorVillanos = this.villanos.entrySet().iterator();
			while (iteratorHeroes.hasNext()) {
				Map.Entry<String, Personaje> entrada = (Entry<String, Personaje>) iteratorHeroes.next();
				Personaje p = entrada.getValue();
				if(!mostrar) {					
					writer.write("Héroe, " + p.mostrarPersonaje() + "\n");
				}else {
					System.out.println(p.mostrarPersonaje());
				}
			}
			while (iteratorVillanos.hasNext()) {
				Map.Entry<String, Personaje> entrada = (Entry<String, Personaje>) iteratorVillanos.next();
				Personaje p = entrada.getValue();
				if(!mostrar) {					
					writer.write("Villano, " + p.mostrarPersonaje() + "\n");
				}else {
					System.out.println(p.mostrarPersonaje());
				}
			}
			System.out.println("Guardado completo...");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void listarPersonajes() {
		List<Competidor> competidores = obtenerListaDeCompetidores();
		Iterator <Competidor> it = competidores.iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
		
	}

	public String listarPersonajesQueVenzanAUno() throws CompetidorNoPerteneceAlJuego, LigaYaExiste{

		String lista = "";

		try {
			String nombre = mostrarMensaje("Nombre retador");
			verificarExistencia(nombre);
			Competidor retador = obtenerCompetidor(nombre);
			String attr2 = mostrarMensaje("Elegir atributo");
			
			Atributo atributo = Atributo.valueOf(attr2.toUpperCase());
			List<Competidor> competidores = obtenerListaDeCompetidores();
			Iterator <Competidor> it = competidores.iterator();
			
			while(it.hasNext()) {
				Competidor contendiente = it.next();
				if(contendiente.getCaracteristicas().get(atributo) > retador.getCaracteristicas().get(atributo))
					lista += contendiente.getNombre() + "\n";
			}
			System.out.println("***** VENCEN SOBRE "+ nombre +" ****");
			
		} catch (NumberFormatException | IOException e) {
			System.err.println("Opcion ingresada invalida");
		}
		
		return lista;
	}
	
	
	public String listarPersonajesPorMultiplesCaracteristicas(String attr1, String attr2, int orden) throws IOException{
		
		Atributo atributo1;
		Atributo atributo2;
		List<Competidor> competidores = obtenerListaDePersonajes();
		String lista = "";
		
		
		atributo1 = Atributo.valueOf(attr1.toUpperCase());
		atributo2 = Atributo.valueOf(attr2.toUpperCase());

		System.out.println("CARACTERISTICAS: " + atributo1 + " / " + atributo2);
		
		Comparator<Competidor> comp = new ComparadorPorAtributo(atributo1, atributo2, orden);

		competidores.sort(comp);
		
		for (Competidor p : competidores) {
			lista += (p.toString()) +  " " + p.getCaracteristicas().get(atributo1) + " " + p.getCaracteristicas().get(atributo2) + "\n";
		}

		return lista;

	}
    
	
	/*
	 * pre: no se puede crear una liga ya existente ni con personajes que no existan
	 * en el archivo de personajes
	 */

	private void cargarLigas(String[] data)throws PerteneceALigaException, LigaYaExiste, CompetidorNoPerteneceAlJuego{
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
				competidor = null;
				try{
					if(tipoAchequear.containsKey(nombreCompetidor)) {
						competidor = tipoAchequear.get(nombreCompetidor);
						for (String key : ligaAAgregar.keySet()) {
							if (ligaAAgregar.get(key).pertenece(tipoAchequear.get(nombreCompetidor))){
								Liga ligaM = (Liga) ligaAAgregar.get(key);
							
								throw new PerteneceALigaException("El competidor " + nombreCompetidor + " ya pertenece a una liga");
							
							}
						}
					}
					
					if(ligaAAgregar.containsKey(nombreCompetidor)) 
						competidor = ligaAAgregar.get(nombreCompetidor); //SI es una liga, tiene que estar ya cargada tambien
				
					if (competidor == null) throw new CompetidorNoPerteneceAlJuego("El competidor " + nombreCompetidor + " no pertenece al Juego"); 
					listaCompetidores.add(competidor);
					
				} catch (CompetidorNoPerteneceAlJuego e){
						System.err.println(e.getMessage());
				}
				catch (PerteneceALigaException e){
					System.err.println(e.getMessage());
			    }
				
				
			}
			
			liga = new Liga(nombreLiga, listaCompetidores);
			ligaAAgregar.put(nombreLiga, liga);
			
			
		}else
		{
			throw new LigaYaExiste("La liga ya existe"); 
		}		
	}
		
	
	public void cargarLigaDesdeArchivo(String path) throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		FileReader archivo = new FileReader(path);
		BufferedReader lector = new BufferedReader(archivo);
		
		try {
			String linea = null;
            linea = lector.readLine();
			
			if (linea == null )
			{
				throw new FormatoArchivoIncorrecto("El formato de archivo no es correcto");
			}
			
			
			while (linea != null) {
				String[] data = linea.split(",");
				cargarLigas(data);
				linea = lector.readLine();
			}
			    mostrarLigas();
			    
		} catch (IOException e) {
			   //e.printStackTrace();

			   System.err.println(e);
		}

		finally{
			if (lector != null)
				lector.close();
		}
		
	}

	public void mostrarLigas() {
		
		System.out.println("Ligas de heroes en juego" + ligaDeHeroes.keySet().toString() + "\n");
		System.out.println("Ligas de villanos en juego" + ligaDeVillanos.keySet().toString() + "\n");
		
		
	}
	
	public void guardarLigasEnArchivo(boolean mostrar) {
		try {
			
			FileWriter writer = new FileWriter("./src/ligas_out.txt", true);
			Iterator<Entry<String, Competidor>> iteratorHeroes = this.ligaDeHeroes.entrySet().iterator();
			Iterator<Map.Entry<String, Competidor>> iteratorVillanos = this.ligaDeVillanos.entrySet().iterator();
			while (iteratorHeroes.hasNext()) {
				Map.Entry<String, Competidor> entrada = (Entry<String, Competidor>) iteratorHeroes.next();
				Competidor c = entrada.getValue();
				if(!mostrar) {					
					writer.write(c.toString() + "\n");
				}else {
					System.out.println(c.toString());
				}
			}
			while (iteratorVillanos.hasNext()) {
				Map.Entry<String, Competidor> entrada = (Entry<String, Competidor>) iteratorVillanos.next();
				Competidor c = entrada.getValue();
				if(!mostrar) {					
					writer.write(c.toString() + "\n");
				}else {
					System.out.println(c.toString());
				}
			}
			System.out.println("Guardado completo...");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public String pelear() throws CompetidorNoPerteneceAlJuego, LigaYaExiste{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ganador = "El ganador es: ";
		String tipoRetador = null;
		String tipoContrincante = null;
		
		try {	
			System.out.println("Nombre de retador\n");
			String nombre = br.readLine();
			Competidor retador = obtenerCompetidor(nombre);
			
			
			if(heroes.containsKey(nombre) || ligaDeHeroes.containsKey(nombre))
			{
				tipoRetador = "heroes"; 
			}
			
			if(villanos.containsKey(nombre) || ligaDeVillanos.containsKey(nombre))
			{   
				tipoRetador = "villanos";
			}
			
			System.out.println("Nombre de contrincante\n");
			nombre = br.readLine();
			Competidor contrincante = obtenerCompetidor(nombre);
			
			if(heroes.containsKey(nombre) || ligaDeHeroes.containsKey(nombre))
			{
				tipoContrincante = "heroes";
			}
			
			if(villanos.containsKey(nombre) || ligaDeVillanos.containsKey(nombre))
			{   
				tipoContrincante = "villanos";
			}
			
			if (tipoRetador.equals(tipoContrincante)) throw new ContrincantesIncompatibles("No pueden pelear competidores del mismo bando");
			
			System.out.println("Indique atributo\n");
			String attr = br.readLine().toUpperCase();
			Atributo atributo = Atributo.valueOf(attr);
			
			if(retador.esGanador(atributo, contrincante))
				ganador += retador.getNombre();
			else if(retador.esGanador(atributo, contrincante))
				ganador += contrincante.getNombre();
			else
				ganador = "Empate";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return ganador;
	}

	
	
	public void iniciarMenu() throws NumberFormatException, IOException, CompetidorNoPerteneceAlJuego {
		menu.generarMenu(this);
	}

	
	//================== Metodos Privados ====================================
	
	
	
	private String mostrarMensaje(String opciones) throws NumberFormatException, IOException {
		System.out.println(opciones + "\n");
		return input.readLine();
	}
	
	private Competidor obtenerCompetidor(String nombre)throws CompetidorNoPerteneceAlJuego {
		verificarExistencia(nombre);
		if(heroes.containsKey(nombre))
			return heroes.get(nombre);
	
		return villanos.get(nombre);
	}
	
	private void verificarExistencia(String nombreCompetidor)throws CompetidorNoPerteneceAlJuego{
		if(!heroes.containsKey(nombreCompetidor) && !ligaDeHeroes.containsKey(nombreCompetidor) 
				&& !villanos.containsKey(nombreCompetidor) && !ligaDeVillanos.containsKey(nombreCompetidor))
			throw new CompetidorNoPerteneceAlJuego("El competidor no pertenece al Juego");
	}
	
	private void verificarExistenciaLiga(String nombreCompetidor)throws LigaYaExiste{
		if(ligaDeHeroes.containsKey(nombreCompetidor) || ligaDeVillanos.containsKey(nombreCompetidor))
			throw new LigaYaExiste("La liga ya existe");
	}
	
	private List<Competidor> obtenerListaDeCompetidores(){
		List<Competidor> competidores = new LinkedList();
		
		for(Map.Entry<String,Personaje> entry : villanos.entrySet()) {
			competidores.add(entry.getValue());
		}
		
		for(Map.Entry<String,Personaje> entry : heroes.entrySet()) {
			competidores.add(entry.getValue());
		}
		
		for(Map.Entry<String,Competidor> entry : ligaDeVillanos.entrySet()) {
			competidores.add(entry.getValue());
		}
		
		for(Map.Entry<String,Competidor> entry : ligaDeHeroes.entrySet()) {
			competidores.add(entry.getValue());
		}
		
		return competidores;
	}
	
	private List<Competidor> obtenerListaDePersonajes(){
		List<Competidor> personajes = new LinkedList();
		
		for(Map.Entry<String,Personaje> entry : villanos.entrySet()) {
			personajes.add(entry.getValue());
		}
		
		for(Map.Entry<String,Personaje> entry : heroes.entrySet()) {
			personajes.add(entry.getValue());
		}
		return personajes;
	}
}
