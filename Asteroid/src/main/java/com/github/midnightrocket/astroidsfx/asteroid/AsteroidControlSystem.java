package com.github.midnightrocket.astroidsfx.asteroid;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.vector.IVector;

import java.util.Random;

public class AsteroidControlSystem implements IEntityProcessingService {
	private static Random RANDOM = new Random();

	@Override
	public void process(final GameData gameData, final World world) {
		if (Math.random() > 0.995) {
			world.addEntity(this.createAsteroid(gameData));
			System.out.println("Create asteroid");
		}


		for (final Asteroid asteroid : world.getEntitiesByClass(Asteroid.class)) {
			asteroid.move(asteroid.getSpeed());

			if (asteroid.getX() < 0) {
				asteroid.setX(gameData.getDisplayWidth());
			} else if (asteroid.getX() > gameData.getDisplayWidth()) {
				asteroid.setX(0);
			}

			if (asteroid.getY() < 0) {
				asteroid.setY(gameData.getDisplayHeight());
			} else if (asteroid.getY() > gameData.getDisplayHeight()) {
				asteroid.setY(0);
			}
		}
	}

	public Asteroid createAsteroid(final GameData gameData) {
		final Asteroid asteroid = Asteroid.create(RANDOM.nextInt(30, 70));
		asteroid.setRotation(RANDOM.nextDouble(360));
		final IVector spawn = gameData.getRandomEdgeSpawnCoordinates();
		asteroid.setCoordinatesFrom(spawn);
		return asteroid;
	}
}
