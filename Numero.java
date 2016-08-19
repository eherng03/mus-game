
/**
 * Constantes asociadas a un número de carta de la baraja española.
 */ 
public enum Numero {
    //Ordenados de menor a mayor de forma habitual
    AS(1),
	DOS(1),
    TRES(12),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    SOTA(10),
    CABALLO(11),
    REY(12);

    private int valor;
    Numero(int valor){		//constructor para asignar el valor a cada numero
    	this.valor = valor;
    }
    
    public int darValorNumero(){		//metodo que devuelve el valor del numero
    	return valor;
    }
}

