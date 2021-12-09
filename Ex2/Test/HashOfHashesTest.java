import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashOfHashesTest {


    HashOfHashes graph ;

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
    }


    @Test
    void connect() {
    }

    @Test
    void nodeIter() {
    }

    @Test
    void edgeIter() {
    }

    @Test
    void testEdgeIter() {
    }

    @Test
    void removeNode() {
    }

    @Test
    void removeEdge() {
    }

    @Test
    void nodeSize() {
    }

    @Test
    void edgeSize() {
    }

    @Test
    void getMC() {
    }
}