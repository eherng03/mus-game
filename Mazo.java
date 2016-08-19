/**
 * Representa un mazo de un número no prefijado de cartas de la baraja española. Cartas que tiene cada jugador en la mano
 */
 public class Mazo {

	private Carta[] cartas;
	private int posicion;
	
	public Mazo(){
		this.cartas = new Carta[Main.NUM_CARTAS_REPARTIR];		//array tamaño 4
		this.posicion = 0;
	}
	
	public void meterCartaAlMazo(Carta carta){		//metodo que añade cartas al mazo
		this.cartas[posicion] = carta;
		posicion++;	
	}
	
	public Carta sacarCartaDelMazo(){		//metodo que devuelve una carta del mazo
		Carta cartaSacada = cartas[posicion-1];
		posicion--;	
		return cartaSacada;
	}
	
	public Carta[] darArrayMazo(){	
		return this.cartas;
	}
	
	public int[] darNumerosMazo(){
		Carta[] cartas = this.cartas;
		int[] carta = new int[Main.NUM_CARTAS_REPARTIR];
		carta[0] = cartas[0].darNumero();
		carta[1] = cartas[1].darNumero();
		carta[2] = cartas[2].darNumero();
		carta[3] = cartas[3].darNumero();
		return carta;
	}
	
}