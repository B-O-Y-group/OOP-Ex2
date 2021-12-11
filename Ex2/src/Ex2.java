import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.Iterator;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //some test
    public static void main(String[] args) {



        runGUI("Ex2/data/G2.json");
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
}