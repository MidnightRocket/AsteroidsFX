package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class PlayerControlSystem implements IEntityProcessingService {

	@Override
	public void process(final GameData gameData, final World world) {

		for (final Entity player : world.getEntitiesByClass(Player.class)) {
			if (gameData.getKeys().isDown(GameKeys.Key.LEFT)) {
				player.setRotation(player.getRotation() - 5);
			}
			if (gameData.getKeys().isDown(GameKeys.Key.RIGHT)) {
				player.setRotation(player.getRotation() + 5);
			}
			if (gameData.getKeys().isDown(GameKeys.Key.UP)) {
				final double changeX = Math.cos(Math.toRadians(player.getRotation()));
				final double changeY = Math.sin(Math.toRadians(player.getRotation()));
				player.move(changeX, changeY);
			}
			if (gameData.getKeys().isDown(GameKeys.Key.SPACE)) {
				this.getBulletSPIs().stream().findFirst().ifPresent(spi -> {
					world.addEntity(spi.createBullet(player, gameData));
				});
			}

			if (player.getX() < 0) {
				player.setX(1);
			}

			if (player.getX() > gameData.getDisplayWidth()) {
				player.setX(gameData.getDisplayWidth() - 1);
			}

			if (player.getY() < 0) {
				player.setY(1);
			}

			if (player.getY() > gameData.getDisplayHeight()) {
				player.setY(gameData.getDisplayHeight() - 1);
			}

		}
	}

	private Collection<? extends BulletSPI> getBulletSPIs() {
		return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}
}
