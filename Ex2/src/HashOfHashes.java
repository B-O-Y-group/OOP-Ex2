import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;


public class HashOfHashes implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> graph;
    private int num_of_edges;
    private int MC;

    public HashOfHashes() {
        this.nodes = new HashMap<>();
        this.graph = new HashMap<>();
        this.num_of_edges = 0;
        this.MC = 0;
    }

    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return graph.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.getKey(), n);
    }

    // TODO
    @Override
    public void connect(int src, int dest, double w) {
        this.graph.get(src).put(dest, new Edge(src, dest, w));
        this.graph.get(dest).put(src, new Edge(src, dest, w));
        this.num_of_edges++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        if (this.nodes.isEmpty()) {
            System.out.println("Error : this graph has no nodes.");
            return null;
        }
        return this.nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        if (this.graph.isEmpty()) {
            System.out.println("Error : no edges in the graph");
        }
        return this.graph.values().iterator().next().values().iterator();
    }

    // TODO
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (!this.nodes.containsKey(node_id)) {
            System.err.println("No such node in the graph !");
            return null;
        }
        return this.graph.get(node_id).values().iterator();
    }

    // TODO
    @Override
    public NodeData removeNode(int key) {
        while (edgeIter(key).hasNext()) {
            graph.get(edgeIter(key).next().getDest()).remove(key);
        }

        this.graph.remove(key);
        this.MC++;
        return null;
    }

    // TODO
    @Override
    public EdgeData removeEdge(int src, int dest) {
//        Edge pop = this.graph.get(src).getD().out.get(dest);
//        this.graph.get(src).getD().out.remove(dest);
//        this.graph.get(dest).getD().in.remove(src);
//        this.MC++;
//        return pop;
        return null;
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
        return this.MC;
    }


}
