
/** 
 * Clase para representar una baraja española con 40 cartas.
 */ 
public class Baraja {
    public final static int NUM_CARTAS_BARAJA=40;

	
	private Carta[] cartas = new Carta[NUM_CARTAS_BARAJA]; //Almacena las 40 cartas de la baraja
	private int posicioncarta;
	private int sacar = 0;

	public void agnade(Carta c) {
		cartas[posicioncarta] = c;
		posicioncarta++;	
	}
	
	public Carta sacarCarta(){
		Carta cartaSacada = cartas[sacar];	//ya que la ultima posicion guardada es la 40, y la ultima carta es la cartas[39]
		sacar++;
		return cartaSacada;
	}


}
