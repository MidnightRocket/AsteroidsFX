package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.interfaces.IntersectsCallback;

public class CollisionDetector implements IPostEntityProcessingService {

	public CollisionDetector() {
	}

	@Override
	public void process(GameData gameData, World world) {
		// two for loops for all entities in the world
		for (CollidableEntity entity1 : world.getEntities(CollidableEntity.class)) {
			for (CollidableEntity entity2 : world.getEntities(CollidableEntity.class)) {

				// if the two entities are identical, skip the iteration
				if (entity1.getID().equals(entity2.getID())) {
					continue;
				}

				// CollisionDetection
				if (this.collides(entity1, entity2)) {
					entity1.collide(world, entity2);
					entity2.collide(world, entity1);
				}
			}
		}

	}

	public Boolean collides(Entity entity1, Entity entity2) {
		return false;
	}

}
