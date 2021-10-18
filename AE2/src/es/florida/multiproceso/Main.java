package es.florida.multiproceso;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	static int NUM_PROCESOS = Runtime.getRuntime().availableProcessors(); //Obtenemos el numero de procesos disponibles.
	
	//Este metodo ejecuta el metodo de la clase "CalcularProbabilidad que le enviaremos a traves de un comando
	// y redireccionaremos la salida del resultado en un fichero y en el contenido del fichero su resultado
	public static void calcularProbabilidad(String fichResultados, String posicionNeo, String velocidadNEO) throws IOException{
		
		String clase = "es.florida.multiproceso.CalcularProbabilidad";
		File directoriocalcularProbabilidad;
		directoriocalcularProbabilidad = new File("C:\\Users\\xavic\\Desktop\\Eclipse\\AE02_T2\\src\\es\\florida\\multiproceso");
		File fichResultado = new File(fichResultados + ".txt");
		
		try {
			
			String javaHome = System.getProperty("java.home");
			String javaBin = javaHome + File.separator + "bin" + File.separator + "java";
			String classpath = System.getProperty("java.class.path");
			String className = clase;
			
			List<String> command = new ArrayList<>(); //Lista donde almacenamos el commando a ejecutar en el processo
			command.add(javaBin);
			command.add("-cp");
			command.add(classpath);
			command.add(className);
			command.add(String.valueOf(posicionNeo));
			command.add(String.valueOf(velocidadNEO));
			
			ProcessBuilder builder = new ProcessBuilder(command); //creamos el objeto builder que le pasaremos el comando
			
			builder.directory(directoriocalcularProbabilidad);//le pasamos al builder el directorio de trabajo
			builder.redirectOutput(fichResultado); //le pasamos a builder el fichResultado para que nos cree el fichero en el directorio con el resultado saliente.
			
			Process process = builder.start();//Ejecutamos el proceso.
			process.waitFor();//Añadimos un tiempo de espera a que finalize el proceso para poder asegurar tener el documento escrito.
			getResultadoFichero(fichResultados);//Llamamos al metodo que lee los ficheros pasandole por parametro el fichero deseado.
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	//Este metodo al que le pasamos por parametro el nombre del fichero leera el fichero y segun el resultado pasara por pantalla un mensaje u otro.
	public static void getResultadoFichero(String nombreFichero) throws InterruptedException {
		
		  double probabilidad = 0;
		
		try {
			
			FileInputStream fichero = new FileInputStream(nombreFichero + ".txt");
			
			InputStreamReader isr = new InputStreamReader(fichero);
			BufferedReader br = new BufferedReader(isr);
			
			String linea = br.readLine();
			probabilidad = Double.valueOf(linea);
			
			br.close();
			
			if(probabilidad > 10) {
				
				System.err.println( "¡ALERTA MUNDIAL! " + nombreFichero + " tiene una probabilidad del " + String.format("%.2f",probabilidad) + "% de impactar contra la tierra.");
				
			}else{
				
				System.out.println( nombreFichero + " tiene una probabilidad del " + String.format("%.2f",probabilidad) + "% de impactar contra la tierra. !PODREMOS VIVIR ALGO MÁS!");
			}
			
		    } catch(FileNotFoundException e) {
		    	System.out.println("No se puede abrir " + nombreFichero);
		    } catch(IOException e) {
		    	System.out.println("No hay nada en " + nombreFichero);
		    }
	}
	

	//Este metodo obtiene por parametro el nombre del fichero y la linea que queremos leer
	//para que nos devuelva una lista que segun al indice que accedamos obtendremos una informacion.
	public static List<String> obtenerInformacionNEO(String nombreFichero , int numLinea) {
		
		//Creo una lista para cada uno de los datos que obtendremos del fichero separado por comas
		List<String> nombresNEO = new ArrayList<String>();
		List<String> posicionNEO = new ArrayList<String>();
		List<String> velocidadNEO = new ArrayList<String>();
		
		List<String> NEO = new ArrayList<String>();// Esta es la lista final que devolveremos con los resultados
		
		int totalLineas = 1; //Inicializamos el total de lineas que tiene el documento.
		
		try {
			
				//Creamos un FileInputStream y le pasamos el fichero para proceder a su lectura por el buffer
				FileInputStream fichero = new FileInputStream(nombreFichero);
				InputStreamReader isr = new InputStreamReader(fichero);
				BufferedReader br = new BufferedReader(isr);
				
				String linea = br.readLine();// Guardamos en una variable la linea leida.
				
				String[] primeraLinea = linea.split(","); //Almacenamos en un array los elementos separados por comas.
	
				nombresNEO.add(primeraLinea[0]); //Guardamos en la lista de nombre el indice 0 que corresponde al nombre ¡Primera linea del documento!.
				posicionNEO.add(primeraLinea[1]);  //Guardamos en la lista la posicion en el indice 1 que corresponde a la posicion ¡Primera linea del documento!.
				velocidadNEO.add(primeraLinea[2]); //Guardamos en la lista la velocidad en el indice 2 que corresponde a la velocidad ¡Primera linea del documento!.
	
				
				while((linea = br.readLine()) != null) { //Leemos el resto de lineas del documento mientras hayan lineas que leer.
					
					String[] restoLineas = linea.split(","); //Almacenamos en un array los elementos separados por comas.
	
						nombresNEO.add(restoLineas[0]); //Guardamos en la lista de nombre el indice 0 que corresponde al nombre.
						posicionNEO.add(restoLineas[1]); //Guardamos en la lista la posicion en el indice 1 que corresponde a la posicion.
						velocidadNEO.add(restoLineas[2]); //Guardamos en la lista la velocidad en el indice 2 que corresponde a la velocidad.
						
						totalLineas++; //Por cada linea almacenada sumamos 1 , de esta manera tendremos el total de lineas del documento.
				}
				
				for(int i = 0; i<= totalLineas; i++) { //Recorremos con un For la cantidad de lineas que tiene el documento analizado
					
					if(numLinea < totalLineas) { //Este if evita que nos salgamos del array en el caso de que entre una linea por encima del total del lineas del documento analizado.
						
						if(numLinea == i) { //Si el numero de linea pasado por parametro coincide con la linea del documento...
							
							NEO.add(nombresNEO.get(i)); //Almacenamos la informacion en la lista NEO
							NEO.add(posicionNEO.get(i));
							NEO.add(velocidadNEO.get(i));
						}
					}
				}
				
				if(numLinea < totalLineas)//Este if evita que nos salgamos del array en el caso de que entre una linea por encima del total del lineas del documento analizado.
				NEO.add(3,String.valueOf(totalLineas)); //Capturamos el total de lineas del documento en el indice 3 de la lista
				
				br.close();
		    } 
			catch(IOException e)
			{
		    	System.out.println("No hay nada en " + nombreFichero);
		    }
		
		return NEO; //Devolvemos la Lista con la informacion solicitada de esta linea especifica del documento.
	}
	
	public static void main(String[] args) throws IOException, InterruptedException  {
		
		//Inicios de los procesos
		long tiempoInicioTotal = System.nanoTime(); 
		long tiempoInicioBloque = System.nanoTime();
		
		int resultadoTiempoMedioPorNeo = 0;
		long tiempoBloqueNEOsAnterior = 0;
		
		
		int lineasLeidas = 0;
		int totalLineas = 1;
		
		//Almacenamos en la lista la informacion devuelta por el metodo "obtenerInformacionNEO"
		//pasandole el nombre del archivo y el numero de la linea que queremos obtener info.
		List<String> currentNEO = obtenerInformacionNEO("NEOs.txt", 0); 
		
		totalLineas = Integer.parseInt(currentNEO.get(3)); // en el indice 3 teniamos el total de las lineas del documento y las almacenamos.
		
		for(int i = 0; i < NUM_PROCESOS; i++ ) { //Bucle que recorre los procesos
			
			long tiempoMedioInicioNEOs = System.nanoTime();
			
			currentNEO = obtenerInformacionNEO("NEOs.txt", i); //Obtenemos la informacion del neo segun la linea que le pasamos en el segundo parametro que coincida con [i]
				
			try {
				
				//0 = nombreFichero  1 = posicionNEO  2 = velocidadNEO
				calcularProbabilidad(currentNEO.get(0),currentNEO.get(1),currentNEO.get(2));//Calculamos la probabilidad del NEO especifico obtenido en currentNEO 
				
				lineasLeidas++; //Incrementamos en 1 las lineas ya procesadas del documento.
				
			} catch (Exception e) {
				//Nada
			}
			
		long tiempoMedioFinNEOs = System.nanoTime();
		long duracionUnico = (tiempoMedioFinNEOs - tiempoMedioInicioNEOs)/1000000; //milisegundos
		
		resultadoTiempoMedioPorNeo += duracionUnico;
		
		//Este "if" se encarga de asegurarse que en el caso de que queden lineas por procesar
		//cuando los procesos hayan acabado, vuelva a empezar hasta leer todo el fichero.
		if( i == NUM_PROCESOS - 1 && i < totalLineas) { 
			
		NUM_PROCESOS += Runtime.getRuntime().availableProcessors();
		
			if(lineasLeidas <= totalLineas) {
				
				long tiempoFinBloque = System.nanoTime();
				long duracion = (tiempoFinBloque - tiempoInicioBloque)/1000000; //milisegundos
				
				System.out.println("\nTiempo ejecucion total este bloque de NEOs: " + (duracion - tiempoBloqueNEOsAnterior) + " ms");
				tiempoBloqueNEOsAnterior = duracion;
				
				
				System.out.println("Tiempo ejecucion Medio por NEO de este bloque: " + (resultadoTiempoMedioPorNeo / NUM_PROCESOS) + " ms");
				System.out.println("Un total de " + Runtime.getRuntime().availableProcessors() + " NEOs leidos en este bloque. \n");
				
				i = lineasLeidas -1;
			}
		} 
	}	
		
		long tiempoFinTotal = System.nanoTime();
		
		long duracionTotal = (tiempoFinTotal - tiempoInicioTotal)/1000000; //milisegundos
		
		System.out.println("\n Tiempo ejecucion total DE TODOS LOS NEOs: " + duracionTotal + " ms");
	}
}
