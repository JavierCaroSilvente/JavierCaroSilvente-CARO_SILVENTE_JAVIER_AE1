import java.util.Arrays;
import java.util.List;

//2. Crea un array de elementos que contenga el nombre de 6 compa�eros de clase y�
//a. Haz que se escriban por la consola en l�neas consecutivas.
//b. Haz lo mismo, pero empleando un objeto de tipo lista.

public class Ejercicio_2 {

	public static void main(String[] args) {
		
		
		//A
		System.out.println("Ejercicio A  \n");
		
		String[] compa�erosArray = new String[] {"Marcos","Ivan","David","Jose","Guillermo","Albert"};
		
		for(String compa�ero:compa�erosArray) {
			System.out.println(compa�ero);
		}
		System.out.println("\n");
		
		//B
		System.out.println("Ejercicio B  \n");
		
		List<String> compa�erosList = Arrays.asList("Antonio","Adrian","Alejandro","Alvaro","Borja","Arturo");
		
		for(String compa�ero:compa�erosList) {
			System.out.println(compa�ero);
		}

	}

}
