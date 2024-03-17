package dk.sdu.mmmi.cbse.common.data;

public interface CollidableEntity extends Entity, GameElement {
	/**
	 * Called when entity collides with another collidable entity.
	 * Do NOT call `collide` method on `otherEntity`, this will be done automatically by CollisionProcessor
	 * `otherEntity` is provided to query which entity it has collided with.
	 *
	 * @param world
	 * @param otherEntity
	 * @return
	 * @param <O>
	 */
	public <O extends CollidableEntity> boolean collide(World world, O otherEntity);
}
