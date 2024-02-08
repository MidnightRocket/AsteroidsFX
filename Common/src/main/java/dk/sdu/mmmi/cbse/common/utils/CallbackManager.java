package dk.sdu.mmmi.cbse.common.utils;

import dk.sdu.mmmi.cbse.common.utils.interfaces.Callback;

import java.util.ArrayList;

public class CallbackManager<T> extends ArrayList<Callback<T>> {
	public void callAll(T input) {
		for (Callback<T> e : this) {
			e.call(input);
		}
	}
}
