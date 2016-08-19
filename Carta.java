/*
 * Clase para representar una carta de la baraja española.
 */

public class Carta {
	
	@SuppressWarnings("unused")
	private Palo palo; // Almacena el palo de la carta
	private Numero numero; // Almacena el nÃºmero de la carta, tipo numero
	private int valor;		//Almacena el valor numerico de la carta, ej el 3 es 12
	
	public Carta(){		//constructor vacio
		this.numero = null;
		this.palo = null;
		this.valor = 0;
	}
	
	//constructor de las cartas
	public Carta(String carta){		
		int numeros = Integer.parseInt(carta.substring(0, carta.length() - 1));
		String palo = carta.substring(carta.length()-1, carta.length());
		
		for(Numero i : Numero.values()){		//for que recorre todos los valores que toma numero
			if( numeros != 10 && numeros != 11 && numeros != 12){		//ya que sota, caballo y rey no estan en las posiciones 10, 11 y 12
				if(numeros == i.ordinal() +1){		// .ordinal te dice la posicion en la que esta el Numero, como empieza en 0, se suma 1
					this.numero = i;
					this.valor = i.darValorNumero();
				}	
			}
			else{
				if(numeros == i.ordinal() + 3){		//ya que la posicion del 7 seria i.ordinal() +1, la del 10 es i.ordinal() + 3
					this.numero = i;
					this.valor = i.darValorNumero();
				}
			}
		}
		
		switch(palo){
			case "o": this.palo = Palo.OROS;
				break;
			case "c": this.palo = Palo.COPAS;
				break;
			case "e": this.palo = Palo.ESPADAS;
				break;
			case "b": this.palo = Palo.BASTOS;
				break;
		}
	}
	//termina constructor
	
	public int darValorCarta(){		//metodo que introduciendo una carta te devuelve su valor
		Carta carta = (Carta) this;
		return carta.valor;
	}
	
	public int darNumero(){		//metodo que introduciendo una carta te devuelve su posicion +1
		return this.numero.ordinal()+1;
	}
	
}
