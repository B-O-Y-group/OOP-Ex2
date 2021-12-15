import api.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainAlgoTest {


    DirectedWeightedGraphAlgorithms gAlgo;
    DirectedWeightedGraph g;
    DirectedWeightedGraphAlgorithms hAlgo;
    DirectedWeightedGraph h;
    NodeData n0 = new Vertex(0, new Point3D(-10, 4, 0));
    NodeData n1 = new Vertex(1, new Point3D(-5, 10, 0));
    NodeData n2 = new Vertex(2, new Point3D(10, 10, 0));
    NodeData n3 = new Vertex(3, new Point3D(9, -1, 0));
    NodeData n4 = new Vertex(4, new Point3D(-4, 0, 0));



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


        h = new HashOfHashes();
        hAlgo = new MainAlgo(h);

        NodeData h1 = new Vertex(1, new Point3D(2, 1, 0));
        NodeData h2 = new Vertex(2, new Point3D(3, 1, 0));
        NodeData h3 = new Vertex(3, new Point3D(4, 2, 0));
        NodeData h4 = new Vertex(4, new Point3D(3, 3, 0));
        NodeData h5 = new Vertex(5, new Point3D(2, 3, 0));
        NodeData h6 = new Vertex(6, new Point3D(1, 2, 0));

        h.addNode(h1);
        h.addNode(h2);
        h.addNode(h3);
        h.addNode(h4);
        h.addNode(h5);
        h.addNode(h6);

        h.connect(h1.getKey(), h2.getKey(), 1);
        h.connect(h1.getKey(), h6.getKey(), 1);
        h.connect(h1.getKey(), h5.getKey(), 1);
        h.connect(h2.getKey(), h1.getKey(), 1);
        h.connect(h2.getKey(), h3.getKey(), 1);
        h.connect(h2.getKey(), h4.getKey(), 1);
        h.connect(h2.getKey(), h5.getKey(), 1);
        h.connect(h3.getKey(), h2.getKey(), 1);
        h.connect(h3.getKey(), h4.getKey(), 1);
        h.connect(h4.getKey(), h2.getKey(), 1);
        h.connect(h4.getKey(), h3.getKey(), 1);
        h.connect(h4.getKey(), h5.getKey(), 1);
        h.connect(h5.getKey(), h1.getKey(), 1);
        h.connect(h5.getKey(), h2.getKey(), 1);
        h.connect(h5.getKey(), h6.getKey(), 1);
        h.connect(h5.getKey(), h4.getKey(), 1);
        h.connect(h6.getKey(), h1.getKey(), 1);
        h.connect(h6.getKey(), h5.getKey(), 1);

        hAlgo.init(h);
    }

    @Test
    void getGraph() {
        DirectedWeightedGraphAlgorithms a = new MainAlgo(g);
        assertEquals(a.getGraph(),g);
    }

    @Test
    void copy() {
        DirectedWeightedGraph copy = gAlgo.copy();

        System.out.println("real ---->" + g.getNode(this.g.nodeIter().next().getKey()).toString());
        System.out.println("copy --->" + copy.getNode(copy.nodeIter().next().getKey()).toString());


        Iterator<NodeData> nodeiter = this.g.nodeIter();
        while (nodeiter.hasNext()){
            NodeData node = nodeiter.next();
    }
    }
    @Test
    void isConnected() {
        DirectedWeightedGraph g = new HashOfHashes();
        DirectedWeightedGraphAlgorithms f = new MainAlgo(g);
        f.getGraph().addNode(new Vertex(0, new Point3D(1,1,1)));
        f.getGraph().addNode(new Vertex(1, new Point3D(1,1,1)));
        f.getGraph().connect(0, 1, 5);
        f.getGraph().connect(1, 0, 5);
        f.init(g);
        assertTrue(f.isConnected());
        f.load("Ex2/data/G1.json");
        assertTrue(f.isConnected());
//        assertTrue(hAlgo.isConnected());
//        h.removeEdge(1,6);
//        h.removeEdge(6,1);
//        h.removeEdge(6,5);
//        h.removeEdge(5,6);
//        hAlgo.init(h);
//        assertFalse(hAlgo.isConnected());
    }

    @Test
    void shortestPathDist() {
        assertEquals(1,hAlgo.shortestPathDist(1,2));

    }

    @Test
    void shortestPath() {
//        assertEquals("[Id: 0, Id: 7, Id: 6, Id: 5, Id: 2, Id: 3, Id: 4]",gAlgo.shortestPath(0,4).toString());
        assertEquals("[Id: 1, Id: 2]", hAlgo.shortestPath(1,2).toString() );
    }

    @Test
    void center() {
        assertEquals(1, hAlgo.center().getKey());

    }

    @Test
    void tsp() {
        DirectedWeightedGraph p;
        DirectedWeightedGraphAlgorithms pAlgo;
        p = new HashOfHashes();


        NodeData p1 = new Vertex(1, new Point3D(2, 1, 0));
        NodeData p2 = new Vertex(2, new Point3D(1, 3, 0));
        NodeData p3 = new Vertex(3, new Point3D(3, 3, 0));
        NodeData p4 = new Vertex(4, new Point3D(2, 2, 0));
        p.addNode(p1);
        p.addNode(p2);
        p.addNode(p3);
        p.addNode(p4);

        p.connect(p1.getKey(), p2.getKey(), 10);
        p.connect(p1.getKey(), p3.getKey(), 15);
        p.connect(p1.getKey(), p4.getKey(), 20);
        p.connect(p2.getKey(), p1.getKey(), 10);
        p.connect(p2.getKey(), p4.getKey(), 25);
        p.connect(p2.getKey(), p3.getKey(), 35);
        p.connect(p3.getKey(), p2.getKey(), 35);
        p.connect(p3.getKey(), p4.getKey(), 30);
        p.connect(p3.getKey(), p1.getKey(), 15);
        p.connect(p4.getKey(), p1.getKey(), 20);
        p.connect(p4.getKey(), p2.getKey(), 25);
        p.connect(p4.getKey(), p3.getKey(), 30);
        pAlgo = new MainAlgo(p);

        pAlgo.init(p);

        List<NodeData> e1 = new ArrayList<>();
        e1.add(pAlgo.getGraph().getNode(2));
        e1.add(pAlgo.getGraph().getNode(1));
        e1.add(pAlgo.getGraph().getNode(3));
        e1.add(pAlgo.getGraph().getNode(4));



        assertEquals("[Id: 3, Id: 1, Id: 2, Id: 4]",pAlgo.tsp(e1).toString());

    }


    @Test
    void load() {
        hAlgo.load("Ex2/data/G1.json");
        assertEquals(17, hAlgo.getGraph().nodeSize());
    }
}