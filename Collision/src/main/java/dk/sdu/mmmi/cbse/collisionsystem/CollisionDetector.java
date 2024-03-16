package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

public class CollisionDetector implements IPostEntityProcessingService {

	public CollisionDetector() {
	}

	@Override
	public void process(GameData gameData, World world) {
		// two for loops for all entities in the world
		for (Entity entity1 : world.getEntities()) {
			for (Entity entity2 : world.getEntities()) {

				// if the two entities are identical, skip the iteration
				if (entity1.getID().equals(entity2.getID())) {
					continue;
				}

				// CollisionDetection
				if (this.collides(entity1, entity2)) {
					world.removeEntity(entity1);
					world.removeEntity(entity2);
				}
			}
		}

	}

	public Boolean collides(Entity entity1, Entity entity2) {
		return false;
	}

}
