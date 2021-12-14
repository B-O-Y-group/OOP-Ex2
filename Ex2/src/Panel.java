import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.Iterator;

public class Panel extends JPanel implements MouseListener {

    public DirectedWeightedGraphAlgorithms algo1, algo2;
    public HashOfHashes graph;
    public static int width;
    public static int high;
    public static double minX;
    public static double minY;
    public static double maxX;
    public static double maxY;
    public NodeData centerNode;
    public Point3D newP;

    public Panel(DirectedWeightedGraph g) {
        Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        width = fullScreen.width;
        high = fullScreen.height;
        algo1 = new MainAlgo(g);
        algo2 = new MainAlgo(g);
        algo1.init(g);
        algo2.init(algo1.copy());
        this.newP = new Point3D(0, 0, 0);
        this.graph = (HashOfHashes) g;

        // this.addMouseListener(this);

        repaint();
        Color c = new Color(0, 204, 204);
        this.setBackground(c);

        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;

        Iterator<NodeData> node = this.algo2.getGraph().nodeIter();
        while (node.hasNext()) {
            NodeData n = node.next();
            minX = Math.min(minX, n.getLocation().x());
            maxX = Math.max(maxX, n.getLocation().x());
            minY = Math.min(minY, n.getLocation().y());
            maxY = Math.max(maxY, n.getLocation().y());
        }


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);


    }

    private void Draw(Graphics g) {
        //-------for Graphics2D ----------------
        Graphics2D g1 = (Graphics2D) g;
        edgeDraw(g1);
        nodeDraw(g1);
        //---------------------------------------
    }

    public static double scaleX(double x) {
        return ((x - minX) / (maxX - minX)) * ((width - 200) - 200) + 200;
    }

    public static double scaleY(double y) {
        return ((y - minY) / (maxY - minY)) * ((high - 200) - 200) + 200;
    }


    private void nodeDraw(Graphics2D g) {
        Iterator<NodeData> nodeIt = this.algo2.getGraph().nodeIter();
        while (nodeIt.hasNext()) {
            NodeData next = nodeIt.next();

            g.setColor(Color.black);
            double x = next.getLocation().x();
            double y = next.getLocation().y();
            x = scaleX(x);
            y = scaleY(y);


            g.fillOval((int) x - 7, (int) y - 7, 12, 12);

            ///------draw key----------------
            String key = "";
            key += "key " + next.getKey();
            Color c = new Color(28, 89, 41);
            g.setColor(c);
            g.setFont(new Font("", Font.BOLD, 15));
            g.drawString(key + "", (int) x - 10, (int) y - 10);


            // todo start , center and end --> point


        }


    }

    public void center(Graphics2D g) {
        Iterator<NodeData> nodeIt = this.algo2.getGraph().nodeIter();
        while (nodeIt.hasNext()) {
            NodeData next = nodeIt.next();
            double x = next.getLocation().x();
            double y = next.getLocation().y();
            x = scaleX(x);
            y = scaleY(y);

            if (next == algo2.center()) {
                System.out.println("------------------------we are here " +
                        "--------------------------------------------------------------");
                g.setColor(Color.red);
                g.fillOval((int) x - 7, (int) y - 7, 12, 12);
                String center = "";
                center += " Center node ";
                g.setColor(Color.YELLOW);
                g.setFont(new Font("", Font.BOLD, 15));
                g.drawString(center + "", (int) x - 10, (int) y - 10);
                this.centerNode = next;
            }

        }
        this.centerNode = null;
    }

    public NodeData getCenter() {
        return this.centerNode;
    }


    private void edgeDraw(Graphics2D g) {


        Iterator<EdgeData> edgeIt = this.algo2.getGraph().edgeIter();
        while (edgeIt.hasNext()) {
            EdgeData next = edgeIt.next();


            double srcX = this.algo2.getGraph().getNode(next.getSrc()).getLocation().x();
            srcX = scaleX(srcX);
            double srcY = this.algo2.getGraph().getNode(next.getSrc()).getLocation().y();
            srcY = scaleY(srcY);
            double destX = this.algo2.getGraph().getNode(next.getDest()).getLocation().x();
            destX = scaleX(destX);
            double destY = this.algo2.getGraph().getNode(next.getDest()).getLocation().y();
            destY = scaleY(destY);

            //--------------------------Draw arrow----------------------------------

            Color color = new Color(255, 255, 240);

            g.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));

            LineArrow arrow = new LineArrow((int) (srcX), (int) (srcY), (int) (destX) - 5, (int) (destY) - 3, color,
                    0);

            g.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
            arrow.draw(g);


            //------- draw weight---------------
            int midX = (int) (srcX + destX) / 2;
            int midY = (int) (srcY + destY) / 2;
            g.setColor(Color.black);
            String weight = "";
            weight += (int) next.getWeight();
            g.setColor(Color.black);
            Color c = new Color(128, 128, 128);
            g.setColor(c);
            g.setFont(new Font("", Font.BOLD, 15));
            g.drawString(weight + "", midX, midY);

        }
    }



    //--------------------------New-----------------------------------------------------------------
    public void RemoveNode(JFrame frame) {

        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        DirectedWeightedGraph newG = this.graph;
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());

        String S = JOptionPane.showInputDialog(frame, "Please give Node Key you want to remove from the graph");
        int key = Integer.parseInt(S);

        try {
            graphAl2.getGraph().removeNode(key);
            graphAl2.init(newG);
            this.repaint();



        } catch (Exception e) {
            System.out.println(" " + e.getCause());

        }

    }


    //not good
    public void addNode(JFrame frame) {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        DirectedWeightedGraph newG = this.graph;
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());
        JOptionPane.showMessageDialog(frame, "Insert Node to the graph ");
        String x = JOptionPane.showInputDialog(frame, "Please give the dx coordinate", "Position", -1);
        String y = JOptionPane.showInputDialog(frame, "Please give the dy coordinate", "Position", -1);
        int key = this.algo2.getGraph().nodeSize() + 1;

        double dx = Double.parseDouble(x);
        double dy = Double.parseDouble(y);


        try {
            Point3D p = new Point3D(dx, dy, 0);
            NodeData n = new Vertex(key, p);
            graphAl2.getGraph().addNode(n);
            graphAl2.init(newG);

            this.repaint();



        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }

    }

    public void addEdge(JFrame frame) {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        DirectedWeightedGraph newG = this.graph;
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());
        JOptionPane.showMessageDialog(frame, "Insert Edge to the graph ");
        String x = JOptionPane.showInputDialog(frame, "Please give a src ", "Edge Src", -1);
        String y = JOptionPane.showInputDialog(frame, "Please give a dest ", "Edge dest", -1);
        String w = JOptionPane.showInputDialog(frame, "Please give a weight ", "Edge weight", -1);


        int src = Integer.parseInt(x);
        int dest = Integer.parseInt(y);
        double weight = Double.parseDouble(w);


        try {

            graphAl2.getGraph().connect(src,dest,weight);
            graphAl2.init(newG);

            this.repaint();



        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }
    }

    public void RemoveEdge(JFrame frame) {
        DirectedWeightedGraphAlgorithms graphAl = new MainAlgo(this.graph);
        DirectedWeightedGraphAlgorithms graphAl2 = new MainAlgo(this.graph);
        DirectedWeightedGraph newG = this.graph;
        graphAl.init(this.graph);
        graphAl2.init(graphAl.copy());
        JOptionPane.showMessageDialog(frame, "Remove Edge from the graph ");
        String x = JOptionPane.showInputDialog(frame, "Please give a src ", "Edge Src", -1);
        String y = JOptionPane.showInputDialog(frame, "Please give a dest ", "Edge dest", -1);



        int src = Integer.parseInt(x);
        int dest = Integer.parseInt(y);



        try {

            graphAl2.getGraph().removeEdge(src,dest);
            graphAl2.init(newG);

            this.repaint();




        } catch (Exception exception) {
            System.out.println(exception.getMessage());

        }
    }


//-----------------------------mouseClicked--------------------------------------------------------------

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.newP = new Point3D(e.getX(), e.getY(), 0);
        ;
//            NodeData n = new Vertex(this.algo2.getGraph().nodeSize() + 1, newP);
//            DirectedWeightedGraph newG = this.algo2.getGraph();
//            newG.addNode(n);;
        //p.repaint();
        //set size;
        //repaint();

    }


    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    //-------------------------------------Draw Arrow------------------------------------------------------
    // from https://itqna.net/questions/3389/how-draw-arrow-using-java2d
    private static final Polygon ARROW_HEAD = new Polygon();

    static {
        ARROW_HEAD.addPoint(0, 0);
        ARROW_HEAD.addPoint(-5, -10);
        ARROW_HEAD.addPoint(5, -10);
    }


    public static class LineArrow {

        private final int x;
        private final int y;
        private final int endX;
        private final int endY;
        private final Color color;
        private final int thickness;

        public LineArrow(int x, int y, int x2, int y2, Color color, int thickness) {
            super();
            this.x = x;
            this.y = y;
            this.endX = x2;
            this.endY = y2;
            this.color = color;
            this.thickness = thickness;
        }

        public void draw(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;


            double angle = Math.atan2(endY - y, endX - x);

            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));


            g2.drawLine(x, y, (int) (endX - 10 * Math.cos(angle)), (int) (endY - 10 * Math.sin(angle)));


            AffineTransform tx1 = g2.getTransform();


            AffineTransform tx2 = (AffineTransform) tx1.clone();


            tx2.translate(endX, endY);
            tx2.rotate(angle - Math.PI / 2);

            g2.setTransform(tx2);
            g2.fill(ARROW_HEAD);


            g2.setTransform(tx1);
        }
    }
}
