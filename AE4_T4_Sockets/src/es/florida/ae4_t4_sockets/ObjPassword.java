package es.florida.ae4_t4_sockets;

import java.io.Serializable;

public class ObjPassword implements Serializable{

	String contraseña, contraseñaEncryp;
	
	public ObjPassword(String contraseña, String contraseñaEncryp) {
		super();
		this.contraseña = contraseña;
		this.contraseñaEncryp = contraseñaEncryp;
	}

	public ObjPassword() {
		super();
	}
	
	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String marca) {
		this.contraseña = marca;
	}

	public String getContraseñaEncryp() {
		return contraseñaEncryp;
	}

	public void setContraseñaEncryp(String modelo) {
		this.contraseñaEncryp = modelo;
	}

	@Override
	public String toString() {
		return "ObjPassword [contraseña=" + contraseña + ", contraseñaEncryp=" + contraseñaEncryp + "]";
	}

}
