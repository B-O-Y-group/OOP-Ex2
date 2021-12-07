import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
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

    public MainAlgo(DirectedWeightedGraph h) {
        init(h);
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
        if (this.graph.nodeSize() == 1) return true;


        if (hasEdge()) {
            // run on all node in the graph
            while (this.graph.nodeIter().hasNext()) {

                int src = this.graph.nodeIter().next().getKey();
                ArrayList<Integer> c = new ArrayList<>();
                if (!hasPath(src, c)) {
                    return false;
                }


            }
            return true;


        }
        return false;


    }


    private boolean hasEdge() {

        while (this.graph.nodeIter().hasNext()) {

            int i = this.graph.nodeIter().next().getKey();

            if (this.graph.edgeIter(i).next() == null) {
                return false;

            }

        }
        return true;

    }

    private boolean hasPath(int src, ArrayList<Integer> c) {


        Iterator<EdgeData> temp = this.graph.edgeIter();

        this.graph.getNode(src).setTag(1); // set Node --> grey


        if (!c.contains(src)) {
            c.add(src);
        }

        if (c.size() == this.graph.nodeSize()) {
            return true;
        }

        while (temp.hasNext()) {

            NodeData node = this.graph.getNode(temp.next().getDest());
            if (!c.contains(node.getKey()))
                c.add(node.getKey());
        }



        for (int i = 0; i < this.graph.nodeSize(); i++) {
            NodeData index = this.graph.getNode(c.get(i));
            if (index.getTag() == 0) {
                System.out.println("hereeelllllllllllllllllllllll" + i );
                return hasPath(index.getKey(), c);
            }
        }

        return false;
    }


    // get by shortedpath().
    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
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
        return null;
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

            }

            JsonArray jsonArrayOfNodes = fileObject.get("Nodes").getAsJsonArray();
            for (JsonElement NodesElement : jsonArrayOfNodes) {
                JsonObject NodeObjects = NodesElement.getAsJsonObject();


                double pos = NodeObjects.get("pos").getAsDouble();
                int id = NodeObjects.get("id").getAsInt();


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
}
