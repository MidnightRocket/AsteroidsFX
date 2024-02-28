package dk.sdu.mmmi.cbse.common.data;

public class GameData {

	private final int displayStart = 0;
	private int displayWidth = 800;
	private int displayHeight = 800;
	private final GameKeys keys = new GameKeys();


	public GameKeys getKeys() {
		return keys;
	}

	public void setDisplayWidth(int width) {
		this.displayWidth = width;
	}

	public int getDisplayWidth() {
		return displayWidth;
	}

	public void setDisplayHeight(int height) {
		this.displayHeight = height;
	}

	public int getDisplayHeight() {
		return displayHeight;
	}


	public boolean isEntityWithinFrame(Entity entity) {
		return entity.getX() >= this.displayStart &&
				entity.getY() >= this.displayStart &&
				entity.getX() <= this.getDisplayWidth() &&
				entity.getY() <= this.getDisplayHeight();
	}

}
