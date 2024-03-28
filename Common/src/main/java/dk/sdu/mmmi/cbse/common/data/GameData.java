package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.interfaces.Entity;

public class GameData {

	private final GameKeys keys = new GameKeys();
	private int displayWidth = 800;
	private int displayHeight = 800;

	public GameKeys getKeys() {
		return keys;
	}

	public int getDisplayWidth() {
		return displayWidth;
	}

	public void setDisplayWidth(int width) {
		this.displayWidth = width;
	}

	public int getDisplayHeight() {
		return displayHeight;
	}

	public void setDisplayHeight(int height) {
		this.displayHeight = height;
	}

	public boolean isEntityWithinFrame(Entity entity) {
		int displayStart = 0;
		return entity.getX() >= displayStart
				&& entity.getY() >= displayStart
				&& entity.getX() <= this.getDisplayWidth()
				&& entity.getY() <= this.getDisplayHeight();
	}

}
