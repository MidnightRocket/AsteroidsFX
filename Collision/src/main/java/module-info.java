import dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;

module Collision {
	requires Common;
	provides ICollisionDetectionService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
}