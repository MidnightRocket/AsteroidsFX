import dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
	requires Common;
	provides ICollisionDetectionService with dk.sdu.mmmi.cbse.collisionsystem.CollisionDetector;
}