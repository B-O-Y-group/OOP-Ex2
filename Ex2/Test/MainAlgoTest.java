import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainAlgoTest {


    DirectedWeightedGraphAlgorithms gAlgo;
    DirectedWeightedGraph g;
    NodeData n0 = new Vertex(0, new Point3D(-10, 4, 0));
    NodeData n1 = new Vertex(1, new Point3D(-5, 10, 0));
    NodeData n2 = new Vertex(2, new Point3D(10, 10, 0));
    NodeData n3 = new Vertex(3, new Point3D(9, -1, 0));
    NodeData n4 = new Vertex(4, new Point3D(-4, 0, 0));

    @BeforeEach
    void init() {
        g = new HashOfHashes();
        gAlgo = new MainAlgo(g);
        g.addNode(n0);
        g.addNode(n1);
        g.addNode(n2);
        g.addNode(n3);
        g.addNode(n4);
        g.connect(0, 1, 10);
        g.connect(0, 4, 5);
        g.connect(1, 4, 2);
        g.connect(1, 2, 1);
        g.connect(2, 3, 4);
        g.connect(3, 0, 7);
        g.connect(3, 2, 6);
        g.connect(4, 1, 3);
        g.connect(4, 2, 9);
        g.connect(4, 3, 2);
        gAlgo.init(g);

    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {

        System.out.println("here we are ");
        DirectedWeightedGraph copy = gAlgo.copy();

        System.out.println("real ---->" + g.getNode(this.g.nodeIter().next().getKey()).toString());
        System.out.println("copy --->" + copy.getNode(copy.nodeIter().next().getKey()).toString());



        assertEquals(g.getNode(this.g.nodeIter().next().getKey()).toString(),
                copy.getNode(copy.nodeIter().next().getKey()).toString());
        // copy.removeNode(0);
        // assertNotEquals(g.getNode(1).toString(), copy.getNode(1).toString());
    }

    @Test
    void isConnected() {
//        assertTrue(gAlgo.isConnected());
//        g.removeEdge(2,3);
//        assertFalse(gAlgo.isConnected());
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void center() {
    }

    @Test
    void tsp() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}