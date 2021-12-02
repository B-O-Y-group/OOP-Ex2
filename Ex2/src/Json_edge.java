public class Json_edge {
    int src;
    double weight;
    int dest;

    public Json_edge(int src, double weight, int dest) {
        this.src = src;
        this.weight = weight;
        this.dest = dest;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getDest() {
        return dest;
    }

    public void setDest(int dest) {
        this.dest = dest;
    }

    @Override
    public String toString() {
        return "Json_edge{" +
                "src=" + src +
                ", weight=" + weight +
                ", dest=" + dest +
                '}';
    }
}
