package api;

public class Vertex implements NodeData {

    private final int id; // from json
    private GeoLocation pos;
    private double weight;
    private int tag;
    private String info;

    private int num_of_neighbors;



    public Vertex(int id, GeoLocation pos) {
        this.id = id;
        this.pos = pos ;
        this.weight = 0; // influenced by edges.
        this.tag = Tags.WHITE.ordinal(); // <white = 0 , grey = 1, black = 2>

        this.info = "";
        this.num_of_neighbors = 0;
    }
    public int getNum_of_neighbors() {
        return num_of_neighbors;
    }


    public void UpdateNum_of_neighbors() {
        this.num_of_neighbors++;
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
        this.pos = p;
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
        return "Id: " + id ;
    }
}
