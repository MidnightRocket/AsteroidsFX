package com.github.midnightrocket.astroidsfx.asteroid;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {
	private static Random RANDOM = new Random();
	private final double speed = 10;

	@Override
	public void process(final GameData gameData, final World world) {
		if (Math.random() > 0.995) {
			world.addEntity(this.createAsteroid());
			System.out.println("Create asteroid");
		}


		for (final Asteroid asteroid : world.getEntitiesByClass(Asteroid.class)) {
			final boolean withinFrameBefore = gameData.isEntityWithinFrame(asteroid);

			asteroid.move(asteroid.getSpeed());

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

			final boolean withinFrameAfter = gameData.isEntityWithinFrame(asteroid);

			if (!withinFrameBefore && !withinFrameAfter) {
				world.removeEntity(asteroid);
				System.out.println("Asteroid out of frame. Removed");
			}
		}
	}

	public Asteroid createAsteroid() {
		final Asteroid asteroid = Asteroid.create(RANDOM.nextInt(30, 70));
		asteroid.setRotation(Math.random() * Math.PI * 2);
		asteroid.setX(100);
		asteroid.setY(100);
		return asteroid;
	}
}
