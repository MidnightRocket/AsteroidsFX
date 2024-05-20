package com.github.midnightrocket.asteroidsfx.enemy;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.metadata.GameElementType;
import dk.sdu.mmmi.cbse.common.vector.BasicVector;

public class Enemy extends CommonEntity implements CollidableEntity {
	private final double speed;

	public Enemy(final double speed) {
		this.speed = speed;

		this.setPolygonCoordinates(
				new BasicVector(12, -1),
				new BasicVector(8, -1),
				new BasicVector(8, -3),
				new BasicVector(6, -3),
				new BasicVector(6, -5),
				new BasicVector(-2, -5),
				new BasicVector(-2, -7),
				new BasicVector(0, -7),
				new BasicVector(0, -9),
				new BasicVector(-10, -9),
				new BasicVector(-10, -5),
				new BasicVector(-8, -5),
				new BasicVector(-8, -3),
				new BasicVector(-6, -3),
				new BasicVector(-6, -1),
				new BasicVector(-10, -1),
				new BasicVector(-10, 1),
				new BasicVector(-6, 1),
				new BasicVector(-6, 3),
				new BasicVector(-8, 3),
				new BasicVector(-8, 5),
				new BasicVector(-10, 5),
				new BasicVector(-10, 9),
				new BasicVector(0, 9),
				new BasicVector(0, 7),
				new BasicVector(-2, 7),
				new BasicVector(-2, 5),
				new BasicVector(2, 5),
				new BasicVector(2, 1),
				new BasicVector(4, 1),
				new BasicVector(4, -1),
				new BasicVector(2, -1),
				new BasicVector(2, -3),
				new BasicVector(4, -3),
				new BasicVector(4, -1),
				new BasicVector(6, -1),
				new BasicVector(6, 1),
				new BasicVector(4, 1),
				new BasicVector(4, 3),
				new BasicVector(2, 3),
				new BasicVector(2, 5),
				new BasicVector(6, 5),
				new BasicVector(6, 3),
				new BasicVector(8, 3),
				new BasicVector(8, 1),
				new BasicVector(12, 1)
		);
	}

	@Override
	public void collide(final World world, final CollidableEntity otherEntity) {
		if (otherEntity instanceof final Bullet bullet && bullet.getShooter().getElementType() == GameElementType.ENEMY) return;
		world.removeEntity(this);
	}

	@Override
	public GameElementType getElementType() {
		return GameElementType.ENEMY;
	}

	double getSpeed() {
		return this.speed;
	}
}
