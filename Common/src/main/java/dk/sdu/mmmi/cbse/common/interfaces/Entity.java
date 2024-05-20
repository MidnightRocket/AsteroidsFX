package dk.sdu.mmmi.cbse.common.interfaces;

public interface Entity {
	String getId();

	double[] getPolygonCoordinates();

	void setPolygonCoordinates(double... coordinates);

	double getX();

	void setX(double x);

	double getY();

	void setY(double y);

	double getRotation();

	void setRotation(double rotation);
}
