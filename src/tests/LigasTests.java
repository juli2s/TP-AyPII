package tests;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import clases.Atributo;
import clases.Personaje;
import clases.Competidor;
import clases.Liga;

public class LigasTests {
	@Test
	public void personajePerteneceALiga() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60);

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60);
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60);
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60);
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   boolean pertenece = liga1.pertenece(jugador3);
	   
	   System.out.println(pertenece);
	   
	   Assert.assertEquals(true, pertenece);
	}
	
	@Test
	public void personajeNoPerteneceALiga() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60);

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60);
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60);
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60);
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   boolean pertenece = liga1.pertenece(jugador5);
	   
	   System.out.println(pertenece);
	   
	   Assert.assertEquals(true, pertenece);
	}
	
	@Test
	public void personajeEnSuperLiga() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60);

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60);
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60);
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60);
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   List<Competidor> listaLiga2 = new LinkedList();

	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   listaLiga2.add(jugador5);
	   listaLiga2.add(liga1);
	   
	   Liga liga2 = new Liga("SuperMinchis", listaLiga2);
	   
	   boolean pertenece = liga2.pertenece(jugador3);
	   
	   System.out.println(pertenece);
	   
	   Assert.assertEquals(true, pertenece);
	}
	
	
	@Test
	public void ganaDueñoAMinchis() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 700 ,30 ,40, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60);

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60);
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60);
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60);
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   List<Competidor> listaLiga2 = new LinkedList();

	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   listaLiga2.add(jugador5);
	   listaLiga2.add(liga1);
	   
	   
       boolean esGanador = jugador1.esGanador(Atributo.VELOCIDAD, liga1);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(true, esGanador);
	}

	@Test
	public void ganaDueñoAMinchisPorFuerza() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 7 ,30 ,140, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60);

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,15 ,40, 60);
	   jugador4 = new Personaje("Chiquita", "Tormenta", 15 ,30 ,40, 60);
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60);
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   List<Competidor> listaLiga2 = new LinkedList();

	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   listaLiga2.add(jugador5);
	   listaLiga2.add(liga1);
	   
	   
       boolean esGanador = jugador1.esGanador(Atributo.FUERZA, liga1);
	   
	   System.out.println(esGanador);
	   
	   Assert.assertEquals(true, esGanador);
	}
	
	@Test
	public void gananMinchisPorFuerza() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 7 ,30 ,140, 60);
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60);

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,135 ,40, 60);
	   jugador4 = new Personaje("Chiquita", "Tormenta", 35 ,35 ,40, 60);
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60);
	   
	   
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   List<Competidor> listaLiga2 = new LinkedList();

	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   listaLiga2.add(jugador5);
	   listaLiga2.add(liga1);
	   
	   
	   
	   Assert.assertFalse(jugador1.esGanador(Atributo.FUERZA, liga1));
	}


}

