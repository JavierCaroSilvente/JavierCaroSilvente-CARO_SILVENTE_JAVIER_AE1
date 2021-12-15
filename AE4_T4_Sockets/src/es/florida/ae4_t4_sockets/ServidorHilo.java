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
			
			ObjPassword ObjContrase�a = new ObjPassword("Contrase�a","Contrase�aEncryp"); //creamos el objeto que lleva la contrase�a con y sin encriptar
			
			outObject.writeObject(ObjContrase�a); //envio del objeto servidor al cliente
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() +" >> Envio a cliente: " + ObjContrase�a.getContrase�a() + " - " + ObjContrase�a.getContrase�aEncryp());
			
			ObjectInputStream inObject = new ObjectInputStream(socket.getInputStream()); //genero un objeto de recepcion
			ObjPassword contrase�aRellena = (ObjPassword) inObject.readObject(); // creo un nuevo objeto donde yo guardo que me llega del cliente
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() +" >> Recibo de cliente la contrase�a: " + contrase�aRellena.getContrase�a());

			
			PrintWriter pw = new PrintWriter(socket.getOutputStream()); // creamos un obj de printwriter para escritura en la conexion
			pw.write("Eligue una opcion de encriptado 1 --> ASCII / 2 --> MD5:" + "\n"); //hacemos la escritura y lo enviamos
			
			pw.flush(); //hacemos el vaciado del obj printwriter
			
			System.err.println("Servidor Hilo " + Thread.currentThread().getName() + " >>> Preparando canal para recibir tipo de encriptado, elecci�n del cliente.");

			InputStream is = socket.getInputStream(); // generamos el inputstream para recepcion
			InputStreamReader isr = new InputStreamReader(is);
			
			BufferedReader bfr = new BufferedReader(isr); // generamos el bufferreader para lectura de linea			
			String resultado = bfr.readLine(); // guardamos lectura linea en var
			System.err.println("Servidor Hilo " + Thread.currentThread().getName() + " >>> Recibe opcion escogida por cliente: " + resultado); // muestro resultado
			
			String contrase�aEncriptada = "";
			String opcionEscogida = "";
			
			//switch segun eleccion tipo de encriptado
			switch(resultado)
			{ 
			   case "1":
				  contrase�aEncriptada = EncryptarASCII(contrase�aRellena.getContrase�a(),1);
				  opcionEscogida = "ASCII";
			      break; 
			   
			   case "2":
				  contrase�aEncriptada = EncryptarMD5(contrase�aRellena.getContrase�a());
				  opcionEscogida = "MD5";
			      break; 
			}
		
			contrase�aRellena.setContrase�aEncryp(contrase�aEncriptada);//rellenamos el objeto con la contrase�a encriptada
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Encrypta contrase�a de: " + contrase�aRellena.getContrase�a() + " a " + contrase�aRellena.getContrase�aEncryp()
			+ " con " + opcionEscogida);
			
			System.err.println("SERVIDOR Hilo " + Thread.currentThread().getName() + " >> Envia contrase�a encryptada con " + opcionEscogida + " a cliente.");
			outObject.writeObject(contrase�aRellena);
			
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
