package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.interfaces.Entity;
import dk.sdu.mmmi.cbse.common.vector.BasicVector;
import dk.sdu.mmmi.cbse.common.vector.IVector;

import java.io.Serializable;
import java.util.Arrays;

public class CommonEntity implements Serializable, Entity {
	private final BasicVector coordinates = new BasicVector();
	private IVector[] polygonCoordinates;
	private double rotation;

	@Override
	public double[] getPolygonCoordinatesValues() {
		return Arrays.stream(this.polygonCoordinates).mapMultiToDouble((c, output) -> {
			output.accept(c.getX());
			output.accept(c.getY());
		}).toArray();
	}

	@Override
	public void setPolygonCoordinates(final IVector... polygonCoordinates) {
		this.polygonCoordinates = polygonCoordinates;
	}

	@Override
	public double getRotation() {
		return this.rotation;
	}

	@Override
	public void setRotation(final double rotation) {
		this.rotation = rotation;
	}

	@Override
	public BasicVector getCoordinates() {
		return this.coordinates;
	}
}
