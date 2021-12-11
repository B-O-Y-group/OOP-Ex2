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
        DirectedWeightedGraph new_g = new HashOfHashes();

        Iterator<NodeData> copyN = this.graph.nodeIter();
        while (copyN.hasNext()) {
            NodeData next = copyN.next();
            new_g.addNode(next);

        }
        Iterator<EdgeData> copyE = this.graph.edgeIter();
        while (copyE.hasNext()){
            EdgeData next = copyE.next();
            new_g.connect(next.getSrc(), next.getDest(), next.getWeight());
        }
        return new_g;
    }

    // check if each node has (n-1) pathes.
    @Override
    public boolean isConnected() {
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            NodeData next = it.next();
            if (!BFS(next.getKey())) {
                return false;
            }
        }
        return true;
    }

    public boolean BFS(int s) {
        double[] dist = new double[this.graph.nodeSize()];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Queue<Integer> queue = new LinkedList<>();
        dist[s] = 0;
        queue.add(s);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            try {
                Iterator<EdgeData> it = this.graph.edgeIter(u);
                while (it.hasNext()) {
                    EdgeData v = it.next();
                    if (dist[v.getDest()] == Double.POSITIVE_INFINITY) {
                        queue.add(v.getDest());
                        dist[v.getDest()] = dist[u] + 1;
                    }

                }
            } catch (NullPointerException e) {
                return false;
            }

        }
        for (double i :
                dist) {
            if (i == Double.POSITIVE_INFINITY) {
                return false;
            }
        }
        return true;
    }


    // get by shortest().
    @Override
    public double shortestPathDist(int src, int dest) {
        int[] visited = new int[this.graph.nodeSize()];
        int visits = 0;
        double[] dist = new double[this.graph.nodeSize()];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        PriorityQueue<EdgeData> queue = new PriorityQueue<>(Comparator.comparingDouble(EdgeData::getWeight));

        //FOR TEST
        ArrayList<Integer> TEST = new ArrayList<>();
        //FOR TEST

        int curr_ver = this.graph.getNode(src).getKey();
        dist[curr_ver] = 0;
        while (visits < this.graph.nodeSize()) {
            visits++;
            if (visits != 1 && !queue.isEmpty()) {
                System.out.println(queue);
                curr_ver = Objects.requireNonNull(queue.poll()).getDest();
            }
//            if (curr_ver == dest) {
//                break;
//            }
            visited[curr_ver] = 2;
            //FOR TEST
            System.out.println(TEST);
            TEST.clear();
            //FOR TEST
            try {
                Iterator<EdgeData> it = this.graph.edgeIter(curr_ver);
                while (it.hasNext()) {
                    EdgeData next = it.next();
                    //FOR TEST
                    TEST.add(next.getDest());
                    //FOR TEST
                    if (visited[next.getDest()] != 2) {

                        double temp_dist = dist[curr_ver] + next.getWeight();
                        if (temp_dist < dist[next.getDest()]) {
                            dist[next.getDest()] = temp_dist;
                            visited[next.getDest()] = 1;
                            queue.add(next);
                        }
                    }
                }
            } catch (NullPointerException ignored) {
            }

        }
        System.out.println(visits);
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
        PriorityQueue<EdgeData> queue = new PriorityQueue<>(Comparator.comparingDouble(EdgeData::getWeight));

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

                    double temp_dist = dist[curr_ver] + next.getWeight();
                    if (temp_dist < dist[next.getDest()]) {
                        dist[next.getDest()] = temp_dist;
                        prev[next.getDest()] = curr_ver;
                        visited[next.getDest()] = 1;
                        queue.add(next);
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
        Iterator<NodeData> it = this.graph.nodeIter();
        double min_path = Double.POSITIVE_INFINITY;
        NodeData center = null;
        while (it.hasNext()) {
            double temp = 0;
            NodeData next = it.next();
            Iterator<NodeData> sum_it = this.graph.nodeIter();
            while (sum_it.hasNext()) {
                NodeData curr = sum_it.next();
                if (next.getKey() != curr.getKey()) {
                    temp += shortestPathDist(next.getKey(), curr.getKey());
                }
            }
            if (temp < min_path) {
                min_path = temp;
                center = next;
            }
        }
        return center;


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
                curr_weight += this.graph.getEdge(list_t.get(i).getKey(), list_t.get(i + 1).getKey()).getWeight();
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

            JsonArray jsonArrayOfNodes = fileObject.get("Nodes").getAsJsonArray();
            for (JsonElement NodesElement : jsonArrayOfNodes) {
                JsonObject NodeObjects = NodesElement.getAsJsonObject();


                int id = NodeObjects.get("id").getAsInt();

                String[] g = NodeObjects.get("pos").getAsString().split(",");
                GeoLocation geoLocation = new Point3D(Double.parseDouble(g[0]), Double.parseDouble(g[1]), Double.parseDouble(g[2]));
                NodeData n = new Vertex(id, geoLocation);
                System.out.println(n);
                this.graph.addNode(n);
            }
                JsonArray jsonArrayOfEdge = fileObject.get("Edges").getAsJsonArray();
            for (JsonElement EdgesElement : jsonArrayOfEdge) {
                JsonObject EdgesObjects = EdgesElement.getAsJsonObject();

                int src = EdgesObjects.get("src").getAsInt();
                double weight = EdgesObjects.get("w").getAsDouble();
                int dest = EdgesObjects.get("dest").getAsInt();
                System.out.println(new Edge(src, dest, weight));
                this.graph.connect(src, dest, weight);
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
