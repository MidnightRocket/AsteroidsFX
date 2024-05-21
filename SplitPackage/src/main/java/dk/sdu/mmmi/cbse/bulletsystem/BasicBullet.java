package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.interfaces.GameElement;
import dk.sdu.mmmi.cbse.common.metadata.GameElementType;

public class BasicBullet extends Bullet {
	private long ttl;

	BasicBullet(final GameElement shooter, final long ttl) {
		super(shooter);
		this.ttl = ttl;
	}

	BasicBullet(final GameElement shooter) {
		this(shooter, 200);
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
		if (otherEntity.getElementType() == GameElementType.BULLET) return;
		if (otherEntity == this.getShooter()) return;
		world.removeEntity(this);
	}
}
