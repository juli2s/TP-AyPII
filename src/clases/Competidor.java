package clases;

import java.util.HashMap;

public interface Competidor extends Comparable<Competidor>{
	
	
	//public boolean esGanador(Atributo atributo, Competidor contrincante);
	public boolean pertenece(Competidor personaje);
	public HashMap<Atributo, Integer> getCaracteristicas();
	public String getNombre();
	public String getBando();
	public boolean esGanador(Atributo atributo, Competidor contrincante);
	

}
