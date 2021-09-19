//3. Escribe un m�todo que sume los n�meros pares hasta el n�mero que acepta como
//par�metro y devuelva el resultado de la suma.
import java.util.Scanner;

public class Ejercicio_3 {

	public static void main(String[] args) {
		
		System.out.println("Introduce un n�mero...");
		Scanner in = new Scanner(System.in);
		
		System.out.println("La suma de los numeros pares es " + ObtenerSumaNumerosPares(in.nextInt()));
		in.close();
	}
	
	public static int ObtenerSumaNumerosPares(int numero) {
		
		int sumaNumerosPares = 0;
		
		for(int i = 0; i <= numero; i++) {
			
			if (i%2==0) {
				sumaNumerosPares += i;
			} 
		}
		return sumaNumerosPares;
	}

}
