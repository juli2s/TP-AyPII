package clases;

import java.util.HashMap;

public interface Competidor {
	
	
	//public boolean esGanador(Atributo atributo, Competidor contrincante);
	public boolean pertenece( Personaje personaje);
	public HashMap<Atributo, Integer> getCaracteristicas();
	public String nombreCompetidor();
	public boolean esGanador(Atributo atributo, Personaje contrincante);
	

}
