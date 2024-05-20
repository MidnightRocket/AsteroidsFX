package com.github.midnightrocket.asteroidsfx.enemy;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Random;
import java.util.ServiceLoader;

public class EnemyControlSystem implements IEntityProcessingService {
	private static final Random RANDOM = new Random();
	private static final double RANDOM_ADD_THRESHOLD = 0.997;
	private static final ServiceLoader<BulletSPI> BULLET_SPI_SERVICE_LOADER = ServiceLoader.load(BulletSPI.class);

	private static Enemy createEnemy(final GameData gameData) {
		final Enemy enemy = new Enemy(1);
		enemy.setCoordinatesFrom(gameData.getRandomEdgeSpawnCoordinates());
		enemy.setRotation(RANDOM.nextDouble(360));
		return enemy;
	}

	private static void doRandomAction(final Enemy enemy, final GameData gameData, final World world) {
		final double maxRotationalDeviation = 60;

		final double currentRotation = enemy.getRotation();
		if (RANDOM.nextInt(16) == 0) {
			enemy.setRotation(RANDOM.nextDouble(currentRotation - maxRotationalDeviation, currentRotation + maxRotationalDeviation));
		}

		if (RANDOM.nextInt(8) == 0) {
			BULLET_SPI_SERVICE_LOADER.findFirst().ifPresent(s -> {
				world.addEntity(s.createBullet(enemy, gameData));
			});
		}
	}

	@Override
	public void process(final GameData gameData, final World world) {
		if (RANDOM.nextDouble() > RANDOM_ADD_THRESHOLD) {
			world.addEntity(createEnemy(gameData));
		}

		for (final Enemy enemy : world.getEntitiesByClass(Enemy.class)) {
			enemy.move(enemy.getSpeed());
			doRandomAction(enemy, gameData, world);

			if (enemy.getX() < 0) {
				enemy.setX(gameData.getDisplayWidth());
			} else if (enemy.getX() > gameData.getDisplayWidth()) {
				enemy.setX(0);
			}

			if (enemy.getY() < 0) {
				enemy.setY(gameData.getDisplayHeight());
			} else if (enemy.getY() > gameData.getDisplayHeight()) {
				enemy.setY(0);
			}
		}
	}
}
