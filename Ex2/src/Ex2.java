import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //some test
    public static void main(String[] args) {

        //runGUI("G1.json");

        DirectedWeightedGraph graph = new HashOfHashes();
        NodeData a = new Vertex(1, new Point3D(2, 1, 0));
        NodeData b = new Vertex(5, new Point3D(2, 2, 0));
        NodeData c = new Vertex(3, new Point3D(3, 3, 0));

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);


        graph.connect(a.getKey(), b.getKey(), 1);
        graph.connect(a.getKey(), c.getKey(), 1);
        graph.removeEdge(a.getKey(), c.getKey());
        graph.connect(c.getKey(), b.getKey(), 1);
//        System.out.println(graph.getEdge(a.getKey(), b.getKey()));


        Iterator<EdgeData> it = graph.edgeIter();
        while (it.hasNext()) {
//            EdgeData test = it.next();
            System.out.println(it.next());

        }
//


        Iterator<NodeData> t = graph.nodeIter();
        while (t.hasNext()) {
            NodeData temp = t.next();
            System.out.println(temp);
        }

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