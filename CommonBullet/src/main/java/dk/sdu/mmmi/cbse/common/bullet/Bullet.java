package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.CollidableEntity;
import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.metadata.GameElementType;

import java.lang.annotation.ElementType;

/**
 *
 * @author corfixen
 */
public abstract class Bullet extends CommonEntity implements CollidableEntity {
	@Override
	public GameElementType getElementType() {
		return GameElementType.BULLET;
	}
}
