import api.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class MainAlgo implements DirectedWeightedGraphAlgorithms {

    public DirectedWeightedGraph graph;

    private double[] dist;

    private int size;

    public MainAlgo(HashOfHashes h) {
        init(h);
        HashMap<>

    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }


    //TODO  does not test yet
    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph copy = new HashOfHashes();

        Iterator<NodeData> nodeDataIterator = this.graph.nodeIter();
        while (nodeDataIterator.hasNext()) {
            copy.addNode(nodeDataIterator.next());
        }

        Iterator<EdgeData> edgeDataIterator = this.graph.edgeIter();
        while (edgeDataIterator.hasNext()) {
            copy.connect(edgeDataIterator.next().getSrc(), edgeDataIterator.next().getDest(), edgeDataIterator.next().getWeight());
        }

        return copy;

    }

    // check if each node has (n-1) pathes.
    @Override
    public boolean isConnected() {
        return false;
    }


    // get by shortedpath().
    @Override
    public double shortestPathDist(int src, int dest) {
        dist = new double[size];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        dist[src] = 0;
        PriorityQueue<NodeData> queue = new PriorityQueue<>();//comparable
        queue.add(this.graph.getNode(src));

        boolean[] visit = new boolean[size];
        while (this.graph.edgeIter(src).hasNext()) {
            EdgeData e = new Edge(this.graph.edgeIter(src).next().getSrc(),
                    this.graph.edgeIter(src).next().getDest(), this.graph.edgeIter(src).next().getWeight());
            dist[e.getDest()] = e.getWeight();


            int[] prev = new int[size];


            while (!queue.isEmpty()) {
                NodeData u = queue.poll();

            }


            return 0;
        }
    }

    // implement by dixtra algorithm. data structure for this algorithm --> Fibonacci heap
    @Override
    public List<NodeData> shortestPath(int src, int dest) {

        return null;
    }

    @Override
    public NodeData center() {


        double min = min(this.graph.nodeIter().next().getKey());
        NodeData ansNode = this.graph.getNode(0);
        while (this.graph.nodeIter().hasNext()) {
            if (this.graph.nodeIter().next().getKey() < 2) {
                continue;
            }

            double temp = 0;


            for (int i = 0; i < this.graph.nodeSize(); i++) {
                temp += shortestPathDist(this.graph.nodeIter().next().getKey(), i);

            }
            if (temp < min) {
                min = temp;
                ansNode = this.graph.nodeIter().next();
            }

        }
        return ansNode;


    }

    private double min(int key) {
        double temp = 0;
        for (int i = 0; i < this.graph.nodeSize(); i++) {
            if (key == this.graph.getNode(i).getKey()) {
                continue;
            }
            temp += shortestPathDist(key, this.graph.getNode(i).getKey());

        }
        return temp;
    }


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        double temp = Double.POSITIVE_INFINITY;
        List<NodeData> path = Collections.emptyList();


        while (this.graph.nodeIter().hasNext()) {
            List<NodeData> path_init = Collections.emptyList();
            path_init.add(this.graph.nodeIter().next());

            ArrayList<NodeData> miss = new ArrayList<>(cities);
            miss.remove(this.graph.nodeIter().next());


            List<NodeData> list_t = tspRec(path_init, miss, 0, temp);

            double curr_weight = shortestPathDist(list_t.get(0).getKey(), list_t.get(list_t.size() - 1).getKey());
            if (curr_weight < temp) {
                path = list_t;
                temp = curr_weight;
            }

        }
        return path;
    }


    public List<NodeData> tspRec(List<NodeData> path, ArrayList<NodeData> miss, double val, double final_v) {

        if (miss.isEmpty()) {
            return path;
        }

        for (int i = 0; i < miss.size(); i++) {
            double t_val = val + shortestPathDist(path.get(path.size() - 1).getKey(), miss.get(i).getKey());
            ArrayList<NodeData> t_miss = new ArrayList<>(miss);
            List<NodeData> t_path = update(path, shortestPath(path.get(path.size() - 1).getKey(), miss.get(i).getKey()), t_miss);

            List<NodeData> temp_list = tspRec(t_path, t_miss, t_val, final_v);

            if (t_val < final_v) {
                path = temp_list;
                final_v = t_val;
            }

        }
        return path;
    }

    public List<NodeData> update(List<NodeData> path, List<NodeData> shortest, ArrayList<NodeData> miss) {

        for (int i = 1; i < shortest.size(); i++) {
            path.add(shortest.get(i));
            miss.remove(shortest.get(i));
        }
        return path;
    }




    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        File input = new File(file);
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            JsonArray jsonArrayOfEdge = fileObject.get("Edges").getAsJsonArray();
            for (JsonElement EdgesElement : jsonArrayOfEdge) {
                JsonObject EdgesObjects = EdgesElement.getAsJsonObject();

                int src = EdgesObjects.get("src").getAsInt();
                double weight = EdgesObjects.get("w").getAsDouble();
                int dest = EdgesObjects.get("dest").getAsInt();

                this.graph.connect(src, dest, weight);
            }

            JsonArray jsonArrayOfNodes = fileObject.get("Nodes").getAsJsonArray();
            for (JsonElement NodesElement : jsonArrayOfNodes) {
                JsonObject NodeObjects = NodesElement.getAsJsonObject();


                double pos = NodeObjects.get("pos").getAsDouble();
                int id = NodeObjects.get("id").getAsInt();

                GeoLocation g = new Point3D(NodeObjects.get("pos").getAsJsonArray().get(0).getAsDouble()
                        , NodeObjects.get("pos").getAsJsonArray().get(1).getAsDouble()
                        , NodeObjects.get("pos").getAsJsonArray().get(2).getAsDouble());

                NodeData n = new Vertex(id, g);

                this.graph.addNode(n);


            }
        } catch (FileNotFoundException e) {
            System.err.println("Error input file not found!");
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.err.println("Error processing input file!");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HashOfHashes a = new HashOfHashes();
        NodeData aa = new Vertex(0, new Point3D(1, 1, 1));
        NodeData bb = new Vertex(1, new Point3D(2, 2, 2));
        NodeData cc = new Vertex(2, new Point3D(3, 3, 3));
        List<EdgeData> A = new ArrayList<>();
        a.addNode(aa);
        a.addNode(bb);
        a.addNode(cc);
        A.add(1, new Edge(aa.getKey(), bb.getKey(), 12));
        A.add(2, new Edge(aa.getKey(), cc.getKey(), 6));
        A.add(3, new Edge(bb.getKey(), cc.getKey(), 15));
        System.out.println();
    }
}
