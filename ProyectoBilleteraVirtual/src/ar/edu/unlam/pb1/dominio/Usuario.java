package ar.edu.unlam.pb1.dominio;

public class Usuario {

	private long dni;
	private String contrasenia;

	public Usuario(long dni, String contrasenia) {
		this.dni = dni;
		this.contrasenia = contrasenia;
	}

	public long getDni() {
		return dni;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setDni(long dni) {
		this.dni = dni;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	
	public String toString() {
		return "Usuario [dni=" + dni + ", contrasenia=" + contrasenia + "]";
	}
	
	
	
}


