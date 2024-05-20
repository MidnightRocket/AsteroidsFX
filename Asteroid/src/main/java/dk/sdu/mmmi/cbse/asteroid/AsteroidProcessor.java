package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroid.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

	private IAsteroidSplitter asteroidSplitter = new AsteroidSplitterImpl();

	@Override
	public void process(final GameData gameData, final World world) {

		for (final Entity asteroid : world.getEntitiesByClass(Asteroid.class)) {
			final double changeX = Math.cos(Math.toRadians(asteroid.getRotation()));
			final double changeY = Math.sin(Math.toRadians(asteroid.getRotation()));

			asteroid.setX(asteroid.getX() + changeX * 0.5);
			asteroid.setY(asteroid.getY() + changeY * 0.5);

			if (asteroid.getX() < 0) {
				asteroid.setX(asteroid.getX() - gameData.getDisplayWidth());
			}

			if (asteroid.getX() > gameData.getDisplayWidth()) {
				asteroid.setX(asteroid.getX() % gameData.getDisplayWidth());
			}

			if (asteroid.getY() < 0) {
				asteroid.setY(asteroid.getY() - gameData.getDisplayHeight());
			}

			if (asteroid.getY() > gameData.getDisplayHeight()) {
				asteroid.setY(asteroid.getY() % gameData.getDisplayHeight());
			}

		}

	}

	/**
	 * Dependency Injection using OSGi Declarative Services.
	 *
	 * @param asteroidSplitter The asteroid splitter logic
	 */
	public void setAsteroidSplitter(final IAsteroidSplitter asteroidSplitter) {
		this.asteroidSplitter = asteroidSplitter;
	}

	public void removeAsteroidSplitter() {
		this.asteroidSplitter = null;
	}


}
