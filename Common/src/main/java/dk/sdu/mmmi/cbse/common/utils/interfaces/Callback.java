package dk.sdu.mmmi.cbse.common.utils.interfaces;

/**
 * Represents a piece of code which can be called with a single parameter.
 *
 * @param <T> The type which this callback accepts.
 * @see dk.sdu.mmmi.cbse.common.utils.CallbackManager
 */
@FunctionalInterface
public interface Callback<T> {
	void call(final T obj);
}
