package dk.sdu.mmmi.cbse.common.interfaces;

import dk.sdu.mmmi.cbse.common.vector.BasicVector;
import dk.sdu.mmmi.cbse.common.vector.IVector;

import java.util.Arrays;

public interface Entity {
	default double[] getPolygonCoordinatesValues() {
		return Arrays.stream(this.getPolygonCoordinates()).mapMultiToDouble((c, output) -> {
			output.accept(c.getX());
			output.accept(c.getY());
		}).toArray();
	}

	IVector[] getPolygonCoordinates();

	void setPolygonCoordinates(final IVector... coordinates);

	default double getX() {
		return this.getCoordinates().getX();
	}

	default void setX(final double x) {
		this.getCoordinates().setX(x);
	}

	default double getY() {
		return this.getCoordinates().getY();
	}

	default void setY(final double y) {
		this.getCoordinates().setY(y);
	}

	default void setCoordinatesFrom(final IVector coordinates) {
		this.setX(coordinates.getX());
		this.setY(coordinates.getY());
	}

	double getRotation();

	void setRotation(final double rotation);

	BasicVector getCoordinates();

	default void move(final IVector direction) {
		this.getCoordinates().add(direction);
	}

	default void move(final double x, final double y) {
		this.move(new BasicVector(x, y));
	}

	default void move(final double speed) {
		final double rotation = Math.toRadians(this.getRotation());
		final double changeX = Math.cos(rotation) * speed;
		final double changeY = Math.sin(rotation) * speed;
		this.move(changeX, changeY);
	}
}
