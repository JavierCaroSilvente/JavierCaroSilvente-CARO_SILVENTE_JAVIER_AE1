//4. Escribe el c�digo necesario para calcular el factorial del n�mero 15, en menos de 5
//instrucciones

public class Ejercicio_4 {

	public static void main(String[] args) {
		
		long numero = 15;
		long Factorial = 1;
		
		for (int i=1;i<=numero;i++)
			Factorial *= i;
			 
		System.out.println("El factorial de " + numero + " es " + Factorial);
	}

}
