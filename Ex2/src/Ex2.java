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

      DirectedWeightedGraph  g = new HashOfHashes();
       DirectedWeightedGraphAlgorithms gAlgo = new MainAlgo(g);

        NodeData k0 = new Vertex(0, new Point3D(1, 2, 0));
        NodeData k1 = new Vertex(1, new Point3D(2, 1, 0));
        NodeData k2 = new Vertex(2, new Point3D(4, 1, 0));
        NodeData k3 = new Vertex(3, new Point3D(5, 1, 0));
        NodeData k4 = new Vertex(4, new Point3D(6, 2, 0));
        NodeData k5 = new Vertex(5, new Point3D(5, 3, 0));
        NodeData k6 = new Vertex(6, new Point3D(4, 3, 0));
        NodeData k7 = new Vertex(7, new Point3D(2, 3, 0));
        NodeData k8 = new Vertex(8, new Point3D(3, 2, 0));

        g.addNode(k0);
        g.addNode(k1);
        g.addNode(k2);
        g.addNode(k3);
        g.addNode(k4);
        g.addNode(k5);
        g.addNode(k6);
        g.addNode(k7);
        g.addNode(k8);

        g.connect(k0.getKey(), k1.getKey(), 4);
        g.connect(k0.getKey(), k7.getKey(), 8);
        g.connect(k1.getKey(), k7.getKey(), 11);
        g.connect(k2.getKey(), k1.getKey(), 8);
        g.connect(k2.getKey(), k3.getKey(), 7);
        g.connect(k3.getKey(), k4.getKey(), 9);
        g.connect(k4.getKey(), k5.getKey(), 10);
        g.connect(k5.getKey(), k2.getKey(), 4);
        g.connect(k5.getKey(), k3.getKey(), 14);
        g.connect(k6.getKey(), k5.getKey(), 2);
        g.connect(k7.getKey(), k8.getKey(), 7);
        g.connect(k7.getKey(), k6.getKey(), 1);
        g.connect(k8.getKey(), k6.getKey(), 6);
        g.connect(k8.getKey(), k2.getKey(), 2);
        g.connect(k0.getKey(), k7.getKey(), 8);

        gAlgo.init(g);



        window win = new window(gAlgo);


        //
        // ********************************
    }
}