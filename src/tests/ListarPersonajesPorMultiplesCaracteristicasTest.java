package tests;

import clases.Juego;
import org.junit.*;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

public class ListarPersonajesPorMultiplesCaracteristicasTest {

	@Test
	public void ordenadoDeMenorAMayorPorVelocidadYFuerza() throws IOException{

		Juego j = Juego.getInstance();
	   j.cargarPersonajesDesdeArchivo("./src/tests/personajes1.txt");
	   j.cargarLigaDesdeArchivo("./src/tests/ligas1.txt");
	   String lista = "The Comedian 100 200\nOzymandias 120 180\n";
	   
	   Assert.assertEquals(lista, j.listarPersonajesPorMultiplesCaracteristicas("velocidad", "fuerza", 1));
	}

	@Test
	public void ordenadoDeMenorAMayorPorResistenciaYVelocidad() throws IOException{

		Juego j = Juego.getInstance();
	   j.cargarPersonajesDesdeArchivo("./src/tests/personajes6.txt");
	   
	   String lista = "SuperFer 20 40\n"
	   		+ "YuriGagari 40 30\n"
			+ "Tormenta 40 40\n"
	   		+ "SuperIta 50 50\n"
			+ "SuperRomi 100 50\n"
	   		+ "The Comedian 150 100\n"
			+ "Ozymandias 200 120\n";
	   
	   Assert.assertEquals(lista, j.listarPersonajesPorMultiplesCaracteristicas("resistencia", "velocidad", 1));
	}
	
	
	@Test
	public void ordenadoDeMayorAMenorPorResistenciaYVelocidad() throws IOException{

		Juego j = Juego.getInstance();
	   j.cargarPersonajesDesdeArchivo("./src/tests/personajes6.txt");
	   
	   String lista = "Ozymandias 200 120\n"
	   		+ "The Comedian 150 100\n"
			+ "SuperRomi 100 50\n"
	   		+ "SuperIta 50 50\n"
			+ "Tormenta 40 40\n"
	   		+ "YuriGagari 40 30\n"
			+ "SuperFer 20 40\n";
	   
	   Assert.assertEquals(lista, j.listarPersonajesPorMultiplesCaracteristicas("resistencia", "velocidad", -1));
	}
}
