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
        runGUI("Ex2/data/G3.json");
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
        // ****** Add your code here ******
        //
        // ********************************
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

        // ****** Add your code here ******
        //
        // ********************************
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

//        NodeData a = new Vertex(0, new Point3D(35.19589389346247, 35.19589389346247, 0));
//        NodeData b = new Vertex(1, new Point3D(35.20319591121872,32.10318254621849, 0));
//        NodeData c = new Vertex(2, new Point3D(35.20752617756255,32.1025646605042, 0));
//        NodeData d = new Vertex(3, new Point3D(35.21007339305892,32.10107446554622, 0));
//        NodeData e = new Vertex(4, new Point3D(35.21310882485876,32.104636394957986, 0));
//        NodeData f = new Vertex(5, new Point3D(35.212111165456015,32.106235628571426, 0));
//        NodeData g = new Vertex(6, new Point3D(35.20797194027441,32.104854472268904, 0));
//        NodeData h = new Vertex(7, new Point3D(35.205764353510894,32.106326494117646, 0));
//        NodeData i = new Vertex(8, new Point3D(35.20154022114608,32.10594485882353, 0));
//        NodeData j = new Vertex(9, new Point3D(35.19805902663438,32.10525428067227, 0));
//        NodeData k = new Vertex(10, new Point3D(35.197400995964486,32.10510889579832, 0));
//        NodeData l = new Vertex(11, new Point3D(35.19351649233253,32.1061811092437, 0));
//        NodeData m = new Vertex(12, new Point3D(35.18950462792575,32.10788938151261, 0));
//        NodeData o = new Vertex(13, new Point3D(35.189568308313156,32.106617263865544, 0));
//        NodeData n = new Vertex(14, new Point3D(35.18869800968523,32.104927164705884, 0));
//        NodeData p = new Vertex(15, new Point3D(35.187594216303474,32.10378225882353, 0));
//        NodeData w = new Vertex(16, new Point3D(35.19381366747377,32.102419275630254, 0));

        NodeData a = new Vertex(0, new Point3D(80,341.19589389346247, 0));
        NodeData b = new Vertex(1, new Point3D(10,220.10318254621849, 0));
        NodeData c = new Vertex(2, new Point3D(270,202.1025646605042, 0));
        NodeData d = new Vertex(3, new Point3D(350,102.10107446554622, 0));

        graph.addNode(a);
        graph.addNode(b);
        graph.addNode(c);
        graph.addNode(d);
////        graph.addNode(e);
////        graph.addNode(f);
////        graph.addNode(g);
////        graph.addNode(h);
////        graph.addNode(i);
////        graph.addNode(j);
////        graph.addNode(k);
////        graph.addNode(l);
////        graph.addNode(m);
////        graph.addNode(o);
////        graph.addNode(n);
////        graph.addNode(p);
////        graph.addNode(w);
//
//
        graph.connect(a.getKey(), b.getKey(), 1);
        graph.connect(a.getKey(), c.getKey(), 2);
        graph.connect(c.getKey(), d.getKey(), 1);

        graph.connect(d.getKey(), b.getKey(), 2);


        graph.connect(b.getKey(), c.getKey(), 4);

        DirectedWeightedGraphAlgorithms al = new MainAlgo(graph);


        window win = new window(al);


        //
        // ********************************
    }
}