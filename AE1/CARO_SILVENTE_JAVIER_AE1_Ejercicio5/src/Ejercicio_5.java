//5. Escribe un método que acepte un array o una lista de elementos y devuelva el mayor de 
//ellos.
public class Ejercicio_5 {

	public static void main(String[] args) {
		
		int[] numeros = new int[]{ 120,55,41,27,25,12,32,8,14,10 };
		
		System.out.println("El numero mayor que hay en el array es: " + ElMayor(numeros));	
	}
	
	public static int ElMayor(int[] numeros) {
		
		int mayor = 0;
		
		for(int i = 0; i < numeros.length; i++) {
			if(numeros[i] > mayor)
				mayor = numeros[i];
		}
		return mayor;
	}

}
