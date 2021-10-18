package es.florida.multiproceso;

public class CalcularProbabilidad {
	
	//Funcion que se le pasa la posicion y la velocidad de los NEOs y te devuelve el resultado
	public double probabilidadColision(double posicionNeo, double velocidadNeo) {
		
		double posicionTierra = 1;
		double velocidadTierra = 100;
	
		for (int i = 0; i < (50 * 365 * 24 * 60 * 60); i++) {
			posicionNeo = posicionNeo + velocidadNeo * i;
		posicionTierra = posicionTierra + velocidadTierra * i;
		}
		double resultado = 100 * Math.random() *
		Math.pow( ((posicionNeo-posicionTierra)/(posicionNeo+posicionTierra)), 2);
		
		return resultado;
	}
	
	//Creo en la clase main un objeto de tipo "CalcularProbabilidad"
	//donde guardaremos en una variable llamada "resultado" el resultado obtenido del Objeto CalcularProbabilidad
	//que le pasaremos por parametros los argumentos de posicion y velocidad del Neo
	public static void main(String[] args) {
		
		CalcularProbabilidad c = new CalcularProbabilidad();
		
		double posicionNEO = Double.parseDouble(args[0]);
		double velocidadNEO = Double.parseDouble(args[1]);
		double resultado = c.probabilidadColision(posicionNEO,velocidadNEO);
		
		System.out.println(resultado);
	}

}
