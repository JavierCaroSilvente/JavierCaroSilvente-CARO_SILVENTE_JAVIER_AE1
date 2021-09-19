//7. Escribe un m�todo que pida por teclado el nombre y los a�os de experiencia como 
//desarrollador de software y muestre el nivel y el salario en base al siguiente criterio:
//a. Si lleva menos de 1 a�o --> �Desarrollador Junior L1 � 15000-18000�
//b. Si lleva entre 1 y 2 a�os --> �Desarrollador Junior L2 � 18000-22000�
//c. Si lleva entre 3 y 5 a�os --> �Desarrollador Senior L1 � 22000-28000�
//d. Si lleva entre 5 y 8 a�os --> �Desarrollador Senior L2 � 28000-36000�
//e. Si lleva m�s de 8 a�os --> �Analista / Arquitecto. Salario a convenir en base a rol�

import java.util.Scanner;
public class Ejercicio_7 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Introduce el nombre: ");
		String nombre = in.next();
		
		System.out.println("Introduce los a�os de experiencia: ");
		int exp = in.nextInt();
		
		System.out.println(ObtenerExperiencia(nombre,exp));
		
		in.close();
	}
	
	public static String ObtenerExperiencia(String nombre, int a�os) {
		
		String exp = "";
		
		if(a�os < 1) {
			
			exp = nombre + " esta en el rango de Desarrollador Junior L1 � 15000-18000�";
		}
		else if(a�os >= 1 && a�os <=2) {
			exp = nombre + " esta en el rango de Desarrollador Junior L2 � 18000-22000�";
		}
		else if(a�os >= 3 && a�os <=5) {
			exp = nombre + " esta en el rango de Desarrollador Senior L1 � 22000-28000�";
		}
		else if(a�os >= 5 && a�os <=8) {
			exp = nombre + " esta en el rango de Desarrollador Senior L2 � 28000-36000�";
		}
		else if(a�os > 8) {
			exp = nombre + " esta en el rango de Analista / Arquitecto. Salario a convenir en base a rol";
		}
		
		return exp;
	}
}
