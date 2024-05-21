package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class BulletPlugin implements IGamePluginService {

	@Override
	public void start(final GameData gameData, final World world) {

	}

	@Override
	public void stop(final GameData gameData, final World world) {
		world.removeEntitiesByClass(Bullet.class);
	}

}
