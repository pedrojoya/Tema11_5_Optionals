package interfaces;

@FunctionalInterface
public interface ConsumidorBinario<T> {
	void aceptar(Integer parametro1, T parametro2);
}
