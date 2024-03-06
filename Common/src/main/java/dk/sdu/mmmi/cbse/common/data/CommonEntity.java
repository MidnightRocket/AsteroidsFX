package dk.sdu.mmmi.cbse.common.data;

import java.io.Serializable;
import java.util.UUID;

public class CommonEntity implements Serializable, Entity {

	private final UUID ID = UUID.randomUUID();

	private double[] polygonCoordinates;
	private double x;
	private double y;
	private double rotation;


	@Override
	public String getID() {
		return ID.toString();
	}


	@Override
	public void setPolygonCoordinates(double... coordinates) {
		this.polygonCoordinates = coordinates;
	}

	@Override
	public double[] getPolygonCoordinates() {
		return polygonCoordinates;
	}


	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getX() {
		return x;
	}


	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	@Override
	public double getRotation() {
		return rotation;
	}


}
