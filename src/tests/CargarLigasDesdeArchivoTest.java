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
import excepciones.PerteneceALigaException;

public class CargarLigasDesdeArchivoTest {

	@Test
	public void CargarUnaLiga() throws IOException {
		   Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes1.txt");
		   
			for (String key : j.getHeroes().keySet()) {
				System.out.println(key);
			}
			
		   j.cargarLigaDesdeArchivo("./src/tests/ligas1.txt");
		  
		   Personaje jugador1 = new Personaje("Edward Blake", "The Comedian", 50 ,10 ,40, 70);
		   
		   boolean pertenece = j.getLigaDeHeroes().get("Watchmen").pertenece(jugador1);
		   
		   System.out.println(pertenece);
		   
		   j.resetearJugadores();
		   
		  // pertenece = j.getLigaDeHeroes().get("Watchmen").pertenece(jugador1);
		   
		   Assert.assertEquals(true, pertenece);
		   
		   
		   
		   //Assert.assertEquals(1, j.getLigaDeHeroes().keySet().size()); 
		   
	}
	
	@Test
	public void CargarUnaLigaDeHeroesYDeVillanos() throws IOException {
		
		 Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes2.txt");
		   j.cargarLigaDesdeArchivo("./src/tests/ligas2.txt");
		   
           Personaje jugador1 = new Personaje("Adrian Veidt2", "Ozymandias", 50 ,10 ,40, 70);
           Personaje jugador2 = new Personaje("Edward Blake", "The Comedian", 50 ,10 ,40, 70);
		   
		   boolean perteneceVillano= j.getLigaDeVillanos().get("ligaDeVillanosTest").pertenece(jugador1);
		   boolean perteneceHeroe= j.getLigaDeHeroes().get("ligaDeHeroesTest").pertenece(jugador1);
		   
		   System.out.println(perteneceVillano);
		   System.out.println(perteneceHeroe);
		   
		   j.resetearJugadores();
		   
		   Assert.assertEquals(true, perteneceVillano);
		   Assert.assertEquals(true, perteneceHeroe);
		  
		   //Assert.assertEquals(2, j.getLigaDeHeroes().keySet().size()); 
		   //Assert.assertEquals(1, j.getLigaDeVillanos().keySet().size());
	}
	
	@Test
	public void cantidadDePersonajesCorrecta() throws IOException {
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
	public void creacionLigaDeLiga() throws IOException {
		
		 Juego j = Juego.getInstance();
		 j.resetearJugadores();
		 
		 j.cargarPersonajesDesdeArchivo("./src/tests/personajes4.txt");
		 j.cargarLigaDesdeArchivo("./src/tests/ligas4.txt");
		   
         Personaje jugador1 = new Personaje("Chiquita", "Tormenta", 50 ,10 ,40, 70);
		   
		   boolean perteneceVillano= j.getLigaDeVillanos().get("SuperMinchis").pertenece(jugador1);
		   
		   System.out.println(perteneceVillano);
		   
		   j.resetearJugadores();
		   
		   Assert.assertEquals(true, perteneceVillano);
		
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
