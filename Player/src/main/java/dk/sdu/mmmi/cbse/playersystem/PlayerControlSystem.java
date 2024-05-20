package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.ServiceLoader;


public class PlayerControlSystem implements IEntityProcessingService {
	private final ServiceLoader<BulletSPI> bulletSPIServiceLoader = ServiceLoader.load(BulletSPI.class);

	@Override
	public void process(final GameData gameData, final World world) {

		for (final Player player : world.getEntitiesByClass(Player.class)) {
			if (gameData.getKeys().isDown(GameKeys.Key.LEFT)) {
				player.setRotation(player.getRotation() - 5);
			}
			if (gameData.getKeys().isDown(GameKeys.Key.RIGHT)) {
				player.setRotation(player.getRotation() + 5);
			}
			if (gameData.getKeys().isDown(GameKeys.Key.UP)) {
				player.move(1);
			}
			if (gameData.getKeys().isPressed(GameKeys.Key.SPACE)) {
				this.bulletSPIServiceLoader.findFirst().ifPresent(spi -> {
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
}
