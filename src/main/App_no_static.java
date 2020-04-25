package main;

import clases.Flujo;
import clases.MyAppUtils;


public class App_no_static {

	// Pseudo-main. Constructor de la clase.
	public App_no_static() {
		
		String result = 
				Flujo.iterate(1, 5, this::quintupleMasUno)
				.filtrar(this::esPar)
				.transformar(this::conGuiones)
				.reducirDesdeFinal("", this::concatenar);
		
		System.out.println(result);

	} // end app
	
	
	/* CLASES */
	public int quintupleMasUno(int valor) {
		return valor * 5 + 1;
	}
	
	public boolean esPar(int valor) {
		return valor % 2 == 0;
	}
	
	public String conGuiones(int valor) {
		return String.format("-%d-", valor);
	}
	
	public String concatenar(String valor1, String valor2) {
		return valor1 + valor2;
	}
	
	// Se llama al constructor de la clase.
	public static void main(String[] args) {
		
		Flujo<Integer> flujo
					= Flujo.of(2, 3, 4, 5, 6, 7)
					.dropWhile(valor -> valor % 2.0 == 0);
		
		System.out.println(flujo);
		new App_no_static();
	}
}
