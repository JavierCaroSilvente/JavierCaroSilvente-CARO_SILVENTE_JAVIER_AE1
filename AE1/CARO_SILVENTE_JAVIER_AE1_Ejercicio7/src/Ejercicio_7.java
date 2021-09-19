//7. Escribe un método que pida por teclado el nombre y los años de experiencia como 
//desarrollador de software y muestre el nivel y el salario en base al siguiente criterio:
//a. Si lleva menos de 1 año --> “Desarrollador Junior L1 – 15000-18000”
//b. Si lleva entre 1 y 2 años --> “Desarrollador Junior L2 – 18000-22000”
//c. Si lleva entre 3 y 5 años --> ”Desarrollador Senior L1 – 22000-28000”
//d. Si lleva entre 5 y 8 años --> “Desarrollador Senior L2 – 28000-36000”
//e. Si lleva más de 8 años --> “Analista / Arquitecto. Salario a convenir en base a rol”

import java.util.Scanner;
public class Ejercicio_7 {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Introduce el nombre: ");
		String nombre = in.next();
		
		System.out.println("Introduce los años de experiencia: ");
		int exp = in.nextInt();
		
		System.out.println(ObtenerExperiencia(nombre,exp));
		
		in.close();
	}
	
	public static String ObtenerExperiencia(String nombre, int años) {
		
		String exp = "";
		
		if(años < 1) {
			
			exp = nombre + " esta en el rango de Desarrollador Junior L1 – 15000-18000€";
		}
		else if(años >= 1 && años <=2) {
			exp = nombre + " esta en el rango de Desarrollador Junior L2 – 18000-22000€";
		}
		else if(años >= 3 && años <=5) {
			exp = nombre + " esta en el rango de Desarrollador Senior L1 – 22000-28000€";
		}
		else if(años >= 5 && años <=8) {
			exp = nombre + " esta en el rango de Desarrollador Senior L2 – 28000-36000€";
		}
		else if(años > 8) {
			exp = nombre + " esta en el rango de Analista / Arquitecto. Salario a convenir en base a rol";
		}
		
		return exp;
	}
}
