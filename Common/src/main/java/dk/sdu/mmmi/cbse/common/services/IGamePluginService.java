package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

/**
 * Defines a game plugin which wants to get notified when the game starts and stops.
 */
public interface IGamePluginService {

	/**
	 * Called by the game engine when a game is started.
	 * <p/>
	 * It is guaranteed that this will be called before any provided {@link IEntityProcessingService#process(GameData, World)}.
	 * <br/>
	 * This will be called exactly once per game session, and will always be called before {@link #stop(GameData, World)}.
	 *
	 * @param gameData The game global {@link GameData} instance.
	 * @param world    The game global {@link World} instance.
	 * @see IEntityProcessingService#process(GameData, World)
	 * @see #stop(GameData, World)
	 */
	void start(final GameData gameData, final World world);

	/**
	 * Called by the game engine when the game stops cleanly.
	 * <p/>
	 * It is guaranteed that any provided {@link IEntityProcessingService#process(GameData, World)}, will not be
	 * called after this, unless a new game session is started, which will result in {@link #start(GameData, World)} being called.
	 * <br/>
	 * This method will never be called if the game halts unexpectedly.
	 * This may be due to uncaught {@link RuntimeException}, or external system events.
	 *
	 * @param gameData The game global {@link GameData} instance.
	 * @param world    The game global {@link World} instance.
	 * @see #start(GameData, World)
	 * @see IEntityProcessingService#process(GameData, World)
	 */
	void stop(final GameData gameData, final World world);
}