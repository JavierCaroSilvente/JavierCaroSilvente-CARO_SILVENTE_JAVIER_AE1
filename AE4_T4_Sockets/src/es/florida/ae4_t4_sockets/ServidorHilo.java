package es.florida.ae4_t4_sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import es.florida.ae4_t4_sockets.ObjPassword;

public class ServidorHilo implements Runnable {

	BufferedReader bfr;
	PrintWriter pw;
	Socket socket;
	
	public ServidorHilo(Socket socket) {
		this.socket = socket;
	}
	
	public static String EncryptarASCII(String text, int rule) {
        StringBuilder encryped = new StringBuilder();
        for(Character a: text.toCharArray())
            encryped.append(Character.toChars((a+rule)%256)) ;
        return encryped.toString(); 
    }
	
	public static String EncryptarMD5(String input) {
		 try {
			 
			 MessageDigest md = MessageDigest.getInstance("MD5");
			 byte[] messageDigest = md.digest(input.getBytes());
			 BigInteger number = new BigInteger(1, messageDigest);
			 String hashtext = number.toString(16);
	
			 while (hashtext.length() < 32)
			 {
				 hashtext = "0" + hashtext;
			 }
			 
			 return hashtext;
		 }
		 catch (NoSuchAlgorithmException e)
		 {
			 throw new RuntimeException(e);
		 }
	}
	
	@Override
	public void run() {
		
		try {
			
			ObjectOutputStream outObject = new ObjectOutputStream(socket.getOutputStream()); //creamos un objeto que enviamos al cliente asociado
			
			ObjPassword ObjContraseña = new ObjPassword("Contraseña","ContraseñaEncryp"); //creamos el objeto que lleva la contraseña con y sin encriptar
			
			outObject.writeObject(ObjContraseña); //envio del objeto servidor al cliente
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() +" >> Envio a cliente: " + ObjContraseña.getContraseña() + " - " + ObjContraseña.getContraseñaEncryp());
			
			ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream()); //genero un objeto de recepcion
			ObjPassword contraseñaRellena = (ObjPassword) inObject.readObject(); // creo un nuevo objeto donde yo guardo que me llega del cliente
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() +" >> Recibo de cliente la contraseña: " + contraseñaRellena.getContraseña());

			
			PrintWriter pw = new PrintWriter(socket.getOutputStream()); // creamos un obj de printwriter para escritura en la conexion
			pw.write("Eligue una opcion de encriptado 1 --> ASCII / 2 --> MD5:" + "\n"); //hacemos la escritura y lo enviamos
			
			pw.flush(); //hacemos el vaciado del obj printwriter
			
			System.err.println("Servidor Hilo " + Thread.currentThread().getName() + " >>> Preparando canal para recibir tipo de encriptado, elección del cliente.");

			InputStream is = socket.getInputStream(); // generamos el inputstream para recepcion
			InputStreamReader isr = new InputStreamReader(is);
			
			BufferedReader bfr = new BufferedReader(isr); // generamos el bufferreader para lectura de linea			
			String resultado = bfr.readLine(); // guardamos lectura linea en var
			System.err.println("Servidor Hilo " + Thread.currentThread().getName() + " >>> Recibe opcion escogida por cliente: " + resultado); // muestro resultado
			
			String contraseñaEncriptada = "";
			String opcionEscogida = "";
			
			//switch segun eleccion tipo de encriptado
			switch(resultado)
			{ 
			   case "1":
				  contraseñaEncriptada = EncryptarASCII(contraseñaRellena.getContraseña(),1);
				  opcionEscogida = "ASCII";
			      break; 
			   
			   case "2":
				  contraseñaEncriptada = EncryptarMD5(contraseñaRellena.getContraseña());
				  opcionEscogida = "MD5";
			      break; 
			}
		
			contraseñaRellena.setContraseñaEncryp(contraseñaEncriptada);//rellenamos el objeto con la contraseña encriptada
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Encrypta contraseña de: " + contraseñaRellena.getContraseña() + " a " + contraseñaRellena.getContraseñaEncryp()
			+ " con " + opcionEscogida);
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Envia contraseña encryptada con " + opcionEscogida + " a cliente.");
			outObject.writeObject(contraseñaRellena);
			
			outObject.close();
			inObject.close();
			socket.close();
			
		} catch(IOException e) {
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >>> Error.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
