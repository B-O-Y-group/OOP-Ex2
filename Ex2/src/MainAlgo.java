import api.*;
import com.google.gson.*;

import java.io.*;
import java.util.*;

public class MainAlgo implements DirectedWeightedGraphAlgorithms {

    public DirectedWeightedGraph graph;
    private int max_node;
    private boolean max_node_activate;

    public MainAlgo(DirectedWeightedGraph h) {
        max_node = 0;
        Iterator<NodeData> it = h.nodeIter();
        while (it.hasNext()) {
            NodeData next = it.next();
            if (next.getKey() > max_node) {
                max_node = next.getKey();
            }
        }
        max_node_activate = true;
        init(h);
    }

    public void max_node() {
        Iterator<NodeData> it = this.graph.nodeIter();
        while (it.hasNext()) {
            NodeData next = it.next();
            if (next.getKey() > max_node) {
                max_node = next.getKey();
            }
        }
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        Iterator<NodeData> it = g.nodeIter();
        while (it.hasNext()) {
            NodeData next = it.next();
            if (next.getKey() > max_node) {
                max_node = next.getKey();
            }
        }
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.graph;
    }


    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph new_g = new HashOfHashes();

        Iterator<NodeData> copyN = this.graph.nodeIter();
        while (copyN.hasNext()) {
            NodeData next = copyN.next();
            NodeData copy_N = new Vertex(next.getKey(), next.getLocation());
            new_g.addNode(copy_N);

        }
        Iterator<EdgeData> copyE = this.graph.edgeIter();
        while (copyE.hasNext()) {
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

    // im // implement of BFS
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
        if (src == dest) {
            return 0;
        }
        if (!max_node_activate) {
            max_node();
        }

        DirectedWeightedGraph new_g = copy();
        int visits = 0;
        PriorityQueue<NodeData> queue = new PriorityQueue<>(Comparator.comparingDouble(NodeData::getWeight));

        NodeData curr_ver = new_g.getNode(src);
        do {
            visits++;
            if (visits != 1) {
                curr_ver = queue.poll();
            }
            if (curr_ver.getKey() == dest) {
                break;
            }
            new_g.getNode(curr_ver.getKey()).setTag(2);
            Iterator<EdgeData> it = new_g.edgeIter(curr_ver.getKey());
            while (it.hasNext()) {
                EdgeData next = it.next();
                if (new_g.getNode(next.getDest()).getTag() != 2) {

                    double temp_dist = new_g.getNode(curr_ver.getKey()).getWeight() + next.getWeight();
                    if (new_g.getNode(next.getDest()).getTag() == 0) {
                        new_g.getNode(next.getDest()).setWeight(temp_dist);
                        new_g.getNode(next.getDest()).setTag(1);
                        queue.add(new_g.getNode(next.getDest()));
                    }
                    if (temp_dist <= new_g.getNode(next.getDest()).getWeight()) {
                        new_g.getNode(next.getDest()).setWeight(temp_dist);
                    }
                }
            }
        }
        while (!queue.isEmpty());
        return new_g.getNode(dest).getWeight();

    }


    // implement by Dijkstra algorithm.
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if (!max_node_activate) {
            max_node();
        }
        DirectedWeightedGraph new_g = copy();
        int visits = 0;
        int[] prev = new int[this.max_node + 1];
        Arrays.fill(prev, -1);
        PriorityQueue<NodeData> queue = new PriorityQueue<>(Comparator.comparingDouble(NodeData::getWeight));

        NodeData curr_ver = new_g.getNode(src);
        do {
            visits++;
            if (visits != 1) {
                curr_ver = queue.poll();
            }
            if (curr_ver.getKey() == dest) {
                break;
            }
            new_g.getNode(curr_ver.getKey()).setTag(2);
            Iterator<EdgeData> it = new_g.edgeIter(curr_ver.getKey());
            while (it.hasNext()) {
                EdgeData next = it.next();
                if (new_g.getNode(next.getDest()).getTag() != 2) {

                    double temp_dist = new_g.getNode(curr_ver.getKey()).getWeight() + next.getWeight();
                    if (new_g.getNode(next.getDest()).getTag() == 0) {
                        new_g.getNode(next.getDest()).setWeight(temp_dist);
                        prev[next.getDest()] = curr_ver.getKey();
                        new_g.getNode(next.getDest()).setTag(1);
                        queue.add(new_g.getNode(next.getDest()));
                    }
                    if (temp_dist <= new_g.getNode(next.getDest()).getWeight()) {
                        new_g.getNode(next.getDest()).setWeight(temp_dist);
                        prev[next.getDest()] = curr_ver.getKey();
                    }
                }
            }
        }
        while (!queue.isEmpty());
        List<NodeData> ans = new ArrayList<>();
        ans.add(new_g.getNode(dest));
        int pointer = prev[dest];
        while (pointer != -1) {
            ans.add(new_g.getNode(pointer));
            pointer = prev[pointer];
        }
        Collections.reverse(ans);

        return ans;
    }

    @Override
    public NodeData center() {
        if (!max_node_activate) {
            max_node();
        }

        DirectedWeightedGraph new_g = copy();
        double final_c = Double.POSITIVE_INFINITY;
        double first = Double.NEGATIVE_INFINITY;
        NodeData center = null;
        int counter = 0;
        Iterator<NodeData> it = new_g.nodeIter();
        while (it.hasNext()) {
            counter++;

            NodeData next = it.next();
            double[] dist = new double[max_node + 1];
            allPath(next.getKey(), dist);
            double max = 0;
            for (int i = 0; i < dist.length; i++) {
                if (dist[i] > max) {
                    max = dist[i];
                }
            }
            if (max < final_c) {
                final_c = max;
                center = next;
            }
        }


        return center;


    }

    public void allPath(int src, double[] dist) {
        DirectedWeightedGraph new_g = copy();
        int visits = 0;
        PriorityQueue<NodeData> queue = new PriorityQueue<>(Comparator.comparingDouble(NodeData::getWeight));

        NodeData curr_ver = new_g.getNode(src);
        do {
            visits++;
            if (visits != 1) {
                curr_ver = queue.poll();
                dist[curr_ver.getKey()] = new_g.getNode(curr_ver.getKey()).getWeight();
            }
            new_g.getNode(curr_ver.getKey()).setTag(2);
            Iterator<EdgeData> it = new_g.edgeIter(curr_ver.getKey());
            while (it.hasNext()) {
                EdgeData next = it.next();
                if (new_g.getNode(next.getDest()).getTag() != 2) {

                    double temp_dist = new_g.getNode(curr_ver.getKey()).getWeight() + next.getWeight();
                    if (new_g.getNode(next.getDest()).getTag() == 0) {
                        new_g.getNode(next.getDest()).setWeight(temp_dist);
                        new_g.getNode(next.getDest()).setTag(1);
                        queue.add(new_g.getNode(next.getDest()));
                    }
                    if (temp_dist <= new_g.getNode(next.getDest()).getWeight()) {
                        new_g.getNode(next.getDest()).setWeight(temp_dist);
                    }
                }
            }
        }
        while (!queue.isEmpty());
//        System.out.println(Arrays.toString(dist));
    }


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        double temp = Double.POSITIVE_INFINITY;
        List<NodeData> path = Collections.emptyList();
        ArrayList<NodeData> sorted_cities = new ArrayList<>(cities);
        sorted_cities.sort((a, b) -> a.getKey() - b.getKey());
        for (NodeData next : sorted_cities) {
            List<NodeData> path_init = new ArrayList<>(Collections.emptyList());
            path_init.add(next);

            ArrayList<NodeData> miss = new ArrayList<>(sorted_cities);
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
        List<NodeData> ans = new ArrayList<>(path);

        for (int i = 0; i < shortest.size(); i++) {
            if (i > 0) {
                ans.add(shortest.get(i));
            }
        }
        return ans;
    }


    @Override
    public boolean save(String file) {
        Gson gson = new GsonBuilder().create();
        try {
            FileOutputStream fileElement = new FileOutputStream(file);
            ObjectOutputStream output = new ObjectOutputStream(fileElement);

            output.writeObject(this.graph);
            output.close();
            fileElement.close();

            System.out.println("File has been created");
        } catch (IOException ioException) {
            System.out.println("Error");
            ioException.printStackTrace();
            return false;
        }
        return true;
    }
    @Override
    public boolean load(String file) {
        File input = new File(file);
        max_node_activate = false;
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

                this.graph.addNode(n);
            }
            JsonArray jsonArrayOfEdge = fileObject.get("Edges").getAsJsonArray();
            for (JsonElement EdgesElement : jsonArrayOfEdge) {
                JsonObject EdgesObjects = EdgesElement.getAsJsonObject();

                int src = EdgesObjects.get("src").getAsInt();
                double weight = EdgesObjects.get("w").getAsDouble();
                int dest = EdgesObjects.get("dest").getAsInt();

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
