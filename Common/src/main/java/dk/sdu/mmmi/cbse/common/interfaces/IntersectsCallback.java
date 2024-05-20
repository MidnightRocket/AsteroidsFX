package dk.sdu.mmmi.cbse.common.interfaces;

/**
 * TODO
 */
@FunctionalInterface
public interface IntersectsCallback {
	/**
	 * TODO
	 *
	 * @param entity1
	 * @param entity2
	 * @return
	 */
	public boolean intersects(CollidableEntity entity1, CollidableEntity entity2);
}