import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //some test
    public static void main(String[] args) {

        //runGUI(args[0]);
        runGUI("GAL.json");
       // System.out.println(getGrapgAlgo("Ex2/data/G1.json").center());

    }


    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraph graph = new HashOfHashes();
        DirectedWeightedGraphAlgorithms algo = new MainAlgo(graph);
        algo.load(json_file);
        graph = algo.getGraph();

        return graph;
    }

    /**
     * This static function will be used to test your implementation
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {

        DirectedWeightedGraphAlgorithms algo = new MainAlgo(getGrapg(json_file));
        algo.init(getGrapg(json_file));

        return algo;
    }

    /**
     * This static function will run your GUI using the json fime.
     *
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******

//        DirectedWeightedGraph graph = new HashOfHashes();
//        graph = new HashOfHashes();
//        NodeData k0 = new Vertex(0, new Point3D(1, 2, 0));
//        NodeData k1 = new Vertex(1, new Point3D(2, 3, 0));
//        NodeData k2 = new Vertex(2, new Point3D(4, 3, 0));
//        NodeData k3 = new Vertex(3, new Point3D(5, 3, 0));
//        NodeData k4 = new Vertex(4, new Point3D(6, 2, 0));
//        NodeData k5 = new Vertex(5, new Point3D(5, 1, 0));
//        NodeData k6 = new Vertex(6, new Point3D(4, 1, 0));
//        NodeData k7 = new Vertex(7, new Point3D(2, 1, 0));
//        NodeData k8 = new Vertex(8, new Point3D(3, 2, 0));
//
//        graph.addNode(k0);
//        graph.addNode(k1);
//        graph.addNode(k2);
//        graph.addNode(k3);
//        graph.addNode(k4);
//        graph.addNode(k5);
//        graph.addNode(k6);
//        graph.addNode(k7);
//        graph.addNode(k8);
//
//        graph.connect(k0.getKey(), k1.getKey(), 4);
//        graph.connect(k0.getKey(), k7.getKey(), 8);
//        graph.connect(k1.getKey(), k7.getKey(), 11);
//        graph.connect(k2.getKey(), k1.getKey(), 8);
//        graph.connect(k2.getKey(), k3.getKey(), 7);
//        graph.connect(k3.getKey(), k4.getKey(), 9);
//        graph.connect(k4.getKey(), k5.getKey(), 10);
//        graph.connect(k5.getKey(), k2.getKey(), 4);
//        graph.connect(k5.getKey(), k3.getKey(), 14);
//        graph.connect(k6.getKey(), k5.getKey(), 2);
//        graph.connect(k7.getKey(), k8.getKey(), 7);
//        graph.connect(k7.getKey(), k6.getKey(), 1);
//        graph.connect(k8.getKey(), k6.getKey(), 6);
//        graph.connect(k8.getKey(), k2.getKey(), 2);


        //DirectedWeightedGraphAlgorithms alg1 = new MainAlgo(graph);



        window win = new window(alg);


        //
        // ********************************
    }

}