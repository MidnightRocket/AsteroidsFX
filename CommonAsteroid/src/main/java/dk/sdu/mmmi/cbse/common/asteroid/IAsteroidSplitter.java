package dk.sdu.mmmi.cbse.common.asteroid;

import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;

/**
 * @author corfixen
 */
public interface IAsteroidSplitter {
	void createSplitAsteroid(final Entity e, final World w);
}
