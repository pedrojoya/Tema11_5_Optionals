package main;

import clases.Flujo;
import clases.MyAppUtils;


public class App_static {

	// Pseudo-main. Constructor de la clase.
	public App_static() {
		
		String result = 
				Flujo.iterate(1, 5, MyAppUtils::quintupleMasUno)
				.filtrar(MyAppUtils::esPar)
				.transformar(MyAppUtils::conGuiones)
				.reducirDesdeFinal("", MyAppUtils::concatenar);
		
		System.out.println(result);

	} // end app
	
	
	// Se llama al constructor de la clase.
	public static void main(String[] args) {
		
		Flujo<Integer> flujo
					= Flujo.of(2, 3, 4, 5, 6, 7)
					.dropWhile(valor -> valor % 2.0 == 0);
		
		System.out.println(flujo);
		new App_static();
	}
}
