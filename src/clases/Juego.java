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
					String tipoPersonaje = data[0].trim();
				
					if (!tipoPersonaje.equals("Héroe") && !tipoPersonaje.equals("Villano") )
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

					if (tipoPersonaje.equals("Héroe")) {
						Personaje h = new Personaje(nombreReal, nombrePersonaje,velocidad,
								fuerza, resistencia,
								destreza, "Heroe");

						heroes.put(nombrePersonaje.trim(), h);
						

					}
					if (tipoPersonaje.equals("Villano")) {
						Personaje v = new Personaje(nombreReal, nombrePersonaje,velocidad,
								fuerza, resistencia,
								destreza, "Villano");
					
					
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
		try {

			String bandoPersonaje = mostrarMensaje("Bando del personaje");
			String nombreReal = mostrarMensaje("nombre real");
			String nombrePersonaje = mostrarMensaje("Nombre de personaje");
			int velocidad = Integer.parseInt(mostrarMensaje("Velocidad"));
			int fuerza = Integer.parseInt(mostrarMensaje("Fuerza"));
			int resistencia = Integer.parseInt(mostrarMensaje("Resistencia"));
			int destreza = Integer.parseInt(mostrarMensaje("Destreza"));
			
			Personaje p = new Personaje(nombreReal, nombrePersonaje, velocidad, fuerza, resistencia, destreza, bandoPersonaje);
			
			if (bandoPersonaje.equals("Heroe")) {
				this.heroes.put(nombrePersonaje, p);

			} else if (bandoPersonaje.equals("Villano")) {
				this.villanos.put(nombrePersonaje, p);
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
			
		} catch (NumberFormatException | IOException a) {
			System.err.println("Opcion ingresada invalida");
		} catch( CompetidorNoPerteneceAlJuego b) {
			System.err.println("El competidor ya existe");
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
		List<Competidor> listaCompetidores = new LinkedList();
		Liga liga;
		Competidor competidor = null;
		String nombreCompetidor = data[1].trim();
		
		if ( (ligaDeHeroes.containsKey(nombreLiga)) || (ligaDeVillanos.containsKey(nombreLiga)) ) 
			throw new LigaYaExiste("La liga ya existe"); 

		competidor = obtenerCompetidor(nombreCompetidor);
		
		if(competidor.getBando().equals("Heroe")){
			ligaAAgregar = ligaDeHeroes;
		}
		else if(competidor.getBando().equals("Villano")){   
			ligaAAgregar = ligaDeVillanos;
		}
			
			
		for (int i = 1; i < data.length; i++) {
				
			nombreCompetidor = data[i].trim();
			
			try{
				competidor = obtenerCompetidor(nombreCompetidor);
				verificarPertenencia(competidor, ligaAAgregar);				
				listaCompetidores.add(competidor);
				
			}catch (CompetidorNoPerteneceAlJuego e){
					System.err.println(e.getMessage());
			}
			catch (PerteneceALigaException e){
				System.err.println(e.getMessage());
			}
		}
		liga = new Liga(nombreLiga, listaCompetidores);
		ligaAAgregar.put(nombreLiga, liga);
			
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

	public void cargarLigasManualmente() throws CompetidorNoPerteneceAlJuego, LigaYaExiste, PerteneceALigaException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			System.out.println("Cantidad de integrantes\n");
			int cantidad = Integer.parseInt(br.readLine());
			String[] data = new String[cantidad + 1];
				
			data[0] = mostrarMensaje("Nombre de liga");

			for(int i = 1; i < data.length; i++) {
				String nombre = mostrarMensaje("Nombre de integrante");
				data[i] = nombre;			
				
			}
			
			cargarLigas(data);
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
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
			if (!mostrar) System.out.println("Guardado Completo..");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public String pelear() throws CompetidorNoPerteneceAlJuego, LigaYaExiste, ContrincantesIncompatibles{
		String ganador = "El ganador es: ";
		
		try {	
			String nombre = mostrarMensaje("Nombre de retador");
			Competidor retador = obtenerCompetidor(nombre);

			nombre = mostrarMensaje("Nombre de contrincante");
			Competidor contrincante = obtenerCompetidor(nombre);
			
			if (retador.getBando().equals(contrincante.getBando())) throw new ContrincantesIncompatibles();
			
			String attr = mostrarMensaje("Indique atributo").toUpperCase();
			Atributo atributo = Atributo.valueOf(attr);
			
			if(retador.esGanador(atributo, contrincante))
				ganador += retador.getNombre();
			else if(contrincante.esGanador(atributo, retador))
				ganador += contrincante.getNombre();
			else
				ganador = "Empate";
			
		} catch (IOException e) {
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

		if(heroes.containsKey(nombre))
			return heroes.get(nombre);
		else if(ligaDeHeroes.containsKey(nombre))
			return ligaDeHeroes.get(nombre);
		else if(villanos.containsKey(nombre))
			return villanos.get(nombre);
		else if(ligaDeVillanos.containsKey(nombre))
			return ligaDeVillanos.get(nombre);
		else
			throw new CompetidorNoPerteneceAlJuego();
	
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
	
	private void verificarPertenencia(Competidor competidor, HashMap<String, Competidor> liga) throws PerteneceALigaException{
		for (String key : liga.keySet()) {
			if (liga.get(key).pertenece(competidor))
				throw new PerteneceALigaException("El competidor " + competidor.getNombre() + " ya pertenece a una liga");
		}
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
