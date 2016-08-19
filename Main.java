
public class Main {
/** 
 * Clase principal de la prÃ¡ctica y su objetivo es iniciar la ejecuciÃ³n del programa. 
 * El mÃ©todo main no se puede cambiar, pero se pueden aÃ±adir nuevos atributos y/o mÃ©todos.
 */ 

    public final static int NUM_JUGADORES_MUS = 4;
    public final static int NUM_CARTAS_REPARTIR = 4;


	public static void main(String[] args) {
		java.util.Scanner sc = new java.util.Scanner(System.in);
		
		// Creo un objeto baraja
		Baraja baraja = new Baraja();
		
		//Leo la baraja
		for (int i=1; i <= Baraja.NUM_CARTAS_BARAJA; i++){
		    Carta c = new Carta(sc.next());
			baraja.agnade(c);
		}
		sc.close();
		
		
		// Creo los jugadores
		Jugador[] jugadores = new Jugador[NUM_JUGADORES_MUS];
		for (int i=1; i <= NUM_JUGADORES_MUS; i++){
			jugadores[i-1] = new Jugador(i);
		}
		
		// Doy una mano de cartas para jugar al mus
		reparteCartasMus(baraja,jugadores);
		
		int pareja[] = calculaPuntosMusParejas(jugadores);
		
		System.out.printf("La pareja 1 ha obtenido %d puntos en esta mano.\n", pareja[0]);
		System.out.printf("La pareja 2 ha obtenido %d puntos en esta mano.\n", pareja[1]);
		
	}


	private static int[] calculaPuntosMusParejas(Jugador[] jugadores) {
		int[] pareja = new int[2];
		int[] piedrasjugador = new int[4];
		Carta[] jugador1 = jugadores[0].mano.darArrayMazo();		//nuevo array de cartas del jugador 1 con las cartas que tenia
		Carta[] jugador2 = jugadores[1].mano.darArrayMazo();		//nuevo array de cartas del jugador 2 con las cartas que tenia
		Carta[] jugador3 = jugadores[2].mano.darArrayMazo();		//nuevo array de cartas del jugador 3 con las cartas que tenia
		Carta[] jugador4 = jugadores[3].mano.darArrayMazo();		//nuevo array de cartas del jugador 4 con las cartas que tenia
	
		//ordenamos las cartas de cada jugador...
		OrdenarMazo(jugador1);		//ordena las cartas por posicion en clase numero
		OrdenarMazo(jugador2);		//ordena las cartas por posicion en clase numero
		OrdenarMazo(jugador3);		//ordena las cartas por posicion en clase numero
		OrdenarMazo(jugador4);		//ordena las cartas por posicion en clase numero
		

//estas repeticiones se usaran para la grande y la chica	
		int[] repeticioncartasJ1 = repeticionescartas(jugador1);
		int[] repeticioncartasJ2 = repeticionescartas(jugador2);
		int[] repeticioncartasJ3 = repeticionescartas(jugador3);
		int[] repeticioncartasJ4 = repeticionescartas(jugador4);
		
		int[][] repCartasJ = {repeticioncartasJ1,repeticioncartasJ2,repeticioncartasJ3, repeticioncartasJ4 } ;		//matriz

//GRANDE		
		//i jugador, j carta
		for(int j = repCartasJ[0].length -1; j >= 0; j--){		//mira primero cada carta, es decir las columnas
				if(repCartasJ[0][j] > repCartasJ[1][j] && repCartasJ[0][j] > repCartasJ[2][j] && repCartasJ[0][j] > repCartasJ[3][j]){
					piedrasjugador[0] = piedrasjugador[0] +1; break;
					
				}
				else if(repCartasJ[1][j] > repCartasJ[0][j] && repCartasJ[1][j] > repCartasJ[2][j] && repCartasJ[1][j] > repCartasJ[3][j]){
					piedrasjugador[1] = piedrasjugador[1] +1; break;
					
				}
				else if(repCartasJ[2][j] > repCartasJ[0][j] && repCartasJ[2][j] > repCartasJ[1][j] && repCartasJ[2][j] > repCartasJ[3][j]){
					piedrasjugador[2] = piedrasjugador[2] +1; break;
					
				}
				else if(repCartasJ[3][j] > repCartasJ[0][j] && repCartasJ[3][j] > repCartasJ[1][j] && repCartasJ[3][j] > repCartasJ[2][j]){
					piedrasjugador[3] = piedrasjugador[3] +1; break;
					
				}
		}
		
	
		
//CHICA
		//i jugador, j carta
		for(int j = 0; j < repCartasJ[0].length; j++){		//mira primero cada carta, es decir las columnas
				if(repCartasJ[0][j] > repCartasJ[1][j] && repCartasJ[0][j] > repCartasJ[2][j] && repCartasJ[0][j] > repCartasJ[3][j]){
					piedrasjugador[0] = piedrasjugador[0] +1; break;
				}
				else if(repCartasJ[1][j] > repCartasJ[0][j] && repCartasJ[1][j] > repCartasJ[2][j] && repCartasJ[1][j] > repCartasJ[3][j]){
					piedrasjugador[1] = piedrasjugador[1] +1; break;
				}
				else if(repCartasJ[2][j] > repCartasJ[0][j] && repCartasJ[2][j] > repCartasJ[1][j] && repCartasJ[2][j] > repCartasJ[3][j]){
					piedrasjugador[2] = piedrasjugador[2] +1; break;
				}
				else if(repCartasJ[3][j] > repCartasJ[0][j] && repCartasJ[3][j] > repCartasJ[1][j] && repCartasJ[3][j] > repCartasJ[2][j]){
					piedrasjugador[3] = piedrasjugador[3] +1; break;
				}
		}	
//  JUEGo
 
		
		int puntosJ1 = juego(jugador1);
		int puntosJ2 = juego(jugador2);
		int puntosJ3 = juego(jugador3);
		int puntosJ4 = juego(jugador4);
		
		int[] puntosJ = {puntosJ1, puntosJ2, puntosJ3, puntosJ4};
		int[] piedrasjuego = new int[NUM_JUGADORES_MUS]; 		//array donde se almacenan unicamente las piedras ganadas en pares
		
	//si nadie tiene juego(todos tienen menos de 30 puntos)...jugada al punto ( suma 1)
		if(puntosJ[0] <= 30 && puntosJ[1] <= 30 && puntosJ[2] <= 30 && puntosJ[3] <= 30 ){
			//si alguno de los jugadores empata con otro... gana la mano, el jugador anterior
			if(puntosJ[0] == puntosJ[1] || puntosJ[0] == puntosJ[2] || puntosJ[0] == puntosJ[3] || puntosJ[1] == puntosJ[2]||puntosJ[1] == puntosJ[3]|| puntosJ[2] == puntosJ[3]){
				if(puntosJ[0] == puntosJ[1] && puntosJ[0] == puntosJ[2] && puntosJ[0] == puntosJ[3]){
					piedrasjuego[0] = piedrasjuego[0] +1;
				}
				else if(puntosJ[0] == puntosJ[1] && puntosJ[0] == puntosJ[2] ){
					piedrasjuego[0] = piedrasjuego[0] +1;
				}
				else if(puntosJ[0] == puntosJ[1]  && puntosJ[0] == puntosJ[3]){
					piedrasjuego[0] = piedrasjuego[0] +1;
				}
				else if( puntosJ[0] == puntosJ[2] && puntosJ[0] == puntosJ[3]){
					piedrasjuego[0] = piedrasjuego[0] +1;
				}
				else if(puntosJ[1] == puntosJ[2] && puntosJ[1] == puntosJ[3]){
					piedrasjuego[1] = piedrasjuego[1] +1;
				}
				else if(puntosJ[0] == puntosJ[1]){
					piedrasjuego[0] = piedrasjuego[0] +1;
				}
				else if(puntosJ[0] == puntosJ[2]){
					piedrasjuego[0] = piedrasjuego[0] +1;
				}
				else if(puntosJ[0] == puntosJ[3]){
					piedrasjuego[0] = piedrasjuego[0] +1;
				}
				else if(puntosJ[1] == puntosJ[2]){
					piedrasjuego[1] = piedrasjuego[1] +1;
				}
				else if(puntosJ[1] == puntosJ[3]){
					piedrasjuego[1] = piedrasjuego[1] +1;
				}
				else if(puntosJ[2] == puntosJ[3]){
					piedrasjuego[2] = piedrasjuego[2] +1;
				}
			}
			else{		//si no hay empates
				if(puntosJ[0] > puntosJ[1] && puntosJ[0] > puntosJ[2] && puntosJ[0] > puntosJ[3]){
					piedrasjuego[0] = piedrasjuego[0] + 1;
				}
				else if(puntosJ[1] > puntosJ[0] && puntosJ[1] > puntosJ[2] && puntosJ[1] > puntosJ[3]){
					piedrasjuego[1] = piedrasjuego[1] + 1;
				}
				else if(puntosJ[2] > puntosJ[0] && puntosJ[2] > puntosJ[1] && puntosJ[2] > puntosJ[3]){
					piedrasjuego[2] = piedrasjuego[2] + 1;
				}
				else if(puntosJ[3] > puntosJ[0] && puntosJ[3] > puntosJ[1] && puntosJ[3] > puntosJ[2]){
					piedrasjuego[3] = piedrasjuego[3] + 1;
				}
			}
	//si las puntuaciones son mayores de 30...		
		}else{		
			//31
			if(puntosJ[0] == 31 || puntosJ[1] == 31 || puntosJ[2] == 31 || puntosJ[3] == 31){
				//si algun jugador empata con otro gana la mano
				if(puntosJ[0] == puntosJ[1] || puntosJ[0] == puntosJ[2] || puntosJ[0] == puntosJ[3] || puntosJ[1] == puntosJ[2]||puntosJ[1] == puntosJ[3]|| puntosJ[2] == puntosJ[3]){
					
					//con 31 suma 3 (en los que dos jugadores ganan es que son pareja)
					if(puntosJ[0] == 31 && (puntosJ[0] == puntosJ[1] && puntosJ[0] == puntosJ[2] && puntosJ[0] == puntosJ[3])){
						piedrasjuego[0] = piedrasjuego[0] +3;
						piedrasjuego[2] = piedrasjuego[2] +3;
						
					}
					else if(puntosJ[0] == 31 &&(puntosJ[0] == puntosJ[1] && puntosJ[0] == puntosJ[2] )){
						piedrasjuego[0] = piedrasjuego[0] +3;
						piedrasjuego[2] = piedrasjuego[2] +3;
						
					}
					else if(puntosJ[0] == 31 &&(puntosJ[0] == puntosJ[1]  && puntosJ[0] == puntosJ[3])){
						piedrasjuego[0] = piedrasjuego[0] +3;
					}
					else if(puntosJ[0] == 31 &&( puntosJ[0] == puntosJ[2] && puntosJ[0] == puntosJ[3])){
						piedrasjuego[0] = piedrasjuego[0] +3;
						piedrasjuego[2] = piedrasjuego[2] +3;
						
					}
					else if(puntosJ[1] == 31 &&(puntosJ[1] == puntosJ[2] && puntosJ[1] == puntosJ[3])){
						piedrasjuego[1] = piedrasjuego[1] +3;
						piedrasjuego[3] = piedrasjuego[3] +3;
						
					}
					else if(puntosJ[0] == 31 &&(puntosJ[0] == puntosJ[1])){
						piedrasjuego[0] = piedrasjuego[0] +3;
					}
					else if(puntosJ[0] == 31 &&(puntosJ[0] == puntosJ[2])){		//ya que son pareja
						piedrasjuego[0] = piedrasjuego[0] +3;
						piedrasjuego[2] = piedrasjuego[2] +3;
						
					}
					else if(puntosJ[0] == 31 &&(puntosJ[0] == puntosJ[3])){
						piedrasjuego[0] = piedrasjuego[0] +3;
					}
					else if(puntosJ[1] == 31 &&(puntosJ[1] == puntosJ[2])){
						piedrasjuego[1] = piedrasjuego[1] +3;
					}
					else if(puntosJ[1] == 31 &&(puntosJ[1] == puntosJ[3])){		//ya que son pareja
						piedrasjuego[1] = piedrasjuego[1] +3;
						piedrasjuego[3] = piedrasjuego[3] +3;
						
					}
					else if(puntosJ[2] == 31 &&(puntosJ[2] == puntosJ[3])){
						piedrasjuego[2] = piedrasjuego[2] +3;
					}
				}
				else{		//si no hay empates
					if(puntosJ[0] == 31){
						piedrasjuego[0] = piedrasjuego[0] + 3;
					}
					if(puntosJ[1] == 31){
						piedrasjuego[1] = piedrasjuego[1] + 3;
					}
					if(puntosJ[2] == 31){
						piedrasjuego[2] = piedrasjuego[2] + 3;
					}
					if(puntosJ[3] == 31){
						piedrasjuego[3] = piedrasjuego[3] + 3;
					}
				}
			}
			//32
			if(puntosJ[0] == 32 || puntosJ[1] == 32 || puntosJ[2] == 32 || puntosJ[3] == 32){
				//si algun jugador empata con otro gana la mano
				if(puntosJ[0] == puntosJ[1] || puntosJ[0] == puntosJ[2] || puntosJ[0] == puntosJ[3] || puntosJ[1] == puntosJ[2]||puntosJ[1] == puntosJ[3]|| puntosJ[2] == puntosJ[3]){
					
					if(puntosJ[0] == 32 &&(puntosJ[0] == puntosJ[1] && puntosJ[0] == puntosJ[2] && puntosJ[0] == puntosJ[3])){
						piedrasjuego[0] = piedrasjuego[0] +2;
						piedrasjuego[2] = piedrasjuego[2] +2;
						
					}
					else if(puntosJ[0] == 32 &&(puntosJ[0] == puntosJ[1] && puntosJ[0] == puntosJ[2] )){
						piedrasjuego[0] = piedrasjuego[0] +2;
						piedrasjuego[2] = piedrasjuego[2] +2;
					}
					else if(puntosJ[0] == 32 &&(puntosJ[0] == puntosJ[1]  && puntosJ[0] == puntosJ[3])){
						piedrasjuego[0] = piedrasjuego[0] +2;
					}
					else if(puntosJ[0] == 32 &&( puntosJ[0] == puntosJ[2] && puntosJ[0] == puntosJ[3])){
						piedrasjuego[0] = piedrasjuego[0] +2;
						piedrasjuego[2] = piedrasjuego[2] +2;
					}
					else if(puntosJ[1] == 32 &&(puntosJ[1] == puntosJ[2] && puntosJ[1] == puntosJ[3])){
						piedrasjuego[1] = piedrasjuego[1] +2;
						piedrasjuego[3] = piedrasjuego[3] +2;
					}
					else if(puntosJ[0] == 32 &&(puntosJ[0] == puntosJ[1])){
						piedrasjuego[0] = piedrasjuego[0] +2;
					}
					else if(puntosJ[0] == 32 &&(puntosJ[0] == puntosJ[2])){
						piedrasjuego[0] = piedrasjuego[0] +2;
						piedrasjuego[2] = piedrasjuego[2] +2;
					}
					else if(puntosJ[0] == 32 &&(puntosJ[0] == puntosJ[3])){
						piedrasjuego[0] = piedrasjuego[0] +2;
					}
					else if(puntosJ[1] == 32 &&(puntosJ[1] == puntosJ[2])){
						piedrasjuego[1] = piedrasjuego[1] +2;
					}
					else if(puntosJ[1] == 32 &&(puntosJ[1] == puntosJ[3])){
						piedrasjuego[1] = piedrasjuego[1] +2;
						piedrasjuego[3] = piedrasjuego[3] +2;
					}
					else if(puntosJ[0] == 32 &&(puntosJ[2] == puntosJ[3])){
						piedrasjuego[2] = piedrasjuego[2] +2;
					}
				}
				else{		//si no hay empates
					if(puntosJ[0] == 32){
						piedrasjuego[0] = piedrasjuego[0] + 2;
					}
					if(puntosJ[1] == 32){
						piedrasjuego[1] = piedrasjuego[1] + 2;
					}
					if(puntosJ[2] == 32){
						piedrasjuego[2] = piedrasjuego[2] + 2;
					}
					if(puntosJ[3] == 32){
						piedrasjuego[3] = piedrasjuego[3] + 2;
					}
				}
			}
			//jugadas de 40 a 32
			for(int i = 40; i > 32; i--){
					if(puntosJ[0] == i || puntosJ[1] == i || puntosJ[2] == i || puntosJ[3] == i){
						//si algun jugador empata con otro gana la mano
						if(puntosJ[0] == puntosJ[1] || puntosJ[0] == puntosJ[2] || puntosJ[0] == puntosJ[3] || puntosJ[1] == puntosJ[2]||puntosJ[1] == puntosJ[3]|| puntosJ[2] == puntosJ[3]){
							//si los puntos son igual a la iteraccion del for...
							if(puntosJ[1]== 1 && (puntosJ[0] == puntosJ[1] && puntosJ[0] == puntosJ[2] && puntosJ[0] == puntosJ[3])){
								piedrasjuego[0] = piedrasjuego[0] +2; 
								piedrasjuego[2] = piedrasjuego[2] +2;
							}
							else if(puntosJ[0] == i &&(puntosJ[0] == puntosJ[1] && puntosJ[0] == puntosJ[2]) ){
								piedrasjuego[0] = piedrasjuego[0] +2;
								piedrasjuego[2] = piedrasjuego[2] +2;
							}
							else if(puntosJ[0] == i &&(puntosJ[0] == puntosJ[1]  && puntosJ[0] == puntosJ[3])){
								piedrasjuego[0] = piedrasjuego[0] +2;
							}
							else if(puntosJ[0] == i &&( puntosJ[0] == puntosJ[2] && puntosJ[0] == puntosJ[3])){
								piedrasjuego[0] = piedrasjuego[0] +2;
								piedrasjuego[2] = piedrasjuego[2] +2;
							}
							else if(puntosJ[1] == i &&(puntosJ[1] == puntosJ[2] && puntosJ[1] == puntosJ[3])){
								piedrasjuego[1] = piedrasjuego[1] +2;
								piedrasjuego[3] = piedrasjuego[3] +2;
							}
							else if(puntosJ[0] == i &&(puntosJ[0] == puntosJ[1])){
								piedrasjuego[0] = piedrasjuego[0] +2;
							}
							else if(puntosJ[0] == i &&(puntosJ[0] == puntosJ[2])){
								piedrasjuego[0] = piedrasjuego[0] +2;
								piedrasjuego[2] = piedrasjuego[2] +2;
							}
							else if(puntosJ[0] == i &&(puntosJ[0] == puntosJ[3])){
								piedrasjuego[0] = piedrasjuego[0] +2;
							}
							else if(puntosJ[1] == i &&(puntosJ[1] == puntosJ[2])){
								piedrasjuego[1] = piedrasjuego[1] +2;
							}
							else if(puntosJ[1] == i &&(puntosJ[1] == puntosJ[3])){
								piedrasjuego[1] = piedrasjuego[1] +2;
								piedrasjuego[3] = piedrasjuego[3] +2;
							}
							else if(puntosJ[2] == i &&(puntosJ[2] == puntosJ[3])){
								piedrasjuego[2] = piedrasjuego[2] +2;
							}
						}
						else{		//si no hay empates
							if(puntosJ[0] == i && (puntosJ[0] > puntosJ[1] && puntosJ[0] > puntosJ[2] && puntosJ[0] > puntosJ[3])){
								piedrasjuego[0] = piedrasjuego[0] + 2;
							}
							if(puntosJ[1] == i && (puntosJ[1] > puntosJ[0] && puntosJ[1] > puntosJ[2] && puntosJ[1] > puntosJ[3])){
								piedrasjuego[1] = piedrasjuego[1] + 2;
							}
							if(puntosJ[2] == i &&(puntosJ[2] > puntosJ[0] && puntosJ[2] > puntosJ[1] && puntosJ[2] > puntosJ[3])){
								piedrasjuego[2] = piedrasjuego[2] + 2;
							}
							if(puntosJ[3] == i &&(puntosJ[3] > puntosJ[0] && puntosJ[3] > puntosJ[1] && puntosJ[3] > puntosJ[2])){
								piedrasjuego[3] = piedrasjuego[3] + 2;
							}
						}		//cierra else
				}			//cierra if
			}			//cierre for
		 }			//cierra else

		//cuando un jugador gana, los puntos de ambas cuentan, por lo tanto los de los otros dos jugadores se anulan
		if(piedrasjuego[0] == 3 ||piedrasjuego[2] == 3){
			piedrasjuego[1] = 0;
			piedrasjuego[3] = 0;
		}else if(piedrasjuego[1] == 3 ||piedrasjuego[3] == 3){
			piedrasjuego[0] = 0;
			piedrasjuego[1] = 0;
		}
		else if(piedrasjuego[0] == 2 ||piedrasjuego[2] == 2){
			piedrasjuego[1] = 0;
			piedrasjuego[3] = 0;
		}else if(piedrasjuego[1] == 2 ||piedrasjuego[3] == 2){
			piedrasjuego[0] = 0;
			piedrasjuego[2] = 0;
		}
		
		piedrasjugador[0] = piedrasjugador[0] + piedrasjuego[0];
		piedrasjugador[1] = piedrasjugador[1] + piedrasjuego[1];
		piedrasjugador[2] = piedrasjugador[2] + piedrasjuego[2];
		piedrasjugador[3] = piedrasjugador[3] + piedrasjuego[3];
		

//PARES
	 	int[] paresjugador1 = pares(jugador1, 1 );
	 	int[] paresjugador2 = pares(jugador2, 2 );
	 	int[] paresjugador3 = pares(jugador3, 3 );
	 	int[] paresjugador4 = pares(jugador4, 4 );
	 	
	 	/*
	 	 * Crea una matriz cuyas filas son los elementos creados en el metodo pares, en la primera columna esta el codigo de
	 	 * repeticion ( 4 = DUPLES DE 4, 3 = duples DE 2, 2 = media, 1 = pares), en la segunda columna el valor de la carta que 
	 	 * se repite, en caso de que sean dos parejas, la segunda carta se almacenaria en la tercera columna, y la ultima columna
	 	 * es el indice de jugador, ya que voy a ordenar la matriz por la segunda columna.
	 	 * 
	 	 */
	 	
	 	int[][] paresJ = {paresjugador1, paresjugador2, paresjugador3, paresjugador4};
	 	
	 	ordenarPares(paresJ);
	 	
	 		int[] puntosParesJ = new int[NUM_JUGADORES_MUS];		//almaceno los puntos de cada jugador en una vartiable separada
	 		
	 		for(int i = 0; i < NUM_JUGADORES_MUS; i++){
	 			if(paresJ[i][3] == 1 ){
	 				puntosParesJ[0] = paresJ[i][0];	// los puntos de cada jugador sera los que trnga el jugador i
	 			}else if(paresJ[i][3] == 2 ){
	 				puntosParesJ[1] = paresJ[i][0];	// los puntos de cada jugador sera los que trnga el jugador i
	 			}else if(paresJ[i][3] == 3 ){
	 				puntosParesJ[2] = paresJ[i][0];	// los puntos de cada jugador sera los que trnga el jugador i
	 			}else if(paresJ[i][3] == 4 ){
	 				puntosParesJ[3] = paresJ[i][0];	// los puntos de cada jugador sera los que trnga el jugador i
	 			}
	 		}
	 		if(paresJ[0][3] == 1 || paresJ[0][3] == 3 ){		//si el jugador en primer puesto es el uno o el tres...
	 			pareja[0] = puntosParesJ[0] + puntosParesJ[2];
	 		}
	 		else if(paresJ[0][3] == 2 || paresJ[0][3] == 4 ){
	 			pareja[1] = puntosParesJ[1] + puntosParesJ[3];
	 		}
	 		
			pareja[0] = pareja[0] + piedrasjugador[0] + piedrasjugador[2];
			pareja[1] = pareja[1] + piedrasjugador[1] + piedrasjugador[3];

		return pareja;
	
	}
	
	//ordena la matriz por la segunda columna, de mayor a menor
	private static void ordenarPares(int[][] paresJ) {
		 for(int i = 0 ; i < paresJ.length - 1 ; i++){		//vueltas que da para intercambiar(numero de filas menos 1)
	            for(int j = i + 1 ; j < paresJ.length ;j++){
	                if(paresJ[i][0] < paresJ[j][0]){
	                    //Intercambiamos filas
	                    int[] filaAuxiliar = paresJ[i];
	                    paresJ[i] = paresJ[j];
	                    paresJ[j] = filaAuxiliar;
	                 }
	                else if(paresJ[i][0] == paresJ[j][0]){
	                	if(paresJ[i][1] < paresJ[j][1]){
	                		//Intercambiamos filas
		                    int[] filaAuxiliar = paresJ[i];
		                    paresJ[i] = paresJ[j];
		                    paresJ[j] = filaAuxiliar;
	                	}
	                }
	            }
		 }
	}


	private static int[] pares(Carta[]cartas, int NumeroJugador){
		//codigo de repeticion: 4 = DUPLES DE 4, 3 = duples DE 2, 2 = media, 1 = pares
		int[] repeticiones = new int[4]; //elemento 0 codigo de repeticion, elemento 1 carta, elemento 2 carta que se repite en duples otra vez
		//y el ultimo elemento es la id del jugador, para luego crear una matriz
	
		//duples(4 iguales, o 2 parejas)
		if(cartas[0].darValorCarta() == cartas[1].darValorCarta() && cartas[0].darValorCarta() == cartas[2].darValorCarta() && cartas[0].darValorCarta() == cartas[3].darValorCarta()){
			repeticiones[0] = 4;
			repeticiones[1] = cartas[0].darValorCarta();  //el valor entre parentesis de la carta
			repeticiones[2] = 0; 
			repeticiones[3] = NumeroJugador;
		}
		else if(cartas[0].darValorCarta() == cartas[1].darValorCarta() && cartas[2].darValorCarta() == cartas[3].darValorCarta()){		//(1,1,2,2)
			repeticiones[0] = 3 ;  
			repeticiones[1] = cartas[0].darValorCarta(); //el valor entre parentesis de la carta
			repeticiones[2] = cartas[2].darValorCarta();
			repeticiones[3] = NumeroJugador;
		}
		//media(3 repetidas)
		else if(cartas[0].darValorCarta() == cartas[1].darValorCarta() && cartas[0].darValorCarta() == cartas[2].darValorCarta() && cartas[2].darValorCarta() != cartas[3].darValorCarta()){		//caso (1, 1, 1, 2)
			repeticiones[0] = 2 ;  
			repeticiones[1] = cartas[0].darValorCarta(); //el valor entre parentesis de la carta
			repeticiones[2] = 0;
			repeticiones[3] = NumeroJugador;
		}
		else if(cartas[1].darValorCarta() == cartas[2].darValorCarta() && cartas[1].darValorCarta() == cartas[3].darValorCarta() && cartas[0].darValorCarta() != cartas[2].darValorCarta()){		//caso (1, 2, 2, 2)
			repeticiones[0] = 2 ;  
			repeticiones[1] = cartas[1].darValorCarta(); //el valor entre parentesis de la carta
			repeticiones[2] = 0;
			repeticiones[3] = NumeroJugador;
		}
		//pares(1 pareja)
		else if(cartas[0].darValorCarta() == cartas[1].darValorCarta()){				//caso (1, 1, 2, 3)
			repeticiones[0] = 1; 
			repeticiones[1] = cartas[0].darValorCarta(); //el valor entre parentesis de la carta
			repeticiones[2] = 0;
			repeticiones[3] = NumeroJugador;
		}
		else if(cartas[1].darValorCarta() == cartas[2].darValorCarta()){				//caso (1, 2, 2, 3)
			repeticiones[0] = 1;
			repeticiones[1] = cartas[1].darValorCarta();  //el valor entre parentesis de la carta
			repeticiones[2] = 0;
			repeticiones[3] = NumeroJugador;
		}
		else if(cartas[2].darValorCarta() == cartas[3].darValorCarta()){				//caso (1, 2, 3, 3)
			repeticiones[0] =  1;
			repeticiones[1] = cartas[2].darValorCarta();  //el valor entre parentesis de la carta
			repeticiones[2] = 0;
			repeticiones[3] = NumeroJugador;
		}
		else{
			repeticiones[0] =  0;
			repeticiones[1] = 0;  //el valor entre parentesis de la carta
			repeticiones[2] = 0;
			repeticiones[3] = NumeroJugador;
			
		}
		return repeticiones;
	}
	
	/*
	 * Método que crea un array tamaño 10(cartas distintas que puede haber, e introduce en la posicion 0 las repeticiones del AS, en la 1 las del DOS
	 * en la 2 las del TRES...	
	 *
	 */
	
	public static int[] repeticionescartas(Carta[] cartas) {	
		int tamaño = 10;					//las cartas entran ya ordenadas
		int[] array = new int[tamaño]; //cada posicion es una carta, y dentro va el numero de veces que se repite. array[0] = AS, array[9] = REY
		Carta cartaAnterior = cartas[0];
		for(int i = 0; i < tamaño; i++){ //recorre el array de posiciones
			for(int j = 0; j < cartas.length; j++){		//recorre las cartas
					 if(cartas[j].darNumero() == 3 ){		//las repeticiones del tres las almacena en ultima posicion
						if( cartas[j].darNumero() == cartaAnterior.darNumero() ){
							array[9] = array[9]+ 1;
							}else if(cartas[j].darNumero() != cartaAnterior.darNumero() ){
								array[9] = 1;
							}
					 } else{
						 if(i < 2){		// las repeticiones del as y el dos lasa guarda donde corresponde, 
							if(cartas[j].darNumero()-1  == i && cartas[j].darNumero() == cartaAnterior.darNumero() ){
								array[i] = array[i]+ 1;
							}
							else if(cartas[j].darNumero()-1 == i && cartas[j].darNumero() != cartaAnterior.darNumero() ){
								array[i] = 1;
							}	
						 }else{			//las demas las guarda un puesto antes al que deberia
							if(cartas[j].darNumero() == i+2 && cartas[j].darNumero() == cartaAnterior.darNumero() ){
								array[i] = array[i]+ 1;
							}
							else if(cartas[j].darNumero() == i+2 && cartas[j].darNumero() != cartaAnterior.darNumero() ){
								array[i] = 1;
						}}
				}
				cartaAnterior = cartas[j];
			}	
		}
		return array;
	}
	
		private static int juego(Carta[] cartas){
		int puntos = 0;
		for(int i = 0; i < cartas.length; i++){
			if(cartas[i].darNumero() == 3 || cartas[i].darNumero() >= 8){		//si es un 3 o mayor o igual que la sota suma 10
				puntos = puntos + 10;}
			else{
			puntos = puntos + cartas[i].darValorCarta();}		//darValorCarta da el valor que esta entre parentesis en la clase numero
		}
		return puntos;
	}
	
		private static void reparteCartasMus(Baraja baraja, Jugador[] jugadores) {			//metodo que reparte las cartas

		for(int j = 0; j < NUM_CARTAS_REPARTIR; j++){
			for(int i = 0; i < NUM_JUGADORES_MUS; i++){
				jugadores[i].meteAMano(baraja.sacarCarta());	
			}
		}	
	}
		
	public static void OrdenarMazo(Carta[] cartas){
		 for(int i = 0 ; i < cartas.length - 1 ; i++){		//vueltas que da para intercambiar
	            for(int j = i + 1 ; j < cartas.length ;j++){
	                if(cartas[i].darValorCarta() > cartas[j].darValorCarta()){
	                    //Intercambiamos valores
	                    Carta variableauxiliar = cartas[i];
	                    cartas[i]=cartas[j];
	                    cartas[j]=variableauxiliar;
		
	                }
	            }
		 }
	}
}