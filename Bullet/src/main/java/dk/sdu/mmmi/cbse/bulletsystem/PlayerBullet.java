package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.data.World;

public class PlayerBullet extends Bullet {
	private long ttl;

	PlayerBullet(long ttl) {
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
	public void collide(World world, CollidableEntity otherEntity) {
		world.removeEntity(this);
	}
}
