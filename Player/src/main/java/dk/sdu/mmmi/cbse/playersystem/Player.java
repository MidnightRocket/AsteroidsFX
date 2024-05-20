package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.metadata.GameElementType;

public class Player extends CommonEntity implements CollidableEntity {

	@Override
	public void collide(final World world, final CollidableEntity otherEntity) {
		if (otherEntity instanceof final Bullet bullet && bullet.getShooter() == this) return;
		world.removeEntity(this);
	}

	@Override
	public GameElementType getElementType() {
		return GameElementType.PLAYER;
	}
}
