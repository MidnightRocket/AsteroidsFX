package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Defines a service which is wants process entities every game tick.
 */
public interface IEntityProcessingService {

	/**
	 * Called by the game engine every game tick.
	 * <p/>
	 * It is expected that this method can be executed many times per second.
	 * <br/>
	 * This will not be called before {@link IGamePluginService#start(GameData, World)}.
	 * And will not be called after {@link IGamePluginService#stop(GameData, World)}
	 *
	 * @param gameData The game global {@link GameData} instance.
	 * @param world    The game global {@link World} instance.
	 * @see IGamePluginService
	 */
	void process(GameData gameData, World world);
}
