package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import clases.Competidor;
import clases.Juego;
import clases.Liga;
import clases.Personaje;
import excepciones.CompetidorNoPerteneceAlJuego;
import excepciones.LigaYaExiste;
import excepciones.PerteneceALigaException;
import excepciones.FormatoArchivoIncorrecto;

public class CargarLigasDesdeArchivoTest {
	
	@Test (expected = FormatoArchivoIncorrecto.class)
	public void ArchivoPersonajeVacio() throws IOException, FormatoArchivoIncorrecto {
		   Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes0.txt");
			
		   
	}
	
	@Test (expected = FormatoArchivoIncorrecto.class)
	public void ArchivoLigaVacio() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		   Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes0.txt");
		   
		   j.cargarLigaDesdeArchivo("./src/tests/ligas0.txt");
			
		   
	}
	
	@Test 
	public void rutaIncorrecta() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		   Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes.txt");
		   
		   
	}
	
	@Test 
	public void personajeConAtributosMal() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		   Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes8.txt");
		   
		   j.cargarLigaDesdeArchivo("./src/tests/ligas8.txt");
		   
		   
	}

	@Test
	public void CargarUnaLiga() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		   Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes1.txt");
			
		   j.cargarLigaDesdeArchivo("./src/tests/ligas1.txt");
		  
		   Competidor jugador1 = new Personaje("Edward Blake", "The Comedian", 50 ,10 ,40, 70);
		   
		   Assert.assertTrue(j.getLigaDeHeroes().get("Watchmen").pertenece(jugador1));
		   
		   j.resetearJugadores();
		   
	}
	
	@Test 
	public void CargarUnPersonajeIncorrecto() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		   Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes6.txt");
			
		   j.cargarLigaDesdeArchivo("./src/tests/ligas6.txt");
		  
		   Competidor jugador1 = new Personaje("Edward Blake", "The Comedian", 50 ,10 ,40, 70);
		   
		   Assert.assertTrue(j.getLigaDeHeroes().get("Watchmen").pertenece(jugador1));
		   
		   j.resetearJugadores();
		   
	}
	
	@Test 
	public void CargarUnPersonajeDeOtraLiga() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		   Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes6.txt");
			
		   j.cargarLigaDesdeArchivo("./src/tests/ligas6.txt");
		   j.cargarLigaDesdeArchivo("./src/tests/ligas7.txt");
		  
		   Competidor jugador1 = new Personaje("Edward Blake", "The Comedian", 50 ,10 ,40, 70);
		   
		   Assert.assertTrue(j.getLigaDeHeroes().get("Watchmen").pertenece(jugador1));
		   
		   j.resetearJugadores();
		   
	}
	@Test (expected = LigaYaExiste.class) 
	public void CargarUnaLigaExistente() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		   Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes6.txt");
		   
		   //System.out.println("pasa");
			
		   j.cargarLigaDesdeArchivo("./src/tests/ligas6.txt");
		   j.cargarLigaDesdeArchivo("./src/tests/ligas6.txt");
		  
		   Competidor jugador1 = new Personaje("Edward Blake", "The Comedian", 50 ,10 ,40, 70);
		   
		   Assert.assertTrue(j.getLigaDeHeroes().get("Watchmen").pertenece(jugador1));
		   
		   j.resetearJugadores();
		   
	}
	
	
	@Test
	public void CargarUnaLigaDeHeroesYDeVillanos() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		
		 Juego j = Juego.getInstance();
		 j.resetearJugadores();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes2.txt");
		   j.cargarLigaDesdeArchivo("./src/tests/ligas2.txt");
		   
           Personaje jugador1 = new Personaje("Adrian Veidt2", "Ozymandias", 50 ,10 ,40, 70);
           Personaje jugador2 = new Personaje("Edward Blake", "The Comedian", 50 ,10 ,40, 70);

           
		   
		   Assert.assertTrue(j.getLigaDeVillanos().get("ligaDeVillanosTest").pertenece(jugador1));
		   Assert.assertTrue(j.getLigaDeHeroes().get("ligaDeHeroesTest").pertenece(jugador2));
		  
		   j.resetearJugadores();
		 
	}
	
	@Test
	public void cantidadDePersonajesCorrecta() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		// Watchmen se carga en ligas1.txt , ligaHeroesTest en ligas2.txt con 1 y 2 competidores respectivamente
		Juego j = Juego.getInstance();
		j.resetearJugadores();
		j.cargarPersonajesDesdeArchivo("./src/tests/personajes2.txt");
		j.cargarLigaDesdeArchivo("./src/tests/ligas2.txt");
		   
		Liga liga1 = (Liga) j.getLigaDeVillanos().get("ligaDeVillanosTest");
		Liga liga2 = (Liga) j.getLigaDeHeroes().get("ligaDeHeroesTest");
		
		 	
		Assert.assertEquals(1, liga1.cantidadDeCompetidores());
		Assert.assertEquals(1, liga2.cantidadDeCompetidores());
		
		j.resetearJugadores();
	}
	
	@Test
	public void creacionLigaDeLiga() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		
		 Juego j = Juego.getInstance();
		 j.resetearJugadores();
		 
		 j.cargarPersonajesDesdeArchivo("./src/tests/personajes4.txt");
		 j.cargarLigaDesdeArchivo("./src/tests/ligas4.txt");
		   
         Personaje jugador1 = new Personaje("Chiquita", "Tormenta", 50 ,10 ,40, 70);
		   
		  Assert.assertTrue(j.getLigaDeVillanos().get("SuperMinchis").pertenece(jugador1));
			
		   j.resetearJugadores();
		   
		
	}
	
	@Test
	public void creacionLigaDeLigayMas() throws IOException, FormatoArchivoIncorrecto, PerteneceALigaException, CompetidorNoPerteneceAlJuego, LigaYaExiste {
		
		 Juego j = Juego.getInstance();
		 j.resetearJugadores();
		 
		 j.cargarPersonajesDesdeArchivo("./src/tests/personajes5.txt");
		 j.cargarLigaDesdeArchivo("./src/tests/ligas5.txt");
		   
         Personaje jugador1 = new Personaje("Yuri", "YuriGagari", 50 ,10 ,40, 70);
         Personaje jugador2 = new Personaje("Ita", "SuperIta", 50 ,10 ,40, 70);
		   
		   boolean perteneceVillano= j.getLigaDeVillanos().get("SuperMinchis").pertenece(jugador1);
		   boolean perteneceVillano2 = j.getLigaDeVillanos().get("SuperMinchis").pertenece(jugador2);
		   
		   System.out.println(perteneceVillano);
		   
		   j.resetearJugadores();
		   
		   Assert.assertEquals(true, perteneceVillano);
		   Assert.assertEquals(true, perteneceVillano2);
		
	}
    
	/**
	@Test (expected = PerteneceALigaException.class)
	public void heroeYaPerteneceAOtraLiga() {
		// Watchmen se carga en ligas1.txt , ligaHeroesTest en ligas2.txt con 1 y 2 competidores respectivamente
		Juego j = Juego.getInstance();
		
		// j.cargarLigaDesdeArchivo("./src/tests/ligas3.txt");
	
	}
**/
}
