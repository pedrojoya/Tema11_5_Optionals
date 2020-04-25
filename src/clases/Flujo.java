package clases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Flujo<T> {

	private final List<T> valores;

	private Flujo(List<T> valores) {
		this.valores = valores;
	}

	public int count(Predicate<T> predicado) {
		int result = 0;

		for (T n : valores) {
			if (predicado.test(n)) {
				result++;
			}
		}
		return result;
	}
	
	public Optional<T> find(Predicate<T> predicado) {
		T result = null;
		
		for (T v : valores) {
			if (predicado.test(v)) {
				result = v;
				break;
			}
		}
		
		return result != null ? Optional.of(result) : 
								Optional.empty();
	}
	
	public Optional<T> findLast(Predicate<T> predicado) {
		T result = null;
		
		for (T v : valores) {
			if (predicado.test(v)) {
				result = v;
			}
		}
		
		return result != null ? Optional.of(result) : 
								Optional.empty();
	}

	public boolean allMatch(Predicate<T> predicado) {
		boolean result = true;

		for (T n : valores) {
			if (!predicado.test(n)) {
				result = false;
				break;
			}
		}
		return result;
	}

	public boolean noneMatch(Predicate<T> predicado) {
		boolean result = true;

		for (T n : valores) {
			if (predicado.test(n)) {
				result = false;
				break;
			}
		}
		return result;
	}

	public boolean anyMatch(Predicate<T> predicado) {
		boolean result = false;

		for (T n : valores) {
			if (predicado.test(n)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public Flujo<T> takeWhile(Predicate<T> predicado) {
		ArrayList<T> result = new ArrayList<>();

		for (T n : valores) {
			if (predicado.test(n)) {
				result.add(n);

			} else {
				break;
			}
		}
		return new Flujo<>(result);
	}

	public Flujo<T> dropWhile(Predicate<T> predicado) {
		ArrayList<T> result = new ArrayList<>();
		boolean condition = false;

		for (T n : valores) {
			if (condition || predicado.test(n)) {
				result.add(n);
				condition = true;
			}
		}
		return new Flujo<>(result);
	}

	public static <T> Flujo<T> iterate(T semilla, int count, Function<T, T> funcion) {

		if (count <= 0) {
			throw new IllegalArgumentException("El valor de count no puede ser menor o igual a 0");
		}

		List<T> result = new ArrayList<>();
		result.add(semilla);

		for (int i = 1; i < count; i++) {
			semilla = funcion.apply(semilla);
			result.add(semilla);
		}

		return new Flujo<>(result);
	}

	public static <T> Flujo<T> iterate(T enteroSemilla, int max, Predicate<T> predicado, Function<T, T> funcion) {

		if (max <= 0) {
			throw new IllegalArgumentException("El valor de max no puede ser menor o igual a 0");
		}

		List<T> result = new ArrayList<>();
		result.add(enteroSemilla);
		int count = 1;

		while (count++ < max && predicado.test(enteroSemilla = funcion.apply(enteroSemilla))) {
			result.add(enteroSemilla);
		}

		return new Flujo<>(result);
	}

	public Flujo<T> filtrar(Predicate<T> predicado) {
		List<T> result = new ArrayList<>();

		for (T v : valores) {
			if (predicado.test(v)) {
				result.add(v);
			}
		}

		return new Flujo<>(result);
	}

	public <R> Flujo<R> transformar(Function<T, R> funcion) {
		List<R> result = new ArrayList<>();

		for (T v : valores) {
			result.add(funcion.apply(v));
		}

		return new Flujo<>(result);
	}

	@SafeVarargs
	public static <T> Flujo<T> of(T... elementos) {
		return new Flujo<>(Arrays.asList(elementos));
	}

	public static <T> Flujo<T> from(List<T> c) {
		return new Flujo<T>(c);
	}

	public List<T> recolectar() {
		return valores;
	}

	public Flujo<T> actuarConIndice(BiConsumer<Integer, T> consumidor) {
		int count = 0;

		for (T e : valores) {
			consumidor.accept(count++, e);
		}

		return new Flujo<>(valores);
	}

	public void consumirConIndice(BiConsumer<Integer, T> consumidor) {
		int count = 0;

		for (T e : valores) {
			consumidor.accept(count++, e);
		}
	}

	public T reducirDesdeFinal(BiFunction<T, T, T> funcion) {

		if (valores == null || valores.size() == 0) {
			return null;
		}

		LinkedList<T> tempList = new LinkedList<T>(valores);
		T result = tempList.getLast();
		tempList.removeLast();

		if (tempList.size() > 0) {

			for (int i = tempList.size() - 1; i >= 0; i--) {
				result = funcion.apply(result, tempList.get(i));
			}
		}
		return result;
	}

	public T reducirDesdeFinal(T identidad, BiFunction<T, T, T> funcion) {

		if (valores == null || valores.size() == 0 || identidad == null) {
			return null;
		}

		LinkedList<T> tempList = new LinkedList<>(valores);
		T result = funcion.apply(identidad, tempList.getLast());
		tempList.removeLast();

		if (tempList.size() > 0) {

			for (int i = tempList.size() - 1; i >= 0; i--) {
				result = funcion.apply(result, tempList.get(i));
			}
		}
		return result;
	}

	@Override
	public String toString() {
		return valores.toString();
	}
}
