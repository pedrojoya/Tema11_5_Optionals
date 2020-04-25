package main;

import java.util.Optional;

import clases.Flujo;


public class App_last {

	// Pseudo-main. Constructor de la clase.
	public App_last() {
		
		Optional<Integer> result = 
				Flujo.iterate(1, 5, this::quintupleMasUno)
				.findLast(this::esPar);

		if(result.isPresent()) {
			System.out.println(result.get());
		} else {
			System.out.println("No existe ningún elemento que cumpla con el predicado.");
		}
		

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
		return valor1.concat(valor2);
	}
	
	// Se llama al constructor de la clase.
	public static void main(String[] args) {
		
		Flujo<Integer> flujo
					= Flujo.of(2, 3, 4, 5, 6, 7)
					.dropWhile(valor -> valor % 2.0 == 0);
		
		System.out.println(flujo);
		new App_last();
	}
}
