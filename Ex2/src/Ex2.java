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
        g_algo.load("Ex2/data/G1.json");


        DirectedWeightedGraph test = new HashOfHashes();
        DirectedWeightedGraphAlgorithms test_algo = new MainAlgo(test);

        DirectedWeightedGraphAlgorithms hAlgo;
        DirectedWeightedGraph h;
        h = new HashOfHashes();
        hAlgo = new MainAlgo(h);

        NodeData h1 = new Vertex(1, new Point3D(2, 1, 0));
        NodeData h2 = new Vertex(2, new Point3D(3, 1, 0));
        NodeData h3 = new Vertex(3, new Point3D(4, 2, 0));
        NodeData h4 = new Vertex(4, new Point3D(3, 3, 0));
        NodeData h5 = new Vertex(5, new Point3D(2, 3, 0));
        NodeData h6 = new Vertex(6, new Point3D(1, 2, 0));

        h.addNode(h1);
        h.addNode(h2);
        h.addNode(h3);
        h.addNode(h4);
        h.addNode(h5);
        h.addNode(h6);



        h.connect(h1.getKey(), h2.getKey(), 1);
        h.connect(h1.getKey(), h6.getKey(), 1);
        h.connect(h1.getKey(), h5.getKey(), 1);
        h.connect(h2.getKey(), h1.getKey(), 1);
        h.connect(h2.getKey(), h3.getKey(), 1);
        h.connect(h2.getKey(), h4.getKey(), 1);
        h.connect(h2.getKey(), h5.getKey(), 1);
        h.connect(h3.getKey(), h2.getKey(), 1);
        h.connect(h3.getKey(), h4.getKey(), 1);
        h.connect(h4.getKey(), h2.getKey(), 1);
        h.connect(h4.getKey(), h3.getKey(), 1);
        h.connect(h4.getKey(), h5.getKey(), 1);
        h.connect(h5.getKey(), h1.getKey(), 1);
        h.connect(h5.getKey(), h2.getKey(), 1);
        h.connect(h5.getKey(), h6.getKey(), 1);
        h.connect(h5.getKey(), h4.getKey(), 1);
        h.connect(h6.getKey(), h1.getKey(), 1);
        h.connect(h6.getKey(), h5.getKey(), 1);

        hAlgo.init(h);
    }

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
//


//        System.out.println("isConnected: " + test_algo.isConnected());
////        System.out.println("NODE LIST: " + tsp_test);
//        System.out.println("SHORTEST DIST: " + test_algo.shortestPathDist(s.getKey(), d.getKey()));
//        System.out.println("ANSWER SHORTEST: " + test_algo.shortestPath(s.getKey(), d.getKey()));
////        System.out.println("TSP: " + g_algo.tsp(tsp_test));
//        System.out.println("Center: " + g_algo.center());



//


//        Iterator<NodeData> t = graph.nodeIter();
//        while (t.hasNext()) {
//            NodeData temp = t.next();
//            System.out.println(temp);
//        }

        //graph.nodeIter().forEachRemaining(nodeData -> System.out.println(nodeData.getKey()));

//    }


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