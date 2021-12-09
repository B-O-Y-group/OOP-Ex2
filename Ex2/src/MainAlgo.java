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
        return false;
    }


    // get by shortest().
    @Override
    public double shortestPathDist(int src, int dest) {
        int[] visited = new int[this.graph.nodeSize()];
        int visits = 0;
        double[] dist = new double[this.graph.nodeSize()];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        PriorityQueue<EdgeData> queue = new PriorityQueue<>();

        int curr_ver = this.graph.getNode(src).getKey();
        dist[curr_ver] = 0;
        while (visits < this.graph.nodeSize()) {
            visits++;
            if (visits != 1) {
                curr_ver = Objects.requireNonNull(queue.poll()).getDest();
            }
            if (curr_ver == dest) {
                break;
            }
            visited[curr_ver] = 2;
            Iterator<EdgeData> it = this.graph.edgeIter(curr_ver);
            while (it.hasNext()) {
                EdgeData next = it.next();
                if (visited[next.getDest()] != 2) {
                    queue.offer(next);
                    double temp_dist = dist[curr_ver] + next.getWeight();
                    if (temp_dist < dist[next.getDest()]) {
                        dist[next.getDest()] = temp_dist;
                        visited[next.getDest()] = 1;
                    }
                }
            }

        }
        return dist[dest];

    }


    // implement by dixtra algorithm. data structure for this algorithm --> Fibonacci heap
    @Override
    public List<NodeData> shortestPath(int src, int dest) {

        int[] visited = new int[this.graph.nodeSize()];
        int visits = 0;
        double[] dist = new double[this.graph.nodeSize()];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        int[] prev = new int[this.graph.nodeSize()];
        Arrays.fill(prev, -1);
        PriorityQueue<EdgeData> queue = new PriorityQueue<>();

        int curr_ver = this.graph.getNode(src).getKey();
        dist[curr_ver] = 0;
        while (visits < this.graph.nodeSize()) {
            visits++;
            if (visits != 1) {
                curr_ver = Objects.requireNonNull(queue.poll()).getDest();
            }
            if (curr_ver == dest) {
                break;
            }
            visited[curr_ver] = 2;
            Iterator<EdgeData> it = this.graph.edgeIter(curr_ver);
            while (it.hasNext()) {
                EdgeData next = it.next();
                if (visited[next.getDest()] != 2) {
                    queue.offer(next);
                    double temp_dist = dist[curr_ver] + next.getWeight();
                    if (temp_dist < dist[next.getDest()]) {
                        dist[next.getDest()] = temp_dist;
                        prev[next.getDest()] = curr_ver;
                        visited[next.getDest()] = 1;
                    }
                }
            }

        }
        List<NodeData> ans = new ArrayList<>();
        ans.add(this.graph.getNode(dest));
        int pointer = prev[dest];
        while (pointer != -1) {
            ans.add(this.graph.getNode(pointer));
            pointer = prev[pointer];
        }
        Collections.reverse(ans);

        return ans;
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

        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            NodeData next = it.next();
            List<NodeData> path_init = new ArrayList<>(Collections.emptyList());
            path_init.add(next);

            ArrayList<NodeData> miss = new ArrayList<>(cities);
            miss.remove(next);

            double curr_weight = 0;

            List<NodeData> list_t = tspRec(path_init, miss, 0, Double.POSITIVE_INFINITY);

            for (int i = 0; i < list_t.size() - 1; i++) {
                curr_weight += this.graph.getEdge(list_t.get(i).getKey(), list_t.get(i+1).getKey()).getWeight();
            }

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
//            System.out.println("check path: " + path);
            System.out.println("check miss: " + miss);
            System.out.println("curr i: " + i);

            double t_val = val + shortestPathDist(path.get(path.size() - 1).getKey(), miss.get(i).getKey());
            ArrayList<NodeData> t_miss = new ArrayList<>(miss);
            System.out.println("shortest list: " + shortestPath(path.get(path.size() - 1).getKey(), miss.get(i).getKey()));
            List<NodeData> t_path = update(path, shortestPath(path.get(path.size() - 1).getKey(), miss.get(i).getKey()), t_miss);

            System.out.println("check missNUM2: " + t_miss);


            List<NodeData> temp_list = tspRec(t_path, t_miss, t_val, final_v);

            if (t_val < final_v) {
                System.out.println("curr_ PATH: " + temp_list);
                path = temp_list;
                final_v = t_val;
            }

        }
        return path;
    }

    public List<NodeData> update(List<NodeData> path, List<NodeData> shortest, ArrayList<NodeData> miss) {
        List<NodeData> ans = new ArrayList<>(path);
        System.out.println("ANSSSSSSSSSSSSSSSSSSSSSS: " + ans);
        for (int i = 0; i < shortest.size(); i++) {
            if (i > 0) {
                ans.add(shortest.get(i));
            }
            System.out.println(miss.remove(shortest.get(i)));

        }
        return ans;
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
