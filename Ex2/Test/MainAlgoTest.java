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


    //TODO for shortest
    DirectedWeightedGraphAlgorithms sAlgo;
    DirectedWeightedGraph s;











    @BeforeEach
    void init() {
        g = new HashOfHashes();
        gAlgo = new MainAlgo(g);

        NodeData k0 = new Vertex(0, new Point3D(1, 2, 0));
        NodeData k1 = new Vertex(1, new Point3D(2, 3, 0));
        NodeData k2 = new Vertex(2, new Point3D(4, 3, 0));
        NodeData k3 = new Vertex(3, new Point3D(5, 3, 0));
        NodeData k4 = new Vertex(4, new Point3D(6, 2, 0));
        NodeData k5 = new Vertex(5, new Point3D(5, 1, 0));
        NodeData k6 = new Vertex(6, new Point3D(4, 1, 0));
        NodeData k7 = new Vertex(7, new Point3D(2, 1, 0));
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