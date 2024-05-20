package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.utils.CallbackManager;
import dk.sdu.mmmi.cbse.common.utils.interfaces.Callback;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static java.util.stream.Collectors.toUnmodifiableSet;

/**
 * @author jcs
 */
public class World {
	private final Collection<Entity> entityMap = new HashSet<>();

	private final CallbackManager<Entity> addEntityCallbacks = new CallbackManager<>();
	private final CallbackManager<Entity> removeEntityCallbacks = new CallbackManager<>();

	public boolean addEntity(final Entity entity) {
		if (!this.entityMap.add(entity)) return false;
		this.addEntityCallbacks.callAll(entity);
		return true;
	}


	public boolean removeEntity(final Entity entity) {
		if (!this.entityMap.remove(entity)) return false;
		this.removeEntityCallbacks.callAll(entity);
		return true;
	}

	public Collection<Entity> getEntities() {
		return Collections.unmodifiableCollection(this.entityMap);
	}

	/**
	 * Get a {@link Collections#unmodifiableCollection read-only Collection} containing all entities from this manager of the {@code entityType}.
	 *
	 * @param entityType A {@link Class} for the type of entities to retrieve.
	 * @param <E>        The entity type.
	 * @return A {@link Collections#unmodifiableCollection read-only Collection} with the entities of {@code entityType}.
	 */
	public final <E extends Entity> Collection<E> getEntitiesByClass(final Class<E> entityType) {
		return this.getEntities().stream().filter(entityType::isInstance).map(entityType::cast).collect(toUnmodifiableSet());
	}

	/**
	 * Remove all entities of a specific type.
	 *
	 * @param entityType The type of entities to remove.
	 * @param <E>        The entity type.
	 * @return A {@link Collection} of the removed entities.
	 */
	public <E extends Entity> Collection<E> removeEntitiesByClass(final Class<E> entityType) {
		final Collection<E> entities = this.getEntitiesByClass(entityType);
		entities.forEach(this::removeEntity);
		return entities;
	}

	public void addEntityAddedCallback(final Callback<Entity> callback) {
		this.addEntityCallbacks.add(callback);
	}

	public void addEntityRemovedCallback(final Callback<Entity> callback) {
		this.removeEntityCallbacks.add(callback);
	}
}
