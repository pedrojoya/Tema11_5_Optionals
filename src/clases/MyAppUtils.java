package clases;

public class MyAppUtils {
	
	private MyAppUtils() {};
	
	public static int quintupleMasUno(int valor) {
		return valor * 5 + 1;
	}
	
	public static boolean esPar(int valor) {
		return valor % 2 == 0;
	}
	
	public static String conGuiones(int valor) {
		return String.format("-%d-", valor);
	}
	
	public static String concatenar(String valor1, String valor2) {
		return valor1 + valor2;
	}
}
