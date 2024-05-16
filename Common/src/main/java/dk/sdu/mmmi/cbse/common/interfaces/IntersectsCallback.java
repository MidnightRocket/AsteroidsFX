package dk.sdu.mmmi.cbse.common.interfaces;

@FunctionalInterface
public interface IntersectsCallback {
	public boolean intersects(CollidableEntity entity1, CollidableEntity entity2);
}
