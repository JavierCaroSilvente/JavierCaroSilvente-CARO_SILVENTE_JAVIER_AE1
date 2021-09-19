//6. Escribe un método que pida 5 números enteros (sin validación de momento), los muestre 
//por consola en orden inverso y devuelva la suma de todos los números proporcionados.

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio_6 {

	public static void main(String[] args) {
		
		System.out.println("Introduce 5 números enteros:");
		System.out.println("La suma de todos los números proporcionados es: " + Calculo());
	}
	
	public static int Calculo() {
		
		List<Integer> numeros = new ArrayList<Integer>();
		int sumaNumero = 0;
		
		Scanner in = new Scanner(System.in);
		
		for(int i = 0; i < 5;i++)
			numeros.add(in.nextInt());
		
		System.out.println("Mostrando números introducidos en orden inverso...");
		
		for(int i = numeros.size()-1; i>=0;i--) {
			sumaNumero += numeros.get(i);
			System.out.println(numeros.get(i));;
		}
		
		in.close();
		
		return sumaNumero;
	}

}
