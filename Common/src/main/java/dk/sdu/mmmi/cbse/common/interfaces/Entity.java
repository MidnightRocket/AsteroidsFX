package dk.sdu.mmmi.cbse.common.interfaces;

public interface Entity {
	String getId();

	void setPolygonCoordinates(double... coordinates);

	double[] getPolygonCoordinates();

	void setX(double x);

	double getX();

	void setY(double y);

	double getY();

	void setRotation(double rotation);

	double getRotation();
}
