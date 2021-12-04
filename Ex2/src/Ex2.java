import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //some test
    public static void main(String[] args) {
        HashOfHashes graph = new HashOfHashes();
        Vertex a = new Vertex(1,new Point3D(1,1,0));
        Vertex b = new Vertex(2,new Point3D(2,2,0));
        Vertex c = new Vertex(3,new Point3D(3,3,0));

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        System.out.println(graph.getNode(1));

        Edge e= new Edge(a.getKey(), b.getKey(), 1);

        graph.addEdge(e);

        System.out.println(graph.getEdge(e.src,e.dest));
//       while (graph.nodeIter().hasNext()) {
//         graph.nodeIter().forEachRemaining(nodeData ->  System.out.println( nodeData.getKey()));
//
//
//       }
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
        //
        // ********************************
    }
}