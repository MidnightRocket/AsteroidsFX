package dk.sdu.mmmi.cbse.common.data;

public interface Entity {
	String getID();

	void setPolygonCoordinates(double... coordinates);

	double[] getPolygonCoordinates();

	void setX(double x);

	double getX();

	void setY(double y);

	double getY();

	void setRotation(double rotation);

	double getRotation();
}
