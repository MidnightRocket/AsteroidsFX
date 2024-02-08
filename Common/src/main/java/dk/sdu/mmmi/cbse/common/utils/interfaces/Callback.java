package dk.sdu.mmmi.cbse.common.utils.interfaces;

@FunctionalInterface
public interface Callback<T> {
	public void call(T obj);
}
