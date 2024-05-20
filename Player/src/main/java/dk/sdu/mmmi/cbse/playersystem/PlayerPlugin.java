package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class PlayerPlugin implements IGamePluginService {

	private Entity player;

	public PlayerPlugin() {
	}

	@Override
	public void start(GameData gameData, World world) {

		// Add entities to the world
		this.player = this.createPlayerShip(gameData);
		world.addEntity(this.player);
	}

	private Entity createPlayerShip(GameData gameData) {

		Entity playerShip = new Player();
		playerShip.setPolygonCoordinates(-5, -5, 10, 0, -5, 5);
		playerShip.setX((double) gameData.getDisplayHeight() / 2);
		playerShip.setY((double) gameData.getDisplayWidth() / 2);
		return playerShip;
	}

	@Override
	public void stop(GameData gameData, World world) {
		// Remove entities
		world.removeEntity(this.player);
	}

}
