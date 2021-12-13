import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.util.*;


public class HashOfHashes implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> edge;
    private HashMap<Integer, HashMap<Integer, List<EdgeData>>> graph;

    private ArrayList<EdgeData> edges_list;
    boolean ed_list_removed;
    private ArrayList<EdgeData> falses_list;

    private int num_of_edges;
    private int MC;

    private int max_node;

    public HashOfHashes() {
        this.nodes = new HashMap<>();
        this.graph = new HashMap<>();
        this.edge = new HashMap<>();

        this.edges_list = new ArrayList<>();
        this.ed_list_removed = false;
        this.falses_list = new ArrayList<>();

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
        if (w< 0){
            throw new NoSuchElementException("no such nodes");
        }
        EdgeData edge = new Edge(src, dest, w);
        if (this.graph.containsKey(src) && this.graph.containsKey(dest)) {
            if (this.graph.get(src).containsKey(dest)) {

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
                }
                this.graph.get(dest).get(src).add(1, edge);

            }
            if (!this.edge.containsKey(src)) {
                this.edge.put(src, new HashMap<>());
            }
            this.edge.get(src).put(dest, edge);
            this.edges_list.add(edge);

            // System.out.println("edge list: " + this.edge.values());
            //   System.out.println("REAL edges LIST: " + this.edges_list);
            this.num_of_edges++;
            this.MC++;
        } else {
            System.out.println("ERROR: SRC: " + src + " DEST: " + dest);
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
        if (ed_list_removed) {

            for (EdgeData e :
                    falses_list) {
                edges_list.remove(e);
            }
            falses_list.clear();
            ed_list_removed = false;
        }
        return this.edges_list.iterator();
    }

    // TODO.
    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        if (!this.nodes.containsKey(node_id)) {
            System.err.println("No such node in the graph !");
            return null;
        }

        return this.edge.get(node_id).values().iterator();
    }

    // TODO
    @Override
    public NodeData removeNode(int key) {

        if (this.graph.containsKey(key)) {
            for (int i = 0; i < this.graph.get(key).size(); i++) {

                if (i != key) {


                    if (this.graph.get(key).get(i).get(1) != null) {
                    }
                    System.out.println("herre e e");

                    removeEdge(this.graph.get(key).get(i).get(1).getSrc(), this.graph.get(key).get(i).get(1).getDest());
                    this.graph.remove(i);

                }
                if (this.graph.get(key).get(i).get(0) != null) {
                    System.out.println("------------------------>" + i);
                    removeEdge(this.graph.get(key).get(i).get(0).getSrc(), this.graph.get(key).get(i).get(0).getDest());

                }
            }
            //System.out.println("------------------------>" + this.graph.get(key));
            NodeData ans = this.nodes.get(key);
            this.graph.remove(key);

            this.MC++;
            this.nodes.remove(key);
            return ans;
        } else {
            throw new NoSuchElementException("no key in the graph ");
        }


    }

    // TODO
    @Override
    public EdgeData removeEdge(int src, int dest) {
        if (this.graph.get(src).get(dest).get(0) != null) {

            falses_list.add(this.graph.get(src).get(dest).get(0));
            ed_list_removed = true;

            this.graph.get(dest).get(src).remove(1);
            this.graph.get(src).get(dest).remove(0);

            this.MC++;
            num_of_edges--;


            return this.edge.get(src).remove(dest);
        } else {
            System.out.println("null here");
            return null;
        }
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
