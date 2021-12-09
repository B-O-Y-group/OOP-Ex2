import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //some test
    public static void main(String[] args) {

        //runGUI("G1.json");

        DirectedWeightedGraph graph = new HashOfHashes();
        DirectedWeightedGraphAlgorithms g_algo = new MainAlgo(graph);
        NodeData s = new Vertex(0, new Point3D(3, 3, 0));
        NodeData a = new Vertex(1, new Point3D(2, 1, 0));
        NodeData b = new Vertex(2, new Point3D(2, 2, 0));
        NodeData c = new Vertex(3, new Point3D(3, 3, 0));

        graph.addNode(s);
        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);


        graph.connect(s.getKey(), a.getKey(), 1);
        graph.connect(s.getKey(), b.getKey(), 2);
        graph.connect(a.getKey(), b.getKey(), 2);
        graph.connect(a.getKey(), c.getKey(), 3);
        graph.connect(b.getKey(), c.getKey(), 0);

        Iterator<EdgeData> it = graph.edgeIter(s.getKey());
        while (it.hasNext()) {
            System.out.println(it.next());

        }

        List<NodeData> tsp_test = new ArrayList<>();
        Iterator<NodeData> n_it = graph.nodeIter();
        while (n_it.hasNext()) {
            tsp_test.add(n_it.next());
        }
        System.out.println("NODE LIST: " + tsp_test);
        System.out.println("SHORTEST DIST: " + g_algo.shortestPathDist(s.getKey(), c.getKey()));
        System.out.println("ANSWER SHORTEST: " + g_algo.shortestPath(s.getKey(), c.getKey()));
//        System.out.println("TSP: " + g_algo.tsp(tsp_test));


//


//        Iterator<NodeData> t = graph.nodeIter();
//        while (t.hasNext()) {
//            NodeData temp = t.next();
//            System.out.println(temp);
//        }

        //graph.nodeIter().forEachRemaining(nodeData -> System.out.println(nodeData.getKey()));

    }


    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = null;
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        window w = new window();


        //
        // ********************************
    }
}