package clases;

import java.util.Comparator;

public class ComparadorPorAtributo implements Comparator<Competidor>{

	Atributo atributo1;
	Atributo atributo2;
	int orden;
	
	public ComparadorPorAtributo(Atributo atributo1, Atributo atributo2, int orden) {
		this.atributo1 = atributo1;
		this.atributo2 = atributo2;
		this.orden = orden;
	}
	
	
	public int compare(Competidor a, Competidor b) {
	
		if(a.getCaracteristicas().get(atributo1) == b.getCaracteristicas().get(atributo1)){
			return orden*a.getCaracteristicas().get(atributo2).compareTo(b.getCaracteristicas().get(atributo2));
		}
		
		return orden*a.getCaracteristicas().get(atributo1).compareTo(b.getCaracteristicas().get(atributo1));
				
	}
}
