import api.GeoLocation;

import java.awt.*;

public class Point3D implements GeoLocation {

    private double x, y, z;


    public Point3D(double x, double y,double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return this.x;
    }

    @Override
    public double y() {
        return this.y;
    }

    @Override
    public double z() {
        return this.z;
    }


    // d = ((x2 - x1)2 + (y2 - y1)2 + (z2 - z1)2)1/2
    @Override
    public double distance(GeoLocation g) {
        return Math.sqrt(Math.pow(this.x - g.x(), 2) + Math.pow(this.y - g.y(), 2) + Math.pow(this.z - g.z(), 2));
    }


    @Override
    public String toString() {
        return "Point3D{" + "x=" + x + ", y=" + y + ", z=" + z + '}';
    }
}
