package clases;

import java.util.Comparator;

public class ComparadorPorAtributo implements Comparator<Personaje>{

	Atributo atributo1;
	Atributo atributo2;
	
	public ComparadorPorAtributo(Atributo atributo1, Atributo atributo2) {
		this.atributo1 = atributo1;
		this.atributo2 = atributo2;
	}

	
	public int compare(Personaje a, Personaje b) {
	
		if(a.getCaracteristicas().get(atributo1) == b.getCaracteristicas().get(atributo1)){
			return a.getCaracteristicas().get(atributo2).compareTo(b.getCaracteristicas().get(atributo2));
		}
		
		return a.getCaracteristicas().get(atributo1).compareTo(b.getCaracteristicas().get(atributo1));
				
	}
}
