package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.interfaces.IntersectsCallback;

public interface ICollisionDetectionService extends IPostEntityProcessingService {

	public void setIntersectsCallback(IntersectsCallback callback);
}
