public class Json_edge {
    int src;
    double w;
    int dest;

    public Json_edge(int src, double weight, int dest) {
        this.src = src;
        this.w = weight;
        this.dest = dest;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
        this.w = w;
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
                ", weight=" + w +
                ", dest=" + dest +
                '}';
    }
}
