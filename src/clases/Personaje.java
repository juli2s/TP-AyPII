package clases;

import java.util.HashMap;


public abstract class Personaje implements Comparable<Personaje>{

	private HashMap<String,Integer> caracteristicas = new HashMap<String,Integer>();
	
	private String nombreReal;
	private String nombrePersonaje;
	
	public Personaje(String nombreReal,String nombrePersonaje,Integer velocidad,Integer fuerza,Integer resistencia,Integer destreza) {
		
		// faltan los try catch
		
		this.nombreReal = nombreReal;
		this.nombrePersonaje = nombrePersonaje;
		
		caracteristicas.put("velocidad",velocidad);
		caracteristicas.put("fuerza",fuerza);
		caracteristicas.put("resistencia",resistencia);
		caracteristicas.put("destreza",destreza);
	}

	public HashMap<String, Integer> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(HashMap<String, Integer> caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getNombreReal() {
		return nombreReal;
	}

	public void setNombreReal(String nombreReal) {
		this.nombreReal = nombreReal;
	}

	public String getNombrePersonaje() {
		return nombrePersonaje;
	}

	public void setNombrePersonaje(String nombrePersonaje) {
		this.nombrePersonaje = nombrePersonaje;
	}
	
	@Override
	public String toString() {
		return this.nombrePersonaje + this.nombreReal;
	}
	
	
	
	
	
	
}

