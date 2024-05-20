package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.asteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

/**
 * @author corfixen
 */
public class AsteroidPlugin implements IGamePluginService {

	@Override
	public void start(final GameData gameData, final World world) {
	}

	@Override
	public void stop(final GameData gameData, final World world) {
		// Remove entities
		for (final Entity asteroid : world.getEntitiesByClass(Asteroid.class)) {
			world.removeEntity(asteroid);
		}
	}
}
