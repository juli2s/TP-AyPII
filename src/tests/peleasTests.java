package tests;

import org.junit.Assert;
import org.junit.Test;

import clases.Atributo;
import clases.Juego;
import clases.Personaje;
import clases.Competidor;

import excepciones.*;
import java.io.IOException;

public class peleasTests {
	@Test
	public void ganaJug1PorVelocidad() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60, "Heroe");
	   
	   
	   boolean esGanador = jugador1.esGanador(Atributo.VELOCIDAD, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(true, esGanador);
	}
	
	@Test
	public void ganaJug2PorVelocidad() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 50 ,40 ,40, 60, "Heroe");
	   
	   
	   boolean esGanador = jugador1.esGanador(Atributo.VELOCIDAD, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(false, esGanador);
	}
	
	@Test
	public void ganaJug2PorResistencia() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 60 ,20 ,40, 60, "Heroe");
	   
	   
	   boolean esGanador = jugador1.esGanador(Atributo.RESISTENCIA, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(false, esGanador);
	}
	
	@Test
	public void ganaJug1PorResistencia() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,10 ,40, 70, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 60 ,20 ,40, 60, "Heroe");
	   
	   //Juego juego =  Juego.getInstance();
	   
	   boolean esGanador = jugador1.esGanador(Atributo.RESISTENCIA, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(true, esGanador);
	}
	
	@Test
	public void empate() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,20 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 50 ,20 ,40, 60, "Heroe");
	   
	   
	   boolean esGanador = jugador1.esGanador(Atributo.RESISTENCIA, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(false, esGanador);
	}
	
	@Test
	public void ganaJugadorContraLigaPorVelocidad() throws FormatoArchivoIncorrecto,
		CompetidorNoPerteneceAlJuego, LigaYaExiste, PerteneceALigaException, IOException{
		Juego j = Juego.getInstance();
		j.resetearJugadores();
		 
		j.cargarPersonajesDesdeArchivo("./src/tests/personajes5.txt");
		j.cargarLigaDesdeArchivo("./src/tests/ligas5.txt");
		 
		Competidor jugador = j.getHeroes().get("The Comedian");
		Competidor liga = j.getLigaDeVillanos().get("SuperMinchis");
	   //Juego juego =  Juego.getInstance();
	   
	   boolean esGanador = jugador.esGanador(Atributo.VELOCIDAD, liga);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertTrue(esGanador);
	}

	
	@Test
	public void ganaLigaContraLigaPorResistencia() throws FormatoArchivoIncorrecto,
		CompetidorNoPerteneceAlJuego, LigaYaExiste, PerteneceALigaException, IOException{
		Juego j = Juego.getInstance();
		j.resetearJugadores();
		 
		j.cargarPersonajesDesdeArchivo("./src/tests/personajes5.txt");
		j.cargarLigaDesdeArchivo("./src/tests/ligas5.txt");
		 
		Competidor liga1 = j.getLigaDeVillanos().get("losMinchis");
		Competidor liga2 = j.getLigaDeHeroes().get("ligaDePelea");
	   //Juego juego =  Juego.getInstance();
	   
	   boolean esGanador = liga1.esGanador(Atributo.RESISTENCIA, liga2);
	   
	   
	   Assert.assertTrue(esGanador);
	}
}
