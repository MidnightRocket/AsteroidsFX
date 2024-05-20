package dk.sdu.mmmi.cbse.common.bullet;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.interfaces.GameElement;

/**
 * @author corfixen
 */
public interface BulletSPI {
	Entity createBullet(final GameElement e, final GameData gameData);
}
