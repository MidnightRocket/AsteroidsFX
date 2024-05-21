package com.github.midnightrocket.asteroidsfx.scoresystemclient;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.graphics.label.Label;
import dk.sdu.mmmi.cbse.common.graphics.label.LabelFactory;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.vector.BasicVector;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ScoreSystemClient implements IEntityProcessingService {
	private final HttpClient httpClient = HttpClient.newHttpClient();
	private Label label;

	@Override
	public void process(final GameData gameData, final World world) {
		final HttpRequest requestGetScore = HttpRequest.newBuilder()
				.uri(URI.create("http://localhost:8080/score"))
				.GET().build();

		// Retrieves the score for amount of asteroids destroyed.
		try {
			final HttpResponse<String> responseGetScore = this.httpClient.send(requestGetScore, HttpResponse.BodyHandlers.ofString());
			this.updateLabel(responseGetScore.body());
		} catch (final ConnectException e) {
			this.updateLabel("error failed to connect.");
		} catch (final IOException | InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private void updateLabel(final String points) {
		this.getLabel().setText(String.format("Asteroids destroyed: %s", points));
	}

	private Label getLabel() {
		if (this.label == null) this.label = LabelFactory.create(new BasicVector(20, 20), "");
		return this.label;
	}
}
