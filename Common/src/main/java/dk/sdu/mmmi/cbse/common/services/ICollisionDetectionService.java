package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;

public interface ICollisionDetectionService {
	void addEntity(final CollidableEntity entity);

	void removeEntity(final CollidableEntity entity);

	void processCollisions(final GameData gameData, final World world);

	/**
	 * Default no-op implementation provided.
	 */
	final class Default implements ICollisionDetectionService {
		@Override
		public void addEntity(final CollidableEntity entity) {
		}

		@Override
		public void removeEntity(final CollidableEntity entity) {
		}

		@Override
		public void processCollisions(final GameData gameData, final World world) {
		}
	}
}
