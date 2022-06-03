package clases;

import java.util.HashMap;


public abstract class Personaje implements Comparable<Personaje>{

	private HashMap<Atributo,Integer> caracteristicas = new HashMap<Atributo,Integer>();
	
	private String nombreReal;
	private String nombrePersonaje;
	
	public Personaje(String nombreReal,String nombrePersonaje,Integer velocidad,Integer fuerza,Integer resistencia,Integer destreza) {
		
		// faltan los try catch
		
		this.nombreReal = nombreReal;
		this.nombrePersonaje = nombrePersonaje;
		
		caracteristicas.put(Atributo.VELOCIDAD,velocidad);
		caracteristicas.put(Atributo.FUERZA,fuerza);
		caracteristicas.put(Atributo.RESISTENCIA,resistencia);
		caracteristicas.put(Atributo.DESTREZA,destreza);
	}

	public HashMap<Atributo, Integer> getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(HashMap<Atributo, Integer> caracteristicas) {
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
	
	@Override
	public int compareTo(Personaje other) {
		Atributo atributo = Atributo.DESTREZA;
		
		for(int i = 0; i < 4; i++) {
			if(this.getCaracteristicas().get(atributo).equals(other.getCaracteristicas().get(atributo))){
				atributo = proximoValor(atributo);
			}
			else {
				return this.getCaracteristicas().get(atributo).compareTo(other.getCaracteristicas().get(atributo));
			}
		}
		
		return 0;
	}
	
private Atributo proximoValor(Atributo valorActual) {
		
		if(valorActual == Atributo.VELOCIDAD) 
			return Atributo.FUERZA;
	
		else if(valorActual == Atributo.FUERZA) 
			return Atributo.RESISTENCIA;
	
		else if(valorActual == Atributo.RESISTENCIA) 
			return Atributo.DESTREZA;
	
		return Atributo.VELOCIDAD;
	}
	
	
}

