package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.interfaces.Entity;

import java.io.Serializable;
import java.util.UUID;

public class CommonEntity implements Serializable, Entity {

	private final UUID id = UUID.randomUUID();

	private double[] polygonCoordinates;
	private double x;
	private double y;
	private double rotation;


	@Override
	public String getId() {
		return this.id.toString();
	}

	@Override
	public double[] getPolygonCoordinates() {
		return this.polygonCoordinates;
	}

	@Override
	public void setPolygonCoordinates(double... coordinates) {
		this.polygonCoordinates = coordinates;
	}

	@Override
	public double getX() {
		return this.x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return this.y;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double getRotation() {
		return this.rotation;
	}

	@Override
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}


}
