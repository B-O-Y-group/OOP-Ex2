import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;


public class HashOfHashes implements DirectedWeightedGraph {
    public HashMap<Integer, Vertex> graph;
    private int num_of_edges;

    public HashOfHashes() {
        this.graph = new HashMap<>();
        this.num_of_edges = 0;
    }

    @Override
    public NodeData getNode(int key) {
        return this.graph.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return graph.get(src).getD().out.get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        this.graph.put(n.getKey(), (Vertex) n);
    }

    @Override
    public void connect(int src, int dest, double w) {
        this.graph.get(src).getD().out.put(dest, new Edge(src, dest, w));
        this.graph.get(src).UpdateNum_of_neighbors();
        this.graph.get(dest).getD().in.put(src, new Edge(src, dest, w));
        this.graph.get(dest).UpdateNum_of_neighbors();
        this.num_of_edges ++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
    }

    @Override
    public NodeData removeNode(int key) {
        Vertex pop = this.graph.get(key);
        for (int i = 0; i < pop.getD().out.size(); i++) {
            removeEdge(pop.getKey(), i);
        }
        for (int j = 0; j < pop.getD().in.size(); j++) {
            removeEdge(j, pop.getKey());
        }
        this.graph.remove(key);
        return pop;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        Edge pop = this.graph.get(src).getD().out.get(dest);
        this.graph.get(src).getD().out.remove(dest);
        this.graph.get(dest).getD().in.remove(src);
        return pop;
    }

    @Override
    public int nodeSize() {
        return this.graph.size();
    }

    @Override
    public int edgeSize() {
        return num_of_edges;
    }


    /// counter fir every change
    @Override
    public int getMC() {
        return 0;
    }
}
