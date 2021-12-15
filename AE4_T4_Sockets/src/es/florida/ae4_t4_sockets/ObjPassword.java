package es.florida.ae4_t4_sockets;

import java.io.Serializable;

public class ObjPassword implements Serializable{

	String contrase�a, contrase�aEncryp;
	
	public ObjPassword(String contrase�a, String contrase�aEncryp) {
		super();
		this.contrase�a = contrase�a;
		this.contrase�aEncryp = contrase�aEncryp;
	}

	public ObjPassword() {
		super();
	}
	
	public String getContrase�a() {
		return contrase�a;
	}

	public void setContrase�a(String marca) {
		this.contrase�a = marca;
	}

	public String getContrase�aEncryp() {
		return contrase�aEncryp;
	}

	public void setContrase�aEncryp(String modelo) {
		this.contrase�aEncryp = modelo;
	}

	@Override
	public String toString() {
		return "ObjPassword [contrase�a=" + contrase�a + ", contrase�aEncryp=" + contrase�aEncryp + "]";
	}

}
