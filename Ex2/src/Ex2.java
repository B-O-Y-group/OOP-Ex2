import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //some test
    public static void main(String[] args) {

        runGUI("Ex2/data/G2.json");
     //   DirectedWeightedGraph test = new HashOfHashes();
    //    DirectedWeightedGraphAlgorithms test_algo = new MainAlgo(test);



     //   test_algo.load("Ex2/data/G3.json");

      //  test_algo.init(test);


        //System.out.println("Center: " + test_algo.center());;

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
        DirectedWeightedGraphAlgorithms alg = new MainAlgo(getGrapg(json_file));
        // ****** Add your code here ******

        System.out.println("========================================================================");
        DirectedWeightedGraph graph = new HashOfHashes();



        NodeData a = new Vertex(0, new Point3D(80,341.19589389346247, 0));
        NodeData b = new Vertex(1, new Point3D(10,220.10318254621849, 0));
        NodeData c = new Vertex(2, new Point3D(270,202.1025646605042, 0));
        NodeData d = new Vertex(3, new Point3D(350,102.10107446554622, 0));

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);


        graph.connect(a.getKey(), b.getKey(), 1);
        graph.connect(a.getKey(), c.getKey(), 2);
        graph.connect(c.getKey(), d.getKey(), 1);

        graph.connect(d.getKey(), b.getKey(), 2);


        graph.connect(b.getKey(), c.getKey(), 4);

        DirectedWeightedGraphAlgorithms al = new MainAlgo(graph);


        window win = new window(alg);


        //
        // ********************************
    }

    public static DirectedWeightedGraphAlgorithms test() {
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
        return hAlgo;
    }
}