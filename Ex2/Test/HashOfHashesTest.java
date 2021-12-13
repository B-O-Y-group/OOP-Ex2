import api.EdgeData;
import api.NodeData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class HashOfHashesTest {


    HashOfHashes graph ;
    NodeData k0 = new Vertex(0, new Point3D(1, 2, 0));
    NodeData k1 = new Vertex(1, new Point3D(2, 3, 0));
    NodeData k2 = new Vertex(2, new Point3D(4, 3, 0));
    NodeData k3 = new Vertex(3, new Point3D(5, 3, 0));
    NodeData k4 = new Vertex(4, new Point3D(6, 2, 0));
    NodeData k5 = new Vertex(5, new Point3D(5, 1, 0));
    NodeData k6 = new Vertex(6, new Point3D(4, 1, 0));
    NodeData k7 = new Vertex(7, new Point3D(2, 1, 0));
    NodeData k8 = new Vertex(8, new Point3D(3, 2, 0));

    @BeforeEach
    public void newGraph(){
        graph = new HashOfHashes();
        graph.addNode(k0);
        graph.addNode(k1);
        graph.addNode(k2);
        graph.addNode(k3);
        graph.addNode(k4);
        graph.addNode(k5);
        graph.addNode(k6);
        graph.addNode(k7);
        graph.addNode(k8);

        graph.connect(k0.getKey(), k1.getKey(), 4);
        graph.connect(k0.getKey(), k7.getKey(), 8);
        graph.connect(k1.getKey(), k7.getKey(), 11);
        graph.connect(k2.getKey(), k1.getKey(), 8);
        graph.connect(k2.getKey(), k3.getKey(), 7);
        graph.connect(k3.getKey(), k4.getKey(), 9);
        graph.connect(k4.getKey(), k5.getKey(), 10);
        graph.connect(k5.getKey(), k2.getKey(), 4);
        graph.connect(k5.getKey(), k3.getKey(), 14);
        graph.connect(k6.getKey(), k5.getKey(), 2);
        graph.connect(k7.getKey(), k8.getKey(), 7);
        graph.connect(k7.getKey(), k6.getKey(), 1);
        graph.connect(k8.getKey(), k6.getKey(), 6);
        graph.connect(k8.getKey(), k2.getKey(), 2);

    }

    public HashOfHashesTest() {
        this.graph = new HashOfHashes();
        for (int i = 0; i < 5; i++) {
            graph.addNode(new Vertex(i,new Point3D(i,i,0)));
        }
    }

    @Test
    void getNode() {
        for (int i = 0; i < this.graph.nodeSize(); i++) {
            assertEquals(this.graph.getNode(i).getKey(), i);
        }
    }

    @Test
    void getEdge() {
        assertEquals("src: 0, dest: 1",this.graph.getEdge(0,1).toString());
        assertEquals("src: 5, dest: 3",this.graph.getEdge(5,3).toString());
    }


    @Test
    void connect() {
        int e1 = graph.edgeSize();
//        Assertions.assertEquals(graph.getEdge(0,7).getTag(),graph.getEdge(0,7));
        graph.connect(8,5,12);
        assertEquals(e1 +1  ,graph.edgeSize());

    }

    @Test
    void nodeIter() { //addNode removeNode
        HashOfHashes empty;
        empty = new HashOfHashes();
        Iterator<NodeData> it2 = empty.nodeIter();
        while (it2.hasNext()) {
         assertFalse(it2.hasNext());
        }
        Iterator<NodeData> it = graph.nodeIter();
        it.next();
        it.remove();
        assertFalse(graph.ed_list_removed);
    }

    @Test
    void edgeIter() {
        HashOfHashes empty;
        empty = new HashOfHashes();
        Iterator<EdgeData> it2 = empty.edgeIter();
        while (it2.hasNext()) {
            assertFalse(it2.hasNext());
        }

    }
    @Test
    void removeNode() {
        int e1 = graph.nodeSize();
        assertEquals(e1,graph.nodeSize());
        graph.removeNode(0);
        assertEquals(e1-1,graph.nodeSize());
        graph.removeNode(1);
//        assertNull(graph.getNode(0));
//        assertNull(graph.getNode(1));
//        assertNull(graph.getEdge(0,1));
//        assertNull(graph.getEdge(0,7));
//        assertNull(graph.getEdge(1,7));
//        assertNull(graph.getEdge(1,2));

    }

    @Test
    void removeEdge() {
        int e1 = graph.edgeSize();
        assertEquals(e1,graph.edgeSize());
        graph.removeEdge(0,7);
        graph.removeEdge(0,1);
        assertEquals(e1 -2, graph.edgeSize());
//        assertNull(graph.getEdge(k0.getKey(), k7.getKey()));
//        assertNull(graph.getEdge(0,7));



//        assertEquals("src: 0, dest: 1, src: 0, dest: 7", graph.edgeIter(0).toString());

    }

    @Test
    void nodeSize() {
        assertEquals(9, graph.nodeSize());
    }

    @Test
    void edgeSize() {
        assertEquals(14, graph.edgeSize());
    }

    @Test
    void getMC() { // remove connect addNode
        assertEquals(23,graph.getMC());
    }
}