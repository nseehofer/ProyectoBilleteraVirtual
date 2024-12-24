package ar.edu.unlam.pb1.dominio;

import ar.edu.unlam.pb1.dominio.enums.Monedas;

public class Operacion {

	private long id = 0;
	private char tipoDeOperacion; 
	private Monedas moneda;
	private double precio;
	private double cantidad;

	public Operacion(long id, char tipoDeOperacion, Monedas moneda, double precio, double cantidad) {
		
		this.id = id;
		this.tipoDeOperacion = tipoDeOperacion;
		this.moneda = moneda;
		this.precio = precio;
		this.cantidad = cantidad;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public char getTipoDeOperacion() {
		return tipoDeOperacion;
	}

	public void setTipoDeOperacion(char tipoDeOperacion) {
		this.tipoDeOperacion = tipoDeOperacion;
	}

	public Monedas getMoneda() {
		return moneda;
	}

	public void setMoneda(Monedas moneda) {
		this.moneda = moneda;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public String toString() {
		return "Operacion [id=" + id + ", tipoDeOperacion=" + tipoDeOperacion + ", moneda=" + moneda + ", precio="
				+ precio + ", cantidad=" + cantidad + "]";
	}

}
