package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.CommonEntity;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.metadata.GameElementType;

/**
 * @author corfixen
 */
public abstract class Asteroid extends CommonEntity implements CollidableEntity {
	@Override
	public GameElementType getElementType() {
		return GameElementType.ASTEROID;
	}
}
