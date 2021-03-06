package clases;

import java.util.HashMap;
import java.util.Objects;


public class Personaje implements Competidor{

	private HashMap<Atributo,Integer> caracteristicas = new HashMap<Atributo,Integer>();
	
	private String nombreReal;
	private String nombrePersonaje;
	private Atributo atributoInicial;
	private String bando;
	
	public Personaje(String nombreReal,String nombrePersonaje,Integer velocidad,Integer fuerza,Integer resistencia,Integer destreza, String bando) {
		
		// faltan los try catch
		
		this.nombreReal = nombreReal;
		this.nombrePersonaje = nombrePersonaje;
		this.atributoInicial = Atributo.VELOCIDAD;
		this.bando = bando;
		
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

	public String getNombre() {
		return nombrePersonaje;
	}

	public void setNombrePersonaje(String nombrePersonaje) {
		this.nombrePersonaje = nombrePersonaje;
	}
	
	public String getBando() {
		return this.bando;
	}
	
	public boolean esGanador(Atributo atributo, Competidor contrincante) {
		this.atributoInicial = atributo;
		return this.compareTo(contrincante) > 0;

	}
	
	
	
	public boolean pertenece(Competidor personaje){
		return this.getNombre().equals( personaje.getNombre());
	}
	
	
	@Override
	public String toString() {
		return this.nombrePersonaje;

	}
	
	
	public String mostrarPersonaje() {
		String result = "";
		for(Integer c : caracteristicas.values()) {
			result += ", " + c.toString();
		}
		
		return  this.nombreReal +", " + this.nombrePersonaje + ", " + result;
	}
	
	public int compareTo(Competidor other) {

		Atributo atributo = this.atributoInicial;
		
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
	
	@Override
	public int hashCode() {
		return Objects.hash(atributoInicial);
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

