import java.util.Arrays;
import java.util.List;

//2. Crea un array de elementos que contenga el nombre de 6 compañeros de clase y…
//a. Haz que se escriban por la consola en líneas consecutivas.
//b. Haz lo mismo, pero empleando un objeto de tipo lista.

public class Ejercicio_2 {

	public static void main(String[] args) {
		
		
		//A
		System.out.println("Ejercicio A  \n");
		
		String[] compañerosArray = new String[] {"Marcos","Ivan","David","Jose","Guillermo","Albert"};
		
		for(String compañero:compañerosArray) {
			System.out.println(compañero);
		}
		System.out.println("\n");
		
		//B
		System.out.println("Ejercicio B  \n");
		
		List<String> compañerosList = Arrays.asList("Antonio","Adrian","Alejandro","Alvaro","Borja","Arturo");
		
		for(String compañero:compañerosList) {
			System.out.println(compañero);
		}

	}

}
