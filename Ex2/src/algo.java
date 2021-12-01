import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import java.util.ArrayList;
import java.util.List;

public class algo implements DirectedWeightedGraphAlgorithms {

    public HashOfHashes graph;

    public algo(HashOfHashes h) {
        this.graph = h;
        init(h);
    }
    @Override
    public void init(DirectedWeightedGraph g) {

    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        HashOfHashes a = new HashOfHashes();


        List<NodeData> nodeData = new ArrayList<>();
        nodeData = shortestPath(a.edgeIter(a.getNode(0).getKey()).next().getSrc(),
                a.edgeIter().next().getDest());
        double min = count(nodeData);
        Vertex temp = (Vertex) nodeData.get(0);




        while (a.nodeIter().hasNext()) {
            for (int i = 1; i < a.nodeSize(); i++) {

                nodeData = shortestPath(a.edgeIter(a.getNode(i).getKey()).next().getSrc(),
                        a.edgeIter().next().getDest());
                if (count(nodeData) < min) {
                    min = count(nodeData);
                    temp = (Vertex) nodeData.get(i);
                }
            }

        }
        return temp;

    }

    private double count(List<NodeData> nodeData) {
        double sum = 0;

        for (int i = 0, j = i + 1; j < nodeData.size(); i++, j++) {
            sum += this.graph.graph.get(nodeData.get(i).getKey()).getD().out.get(nodeData.get(j).getKey()).weight;
        }


        return sum;

    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        return false;
    }
}
