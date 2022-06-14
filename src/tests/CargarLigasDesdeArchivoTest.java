package tests;

import static org.junit.Assert.*;

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
	public void CargarUnaLiga() {
		Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes1.txt");
		   j.cargarLigaDesdeArchivo("./src/tests/ligas1.txt");
		  
		   Assert.assertEquals(1, j.getLigaDeHeroes().keySet().size()); 
		   
	}
	@Test
	public void CargarUnaLigaDeHeroesYDeVillanos() {
		// como juego solo tiene una instancia ( singleton), aca vamos a tener 2 ligas de heroes, 1 de este test y una del anterior
		Juego j = Juego.getInstance();
		   j.cargarPersonajesDesdeArchivo("./src/tests/personajes2.txt");
		   j.cargarLigaDesdeArchivo("./src/tests/ligas2.txt");
		  
		   Assert.assertEquals(2, j.getLigaDeHeroes().keySet().size()); 
		   Assert.assertEquals(1, j.getLigaDeVillanos().keySet().size());
	}
	
	@Test
	public void cantidadDePersonajesCorrecta() {
		// Watchmen se carga en ligas1.txt , ligaHeroesTest en ligas2.txt con 1 y 2 competidores respectivamente
		Juego j = Juego.getInstance();
		 	Liga liga1 = (Liga) j.getLigaDeHeroes().get("Watchmen");
		 	Liga liga2 = (Liga) j.getLigaDeHeroes().get("ligaDeHeroesTest");
		   Assert.assertEquals(1, liga1.cantidadDeCompetidores());
		   Assert.assertEquals(2, liga2.cantidadDeCompetidores());
	}

	
	@Test (expected = PerteneceALigaException.class)
	public void heroeYaPerteneceAOtraLiga() {
		// Watchmen se carga en ligas1.txt , ligaHeroesTest en ligas2.txt con 1 y 2 competidores respectivamente
		Juego j = Juego.getInstance();
		
		// j.cargarLigaDesdeArchivo("./src/tests/ligas3.txt");
	
	}

}
