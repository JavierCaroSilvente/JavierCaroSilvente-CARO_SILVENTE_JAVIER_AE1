package es.florida.ae4_t4_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.print("Introducir IP: ");
		String host = teclado.nextLine();
		
		System.out.print("Introducir puerto: ");
		int puerto = Integer.parseInt(teclado.nextLine());
		
		System.out.println("CLIENTE >> Arranca -> esperando mensaje del servidor...");
		Socket cliente = new Socket(host,puerto); //creacion de la conexion
		
		ObjectInputStream inObject = new ObjectInputStream(cliente.getInputStream()); //recibo objeto contrase�a del servidor
		ObjPassword ObjContrase�a = (ObjPassword) inObject.readObject(); //Hago un cast del object
		
		System.out.println("CLIENTE >> Recibo del servidor: " + ObjContrase�a.getContrase�a() + " - " + ObjContrase�a.getContrase�aEncryp());
		System.out.println("CLIENTE >> Actualizar informaci�n del objeto...");
		
		System.out.print("Introducir nueva contrase�a: ");
		String contrase�a = teclado.nextLine();
		
		ObjContrase�a.setContrase�a(contrase�a); //A�adimos contrase�a plana al objeto que enviaremos al servidor
		
		Thread.sleep(1000);// pausa
		
		System.out.println("CLIENTE >> Envio al servidor la contrase�a: " + ObjContrase�a.getContrase�a() + " en texto plano.");
		ObjectOutputStream outObject = new ObjectOutputStream(cliente.getOutputStream()); //creamos el envio del object
		outObject.writeObject(ObjContrase�a); //envio del object al server
		
		InputStream is = cliente.getInputStream();//creamos recepcion de entrada de texto
		InputStreamReader isr = new InputStreamReader(is);
		
		BufferedReader bfr = new BufferedReader(isr);// buffer para lectura
		
		OutputStream os = cliente.getOutputStream(); //Output para envio del print
		
		PrintWriter pw = new PrintWriter(os); //creamos el print
		
		String opcionesDeServidor = bfr.readLine(); //
		
		System.err.println("SERVIDOR Hilo pregunta a cliente >>> " + opcionesDeServidor);
		Thread.sleep(1000);
		
		System.out.print("Introduce tu elecci�n: ");
		String opcion = teclado.nextLine();
		pw.print(opcion + "\n");
		pw.flush();

		ObjContrase�a = (ObjPassword) inObject.readObject(); //Hago un cast del object
		System.out.println("CLIENTE >> Recibo del servidor la contrase�a encryptada: " + ObjContrase�a.getContrase�aEncryp());

		inObject.close();
		outObject.close();
		cliente.close();
		
	}
	
}


   

