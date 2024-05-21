import dk.sdu.mmmi.cbse.common.graphics.label.LabelProvider;
import dk.sdu.mmmi.cbse.main.javafxbindings.JavaFxLabelProvider;

module Core {
	requires Common;
	requires javafx.graphics;
	requires javafx.controls;
	opens dk.sdu.mmmi.cbse.main to javafx.graphics;
	uses dk.sdu.mmmi.cbse.common.services.IGamePluginService;
	uses dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
	uses dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;
	provides LabelProvider with JavaFxLabelProvider;
}


