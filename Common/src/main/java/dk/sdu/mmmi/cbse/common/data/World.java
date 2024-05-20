package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.utils.CallbackManager;
import dk.sdu.mmmi.cbse.common.utils.interfaces.Callback;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableSet;

public class World {
	private final Map<Class<? extends Entity>, Collection<Entity>> entityClassMap = new HashMap<>();

	private final CallbackManager<Entity> addEntityCallbacks = new CallbackManager<>();
	private final CallbackManager<Entity> removeEntityCallbacks = new CallbackManager<>();

	private static <E extends Entity> Collection<E> collectStreamToEntityType(final Stream<Collection<Entity>> stream) {
		return stream.<Entity>mapMulti(Iterable::forEach).map(e -> (E) e).collect(toUnmodifiableSet());
	}

	public boolean addEntity(final Entity entity) {
		final Class<? extends Entity> entityType = entity.getClass();
		if (!this.entityClassMap.computeIfAbsent(entityType, k -> new HashSet<>()).add(entity)) return false;

		this.addEntityCallbacks.callAll(entity);
		return true;
	}

	public boolean removeEntity(final Entity entity) {
		final Class<? extends Entity> entityType = entity.getClass();
		if (!this.entityClassMap.containsKey(entityType)) return false;
		if (!this.entityClassMap.get(entityType).remove(entity)) return false;

		this.removeEntityCallbacks.callAll(entity);
		return true;
	}

	public Collection<Entity> getEntities() {
		return this.entityClassMap.values().stream().<Entity>mapMulti(Iterable::forEach).collect(toUnmodifiableSet());
	}

	/**
	 * Get a {@link Collections#unmodifiableCollection read-only Collection} containing all entities from this manager of the {@code entityType}.
	 *
	 * @param entityType A {@link Class} for the type of entities to retrieve.
	 * @param <E>        The entity type.
	 * @return A {@link Collections#unmodifiableCollection read-only Collection} with the entities of {@code entityType}.
	 */
	public final <E extends Entity> Collection<E> getEntitiesByClass(final Class<E> entityType) {
		final Stream<Collection<Entity>> stream = this.entityClassMap.entrySet().stream().filter(e -> entityType.isAssignableFrom(e.getKey())).map(Map.Entry::getValue);
		return collectStreamToEntityType(stream);
	}

	/**
	 * Remove all entities of a specific type.
	 *
	 * @param entityType The type of entities to remove.
	 * @param <E>        The entity type.
	 * @return A {@link Collection} of the removed entities.
	 */
	public <E extends Entity> Collection<E> removeEntitiesByClass(final Class<E> entityType) {
		final Collection<Class<? extends Entity>> entityTypesToRemove = this.entityClassMap.keySet().stream().filter(entityType::isAssignableFrom).collect(toUnmodifiableSet());
		return collectStreamToEntityType(entityTypesToRemove.stream().map(this.entityClassMap::remove));
	}

	public void addEntityAddedCallback(final Callback<Entity> callback) {
		this.addEntityCallbacks.add(callback);
	}

	public void addEntityRemovedCallback(final Callback<Entity> callback) {
		this.removeEntityCallbacks.add(callback);
	}
}
