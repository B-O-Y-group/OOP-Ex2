import api.GeoLocation;
import api.NodeData;

import java.awt.*;

public class Vertex implements NodeData {

    private final int id; // from json
    private Point3D pos;
    private double weight;
    private int tag;
    private String info;
    private Direction d;
    private int num_of_neighbors;

    public int getNum_of_neighbors() {
        return num_of_neighbors;
    }

    public Direction getD() {
        return d;
    }

    public void UpdateNum_of_neighbors() {
        this.num_of_neighbors++;
    }

    public Vertex(int id, Point3D pos) {
        this.id = id;
        this.pos = pos;
        this.weight = 0; // influenced by edges.
        this.tag = Tags.WHITE.ordinal(); // <white = 0 , grey = 1, black = 2>
        this.d = new Direction();
        this.info = "";
        this.num_of_neighbors = 0;
    }


    public static void main(String[] args) {

        Vertex v = new Vertex(1, new Point3D(1, 1, 1));

        System.out.println(v.getTag());
    }

    @Override
    public int getKey() {
        return this.id;
    }

    @Override
    public GeoLocation getLocation() {
        return this.pos;
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.pos.x = p.x();
        this.pos.y = p.y();
        this.pos.z = p.z();
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public void setWeight(double w) {
        this.weight = w;
    }

    @Override
    public String getInfo() {
        return this.info;
    }

    @Override
    public void setInfo(String s) {
        this.info = s;
    }

    @Override
    public int getTag() {
        return this.tag;
    }

    @Override
    public void setTag(int t) {
        this.tag = t;
    }
}
