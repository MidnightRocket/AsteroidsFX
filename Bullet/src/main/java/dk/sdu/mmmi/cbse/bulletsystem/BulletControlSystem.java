package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

	private static void updateBulletPosition(Bullet bullet, int speed) {
		double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
		double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
		bullet.setX(bullet.getX() + changeX * speed);
		bullet.setY(bullet.getY() + changeY * speed);
	}

	private final int speed = 3;

	@Override
	public void process(GameData gameData, World world) {

		for (PlayerBullet bullet : world.getEntities(PlayerBullet.class)) {
			BulletControlSystem.updateBulletPosition(bullet, this.speed);
			bullet.decrementTtl();
			if (!bullet.isAlive() || !gameData.isEntityWithinFrame(bullet)) {
				world.removeEntity(bullet);
			}
		}
	}

	@Override
	public Entity createBullet(Entity shooter, GameData gameData) {
		PlayerBullet bullet = new PlayerBullet();
		bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
		bullet.setX(shooter.getX());
		bullet.setY(shooter.getY());
		bullet.setRotation(shooter.getRotation());
		BulletControlSystem.updateBulletPosition(bullet, 5);
		return bullet;
	}
}
