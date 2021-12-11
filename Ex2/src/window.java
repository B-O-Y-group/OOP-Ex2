import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class window extends JFrame implements ActionListener {

    public DirectedWeightedGraph graph;
    private int MC;
    private MenuItem save, load, menuItem3;
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
        int high = fullScreen.height/ 2;
        this.setSize(width, high);
        addMenu();

       // JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
               // JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      //  this.setContentPane(pane);


        //todo panel9
        initPanel();


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // --> closing the trade
        this.setVisible(true);


    }
    private void initPanel() {
        Panel panel = new Panel(this.graph);
        this.add(panel);
    }

    private void addMenu() {

        MenuBar menuBar = new MenuBar();
        this.setMenuBar(menuBar);


        Menu json_file = new Menu("File");
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


        save = new MenuItem("save");
        save.addActionListener(this); // --> listed to menuItem 1 , this

        load = new MenuItem("load");
        load.addActionListener(this);

        menuItem3 = new MenuItem("G3.json");
        menuItem3.addActionListener(this);


        json_file.add(save);
        json_file.add(load);
        json_file.add(menuItem3);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == save) {
        if (e.getSource() == save) {
            //TODO open file
            System.out.println("save clicked");
            save();

        } else if (e.getSource() == load) {
            //TODO open file
            System.out.println("load 2 clicked");
        } else if (e.getSource() == menuItem3) {
            //TODO open file
            System.out.println("json 3 clicked");

        }
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

    private void save() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("save file ");

        int userS  = fileChooser.showSaveDialog(this);

        DirectedWeightedGraphAlgorithms algo1 = new MainAlgo(graph);
        DirectedWeightedGraphAlgorithms algo2 = new MainAlgo(graph);;
        algo1.init(graph);
        algo2.init(algo1.copy());

        if (userS  == JFileChooser.APPROVE_OPTION)
        {
            File Save = fileChooser.getSelectedFile();
            String FileName= Save.getAbsolutePath();
            algo2.save(FileName);
            System.out.println("Save as file: " + Save.getAbsolutePath());
        }

    }


    //todo need to fix
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
        double distPath = graphAl2.shortestPathDist( src, dest);

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
                ans += list.get(0).getKey() ;
                for (int i = 1; i < list.size(); i++) {
                    ans += " --> " +list.get(i).getKey() ;

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






}





