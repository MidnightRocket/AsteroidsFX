package dk.sdu.mmmi.cbse.collisionsystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;

import java.util.Collection;
import java.util.HashSet;
import java.util.Stack;

public class CollisionDetector implements ICollisionDetectionService {
	private final Collection<CollidableEntityContainer> entities = new HashSet<>();
	private final Stack<CollidableEntityContainer> toBeAdded = new Stack<>();
	private final Stack<CollidableEntityContainer> toBeRemoved = new Stack<>();

	@Override
	public void processCollisions(final GameData gameData, final World world) {
		while (!this.toBeAdded.empty()) {
			final CollidableEntityContainer c = this.toBeAdded.pop();
			if (this.toBeRemoved.contains(c)) continue;
			this.entities.add(c);
		}

		// two for loops for all entities in the world
		for (final CollidableEntityContainer entity1 : this.entities) {
			for (final CollidableEntityContainer entity2 : this.entities) {
				// CollisionDetection
				if (entity1.doesCollideWith(entity2)) {
					entity1.getEntity().collide(world, entity2.getEntity());
				}
			}
		}

		while (!this.toBeRemoved.empty()) this.entities.remove(this.toBeRemoved.pop());
	}

	@Override
	public void addEntity(final CollidableEntity entity) {
		this.toBeAdded.add(new CollidableEntityContainer(entity));
	}

	@Override
	public void removeEntity(final CollidableEntity entity) {
		this.toBeRemoved.add(new CollidableEntityContainer(entity));
	}
}
