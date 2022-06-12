package clases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Liga {
    private HashMap<Atributo,Integer> caracteristicas = new HashMap<Atributo,Integer>();
	
	private String nombreLiga;
	private Atributo atributoInicial;
	private List<Competidor> integrantes = new LinkedList();
	
	public Liga(String nombreLiga,List<Competidor> integrantes) {
		
		// faltan los try catch
		this.nombreLiga = nombreLiga;
		this.integrantes = integrantes;
		
		int sumaVelocidad = 0;
		int sumaFuerza = 0;
		int sumaResistencia = 0;
		int sumaDestreza = 0;
		int cantIntegrantes = integrantes.size();
		
		for (Competidor integrante: integrantes) {
			sumaVelocidad = integrante.getCaracteristicas().get(Atributo.VELOCIDAD);
			sumaFuerza = integrante.getCaracteristicas().get(Atributo.FUERZA);
			sumaResistencia = integrante.getCaracteristicas().get(Atributo.RESISTENCIA);
			sumaDestreza = integrante.getCaracteristicas().get(Atributo.DESTREZA);
			
		}
		
		caracteristicas.put(Atributo.VELOCIDAD,sumaVelocidad/cantIntegrantes);
		caracteristicas.put(Atributo.FUERZA,sumaFuerza/cantIntegrantes);
		caracteristicas.put(Atributo.RESISTENCIA,sumaResistencia/cantIntegrantes);
		caracteristicas.put(Atributo.DESTREZA,sumaDestreza/cantIntegrantes);
	}
	
	public HashMap<Atributo, Integer> getCaracteristicas() {
		return caracteristicas;
	}
	
    public boolean agregarCompetidor(Competidor competidor){
    	//falta ver que aun no pertenezca
    	
    	boolean inserto = true;
    	
    	int velocidad = 0;
		int fuerza = 0;
		int resistencia = 0;
		int destreza = 0;
		int cantIntegrantes = integrantes.size() + 1;
		
		
	    velocidad = this.getCaracteristicas().get(Atributo.VELOCIDAD) + competidor.getCaracteristicas().get(Atributo.VELOCIDAD);
		fuerza = this.getCaracteristicas().get(Atributo.FUERZA) + competidor.getCaracteristicas().get(Atributo.FUERZA);
		resistencia = this.getCaracteristicas().get(Atributo.RESISTENCIA) + competidor.getCaracteristicas().get(Atributo.RESISTENCIA);
		destreza = this.getCaracteristicas().get(Atributo.DESTREZA) + competidor.getCaracteristicas().get(Atributo.DESTREZA);
		
		caracteristicas.put(Atributo.VELOCIDAD,velocidad/cantIntegrantes);
		caracteristicas.put(Atributo.FUERZA,fuerza/cantIntegrantes);
		caracteristicas.put(Atributo.RESISTENCIA,resistencia/cantIntegrantes);
		caracteristicas.put(Atributo.DESTREZA,destreza/cantIntegrantes);
		
		return inserto;
		
	}
    
    public String nombreCompetidor() {
		return nombreLiga;
	}
   
    public boolean pertenece( Personaje personaje){
    	boolean pertenece = false;
    	
    	for (Competidor integrante: integrantes) {
    		pertenece =  pertenece || integrante.pertenece(personaje);
			
		}
    	
    	return pertenece;
    }
    

	public boolean esGanador(Atributo atributo, Personaje contrincante) {
		//habria que chequear que los personajes pertenezcan al juego?
		//this.atributoInicial = atributo;
		boolean esGanador = false;
		
		if (this.compareTo(contrincante) > 0 )
		{
			esGanador = true;
		}
		
		return esGanador;
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
	
	private Atributo proximoValor(Atributo valorActual) {
		
		if(valorActual == Atributo.VELOCIDAD) 
			return Atributo.FUERZA;
	
		else if(valorActual == Atributo.FUERZA) 
			return Atributo.RESISTENCIA;
	
		else if(valorActual == Atributo.FUERZA) 
			return Atributo.RESISTENCIA;
	
		else if(valorActual == Atributo.RESISTENCIA) 
			return Atributo.DESTREZA;
	
		return Atributo.VELOCIDAD;
	}



}
