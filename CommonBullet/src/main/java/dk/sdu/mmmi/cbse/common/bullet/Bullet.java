package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.interfaces.GameElement;
import dk.sdu.mmmi.cbse.common.metadata.GameElementType;

/**
 * @author corfixen
 */
public abstract class Bullet extends CommonEntity implements CollidableEntity {
	private final GameElement shooter;

	protected Bullet(final GameElement shooter) {
		this.shooter = shooter;
		this.setCoordinatesFrom(shooter.getCoordinates());
		this.setRotation(shooter.getRotation());
	}

	public GameElement getShooter() {
		return this.shooter;
	}

	@Override
	public GameElementType getElementType() {
		return GameElementType.BULLET;
	}
}
