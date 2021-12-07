import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;


public class HashOfHashes implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, EdgeData> edge;

    private HashMap<Integer, HashMap<Integer, List<EdgeData>>> graph;
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
        if (this.graph.get(src).get(dest).get(0) == null) {
            throw new NoSuchElementException("No such edge in the graph");
        } else {
            return this.graph.get(src).get(dest).get(0);
        }
    }

    @Override
    public void addNode(NodeData n) {
        this.graph.put(n.getKey(), new HashMap<>());

        this.nodes.put(n.getKey(), n);
        this.MC++;
    }


    @Override
    public void connect(int src, int dest, double w) {
        EdgeData edge = new Edge(src, dest, w);
        if (this.graph.containsKey(src) && this.graph.containsKey(dest)) {
            if (this.graph.get(src).containsKey(dest)) {
                System.out.println("here 1");
                this.graph.get(src).get(dest).add(0, edge);
                this.graph.get(dest).get(src).add(1, edge);

            } else {
                // init new list for src and dest hash
                this.graph.get(src).put(dest, new ArrayList<>(2));
                this.graph.get(dest).put(src, new ArrayList<>(2));

                // adding the new edge to the hash
                this.graph.get(src).get(dest).add(0, edge);

                // the list is empty so need to init the first index
                if (this.graph.get(dest).get(src).isEmpty()) {
                    this.graph.get(dest).get(src).add(0, null);
                    this.graph.get(dest).get(src).add(1, edge);
                } else {
                    this.graph.get(dest).get(src).add(1, edge);
                }

            }
            this.edge.put(src, edge);
            this.num_of_edges++;
            this.MC++;
        } else {
            throw new NoSuchElementException("no such nodes");
        }
    }


    // todo trow exception
    @Override
    public Iterator<NodeData> nodeIter() {
        return this.nodes.values().iterator();
    }


    @Override
    public Iterator<EdgeData> edgeIter() {
        return this.edge.values().iterator();
    }

    // TODO.
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (!this.nodes.containsKey(node_id)) {
            System.err.println("No such node in the graph !");
            return null;
        }
        return this.graph.get(node_id).values().iterator().next().iterator();
    }

    // TODO
    @Override
    public NodeData removeNode(int key) {

        for (int i = 0; i < this.graph.get(key).size(); i++) {
            if (this.graph.get(key).get(i).get(0) != null) {
                removeEdge(this.graph.get(key).get(i).get(0).getSrc(), this.graph.get(key).get(i).get(0).getDest());

            }
            if (this.graph.get(key).get(i).get(1) != null) {
                removeEdge(this.graph.get(key).get(i).get(1).getSrc(), this.graph.get(key).get(i).get(1).getDest());

            }
        }

        this.graph.remove(key);
        this.MC++;
        return this.nodes.remove(key);
    }

    // TODO
    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (this.graph.get(src).get(dest).get(0) != null) {
            this.graph.get(src).get(dest).remove(0);
            this.graph.get(dest).get(src).remove(1);
        }
        this.MC++;

        return this.edge.remove(src);
    }

    @Override
    public int nodeSize() {
        return this.graph.size();
    }

    @Override
    public int edgeSize() {
        return num_of_edges;
    }


    /// counter every change
    @Override
    public int getMC() {
        return this.MC;
    }


}
