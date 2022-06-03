package clases;

import java.util.Comparator;

public class ComparadorPorAtributo implements Comparator<Personaje>{

	Atributo atributo;

	
	public ComparadorPorAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

	
	public int compare(Personaje a, Personaje b) {
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
	
}
