import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class window extends JFrame implements ActionListener {

    public DirectedWeightedGraph graph;
    public DirectedWeightedGraphAlgorithms graphAlgo;
    private int MC;
    private MenuItem menuItem1, menuItem2, menuItem3;
    private MenuItem isConnected, shortestPathDist, shortestPath, center, tsp;


//    public window() {
//        this.graph = new HashOfHashes();
//        this.MC = 0;
//
//    }

    public window(DirectedWeightedGraph G) {
        this.graph = G;
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
        } else if (e.getSource() == isConnected) {
            // isConneceted();
            //todo open the func
            System.out.println("isConnected clicked");
        } else if (e.getSource() == center) {
            //todo open the func
            System.out.println("center clicked");
        } else if (e.getSource() == shortestPath) {
            //todo open the func
            System.out.println("shortestPath clicked");
        } else if (e.getSource() == shortestPathDist) {
            //todo open the func
            System.out.println("shortestPathDist clicked");
        } else if (e.getSource() == tsp) {
            //todo open the func
            System.out.println("tsp clicked ");
        }


    }

    private void initPanel(){
        Panel panel = new Panel(this.graph);
        this.add(panel);
    }


}





