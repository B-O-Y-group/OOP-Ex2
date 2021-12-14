import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class window extends JFrame implements ActionListener {

    public DirectedWeightedGraph graph;
    private int MC;
    private MenuItem save, load;
    private MenuItem addNode, addEdge, removeNode, removeEdge;
    private MenuItem isConnected, shortestPathDist, shortestPath, Center, tsp;
    public Panel panel;
    DirectedWeightedGraphAlgorithms ALGO;


    public window(DirectedWeightedGraphAlgorithms G) {
        this.graph = G.getGraph();
        intiGraph(graph);
        MC = graph.getMC();


    }

    private void intiGraph(DirectedWeightedGraph graph) {

        this.setTitle("EX-2 - Graph - BOY ");


        // todo  add edge button and  add node button

        Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        int width = fullScreen.width;
        int high = fullScreen.height;
        this.setSize(width, high);
        addMenu();


        initPanel(this.graph);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // --> closing the trade
        this.setVisible(true);


    }

    //was   private void initPanel() before
    private void initPanel(DirectedWeightedGraph graph) {
        this.panel = new Panel(graph);
        this.add(panel);
    }

    private void addMenu() {

        MenuBar menuBar = new MenuBar();
        this.setMenuBar(menuBar);


        Menu file = new Menu("File");
        Menu Algorithm = new Menu("Algorithm");
        Menu addToGraph = new Menu("New");


        menuBar.add(file);
        menuBar.add(Algorithm);
        menuBar.add(addToGraph);

        addEdge = new MenuItem("Add Edge");
        addEdge.addActionListener(this);
        addNode = new MenuItem("Add Node");
        addNode.addActionListener(this);
        removeNode = new MenuItem("remove Node");
        removeNode.addActionListener(this);
        removeEdge = new MenuItem("remove Edge");
        removeEdge.addActionListener(this);

        addToGraph.add(addNode);
        addToGraph.add(addEdge);
        addToGraph.add(removeNode);
        addToGraph.add(removeEdge);


        isConnected = new MenuItem("isConnected");
        isConnected.addActionListener(this);
        Center = new MenuItem("Center");
        Center.addActionListener(this);
        shortestPath = new MenuItem("shortestPath");
        shortestPath.addActionListener(this);
        shortestPathDist = new MenuItem("shortestPathDist");
        shortestPathDist.addActionListener(this);
        tsp = new MenuItem("tsp");
        tsp.addActionListener(this);

        Algorithm.add(isConnected);
        Algorithm.add(Center);
        Algorithm.add(shortestPath);
        Algorithm.add(shortestPathDist);
        Algorithm.add(tsp);


        save = new MenuItem("save");
        save.addActionListener(this);
        load = new MenuItem("load");
        load.addActionListener(this);


        file.add(save);
        file.add(load);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // --------------File-------------------
        if (e.getSource() == save) {
            System.out.println("save clicked");
            save();

        } else if (e.getSource() == load) {
            load();
            System.out.println("load clicked");
        }

        // --------------Algorithm-------------------
        else if (e.getSource() == isConnected) {
            isConnected();
            System.out.println("isConnected clicked");
        } else if (e.getSource() == Center) {
            FindCenter();
            System.out.println("Center clicked");
        } else if (e.getSource() == shortestPath) {
            ShortestPath();

            System.out.println("shortestPath clicked");
        } else if (e.getSource() == shortestPathDist) {
            shortestPathDist();

            System.out.println("shortestPathDist clicked");
        } else if (e.getSource() == tsp) {
            TSP();
            //todo open the func
            System.out.println("tsp clicked ");
        }

        // --------------New -------------------
        else if (e.getSource() == addNode) {
            addNode();
            System.out.println("add node clicked");
        } else if (e.getSource() == addEdge) {
            addEdge();
            System.out.println("add edge clicked");
        } else if (e.getSource() == removeNode) {
            RemoveNode();
            System.out.println("remove node clicked");
        } else if (e.getSource() == removeEdge) {
            RemoveEdge();
            System.out.println("remove edge clicked");
        }

    }

    //===============New================================================
    private void addNode() {
        //  this.panel.addNode(this);


        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);

        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());


        JOptionPane.showMessageDialog(this, "Insert Node to the graph ");
        String x = JOptionPane.showInputDialog(this, "Please give the dx coordinate", "Position", -1);
        String y = JOptionPane.showInputDialog(this, "Please give the dy coordinate", "Position", -1);
        int key = graphAl2.getGraph().nodeSize();

        double dx = Double.parseDouble(x);
        double dy = Double.parseDouble(y);

        try {
            Point3D p = new Point3D(dx, dy, 0);
            NodeData n = new Vertex(key, p);
            graphAl2.getGraph().addNode(n);
            //Frame f = new window(graphAl2);
            graphAl2.save("GAL.json");


            boolean b = this.ALGO.load("GAL.json");
            this.dispose();
            new window(this.ALGO);



//            new window(graphAl2);

//           repaint();

        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }

    }


    //this.panel.addNode(this); }

    private void addEdge() {
        this.panel.addEdge(this);
    }

    private void RemoveEdge() {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        DirectedWeightedGraph newG = this.graph;
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());
        JOptionPane.showMessageDialog(this, "Remove Edge from the graph ");
        String x = JOptionPane.showInputDialog(this, "Please give a src ", "Edge Src", -1);
        String y = JOptionPane.showInputDialog(this, "Please give a dest ", "Edge dest", -1);


        int src = Integer.parseInt(x);
        int dest = Integer.parseInt(y);


        try {

            newG.removeEdge(src, dest);
            graphAl2.init(newG);


            repaint();


        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }
    }

    private void RemoveNode() {
        this.panel.RemoveNode(this);
    }


    //===============Algo================================================
    //todo need to fix
    private void TSP() {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());

        List<NodeData> list = new ArrayList<>();
        List<NodeData> listN;

        String max = JOptionPane.showInputDialog(this,
                "Please enter a size for the list<= " + graphAl2.getGraph().nodeSize());

        int size = Integer.parseInt(max);
        try {

            String ans = "";

            for (int i = 0; i < size; i++) {
                String path = JOptionPane.showInputDialog(this,
                        "Please enter a node  " + graphAl2.getGraph().getNode(i));
                int nodeT = Integer.parseInt(path);
                list.add(graphAl2.getGraph().getNode(nodeT));

            }

            listN = graphAl2.tsp(list);

            for (int i = 0; i < listN.size(); i++) {
                ans += "" + listN.get(i).getKey();


                JOptionPane.showMessageDialog(null, "Tsp :  " + ans, "Tsp",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (NumberFormatException exception) {
            if (size > graphAl2.getGraph().nodeSize()) {
                JOptionPane.showMessageDialog(null, "Your number is bigger from  the node size of the graph  ",
                        "Tsp",
                        JOptionPane.WARNING_MESSAGE);
                System.out.println(exception.getMessage());
            }
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "ex  ",
                    "Tsp",
                    JOptionPane.WARNING_MESSAGE);
            System.out.println(exception.getMessage());
        }

    }

    private void shortestPathDist() {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());


        String srcN = JOptionPane.showInputDialog(this, "Insert source node", "ShortestPathDist", -1);
        String destN = JOptionPane.showInputDialog(this, "Insert destination node", "ShortestPathDist", -1);

        int src = Integer.parseInt(srcN);
        int dest = Integer.parseInt(destN);
        double distPath = graphAl2.shortestPathDist(src, dest);

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
                ans += list.get(0).getKey();
                for (int i = 1; i < list.size(); i++) {
                    ans += " --> " + list.get(i).getKey();

                }
            } else {
                ans = " There  are no path between the two given nodes ";

            }
            JOptionPane.showMessageDialog(this, "The Shortest Path is :  \n" + ans, " ShortestPath ",
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

        NodeData ans = graphAl2.center();

        if (ans != null) {
            JOptionPane.showMessageDialog(this, "The center is : " + ans.getKey(), "Center",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "The center is  null ", "Center",
                    JOptionPane.INFORMATION_MESSAGE);
        }


    }

    private void isConnected() {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);

        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());
        boolean ans = graphAl2.isConnected();
        if (ans) {
            JOptionPane.showMessageDialog(this, "The graph is connected ", " isConnected ",
                    JOptionPane.QUESTION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "The graph is not  connected ", " isConnected ",
                    JOptionPane.INFORMATION_MESSAGE);

        }


    }

    //=============File==================================================
    // todo not working well
    private void load() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("load file ");

        DirectedWeightedGraphAlgorithms algo1 = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms algo2 = new MainAlgo(this.graph);

        int returnV = fileChooser.showOpenDialog(this);
        if (returnV == JFileChooser.APPROVE_OPTION) {
            try {
                File selected = fileChooser.getSelectedFile();
                algo1.load(selected.getAbsolutePath());
                algo1.init(this.graph);
                algo2.init(algo1.copy());

                initPanel(algo2.getGraph());

            } catch (Exception exception) {
                System.out.println(exception.getMessage());

            }
        }


    }

    private void save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("save file ");

        int userS = fileChooser.showSaveDialog(this);

        DirectedWeightedGraphAlgorithms algo1 = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms algo2 = new MainAlgo(this.graph);
        ;
        algo1.init(graph);
        algo2.init(algo1.copy());

        if (userS == JFileChooser.APPROVE_OPTION) {
            File Save = fileChooser.getSelectedFile();
            String FileName = Save.getAbsolutePath();
            algo2.save(FileName);
        }

    }


}





