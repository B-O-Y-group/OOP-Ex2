import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainAlgoTest {

    @Test
    void init() {
    }

    @Test
    void getGraph() {
    }

    @Test
    void copy() {

        HashOfHashes graph = new HashOfHashes();
        HashOfHashes graphCopy = new HashOfHashes();
        graph.setMC(4);

        MainAlgo r = new MainAlgo(graphCopy);
        DirectedWeightedGraph deepCopy =  r.copy();

       graph.setMC(4);

        assertNotEquals(deepCopy.getMC(), graph.getMC());

    }

    @Test
    void isConnected() {
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