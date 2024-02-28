package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;

public class PlayerBullet extends Bullet {
	private long ttl;

	public PlayerBullet(long ttl) {
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
}
