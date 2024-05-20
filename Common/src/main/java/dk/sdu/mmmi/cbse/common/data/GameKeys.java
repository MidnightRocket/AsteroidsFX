package dk.sdu.mmmi.cbse.common.data;

public class GameKeys {
	private final boolean[] keys;
	private final boolean[] previousKeys;

	public GameKeys() {
		this.keys = new boolean[Key.values().length];
		this.previousKeys = new boolean[Key.values().length];
	}

	public void update() {
		System.arraycopy(this.keys, 0, this.previousKeys, 0, this.keys.length);
	}

	public void setKey(final Key k, final boolean state) {
		this.keys[k.ordinal()] = state;
	}

	public boolean isDown(final Key k) {
		return this.keys[k.ordinal()];
	}

	public boolean isPressed(final Key k) {
		return this.keys[k.ordinal()] && !this.previousKeys[k.ordinal()];
	}

	public enum Key {
		UP,
		LEFT,
		RIGHT,
		SPACE;
	}
}
