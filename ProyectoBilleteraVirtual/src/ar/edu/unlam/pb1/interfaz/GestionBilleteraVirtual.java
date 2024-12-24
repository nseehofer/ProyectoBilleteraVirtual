package ar.edu.unlam.pb1.interfaz;

import java.util.Scanner;

import ar.edu.unlam.pb1.dominio.BilleteraVirtual;
import ar.edu.unlam.pb1.dominio.Operacion;
import ar.edu.unlam.pb1.dominio.Usuario;
import ar.edu.unlam.pb1.dominio.enums.Monedas;
import ar.edu.unlam.pb1.interfaz.enums.MenuPrincipal;

public class GestionBilleteraVirtual {

	private static Scanner teclado = new Scanner(System.in);

	public static void main(String[] args) {

		boolean sesionInciada = false;
		BilleteraVirtual billeteraVirtual = new BilleteraVirtual();
		registrarUsuario(billeteraVirtual);

		do {
			sesionInciada = iniciarSesion(billeteraVirtual);

		} while (!sesionInciada);

		MenuPrincipal opcion = null;

		do {

			mostrarMenuPrincipal();
			opcion = ingresarOpcionDelMenuPrincipalValidada();

			switch (opcion) {
			case COMPRAR_MONEDA:
				comprarMoneda(billeteraVirtual);
				break;
			case VENDER_MONEDA:
				venderMoneda(billeteraVirtual);
				break;
			case MOSTRAR_OPERACION_MAYOR_CANTIDAD_POR_TIPO_DE_OPERACION:
				mostrarOperacionMayorCantidadPorTipoDeOperacion(billeteraVirtual);
				break;
			case MOSTRAR_OPERACIONES_POR_MONEDA:
				mostrarOperacionesPorMoneda(billeteraVirtual);
				break;
			case MOSTRAR_OEPRACION_POR_ID:
				mostrarOperacionPorId(billeteraVirtual);
				break;
			case SALIR:
				mostrarPorPantalla("\nHasta luego!");
				break;
			}

		} while (opcion != MenuPrincipal.SALIR);
	}

	private static void registrarUsuario(BilleteraVirtual billeteraVirtual) {
		Usuario usuario = new Usuario(ingresarLong("\nIngrese DNI: "), ingresarString("\nIngresar contrasenia"));
		billeteraVirtual.registrarUsuario(usuario);
		mostrarPorPantalla("\nUsuario registrado!");
	}

	private static void comprarMoneda(BilleteraVirtual billeteraVirtual) {

		double cantidadAComprar = 0;
		Monedas monedaIngresada = ingresarMoneda();

		do {
			cantidadAComprar = ingresarDouble("\nIngresar la cantidad a comprar: ");

		} while (!(cantidadAComprar > 0));

		if (billeteraVirtual.registrarOperacion(monedaIngresada, cantidadAComprar, 'c') == true) {
			mostrarPorPantalla("\n La operacion ha sido exitosa!");
		} else {
			mostrarPorPantalla("\n ERROR! La operacion no se ha realizado");
		}

	}

	private static void venderMoneda(BilleteraVirtual billeteraVirtual) {

		double cantidadAVender = 0;
		Monedas monedaIngresada = ingresarMoneda();

		do {
			cantidadAVender = ingresarDouble("\nIngresar la cantidad a vender: ");

		} while (!(cantidadAVender > 0));

		if (billeteraVirtual.hayCantidadDeMonedaSuficiente(monedaIngresada, cantidadAVender)
				&& billeteraVirtual.registrarOperacion(monedaIngresada, cantidadAVender, 'v') == true) {
			mostrarPorPantalla("\n La operacion ha sido exitosa!");
		} else {
			mostrarPorPantalla("\n ERROR! La operacion no se ha realizado");
		}

	}

	private static void mostrarOperacionMayorCantidadPorTipoDeOperacion(BilleteraVirtual billeteraVirtual) {

		char tipoDeOperacion = ingresarChar(
				"\nIngresar inicial del tipo de operacion: 'c' para COMPRAR y 'v' para VENDER ");

		Operacion operacionMayorCantidadPorTipoDeOperacion = billeteraVirtual
				.obtenerOperacionMayorCantidadPorTipoDeOperacion(tipoDeOperacion);

		if (operacionMayorCantidadPorTipoDeOperacion != null) {
			mostrarPorPantalla(operacionMayorCantidadPorTipoDeOperacion.toString());
		} else {
			mostrarPorPantalla("\nLa operacion no existe!");
		}

	}

	private static void mostrarOperacionesPorMoneda(BilleteraVirtual billeteraVirtual) {

		Monedas monedaIngresada = ingresarMoneda();

		mostrarOperaciones(billeteraVirtual.obtenerOperacionesPorMoneda(monedaIngresada));

	}

	private static void mostrarOperacionPorId(BilleteraVirtual billeteraVirtual) {

		long idIngresado = ingresarLong("\nIngresar el ID de la operacion ");

		Operacion operacionPorId = billeteraVirtual.obtenerPorId(idIngresado);

		if (operacionPorId != null) {
			mostrarPorPantalla(operacionPorId.toString());
		} else {
			mostrarPorPantalla("\nNo se ha encontrado la operacion!");
		}

	}

	private static MenuPrincipal ingresarOpcionDelMenuPrincipalValidada() {

		MenuPrincipal opcion = null;
		do {
			opcion = MenuPrincipal.values()[ingresarEntero("\nIngrese la opcion deseada ") - 1];
		} while (opcion.ordinal() < MenuPrincipal.COMPRAR_MONEDA.ordinal()
				&& opcion.ordinal() > MenuPrincipal.SALIR.ordinal());

		return opcion;
	}

	private static void mostrarMenuPrincipal() {
		String menu = "\n*****Menu Billetera virtual*****\n";
		for (int i = 0; i < MenuPrincipal.values().length; i++) {
			menu += (i + 1) + ". " + MenuPrincipal.values()[i].getDescripcion() + "\n";
		}
		mostrarPorPantalla(menu);
	}

	private static void mostrarPorPantalla(String mensaje) {
		System.out.println(mensaje);
	}

	private static boolean iniciarSesion(BilleteraVirtual billeteraVirtual) {
		long dni = ingresarEntero("\nIngrese el DNI:");
		String contrasenia = ingresarString("\nIngrese la contrasenia:");
		return billeteraVirtual.iniciarSesion(dni, contrasenia);
	}

	private static char ingresarChar(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next().charAt(0);
	}

	private static int ingresarEntero(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextInt();
	}

	private static long ingresarLong(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextLong();
	}

	private static double ingresarDouble(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.nextDouble();
	}

	private static String ingresarString(String mensaje) {
		mostrarPorPantalla(mensaje);
		return teclado.next();
	}

	private static Monedas ingresarMoneda() {
		mostrarMonedas();
		String monedaIngresada = ingresarString("\nIngrese el texto de la moneda").toUpperCase();
		return Monedas.valueOf(monedaIngresada);
	}

	private static void mostrarMonedas() {
		String monedas = "\n*****Monedas*****\n";
		for (int i = 0; i < Monedas.values().length; i++) {
			monedas += Monedas.values()[i] + "\n";
		}
		mostrarPorPantalla("\n" + monedas);
	}

	private static void mostrarOperaciones(Operacion[] operaciones) {

		for (int i = 0; i < operaciones.length; i++) {
			if (operaciones[i] != null) {
				mostrarPorPantalla("\n" + operaciones[i].toString());
			}

		}
	}
}
