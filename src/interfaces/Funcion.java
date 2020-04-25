package interfaces;

@FunctionalInterface
public interface Funcion<T, R> {
	
	R aplicar(T numero);
}
