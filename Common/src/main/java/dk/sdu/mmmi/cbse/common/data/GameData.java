package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.interfaces.Entity;

public class GameData {

	private final GameKeys keys = new GameKeys();
	private int displayWidth = 800;
	private int displayHeight = 800;

	public GameKeys getKeys() {
		return this.keys;
	}

	public int getDisplayWidth() {
		return this.displayWidth;
	}

	public void setDisplayWidth(final int width) {
		this.displayWidth = width;
	}

	public int getDisplayHeight() {
		return this.displayHeight;
	}

	public void setDisplayHeight(final int height) {
		this.displayHeight = height;
	}

	public boolean isEntityWithinFrame(final Entity entity) {
		final int displayStart = 0;
		return entity.getX() >= displayStart
				&& entity.getY() >= displayStart
				&& entity.getX() <= this.getDisplayWidth()
				&& entity.getY() <= this.getDisplayHeight();
	}

}
