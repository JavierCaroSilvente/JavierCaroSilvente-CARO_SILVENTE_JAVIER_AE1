//8. Escribe un método que pida por teclado 2 números como extremos de un intervalo. Luego 
//imprime por pantalla todos los números entre ese intervalo, indicando junto al número si es 
//un número primo o no. Al terminar de mostrar los números por pantalla, debe mostrar un 
//mensaje indicando el tiempo consumido en la ejecución del método.

import java.util.Scanner;

public class Ejercicio_8 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int numero1, numero2;
		
		 System.out.println("Introduce el primer número de intervalo de rango: ");
		numero1 = in.nextInt();
		 System.out.println("Introduce el segundo número de intervalo de rango: ");
		numero2 = in.nextInt();
		
		Calculo(numero1,numero2);
		
		in.close();
	}
	
	public static void Calculo(int numero1, int numero2) {

		 long inicio = System.currentTimeMillis();
		
		for(int i = numero1; i <= numero2; i++) {
			
		 boolean esPrimo = true;

            for (int j = 2; j <= Math.sqrt(i) && esPrimo; j++) {
                if (i % j == 0) {
                    esPrimo = false;
                    System.out.println(i + " NO es un número primo.");
                }
            }

            if (esPrimo) {
                System.out.println(i + " SI es un número primo.");
            }
		}
		
		 long fin = System.currentTimeMillis();
		 float sec = (fin - inicio) / 1000F; System.out.println("El metodo a tardado en ejecutarse: " + sec + " segundos.");
	}
}
