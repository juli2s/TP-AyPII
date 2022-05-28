package clases;

import java.util.HashMap;


public abstract class Personaje implements Comparable<Personaje>{

	private HashMap<String,Integer> caracteristicas = new HashMap<String,Integer>();
	
	
	public Personaje(Integer velocidad,Integer fuerza,Integer resistencia,Integer destreza) {
		caracteristicas.put("velocidad",velocidad);
		caracteristicas.put("fuerza",fuerza);
		caracteristicas.put("resistencia",resistencia);
		caracteristicas.put("destreza",destreza);
	}
	
}

