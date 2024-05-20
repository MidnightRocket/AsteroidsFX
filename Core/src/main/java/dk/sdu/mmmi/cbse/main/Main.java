package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.data.*;
import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.services.ICollisionDetectionService;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.Collection;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.stream.Collectors.toList;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

	private final GameData gameData = new GameData();
	private final World world = new World();
	private final Map<Entity, Polygon> polygons = new ConcurrentHashMap<>();
	private final Pane gameWindow = new Pane();

	public static void main(String[] args) {
		launch(Main.class);
	}

	@Override
	public void start(Stage window) throws Exception {
		Text text = new Text(10, 20, "Destroyed asteroids: 0");
		this.gameWindow.setPrefSize(this.gameData.getDisplayWidth(), this.gameData.getDisplayHeight());
		this.gameWindow.getChildren().add(text);

		Scene scene = new Scene(this.gameWindow);
		scene.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.LEFT)) {
				this.gameData.getKeys().setKey(GameKeys.LEFT, true);
			} else if (event.getCode().equals(KeyCode.RIGHT)) {
				this.gameData.getKeys().setKey(GameKeys.RIGHT, true);
			} else if (event.getCode().equals(KeyCode.UP)) {
				this.gameData.getKeys().setKey(GameKeys.UP, true);
			} else if (event.getCode().equals(KeyCode.SPACE)) {
				this.gameData.getKeys().setKey(GameKeys.SPACE, true);
			}
		});
		scene.setOnKeyReleased(event -> {
			if (event.getCode().equals(KeyCode.LEFT)) {
				this.gameData.getKeys().setKey(GameKeys.LEFT, false);
			} else if (event.getCode().equals(KeyCode.RIGHT)) {
				this.gameData.getKeys().setKey(GameKeys.RIGHT, false);
			} else if (event.getCode().equals(KeyCode.UP)) {
				this.gameData.getKeys().setKey(GameKeys.UP, false);
			} else if (event.getCode().equals(KeyCode.SPACE)) {
				this.gameData.getKeys().setKey(GameKeys.SPACE, false);
			}
		});

		this.world.addEntityAddedCallback(entity -> {
			Polygon polygon = new Polygon(entity.getPolygonCoordinates());
			this.polygons.put(entity, polygon);
			this.gameWindow.getChildren().add(polygon);
		});

		this.world.addEntityRemovedCallback(entity -> {
			Polygon polygon = this.polygons.remove(entity);
			if (polygon != null) {
				this.gameWindow.getChildren().remove(polygon);
			}
		});

		// Lookup all Game Plugins using ServiceLoader
		for (IGamePluginService iGamePlugin : this.getPluginServices()) {
			iGamePlugin.start(this.gameData, this.world);
		}

		this.render();

		window.setScene(scene);
		window.setTitle("ASTEROIDS");
		window.show();

	}

	private void render() {
		new AnimationTimer() {
			private long then = 0;

			@Override
			public void handle(long now) {
				Main.this.update();
				Main.this.draw();
				Main.this.gameData.getKeys().update();
			}

		}.start();
	}

	private void update() {

		// Update
		for (IEntityProcessingService entityProcessorService : this.getEntityProcessingServices()) {
			entityProcessorService.process(this.gameData, this.world);
		}
		for (IPostEntityProcessingService postEntityProcessorService : this.getPostEntityProcessingServices()) {
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
		for (Entity entity : this.world.getEntities()) {
			Polygon polygon = this.polygons.get(entity);
			polygon.setTranslateX(entity.getX());
			polygon.setTranslateY(entity.getY());
			polygon.setRotate(entity.getRotation());
		}
	}

	private Collection<? extends IGamePluginService> getPluginServices() {
		return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}

	private Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
		return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}

	private Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
		return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}

	private Collection<? extends ICollisionDetectionService> getCollisionDetectionServices() {
		return ServiceLoader.load(ICollisionDetectionService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
	}
}
