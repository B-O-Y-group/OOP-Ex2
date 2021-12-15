import api.*;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {


    //Main
    public static void main(String[] args) {

        runGUI("Ex2/data/G1.json");
        DirectedWeightedGraph s = new HashOfHashes();
        DirectedWeightedGraphAlgorithms test = new MainAlgo(s);
        test.load("Ex2/data/G1.json");


        System.out.println(test.center());




        //---------1,000 Nodes---------
//        DirectedWeightedGraph graph_1 = new api.HashOfHashes();
//        DirectedWeightedGraphAlgorithms g_algo_1 = new api.MainAlgo(graph_1);
//        g_algo_1.load("Ex2/data/1000Nodes.json");
//        long startTime = System.currentTimeMillis();
//        String first = "" + g_algo_1.isConnected();
//        long endTime = System.currentTimeMillis();
//        long final_time = endTime - startTime;
//        System.out.print("1,000 Nodes --> isConnected: " + first + "  " + final_time + " MS  ");
//
//        startTime = System.currentTimeMillis();
//        first = "" + g_algo_1.center();
//        endTime = System.currentTimeMillis();
//        final_time = endTime - startTime;
//
//        System.out.println("Center: " + first + "  " + final_time + " MS");


        //---------10,000 Nodes---------
//        System.out.println("\n");
//        DirectedWeightedGraph graph_2 = new HashOfHashes();
//        DirectedWeightedGraphAlgorithms g_algo_2 = new MainAlgo(graph_2);
//        g_algo_2.load("Ex2/data/10000Nodes.json");
//
//        long startTime = System.currentTimeMillis();
//        String first = "" + g_algo_2.isConnected();
//        long endTime = System.currentTimeMillis();
//        long final_time = endTime - startTime;
//        System.out.print("10,000 Nodes --> isConnected: " + first + "  " + final_time + " MS  ");
//
//        startTime = System.currentTimeMillis();
//        first = "" + g_algo_2.center();
//        endTime = System.currentTimeMillis();
//        final_time = endTime - startTime;
//
//        System.out.println("Center: " + first + "  " + final_time + " MS");
//
//        //---------100,000 Nodes---------
//        DirectedWeightedGraph graph_3 = new api.HashOfHashes();
//        DirectedWeightedGraphAlgorithms g_algo_3 = new api.MainAlgo(graph_3);
//        g_algo_3.load("Ex2/data/100000Nodes.json");
//


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

        window win = new window(alg);


    }


}