package clases;

import java.util.Comparator;

public class ComparadorPorAtributo implements Comparator<Personaje>{

<<<<<<< HEAD
	Atributo atributo1;
	Atributo atributo2;
	
	public ComparadorPorAtributo(Atributo atributo1, Atributo atributo2) {
		this.atributo1 = atributo1;
		this.atributo2 = atributo2;
=======
	Atributo atributo;

	
	public ComparadorPorAtributo(Atributo atributo) {
		this.atributo = atributo;
>>>>>>> 03e0bbd6c83db721cfcd3035a713a10dedbc50bb
	}

	
	public int compare(Personaje a, Personaje b) {
<<<<<<< HEAD
	
		if(a.getCaracteristicas().get(atributo1) == b.getCaracteristicas().get(atributo1)){
			return a.getCaracteristicas().get(atributo2).compareTo(b.getCaracteristicas().get(atributo2));
		}
		
		return a.getCaracteristicas().get(atributo1).compareTo(b.getCaracteristicas().get(atributo1));
				
	}
=======
		
		for(int i = 0; i < 4; i++) {
		  
		    
			if(a.getCaracteristicas().get(atributo) == b.getCaracteristicas().get(atributo)){
				atributo = proximoValor(atributo);
			}
			else {
				return a.getCaracteristicas().get(atributo).compareTo(b.getCaracteristicas().get(atributo));
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
	
>>>>>>> 03e0bbd6c83db721cfcd3035a713a10dedbc50bb
}
