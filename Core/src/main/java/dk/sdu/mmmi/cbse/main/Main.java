package dk.sdu.mmmi.cbse.main;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {
	public static void main(final String[] args) {
		launch();
	}

	@Override
	public void start(final Stage primaryStage) throws Exception {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ModuleConfig.class);
		final Game game = ctx.getBean(Game.class);
		game.start(primaryStage);
	}
}
