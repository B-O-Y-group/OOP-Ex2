import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //some test
    public static void main(String[] args) {

        //runGUI("G1.json");

        DirectedWeightedGraph graph = new HashOfHashes();
        DirectedWeightedGraphAlgorithms g_algo = new MainAlgo(graph);
        g_algo.load("C:\\Users\\oron\\Desktop\\GitHub\\OOP-Ex2\\Ex2\\data\\G1.json");


        System.out.println("isConnected: " + g_algo.isConnected());
//        System.out.println("NODE LIST: " + tsp_test);
//        System.out.println("SHORTEST DIST: " + g_algo.shortestPathDist(s.getKey(), c.getKey()));
//        System.out.println("ANSWER SHORTEST: " + g_algo.shortestPath(s.getKey(), c.getKey()));
//        System.out.println("TSP: " + g_algo.tsp(tsp_test));
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
        window w = new window();


        //
        // ********************************
    }
}