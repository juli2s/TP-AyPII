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
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60, "Heroe");

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60, "Villano");
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60, "Villano");
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60, "Villano");
	   
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
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60, "Heroe");

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60, "Villano");
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60, "Villano");
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60, "Villano");
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   boolean pertenece = liga1.pertenece(jugador5);
	   
	   System.out.println(pertenece);
	   
	   Assert.assertEquals(false, pertenece);
	}
	
	@Test
	public void personajeEnSuperLiga() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60, "Heroe");

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60, "Villano");
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60, "Villano");
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60, "Villano");
	   
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
	public void personajeEnSuperLigaChequearPromedio() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60, "Heroe");

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60, "Villano");
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60, "Villano");
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60, "Villano");
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   List<Competidor> listaLiga2 = new LinkedList();

	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   int promedioVLiga1 = (50+40)/2;
	   int promedioFLiga1 = (30+30)/2;
	   int promedioRLiga1 = (40+40)/2;
	   int promedioDLiga1 = (60+60)/2;
	   
	   
	   listaLiga2.add(jugador5);
	   listaLiga2.add(liga1);
	   
	   int promedioVLiga2 = (((50+40)/2) + 40)/2 ;
	   int promedioFLiga2 = ((30+30)/2 + 30) /2;
	   int promedioRLiga2 = ((40+40)/2 + 40) /2;
	   int promedioDLiga2 = ((60+60)/2 + 60) /2;
	   
	   
	   Liga liga2 = new Liga("SuperMinchis", listaLiga2);
	   
	   boolean pertenece = liga2.pertenece(jugador3);
	   
	   int VELOCIDADLiga1 = liga1.getCaracteristicas().get(Atributo.VELOCIDAD);
	   int FUERZALiga1= liga1.getCaracteristicas().get(Atributo.FUERZA);
	   int RESISTENCIALiga1= liga1.getCaracteristicas().get(Atributo.RESISTENCIA);
	   int DESTREZALiga1= liga1.getCaracteristicas().get(Atributo.DESTREZA);
	   
	   int VELOCIDADLiga2= liga2.getCaracteristicas().get(Atributo.VELOCIDAD);
	   int FUERZALiga2= liga2.getCaracteristicas().get(Atributo.FUERZA);
	   int RESISTENCIALiga2= liga2.getCaracteristicas().get(Atributo.RESISTENCIA);
	   int DESTREZALiga2= liga2.getCaracteristicas().get(Atributo.DESTREZA);
	   
	   System.out.println("Liga1 " + "Velocidad: " + VELOCIDADLiga1 + "FUERZA: " + FUERZALiga1 + "RESISTENCIA: " + RESISTENCIALiga1 
			            + "DESTREZA: "+ DESTREZALiga1);

	   System.out.println("Liga2 " + "Velocidad: " + VELOCIDADLiga2 + "FUERZA: " + FUERZALiga2 + "RESISTENCIA: " + RESISTENCIALiga2 
			            + "DESTREZA: "+ DESTREZALiga2);
	   
	   System.out.println(pertenece);
	   
	   Assert.assertEquals(promedioVLiga1,VELOCIDADLiga1 );
	   Assert.assertEquals(promedioFLiga1,FUERZALiga1 );
	   Assert.assertEquals(promedioRLiga1,RESISTENCIALiga1 );
	   Assert.assertEquals(promedioDLiga1,DESTREZALiga1 );
	   
	   Assert.assertEquals(promedioVLiga2,VELOCIDADLiga2 );
	   Assert.assertEquals(promedioFLiga2,FUERZALiga2 );
	   Assert.assertEquals(promedioRLiga2,RESISTENCIALiga2 );
	   Assert.assertEquals(promedioDLiga2,DESTREZALiga2 );

	   
	   
	   Assert.assertEquals(true, pertenece);
	}


	@Test
	public void personajeEnSuperLigaChequearPromedioAlAgregar() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 50 ,30 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60, "Heroe");

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60, "Villano");
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60, "Villano");
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60, "Villano");
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   List<Competidor> listaLiga2 = new LinkedList();

	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   int promedioVLiga1 = (50+40)/2;
	   int promedioFLiga1 = (30+30)/2;
	   int promedioRLiga1 = (40+40)/2;
	   int promedioDLiga1 = (60+60)/2;
	   
	   
	   listaLiga2.add(jugador5);
	   listaLiga2.add(liga1);
	   
	   
	   Liga liga2 = new Liga("SuperMinchis", listaLiga2);
	   
	   liga2.agregarCompetidor(jugador1);
	   
	   int promedioVLiga2 = (((50+40)/2) + 40 + 50 )/3 ;
	   int promedioFLiga2 = ((30+30)/2 + 30 + 30) / 3;
	   int promedioRLiga2 = ((40+40)/2 + 40 + 40) / 3;
	   int promedioDLiga2 = ((60+60)/2 + 60 + 60 ) / 3;
	   
	   
	   boolean pertenece = liga2.pertenece(jugador3);
	   
	   int VELOCIDADLiga1 = liga1.getCaracteristicas().get(Atributo.VELOCIDAD);
	   int FUERZALiga1= liga1.getCaracteristicas().get(Atributo.FUERZA);
	   int RESISTENCIALiga1= liga1.getCaracteristicas().get(Atributo.RESISTENCIA);
	   int DESTREZALiga1= liga1.getCaracteristicas().get(Atributo.DESTREZA);
	   
	   int VELOCIDADLiga2= liga2.getCaracteristicas().get(Atributo.VELOCIDAD);
	   int FUERZALiga2= liga2.getCaracteristicas().get(Atributo.FUERZA);
	   int RESISTENCIALiga2= liga2.getCaracteristicas().get(Atributo.RESISTENCIA);
	   int DESTREZALiga2= liga2.getCaracteristicas().get(Atributo.DESTREZA);
	   
	   System.out.println("Liga1 " + "Velocidad: " + VELOCIDADLiga1 + "FUERZA: " + FUERZALiga1 + "RESISTENCIA: " + RESISTENCIALiga1 
			            + "DESTREZA: "+ DESTREZALiga1);

	   System.out.println("Liga2 " + "Velocidad: " + VELOCIDADLiga2 + "FUERZA: " + FUERZALiga2 + "RESISTENCIA: " + RESISTENCIALiga2 
			            + "DESTREZA: "+ DESTREZALiga2);
	   
	   System.out.println(pertenece);
	   
	   Assert.assertEquals(promedioVLiga1,VELOCIDADLiga1 );
	   Assert.assertEquals(promedioFLiga1,FUERZALiga1 );
	   Assert.assertEquals(promedioRLiga1,RESISTENCIALiga1 );
	   Assert.assertEquals(promedioDLiga1,DESTREZALiga1 );
	   
	   Assert.assertEquals(promedioVLiga2,VELOCIDADLiga2 );
	   Assert.assertEquals(promedioFLiga2,FUERZALiga2 );
	   Assert.assertEquals(promedioRLiga2,RESISTENCIALiga2 );
	   Assert.assertEquals(promedioDLiga2,DESTREZALiga2 );

	   
	   
	   Assert.assertEquals(true, pertenece);
	}

	
	@Test
	public void ganaDueñoAMinchis() {
	   Personaje jugador1;
	   Personaje jugador2;	
	   Personaje jugador3;
	   Personaje jugador4;
	   Personaje jugador5;
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 700 ,30 ,40, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60, "Heroe");

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,30 ,40, 60, "Villano");
	   jugador4 = new Personaje("Chiquita", "Tormenta", 40 ,30 ,40, 60, "Villano");
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60, "Villano");
	   
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
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 7 ,30 ,140, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60, "Heroe");

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,15 ,40, 60, "Villano");
	   jugador4 = new Personaje("Chiquita", "Tormenta", 15 ,30 ,40, 60, "Villano");
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60, "Villano");
	   
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
	
	   jugador1 = new Personaje("Romi", "SuperRomi", 7 ,30 ,140, 60, "Heroe");
	   jugador2 = new Personaje("Fer", "SuperFer", 40 ,30 ,40, 60, "Heroe");

	   jugador3 = new Personaje("Ita", "SuperIta", 50 ,135 ,40, 60, "Villano");
	   jugador4 = new Personaje("Chiquita", "Tormenta", 35 ,35 ,40, 60, "Villano");
	   
	   jugador5 = new Personaje("Yuri", "YuriGagari", 40 ,30 ,40, 60, "Villano");
	   
	   
	   
	   List<Competidor> listaLiga1 = new LinkedList();
	   
	   listaLiga1.add(jugador3);
	   listaLiga1.add(jugador4);
	   
	   List<Competidor> listaLiga2 = new LinkedList();

	   Liga liga1 = new Liga("Minchis", listaLiga1);
	   
	   listaLiga2.add(jugador5);
	   listaLiga2.add(liga1);
	   
	   System.out.println(liga1.getCaracteristicas().get(Atributo.FUERZA));
       boolean esGanador = jugador1.esGanador(Atributo.FUERZA, liga1);
	   
      
	   System.out.println(esGanador);
	   
	   Assert.assertFalse(jugador1.esGanador(Atributo.FUERZA, liga1));
	}


}

