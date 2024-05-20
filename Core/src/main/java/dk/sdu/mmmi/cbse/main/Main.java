package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
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
	private final Collection<IPostEntityProcessingService> postEntityProcessingServices;
	private final Collection<ICollisionDetectionService> collisionDetectionServices;

	public Main() {
		this.gamePluginServices = loadServices(IGamePluginService.class);
		this.entityProcessingServices = loadServices(IEntityProcessingService.class);
		this.postEntityProcessingServices = loadServices(IPostEntityProcessingService.class);
		this.collisionDetectionServices = loadServices(ICollisionDetectionService.class);
	}

	private static <S> Collection<S> loadServices(final Class<S> serviceClass) {
		return ServiceLoader.load(serviceClass).stream().map(ServiceLoader.Provider::get).toList();
	}

	public static void main(final String[] args) {
		launch();
	}

	@Override
	public void start(final Stage window) {
		final Text text = new Text(10, 20, "Destroyed asteroids: 0");
		this.gameWindow.setPrefSize(this.gameData.getDisplayWidth(), this.gameData.getDisplayHeight());
		this.gameWindow.getChildren().add(text);

		final Scene scene = this.getScene();

		this.world.addEntityAddedCallback(entity -> {
			final Polygon polygon = new Polygon(entity.getPolygonCoordinatesValues());
			this.polygons.put(entity, polygon);
			this.gameWindow.getChildren().add(polygon);
		});

		this.world.addEntityRemovedCallback(entity -> {
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
		for (final IPostEntityProcessingService postEntityProcessorService : this.getPostEntityProcessingServices()) {
			postEntityProcessorService.process(this.gameData, this.world);
		}

		this.getCollisionDetectionServices().forEach(service -> {
			service.setIntersectsCallback((entity1, entity2) -> {
				if (!this.polygons.containsKey(entity1) || !this.polygons.containsKey(entity2)) return false;
				return this.polygons.get(entity1).getBoundsInParent().intersects(this.polygons.get(entity2).getBoundsInParent());
			});
			service.process(this.gameData, this.world);
		});
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

	private Collection<IPostEntityProcessingService> getPostEntityProcessingServices() {
		return this.postEntityProcessingServices;
	}

	private Collection<ICollisionDetectionService> getCollisionDetectionServices() {
		return this.collisionDetectionServices;
	}
}
