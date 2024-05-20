package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.vector.BasicVector;

public class PlayerPlugin implements IGamePluginService {

	private Entity player;

	public PlayerPlugin() {
	}

	@Override
	public void start(final GameData gameData, final World world) {

		// Add entities to the world
		this.player = this.createPlayerShip(gameData);
		world.addEntity(this.player);
	}

	private Entity createPlayerShip(final GameData gameData) {
		final Entity playerShip = new Player();
		playerShip.setPolygonCoordinates(
				new BasicVector(-5, -5),
				new BasicVector(10, 0),
				new BasicVector(-5, 5)
		);
		playerShip.setCoordinatesFrom(gameData.getCenter());
		return playerShip;
	}

	@Override
	public void stop(final GameData gameData, final World world) {
		// Remove entities
		world.removeEntity(this.player);
	}

}
