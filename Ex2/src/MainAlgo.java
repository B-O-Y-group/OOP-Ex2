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
        int[] visited = new int[this.max_node + 1];
        System.out.println("MAXXX: " + (this.max_node + 1));
        int visits = 0;
        double[] dist = new double[this.max_node + 1];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        PriorityQueue<EdgeData> queue = new PriorityQueue<>(Comparator.comparingDouble(EdgeData::getWeight));

        //FOR TEST
        ArrayList<Integer> TEST = new ArrayList<>();
        //FOR TEST

        int curr_ver = this.graph.getNode(src).getKey();
        System.out.println("curr " + curr_ver);
        dist[curr_ver] = 0;
        do {
            visits++;
            if (visits != 1) {
                System.out.println(queue);
                curr_ver = queue.poll().getDest();
            }
//            if (curr_ver == dest) {
//                break;
//            }
            visited[curr_ver] = 2;
            //FOR TEST
//            System.out.println(TEST);
            TEST.clear();
            //FOR TEST
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

        }
        while (!queue.isEmpty());
        System.out.println(visits);
        return dist[dest];

    }


    // implement by dixtra algorithm. data structure for this algorithm --> Fibonacci heap
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        int[] visited = new int[this.max_node + 1];
        int visits = 0;
        double[] dist = new double[this.max_node + 1];
        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        int[] prev = new int[this.max_node + 1];
        Arrays.fill(prev, -1);
        PriorityQueue<EdgeData> queue = new PriorityQueue<>(Comparator.comparingDouble(EdgeData::getWeight));

        int curr_ver = this.graph.getNode(src).getKey();
        dist[curr_ver] = 0;
        do {
            visits++;
            if (visits != 1) {
                curr_ver = queue.poll().getDest();
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
        while (!queue.isEmpty());
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

        DirectedWeightedGraph new_g = copy();

        Iterator<NodeData> it = new_g.nodeIter();
        HashMap<Integer, Double> nodes_val = new HashMap<>();

        double final_c = Double.POSITIVE_INFINITY;
        NodeData center = null;
        while (it.hasNext()) {
            double first = Double.NEGATIVE_INFINITY;
            int next = it.next().getKey();
            Iterator<NodeData> it_first = new_g.nodeIter();
            while (it_first.hasNext()) {
                int first_next = it_first.next().getKey();
                double temp = shortestPathDist( first_next,next);
                if (temp > first) {
                    first = temp;
                }
            }
            nodes_val.put(next, first);
        }

        Iterator<Integer> fin_it = nodes_val.keySet().iterator();
        System.out.println("Final LIST = " + nodes_val);
        while (fin_it.hasNext()) {
            int fin_next = fin_it.next();
            if (nodes_val.get(fin_next) < final_c) {
                final_c = nodes_val.get(fin_next);
                center = new_g.getNode(fin_next);
            }
        }
        return center;

//        Iterator<NodeData> it = this.graph.nodeIter();
//        NodeData center = null;
//        double max = Double.NEGATIVE_INFINITY;
//        double min = Double.POSITIVE_INFINITY;
//        HashMap<Double, NodeData> final_list = new HashMap<>();
//        while (it.hasNext()) {
//            NodeData next = it.next();
//            Iterator<NodeData> Node_it = this.graph.nodeIter();
//            ArrayList<Double> list = new ArrayList<>();
//            while (Node_it.hasNext()) {
//                NodeData temp_node = Node_it.next();
//                if (temp_node.getKey() != next.getKey()) {
//
//                    list.add(shortestPathDist(temp_node.getKey(), next.getKey()));
//                }
//
//            }
//            double s = Double.NEGATIVE_INFINITY;
//            for (Double aDouble : list) {
//                if (aDouble > s) {
//                    s = aDouble;
//                }
//            }
//            System.out.println("NEXTTTTT " + next + list);
//            final_list.put(s, next);
//        }
//        double a = Double.POSITIVE_INFINITY;
//        for (double next : final_list.keySet()) {
//            if (next < a) {
//                a = next;
//            }
//        }
//        System.out.println("CHECK " + final_list.keySet());
//
//        return final_list.get(a);

//                NodeData temp_node = Node_it.next();
//                if (temp_node.getKey() != next.getKey()) {
//                    System.out.println("NEXT: " + next + " TEMP: " + temp_node);
//                    double temp = shortestPathDist(next.getKey(), temp_node.getKey());
//                    test.add(temp);
//                    if (temp >= max) {
//                        max = temp;
//                    }
//                }
//
//
//            }
//            System.out.println("NODE ID: " + next.getKey() + " TETTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT" + test);
//            if (max <= min) {
//                min = max;
//                center = next;
//            }
//        }
//        System.out.println("MIN " + min);
//        return center;
//        Iterator<NodeData> it = this.graph.nodeIter();
//        double min_path = Double.POSITIVE_INFINITY;
//        NodeData center = null;
//        while (it.hasNext()) {
//            double temp = 0;
//            NodeData next = it.next();
//            Iterator<NodeData> sum_it = this.graph.nodeIter();
//            while (sum_it.hasNext()) {
//                NodeData curr = sum_it.next();
//                if (next.getKey() != curr.getKey()) {
//                    temp += shortestPathDist(next.getKey(), curr.getKey());
//                }
//            }
//            if (temp < min_path) {
//                min_path = temp;
//                center = next;
//            }
//        }
//        return center;


    }


    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        double temp = Double.POSITIVE_INFINITY;
        List<NodeData> path = Collections.emptyList();
        ArrayList<NodeData> sorted_cities = new ArrayList<>(cities);
        sorted_cities.sort((a,b) -> a.getKey() - b.getKey());
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
//            System.out.println("check path: " + path);
            //  System.out.println("check miss: " + miss);
            //  System.out.println("curr i: " + i);

            double t_val = val + shortestPathDist(path.get(path.size() - 1).getKey(), miss.get(i).getKey());
            ArrayList<NodeData> t_miss = new ArrayList<>(miss);
            //   System.out.println("shortest list: " + shortestPath(path.get(path.size() - 1).getKey(),
            //     miss.get(i).getKey()));
            List<NodeData> t_path = update(path, shortestPath(path.get(path.size() - 1).getKey(), miss.get(i).getKey()), t_miss);

            //   System.out.println("check missNUM2: " + t_miss);


            List<NodeData> temp_list = tspRec(t_path, t_miss, t_val, final_v);

            if (t_val < final_v) {
                //    System.out.println("curr_ PATH: " + temp_list);
                path = temp_list;
                final_v = t_val;
            }

        }
        return path;
    }

    public List<NodeData> update(List<NodeData> path, List<NodeData> shortest, ArrayList<NodeData> miss) {
        List<NodeData> ans = new ArrayList<>(path);
        //    System.out.println("ANSSSSSSSSSSSSSSSSSSSSSS: " + ans);
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
        Gson gson = new Gson();
        try {
            gson.toJson(this.graph, new FileWriter(file));
        } catch (IOException e) {
            e.printStackTrace();
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
//                System.out.println(n);
                this.graph.addNode(n);
            }
            JsonArray jsonArrayOfEdge = fileObject.get("Edges").getAsJsonArray();
            for (JsonElement EdgesElement : jsonArrayOfEdge) {
                JsonObject EdgesObjects = EdgesElement.getAsJsonObject();

                int src = EdgesObjects.get("src").getAsInt();
                double weight = EdgesObjects.get("w").getAsDouble();
                int dest = EdgesObjects.get("dest").getAsInt();
//                System.out.println(new Edge(src, dest, weight));
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
