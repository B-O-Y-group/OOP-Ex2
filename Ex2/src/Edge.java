import api.EdgeData;

public class Edge implements EdgeData, Comparable<EdgeData> {

    public int src;
    public int dest;
    public double weight;
    public int tag;


    public Edge(int src, int dest, double weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
        this.tag = Tags.WHITE.ordinal();
    }


    @Override
    public int getSrc() {
        return  this.src;
    }

    @Override
    public int getDest() {
        return this.dest;
    }

    @Override
    public double getWeight() {
        return this.weight;
    }

    @Override
    public String getInfo() {
        return "src: " + this.src + " dest: " + this.dest + " weight: " + this.weight;
    }

    @Override
    public void setInfo(String s) {

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
        return "Edge{" +
                "src=" + src +
                ", dest=" + dest +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(EdgeData o) {
        return Double.compare(this.weight, o.getWeight());
    }
}
