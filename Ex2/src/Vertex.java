import api.GeoLocation;
import api.NodeData;

import java.awt.*;

public class Vertex implements NodeData {

    private final int id; // from json
    private GeoLocation pos;
    private double weight;
    private int tag;
    private String info;

    private int num_of_neighbors;

    public int getNum_of_neighbors() {
        return num_of_neighbors;
    }



    public void UpdateNum_of_neighbors() {
        this.num_of_neighbors++;
    }

    public Vertex(int id, GeoLocation pos) {
        this.id = id;
        this.pos = pos;
        this.weight = 0; // influenced by edges.
        this.tag = Tags.WHITE.ordinal(); // <white = 0 , grey = 1, black = 2>

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
        this.pos = new Point3D(p.x(), p.y(), p.z());
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

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", pos=" + pos +
                ", weight=" + weight +
                ", tag=" + tag +
                '}';
    }
}
