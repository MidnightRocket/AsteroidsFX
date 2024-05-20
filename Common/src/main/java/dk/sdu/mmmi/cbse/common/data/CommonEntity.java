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
	public IVector[] getPolygonCoordinates() {
		return this.polygonCoordinates;
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
