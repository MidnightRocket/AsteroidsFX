package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.interfaces.GameElement;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.vector.BasicVector;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

	private final int speed = 3;

	private static void updateBulletPosition(final Bullet bullet, final int speed) {
		bullet.move(speed);
	}

	@Override
	public void process(final GameData gameData, final World world) {

		for (final PlayerBullet bullet : world.getEntitiesByClass(PlayerBullet.class)) {
			BulletControlSystem.updateBulletPosition(bullet, this.speed);
			bullet.decrementTtl();
			if (!bullet.isAlive() || !gameData.isEntityWithinFrame(bullet)) {
				world.removeEntity(bullet);
			}
		}
	}

	@Override
	public Entity createBullet(final GameElement shooter, final GameData gameData) {
		final PlayerBullet bullet = new PlayerBullet(shooter);
		bullet.setPolygonCoordinates(
				new BasicVector(1, -1),
				new BasicVector(1, 1),
				new BasicVector(-1, 1),
				new BasicVector(-1, -1)
		);
		BulletControlSystem.updateBulletPosition(bullet, 5);
		return bullet;
	}
}
