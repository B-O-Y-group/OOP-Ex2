import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class window extends JFrame implements ActionListener {

    public DirectedWeightedGraph graph;
    private int MC;
    private MenuItem menuItem1, menuItem2, menuItem3;
    private MenuItem isConnected, shortestPathDist, shortestPath, center, tsp;



    public window(DirectedWeightedGraphAlgorithms G) {
        this.graph = G.getGraph();
        intiGraph(graph);
        MC = graph.getMC();

    }

    private void intiGraph(DirectedWeightedGraph graph) {

        this.setTitle("EX-2 - Graph - BOY ");

        Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = fullScreen.width / 2;
        int high = fullScreen.height / 2;
        this.setSize(width, high);
        addMenu();


        //todo panel9
        initPanel();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // --> closing the trade
        this.setVisible(true);


    }

    private void addMenu() {

        MenuBar menuBar = new MenuBar();
        this.setMenuBar(menuBar);


        Menu json_file = new Menu("json file");
        Menu Algorithm = new Menu("Algorithm");
        // Algorithm.addActionListener(this);

        menuBar.add(json_file);
        menuBar.add(Algorithm);

        isConnected = new MenuItem("isConnected");
        isConnected.addActionListener(this);
        center = new MenuItem("center");
        center.addActionListener(this);
        shortestPath = new MenuItem("shortestPath");
        shortestPath.addActionListener(this);
        shortestPathDist = new MenuItem("shortestPathDist");
        shortestPathDist.addActionListener(this);
        tsp = new MenuItem("tsp");
        tsp.addActionListener(this);

        Algorithm.add(isConnected);
        Algorithm.add(center);
        Algorithm.add(shortestPath);
        Algorithm.add(shortestPathDist);
        Algorithm.add(tsp);


        menuItem1 = new MenuItem("G1.json");
        menuItem1.addActionListener(this); // --> listed to menuItem 1 , this

        menuItem2 = new MenuItem("G2.json");
        menuItem2.addActionListener(this);

        menuItem3 = new MenuItem("G3.json");
        menuItem3.addActionListener(this);


        json_file.add(menuItem1);
        json_file.add(menuItem2);
        json_file.add(menuItem3);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menuItem1) {
            //TODO open file
            try {
                FileReader r = new FileReader("C:\\Users\\97252\\Documents\\GitHub\\OOP-Ex2\\Ex2\\data\\G1.json");
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
            System.out.println("json 1 clicked");
        } else if (e.getSource() == menuItem2) {
            //TODO open file
            System.out.println("json 2 clicked");
        } else if (e.getSource() == menuItem3) {
            //TODO open file
            System.out.println("json 3 clicked");


            // --------------Algorithm-------------------
        } else if (e.getSource() == isConnected) {
            isConnected();
            System.out.println("isConnected clicked");
        } else if (e.getSource() == center) {
            //todo draw
            FindCenter();
            System.out.println("center clicked");
        } else if (e.getSource() == shortestPath) {
            ShortestPath();
            //todo maybe draw
            System.out.println("shortestPath clicked");
        } else if (e.getSource() == shortestPathDist) {
            shortestPathDist();
            //todo maybe draw
            System.out.println("shortestPathDist clicked");
        } else if (e.getSource() == tsp) {
            TSP();
            //todo open the func
            System.out.println("tsp clicked ");
        }


    }

    private void TSP() {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());

        List<NodeData> list = new ArrayList<>();
        List<NodeData> listN ;

        String max = JOptionPane.showInputDialog(this,
                "Please enter a number of cities you want to visit  <= " + graphAl2.getGraph().nodeSize());

        int cities = Integer.parseInt(max);
        try {
            String ans = "";

            for (int i = 0; i < cities; i++) {
                String path = JOptionPane.showInputDialog(this,
                        "Please enter a node  " + graphAl2.getGraph().getNode(i));
                int nodeT = Integer.parseInt(path);
                list.add(graphAl2.getGraph().getNode(nodeT));
                System.out.println("---------------------------> " + graphAl2.getGraph().getNode(nodeT));
            }

            listN = graphAl2.tsp(list);

            for (int i = 0; i < listN.size(); i++) {
                ans += listN.get(i).getKey();
                System.out.println("--------------------------------------------------------");
                System.out.println(ans);
                System.out.println("--------------------------------------------------------");

                JOptionPane.showMessageDialog(null, "Tsp :  " + ans,  "Tsp",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException exception) {
            if (cities > graphAl2.getGraph().nodeSize()) {
                JOptionPane.showMessageDialog(null, "Your number is bigger from  the node size of the graph  ",
                        "Tsp",
                        JOptionPane.WARNING_MESSAGE);
            }
        }catch (Exception exception){
            JOptionPane.showMessageDialog(null, "ex  ",
                    "Tsp",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    private void shortestPathDist() {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());


        String srcN = JOptionPane.showInputDialog(this, "Insert source node","ShortestPathDist", -1 );
        String destN = JOptionPane.showInputDialog(this, "Insert destination node", "ShortestPathDist", -1);

        int src = Integer.parseInt(srcN);
        int dest = Integer.parseInt(destN);
        double distPath = graphAl2.shortestPathDist((int) src, (int) dest);

        if (distPath == Double.POSITIVE_INFINITY) {
            JOptionPane.showMessageDialog(null, "There isn't a shortest path distance", "Shortest Path Dist",
                    JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(null, "The shortest path distance is: " + distPath, "Shortest Path Dist",
                    JOptionPane.INFORMATION_MESSAGE);

        }


    }

    private void ShortestPath() {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());
        String srcN = JOptionPane.showInputDialog(this, "insert source node");
        String destN = JOptionPane.showInputDialog(this, "insert destination node");
        List<NodeData> list;

        try {
            int src = Integer.parseInt(srcN);
            int dest = Integer.parseInt(destN);

            list = graphAl2.shortestPath(src, dest);
            String ans = "";
            if (list != null) {
                ans += list.get(0) + "\n";
                for (int i = 1; i < list.size(); i++) {
                    ans += " --> " +list.get(i) + "\n";

                }
            } else {
                ans = " There  are no path between the two given nodes ";

            }
            JOptionPane.showMessageDialog(null, "The Shortest Path is :  \n" + ans, " ShortestPath ",
                    JOptionPane.INFORMATION_MESSAGE);


        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, "Error : " + exception.getMessage(), "ShortestPath",
                    JOptionPane.WARNING_MESSAGE);

        }
    }

    private void FindCenter() {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);

        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());
        //todo in  PANEL
    }

    private void isConnected() {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);

        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());
        boolean ans = graphAl2.isConnected();
        if (ans) {
            JOptionPane.showMessageDialog(null, "The graph is connected ", " isConnected ",
                    JOptionPane.QUESTION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "The graph is not  connected ", " isConnected ",
                    JOptionPane.INFORMATION_MESSAGE);

        }


    }

    private void initPanel() {
        Panel panel = new Panel(this.graph);
        this.add(panel);
    }


}





