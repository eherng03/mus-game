/** 
 * Clase para representar a un jugador del juego del mus.
 */ 
public class Jugador {
	
	@SuppressWarnings("unused")
	private int idJugador; //Identificador único de jugador
	public Mazo mano; //Almacena las cartas del jugador en la jugada actual.

	public Jugador(int i){
		this.idJugador = i;
		this.mano = new Mazo();
	}
	

	public void meteAMano(Carta CartaRepartida) {
		this.mano.meterCartaAlMazo(CartaRepartida);		//mete la carta a la mano del jugador llamante
	 }
	   
	
}
