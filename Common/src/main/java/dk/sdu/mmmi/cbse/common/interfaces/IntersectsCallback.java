package dk.sdu.mmmi.cbse.common.interfaces;

/**
 * A Callback which can be used to determine if two {@link CollidableEntity collidable entities} intersects.
 */
@FunctionalInterface
public interface IntersectsCallback {
	/**
	 * Called to check if two entities intersects according to the implementation.
	 *
	 * @param entity1 The first {@link CollidableEntity}.
	 * @param entity2 The second {@link CollidableEntity}.
	 * @return {@code true} if the two {@link CollidableEntity entities} intersects according to the implementation.
	 * {@code false} otherwise.
	 */
	public boolean intersects(final CollidableEntity entity1, final CollidableEntity entity2);
}