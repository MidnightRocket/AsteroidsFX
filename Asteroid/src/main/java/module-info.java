import com.github.midnightrocket.astroidsfx.asteroid.AsteroidControlSystem;
import com.github.midnightrocket.astroidsfx.asteroid.AsteroidPlugin;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

module Asteroid {
	requires Common;
	requires CommonAsteroid;
	requires java.net.http;
	requires CommonBullet;
	provides IGamePluginService with AsteroidPlugin;
	provides IEntityProcessingService with AsteroidControlSystem;
}