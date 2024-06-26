package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.main.javafxbindings.JavaFxLabelProvider;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

public class Main extends Application {

	private final GameData gameData = new GameData();
	private final World world = new World();
	private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
	private final Pane gameWindow = new Pane();


	private final Collection<IGamePluginService> gamePluginServices;
	private final Collection<IEntityProcessingService> entityProcessingServices;
	private final ICollisionDetectionService collisionDetectionService;

	public Main() {
		this.gamePluginServices = loadServices(IGamePluginService.class);
		this.entityProcessingServices = loadServices(IEntityProcessingService.class);
		this.collisionDetectionService = ServiceLoader.load(ICollisionDetectionService.class).findFirst().orElse(new ICollisionDetectionService.Default());
	}

	private static <S> Collection<S> loadServices(final Class<S> serviceClass) {
		return ServiceLoader.load(serviceClass).stream().map(ServiceLoader.Provider::get).toList();
	}

	public static void main(final String[] args) {
		launch();
	}

	@Override
	public void start(final Stage window) {
		this.gameWindow.setPrefSize(this.gameData.getDisplayWidth(), this.gameData.getDisplayHeight());

		final Scene scene = this.getScene();

		JavaFxLabelProvider.setRoot(this.gameWindow);

		this.world.addEntityAddedCallback(entity -> {
			if (entity instanceof final CollidableEntity collidableEntity) {
				this.getCollisionDetectionService().addEntity(collidableEntity);
			}

			final Polygon polygon = new Polygon(entity.getPolygonCoordinatesValues());
			this.polygons.put(entity, polygon);
			this.gameWindow.getChildren().add(polygon);
		});

		this.world.addEntityRemovedCallback(entity -> {
			if (entity instanceof final CollidableEntity collidableEntity) {
				this.getCollisionDetectionService().removeEntity(collidableEntity);
			}

			final Polygon polygon = this.polygons.remove(entity);
			if (polygon != null) {
				this.gameWindow.getChildren().remove(polygon);
			}
		});

		// Lookup all Game Plugins using ServiceLoader
		for (final IGamePluginService iGamePlugin : this.getPluginServices()) {
			iGamePlugin.start(this.gameData, this.world);
		}

		this.render();

		window.setScene(scene);
		window.setTitle("ASTEROIDS");
		window.show();

	}

	private Scene getScene() {
		final Scene scene = new Scene(this.gameWindow);
		scene.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.LEFT)) {
				this.gameData.getKeys().setKey(GameKeys.Key.LEFT, true);
			} else if (event.getCode().equals(KeyCode.RIGHT)) {
				this.gameData.getKeys().setKey(GameKeys.Key.RIGHT, true);
			} else if (event.getCode().equals(KeyCode.UP)) {
				this.gameData.getKeys().setKey(GameKeys.Key.UP, true);
			} else if (event.getCode().equals(KeyCode.SPACE)) {
				this.gameData.getKeys().setKey(GameKeys.Key.SPACE, true);
			}
		});
		scene.setOnKeyReleased(event -> {
			if (event.getCode().equals(KeyCode.LEFT)) {
				this.gameData.getKeys().setKey(GameKeys.Key.LEFT, false);
			} else if (event.getCode().equals(KeyCode.RIGHT)) {
				this.gameData.getKeys().setKey(GameKeys.Key.RIGHT, false);
			} else if (event.getCode().equals(KeyCode.UP)) {
				this.gameData.getKeys().setKey(GameKeys.Key.UP, false);
			} else if (event.getCode().equals(KeyCode.SPACE)) {
				this.gameData.getKeys().setKey(GameKeys.Key.SPACE, false);
			}
		});
		return scene;
	}

	private void render() {
		new AnimationTimer() {
			@Override
			public void handle(final long now) {
				Main.this.update();
				Main.this.draw();
				Main.this.gameData.getKeys().update();
			}

		}.start();
	}

	private void update() {
		// Update
		for (final IEntityProcessingService entityProcessorService : this.getEntityProcessingServices()) {
			entityProcessorService.process(this.gameData, this.world);
		}
		this.getCollisionDetectionService().processCollisions(this.gameData, this.world);
	}

	private void draw() {
		for (final Entity entity : this.world.getEntities()) {
			final Polygon polygon = this.polygons.get(entity);
			polygon.setTranslateX(entity.getX());
			polygon.setTranslateY(entity.getY());
			polygon.setRotate(entity.getRotation());
		}
	}

	private Collection<IGamePluginService> getPluginServices() {
		return this.gamePluginServices;
	}

	private Collection<IEntityProcessingService> getEntityProcessingServices() {
		return this.entityProcessingServices;
	}

	private ICollisionDetectionService getCollisionDetectionService() {
		return this.collisionDetectionService;
	}
}
