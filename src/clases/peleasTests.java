package clases;

import org.junit.Assert;
import org.junit.Test;

public class peleasTests {
	@Test
	public void ganaJug1PorVelocidad() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60);
	   
	   
	   boolean esGanador = jugador1.esGanador(Atributo.VELOCIDAD, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(true, esGanador);
	}
	
	@Test
	public void ganaJug2PorVelocidad() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 50 ,40 ,40, 60);
	   
	   
	   boolean esGanador = jugador1.esGanador(Atributo.VELOCIDAD, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(false, esGanador);
	}
	
	@Test
	public void ganaJug2PorResistencia() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 60 ,20 ,40, 60);
	   
	   
	   boolean esGanador = jugador1.esGanador(Atributo.RESISTENCIA, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(false, esGanador);
	}
	
	@Test
	public void ganaJug1PorResistencia() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,10 ,40, 70);
	   jugador2 = new Personaje("Fer", "SuperFer", 60 ,20 ,40, 60);
	   
	   Juego juego =  Juego.getInstance();
	   
	   boolean esGanador = jugador1.esGanador(Atributo.RESISTENCIA, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(true, esGanador);
	}
	
	@Test
	public void empate() {
	   Personaje jugador1;
	   Personaje jugador2;	
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,20 ,40, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 50 ,20 ,40, 60);
	   
	   
	   boolean esGanador = jugador1.esGanador(Atributo.RESISTENCIA, jugador2);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(false, esGanador);
	}
	
	

}
