import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //some test
    public static void main(String[] args) {

        //runGUI("G1.json");

        DirectedWeightedGraph graph = new HashOfHashes();
        DirectedWeightedGraphAlgorithms g_algo = new MainAlgo(graph);



        DirectedWeightedGraph test = new HashOfHashes();
        DirectedWeightedGraphAlgorithms test_algo = new MainAlgo(test);

//        NodeData s = new Vertex(0,new Point3D(1,1,1));
//        NodeData a = new Vertex(1,new Point3D(1,1,1));
//        NodeData b = new Vertex(2,new Point3D(1,1,1));
//        NodeData c = new Vertex(3,new Point3D(1,1,1));
//        NodeData d = new Vertex(4,new Point3D(1,1,1));
//
//        test.addNode(s);
//        test.addNode(a);
//        test.addNode(b);
//        test.addNode(c);
//        test.addNode(d);
//
//        test.connect(s.getKey(), a.getKey(), 1);
//        test.connect(s.getKey(), b.getKey(), 0);
//        test.connect(s.getKey(), c.getKey(), 1);
//        test.connect(s.getKey(), d.getKey(), 1);
//
//        test.connect(a.getKey(), b.getKey(), 0);
//        test.connect(a.getKey(), c.getKey(), 1);
//        test.connect(a.getKey(), d.getKey(), 1);
//
//
//        test.connect(b.getKey(), c.getKey(), 0);
//        test.connect(c.getKey(), d.getKey(), 0);
//        test.connect(d.getKey(), s.getKey(), 1);

        g_algo.load("Ex2/data/G1.json");

        System.out.println("isConnected: " + g_algo.isConnected());
//        System.out.println("NODE LIST: " + tsp_test);
//        System.out.println("ANSWER SHORTEST: " + g_algo.shortestPath(20, 45));
//        System.out.println("SHORTEST DIST: " + g_algo.shortestPathDist(20, 45));
//        System.out.println("TSP: " + g_algo.tsp(g_algo.shortestPath(1, 16)));
        System.out.println("Center: " + g_algo.center());



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
//        window w = new window();


        //
        // ********************************
    }
}