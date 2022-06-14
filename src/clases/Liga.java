package clases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Liga implements Competidor{
    private HashMap<Atributo,Integer> caracteristicas = new HashMap<Atributo,Integer>();
	
	private String nombreLiga;
	private Atributo atributoInicial;
	private List<Competidor> integrantes = new LinkedList();
	
	
	public Liga(String nombreLiga,List<Competidor> integrantes) {
		
		// faltan los try catch
		this.nombreLiga = nombreLiga;
		this.integrantes = integrantes;
		this.atributoInicial = Atributo.VELOCIDAD;
		//calcularAtributos();
		
	}
	
	
	public HashMap<Atributo, Integer> getCaracteristicas() {
		return caracteristicas;
	}
	
	
    public boolean agregarCompetidor(Competidor competidor){
    	//falta ver que aun no pertenezca
    	
    	boolean inserto = true;
		integrantes.add(competidor);
		calcularAtributos();
		
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
    

	public boolean esGanador(Atributo atributo, Competidor contrincante) {
		//habria que chequear que los personajes pertenezcan al juego?
		this.atributoInicial = atributo;				
		return this.compareTo(contrincante) > 0;
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

	private void calcularAtributos(){
		
		caracteristicas.put(Atributo.VELOCIDAD, calcularSumaDeAtributos(Atributo.VELOCIDAD)/integrantes.size());
		caracteristicas.put(Atributo.FUERZA, calcularSumaDeAtributos(Atributo.FUERZA)/integrantes.size());
		caracteristicas.put(Atributo.RESISTENCIA, calcularSumaDeAtributos(Atributo.RESISTENCIA)/integrantes.size());
		caracteristicas.put(Atributo.DESTREZA, calcularSumaDeAtributos(Atributo.DESTREZA)/integrantes.size());		
	
	}
	
	private Integer calcularSumaDeAtributos(Atributo atributo) {
		Integer suma = 0;
		
		for (Competidor integrante: integrantes) {
			suma += integrante.getCaracteristicas().get(atributo);
		}
		
		return suma;
	}
	// agrego metodo para saber cuantos competidores tiene una liga.
	
	public int cantidadDeCompetidores() {
		return this.integrantes.size();
		
	}

}
