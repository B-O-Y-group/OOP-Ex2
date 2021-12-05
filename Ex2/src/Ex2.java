import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.util.ArrayList;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //some test
    public static void main(String[] args) {

//        runGUI("G1.json");

        DirectedWeightedGraph graph = new HashOfHashes();
        NodeData a = new Vertex(1,new Point3D(1,1,0));
        NodeData b = new Vertex(2,new Point3D(2,2,0));
        NodeData c = new Vertex(3,new Point3D(3,3,0));

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        System.out.println(graph.getNode(1));

        Edge e= new Edge(a.getKey(), b.getKey(), 1);

        graph.connect(e.src,e.dest,1);

        //System.out.println();

//
        System.out.println(graph.getEdge(e.src,e.dest));

        graph.removeEdge(a.getKey(),b.getKey());
        System.out.println(graph.getEdge(e.src,e.dest));

        ArrayList<Integer> test = new ArrayList<>();
        test.add(0,3);
        test.add(1, 9);
        System.out.println(test);

//       while (graph.nodeIter().hasNext()) {
//         graph.nodeIter().forEachRemaining(nodeData ->  System.out.println( nodeData.getKey()));


       }





    /**
     * This static function will be used to test your implementation
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
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        // ****** Add your code here ******
        window w = new window();




        //
        // ********************************
    }
}