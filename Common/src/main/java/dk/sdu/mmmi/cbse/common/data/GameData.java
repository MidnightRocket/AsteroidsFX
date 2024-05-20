package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.vector.BasicVector;
import dk.sdu.mmmi.cbse.common.vector.IVector;

import java.util.Random;

public class GameData {
	private final Random random = new Random();

	private final GameKeys keys = new GameKeys();
	private final int displayStart = 0;
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
		return entity.getX() >= displayStart
				&& entity.getY() >= displayStart
				&& entity.getX() <= this.getDisplayWidth()
				&& entity.getY() <= this.getDisplayHeight();
	}

	public Random getRandom() {
		return random;
	}

	public IVector getCenter() {
		return new IVector() {
			@Override
			public double getX() {
				return GameData.this.displayWidth / 2.0;
			}

			@Override
			public double getY() {
				return GameData.this.displayHeight / 2.0;
			}
		};
	}

	public IVector getRandomEdgeSpawnCoordinates() {
		if (this.random.nextBoolean()) return getRandomSpawnFromVerticalSides();
		return getRandomSpawnFromHorizontalSides();
	}

	private IVector getRandomSpawnFromHorizontalSides() {
		if (this.random.nextBoolean()) {
			return new BasicVector(this.random.nextDouble(this.displayWidth), this.displayStart);
		}
		return new BasicVector(this.random.nextDouble(this.displayWidth), this.displayHeight);
	}

	private IVector getRandomSpawnFromVerticalSides() {
		if (this.random.nextBoolean()) {
			return new BasicVector(this.displayStart, this.random.nextDouble(this.displayHeight));
		}
		return new BasicVector(this.displayWidth, this.random.nextDouble(this.displayHeight));
	}
}
