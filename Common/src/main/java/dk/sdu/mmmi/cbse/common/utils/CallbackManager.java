package dk.sdu.mmmi.cbse.common.utils;

import dk.sdu.mmmi.cbse.common.utils.interfaces.Callback;

import java.util.ArrayList;

/**
 * Manages multiple {@link Callback callbacks}.
 *
 * @param <T> The type which the {@link Callback} should accept.
 * @see Callback
 */
public class CallbackManager<T> extends ArrayList<Callback<T>> {
	public void callAll(final T input) {
		for (final Callback<T> e : this) {
			e.call(input);
		}
	}
}
