import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class MainAlgo implements DirectedWeightedGraphAlgorithms {

    public DirectedWeightedGraph graph;
    private NodeData[] color, partition, Adj;
    private double[] dist;


    private int n;


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
        if (this.graph == null)
            return null;

        DirectedWeightedGraph copy = new HashOfHashes();

        Iterator<NodeData> nodeDataIterator = this.graph.nodeIter();
        while (nodeDataIterator.hasNext()) {
            NodeData n = nodeDataIterator.next();
            copy.addNode(n);
        }


            Iterator<EdgeData> edgeDataIterator = this.graph.edgeIter();//nodeDataIterator.next().getKey());
                while (edgeDataIterator.hasNext()) {
                    copy.connect(edgeDataIterator.next().getSrc(),
                            edgeDataIterator.next().getDest(),
                            edgeDataIterator.next().getWeight());
                    System.out.println("ze tov ");
                }


        return copy;

    }


    // check if each node has (n-1) pathes.
    // todo BFS  algorithm
    @Override
    public boolean isConnected() {

//        boolean ans = true;
//        BFS(0);
//        for (int i = 0; ans && i < copy().nodeSize(); i++) {
//            if (dist[i] == null) ans = false;
//        }
//        return ans;


//
//        if (this.graph.nodeSize() == 1) return true;
//
//
//        if (hasEdge()) {
//            System.out.println("--------------------1");
//            // run on all node in the graph
//            while (this.graph.nodeIter().hasNext()) {
//                System.out.println("--------------------2");
//                int src = this.graph.nodeIter().next().getKey();
//                ArrayList<Integer> c = new ArrayList<>();
//                if (!hasPath(src, c)) {
//                    return false;
//                }
//
//
//            }
//            return true;
//
//
//        }
//        return false;
//
        return false;
    }

    private void BFS(DirectedWeightedGraph g, int src) {
        while (g.nodeIter().hasNext()) {
            g.nodeIter().next().setTag(0);
            Arrays.fill(dist, Integer.MAX_VALUE); //all of the dist infinity
            Arrays.fill(partition, null);
        }
        this.graph.getNode(src).setTag(1);
        dist[src] = 0;
        partition[src] = null; // the pointer of src is null
        Queue<Integer> queue = new LinkedList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (NodeData n : Adj) {
                if (g.nodeIter().next().getTag() == 0)
                    g.nodeIter().next().setTag(1);
                dist[n.getKey()] = dist[n.getKey()] + 1;
                partition[n.getKey()] = g.getNode(u);
                queue.add(n.getKey());
            }
            g.getNode(u).setTag(2);
        }

    }


    private boolean hasEdge() {

        Iterator<NodeData> nodeIter = this.graph.nodeIter();

        while (nodeIter.hasNext()) {

            NodeData i = nodeIter.next();

            if (this.graph.edgeIter(i.getKey()).next() == null) {
                return false;

            }


            // System.out.println(this.graph.edgeIter(i.getKey()).next());
            //    System.out.println(i);
        }
        return true;

    }

    private boolean hasPath(int src, ArrayList<Integer> list) {


        Iterator<EdgeData> edgeIter = this.graph.edgeIter();

        this.graph.getNode(src).setTag(1); // set Node --> grey


        if (!list.contains(src)) {
            list.add(src);
        }

        if (list.size() == this.graph.nodeSize()) {
            return true;
        }

        while (edgeIter.hasNext()) {

            NodeData node = this.graph.getNode(edgeIter.next().getDest());
            if (!list.contains(node.getKey()))
                list.add(node.getKey());
        }


        System.out.println(list);

        for (int i = 0; i < this.graph.nodeSize(); i++) {
            if (this.graph.getNode(i) != null) {
                if (this.graph.getNode(i).getTag() == 0) {
                    return hasPath(this.graph.getNode(i).getTag(), list);
                }
            }
        }
        return false;
    }


    // get by shortedpath().
    //implement of dijkstra algorithm


    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }


    // implement by dijkstra  algorithm. data structure for this algorithm --> Fibonacci heap
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
