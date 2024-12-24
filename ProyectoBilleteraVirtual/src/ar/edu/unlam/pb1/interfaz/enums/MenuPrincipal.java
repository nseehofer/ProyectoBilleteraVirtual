package ar.edu.unlam.pb1.interfaz.enums;

public enum MenuPrincipal {
	COMPRAR_MONEDA("Comprar moneda"), 
	VENDER_MONEDA("Vender Moneda"),
	MOSTRAR_OPERACION_MAYOR_CANTIDAD_POR_TIPO_DE_OPERACION("Mostrar operacion de mayor cantidad por tipo de operacion"),
	MOSTRAR_OPERACIONES_POR_MONEDA("Mostrar operaciones por moneda"),
	MOSTRAR_OEPRACION_POR_ID("Mostrar operacion por su ID"),
	SALIR("Salir");

	private String descripcion;

	MenuPrincipal(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
}
