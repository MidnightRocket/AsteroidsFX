package dk.sdu.mmmi.cbse.common.interfaces;

import dk.sdu.mmmi.cbse.common.data.World;

public interface CollidableEntity extends Entity, GameElement {
	/**
	 * Called when entity collides with another collidable entity.
	 * Do NOT call `collide` method on `otherEntity`, this will be done automatically by CollisionProcessor
	 * `otherEntity` is provided to query which entity it has collided with.
	 *
	 * @param world
	 * @param otherEntity
	 * @return
	 */
	public void collide(World world, CollidableEntity otherEntity);
}
