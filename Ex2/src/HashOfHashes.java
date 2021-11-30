import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;




public class HashOfHashes implements DirectedWeightedGraph {
    public HashMap<Integer, Vertex> graph ;
    private HashMap<Integer, Vertex> list_of_nodes;

    public HashOfHashes() {
        this.list_of_nodes = new HashMap<>();
        this.graph = new HashMap<>();
    }






    @Override
    public NodeData getNode(int key) {
        return this.graph.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(NodeData n) {

    }

    @Override
    public void connect(int src, int dest, double w) {

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
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }


    /// counter fir every change
    @Override
    public int getMC() {
        return 0;
    }
}
