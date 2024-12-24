package ar.edu.unlam.pb1.dominio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import ar.edu.unlam.pb1.dominio.enums.Monedas;

public class BilleteraVirtual {

	private static long siguienteId;
	private Usuario usuario;
	private Operacion[] operaciones;

	public BilleteraVirtual() {
		this.operaciones = new Operacion[25];

	}

	public void registrarUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public boolean iniciarSesion(long dni, String contrasenia) {

		boolean seInicioSesion = false;

		if (this.usuario.getDni() == dni && this.usuario.getContrasenia().equals(contrasenia)) {
			seInicioSesion = true;
		}

		return seInicioSesion;
	}

	public Operacion obtenerPorId(long id) {

		Operacion operacionEncontrada = null;
		boolean seEncontro = false;
		int indice = 0;
		while (indice < this.operaciones.length && !seEncontro) {
			if (this.operaciones[indice] != null && this.operaciones[indice].getId() == id) {
				operacionEncontrada = this.operaciones[indice];
				seEncontro = true;
			}
			indice++;
		}

		return operacionEncontrada;
	}

	public Operacion[] obtenerOperacionesPorMoneda(Monedas moneda) {

		Operacion operacionParaAgregarAlArray = null;
		ArrayList<Operacion> operacionesPorMonedaSinLimiteArrayList = new ArrayList<>();

		for (int i = 0; i < this.operaciones.length; i++) {
			if (this.operaciones[i] != null && this.operaciones[i].getMoneda().equals(moneda)) {
				operacionParaAgregarAlArray = this.operaciones[i];
				operacionesPorMonedaSinLimiteArrayList.add(operacionParaAgregarAlArray);
			}
		}

		Operacion[] operacionesPorMoneda = operacionesPorMonedaSinLimiteArrayList
				.toArray(new Operacion[operacionesPorMonedaSinLimiteArrayList.size()]);

		ordenarOperacionesPorIdDescendente(operacionesPorMoneda);

		return operacionesPorMoneda;
	}

	public Operacion obtenerOperacionMayorCantidadPorTipoDeOperacion(char tipoDeOperacion) {

		Operacion operacionDeMayorCantidad = null, operacionEncontrada = null;

		double mayorCantidad = 0;
		boolean seEncontro = false;
		int indice = 0, indiceParaNoAnalizar = 0;

		while (indice < this.operaciones.length && !seEncontro) {
			if (this.operaciones[indice] != null && this.operaciones[indice].getTipoDeOperacion() == tipoDeOperacion) {
				operacionDeMayorCantidad = this.operaciones[indice];
				mayorCantidad = this.operaciones[indice].getCantidad();
				indiceParaNoAnalizar = indice;
				seEncontro = true;
			}
			indice++;
		}

		for (int i = indiceParaNoAnalizar; i < this.operaciones.length; i++) {
			if (this.operaciones[i] != null && this.operaciones[i].getTipoDeOperacion() == tipoDeOperacion
					&& mayorCantidad < this.operaciones[i].getCantidad()) {
				mayorCantidad = this.operaciones[i].getCantidad();
				operacionDeMayorCantidad = this.operaciones[i];
			}

		}

		return operacionDeMayorCantidad;

	}

	public boolean hayCantidadDeMonedaSuficiente(Monedas moneda, double cantidadAVender) {

		boolean laCantidadDeMonedaEsSuficiente = false;
		double acumuladorDeMoneda = 0.0;

		for (int i = 0; i < this.operaciones.length; i++) {
			if (this.operaciones[i] != null && this.operaciones[i].getMoneda().equals(moneda)
					&& this.operaciones[i].getTipoDeOperacion() == 'c') {
				acumuladorDeMoneda += this.operaciones[i].getCantidad();
			} else if (this.operaciones[i] != null && this.operaciones[i].getMoneda().equals(moneda)
					&& this.operaciones[i].getTipoDeOperacion() == 'v') {
				acumuladorDeMoneda -= this.operaciones[i].getCantidad();
			}

			if (acumuladorDeMoneda >= cantidadAVender) {
				laCantidadDeMonedaEsSuficiente = true;
			}

		}

		return laCantidadDeMonedaEsSuficiente;
	}

	public boolean registrarOperacion(Monedas moneda, double cantidad, char tipoOperacion) {

		boolean operacionAgregadada = false, seAgregoAlArray = false;

		Operacion nuevaOperacion = new Operacion(siguienteId++, tipoOperacion, moneda, obtenerPrecioDeMoneda(moneda),
				cantidad);
		int indice = 0;

		while (indice < this.operaciones.length && !seAgregoAlArray) {
			if (this.operaciones[indice] == null) {
				this.operaciones[indice] = nuevaOperacion;
				seAgregoAlArray = true;
				operacionAgregadada = true;
			}
			indice++;
		}
		return operacionAgregadada;
	}

	private Operacion[] ordenarOperacionesPorIdDescendente(Operacion[] operaciones) {

		Operacion aux = null;

		for (int i = 0; i < operaciones.length; i++) {
			for (int j = 0; j < operaciones.length - 1; j++) {
				if (operaciones[j] != null && operaciones[j + 1] != null
						&& operaciones[j].getId() < operaciones[j + 1].getId()) {
					aux = operaciones[j + 1];
					operaciones[j + 1] = operaciones[j];
					operaciones[j] = aux;
				}
			}
		}

		return operaciones;
	}

	private double obtenerPrecioDeMoneda(Monedas moneda) {
		double precio = 0.0;

		switch (moneda) {
		case BITCOIN:
			precio = 67668.01;
			break;
		case ETHEREUM:
			precio = 3500.8;
			break;
		case PAX:
			precio = 2311;
			break;
		}

		return precio;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Operacion[] getOperaciones() {
		return operaciones;
	}

	public void setOperaciones(Operacion[] operaciones) {
		this.operaciones = operaciones;
	}

	public String toString() {
		return "BilleteraVirtual [usuario=" + usuario + ", operaciones=" + Arrays.toString(operaciones) + "]";
	}

}
