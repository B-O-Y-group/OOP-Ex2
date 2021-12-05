import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class HashOfHashes implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, EdgeData> edge; // dd
    private int[] ar_edge;

    private HashMap<Integer, HashMap<Integer, EdgeData[]>> graph;
    private int num_of_edges;
    private int MC;

    public HashOfHashes() {
        this.nodes = new HashMap<>();
        this.graph = new HashMap<>();
        this.edge = new HashMap<>();
        this.num_of_edges = 0;
        this.MC = 0;


    }

    @Override
    public NodeData getNode(int key) {
        return this.nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        if (this.graph.containsKey(src)) {


            if (this.graph.get(src).get(dest)[0] == null) {
                throw new NoSuchElementException();
            } else {
                return this.graph.get(src).get(dest)[0];
            }
        }
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        this.nodes.put(n.getKey(), n);
        this.graph.put(n.getKey(), new HashMap<>());
        this.MC++;


    }


    ///_____________________________________________


    @Override
    public void connect(int src, int dest, double w) {

        EdgeData edge = new Edge(src, dest, w);

        if (this.graph.containsKey(src) && this.graph.containsKey(dest)) {
            if (this.graph.get(src).containsKey(dest)) {
                this.graph.get(src).get(dest)[0] = edge;
                this.graph.get(dest).get(src)[1] = edge;
            } else {
                this.graph.get(src).put(dest, new EdgeData[2]);
                this.graph.get(dest).put(src, new EdgeData[2]);
                this.graph.get(src).get(dest)[0] = edge;
                this.graph.get(dest).get(src)[1] = edge;
            }
            this.edge.put(src,edge);
            this.num_of_edges++;
        } else {
            System.out.println("no such nodes");
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return this.nodes.values().iterator();
    }


    @Override
    public Iterator<EdgeData> edgeIter() {
        return this.edge.values().iterator();
    }

    // TODO
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (!this.nodes.containsKey(node_id)) {
            System.err.println("No such node in the graph !");
            return null;
        }
//        return this.graph.get(node_id).values().iterator();
        return null;
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

//        EdgeData ans = new Edge(src, dest, edge.get(src).getWeight());
//        this.graph.get(src).remove(src, ans);
//        this.graph.get(dest).remove(dest, ans);
//        this.edge.remove(src, ans);
//        this.edge.remove(dest, ans);
//
//
//        Edge pop = this.graph.get(src).getD().out.get(dest);
//        this.MC++;
////        return pop;
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
