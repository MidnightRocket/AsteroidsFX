package com.github.midnightrocket.astroidsfx.asteroid;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.interfaces.CollidableEntity;
import dk.sdu.mmmi.cbse.common.metadata.GameElementType;
import dk.sdu.mmmi.cbse.common.vector.BasicVector;
import dk.sdu.mmmi.cbse.common.vector.IVector;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public final class Asteroid extends dk.sdu.mmmi.cbse.common.asteroid.Asteroid {
	private static final Random RANDOM = new Random();
	private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

	private final double size;


	private Asteroid(final double size) {
		this.size = size;
	}

	public static Asteroid create(final double size) {
		if (size <= 0) throw new IllegalArgumentException("Size must be 1 or greater");
		final Asteroid asteroid = new Asteroid(size);
		asteroid.setPolygonCoordinates(calculatePolygonCoordinates(RANDOM.nextInt(5, 10), size));
		return asteroid;
	}

	private static IVector[] calculatePolygonCoordinates(final int polygonEdges, final double radius) {
		final IVector[] polygonCoordinates = new IVector[polygonEdges];
		// loops over all vertices in a regular polygon and creates and adds coordinates for each, based on the radius and the amount of edges
		for (int vertex = 0; vertex < polygonCoordinates.length; vertex++) {
			// find x
			final double x = radius * Math.cos(2 * Math.PI * vertex / polygonEdges);
			// find y
			final double y = radius * Math.sin(2 * Math.PI * vertex / polygonEdges);

			final IVector point = new BasicVector(x, y);
			polygonCoordinates[vertex] = point;
		}

		return polygonCoordinates;
	}

	@Override
	public void collide(final World world, final CollidableEntity otherEntity) {
		if (otherEntity instanceof final Bullet bullet) this.split(world, bullet);
	}

	private void split(final World world, final Bullet bullet) {
		if (bullet.getShooter().getElementType() == GameElementType.PLAYER) this.incrementScore(1);

		final double maxRotationalDeviation = 30;
		final double minRotationalDeviation = 20;
		final double minSizeThreshold = 20;

		world.removeEntity(this);
		final double newSize = this.size / 2;
		if (newSize < minSizeThreshold) return;


		final Asteroid asteroid1 = create(newSize);
		final Asteroid asteroid2 = create(newSize);

		asteroid1.setRotation(RANDOM.nextDouble(this.getRotation() - maxRotationalDeviation, this.getRotation() - minRotationalDeviation));
		asteroid2.setRotation(RANDOM.nextDouble(this.getRotation() + minRotationalDeviation, this.getRotation() + maxRotationalDeviation));

		asteroid1.setCoordinatesFrom(this.getCoordinates());
		asteroid2.setCoordinatesFrom(this.getCoordinates());

		world.addEntity(asteroid1);
		world.addEntity(asteroid2);
	}


	double getSpeed() {
		return 100 / this.size;
	}

	private void incrementScore(final int points) {
		final HttpRequest requestAddToScore = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/score/add/" + points))
				.PUT(HttpRequest.BodyPublishers.ofString(""))
				.build();

		try {
			HTTP_CLIENT.send(requestAddToScore, HttpResponse.BodyHandlers.ofString());
		} catch (final ConnectException ignored) {
		} catch (final IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
