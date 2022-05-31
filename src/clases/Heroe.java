package clases;

public class Heroe extends Personaje {

	public Heroe(String nombreReal,String nombrePersonaje,Integer velocidad, Integer fuerza, Integer resistencia, Integer destreza) {
		super(nombreReal, nombrePersonaje, velocidad, fuerza, resistencia, destreza);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compareTo(Personaje o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Heroe \n" + super.toString();
	}

}
