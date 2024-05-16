package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.interfaces.IntersectsCallback;
import dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;

public class CollisionDetector implements ICollisionDetectionService {

	private IntersectsCallback intersectsCallback = new IntersectsCallback() {
		/*
		 * Providing default callback which returns false.
		 * This is to avoid calling method on null pointer.
		 */
		@Override
		public boolean intersects(CollidableEntity entity1, CollidableEntity entity2) {
			return false;
		}
	};

	public CollisionDetector() {
	}

	@Override
	public void process(GameData gameData, World world) {
		// two for loops for all entities in the world
		for (CollidableEntity entity1 : world.getEntities(CollidableEntity.class)) {
			for (CollidableEntity entity2 : world.getEntities(CollidableEntity.class)) {

				// if the two entities are identical, skip the iteration
				if (entity1.equals(entity2)) {
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

	public Boolean collides(CollidableEntity entity1, CollidableEntity entity2) {
		return this.intersectsCallback.intersects(entity1, entity2);
	}

	@Override
	public void setIntersectsCallback(IntersectsCallback callback) {
		this.intersectsCallback = callback;
	}
}
