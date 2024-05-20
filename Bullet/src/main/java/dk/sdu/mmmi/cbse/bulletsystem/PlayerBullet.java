package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.metadata.GameElementType;

public class PlayerBullet extends Bullet {
	private long ttl;

	PlayerBullet(final long ttl) {
		this.ttl = ttl;
	}

	PlayerBullet() {
		this(100);
	}

	boolean isAlive() {
		return this.ttl > 0;
	}

	long decrementTtl() {
		this.ttl--;
		return this.ttl;
	}

	@Override
	public void collide(final World world, final CollidableEntity otherEntity) {
		if (otherEntity.getElementType() != GameElementType.BULLET) {
			world.removeEntity(this);
		}
	}
}
