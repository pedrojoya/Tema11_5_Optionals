package interfaces;

@FunctionalInterface
public interface Predicado<T> {
	
	boolean test(T parametro);
}
