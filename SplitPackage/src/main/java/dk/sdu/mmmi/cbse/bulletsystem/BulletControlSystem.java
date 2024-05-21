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

	@Override
	public void process(final GameData gameData, final World world) {

		System.out.println("Bullets from split package");
	}

	@Override
	public Entity createBullet(final GameElement shooter, final GameData gameData) {
		final BasicBullet bullet = new BasicBullet(shooter);
		bullet.setPolygonCoordinates(
				new BasicVector(1, -1),
				new BasicVector(1, 1),
				new BasicVector(-1, 1),
				new BasicVector(-1, -1)
		);
		return bullet;
	}
}
