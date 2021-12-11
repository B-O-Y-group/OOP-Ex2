import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;

public class Panel extends JPanel implements MouseListener {

    public int x, y;
    DirectedWeightedGraph graph;
    DirectedWeightedGraphAlgorithms algo1, algo2;






    public Panel(DirectedWeightedGraph g) {
        algo1 = new MainAlgo(g);
        algo2 = new MainAlgo(g);

        algo1.init(g);

        algo2.init(algo1.copy());

        repaint();



        this.setBackground(Color.LIGHT_GRAY);
        this.addMouseListener(this);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);


    }

    private void Draw(Graphics g) {

        nodeDraw(g);
        //   edgeDraw(g);
        //-------for Graphics2D g----------------
        Graphics2D g1 = (Graphics2D) g;
        edgeDraw(g1);
        //---------------------------------------
    }


    private void nodeDraw(Graphics g) {

        Iterator<NodeData> nodeIt = this.algo2.getGraph().nodeIter();
        while (nodeIt.hasNext()) {
            NodeData next = nodeIt.next();

            g.setColor(Color.red);
            //todo scale func

            double x = next.getLocation().x();
            double y = next.getLocation().y();

            // System.out.println("key --> " + next.getKey() + " (" + x + "," + y + ")");
            String key = "";
            key += "key " + next.getKey();
            g.fillOval((int) x, (int) y, 10, 10);
            g.setColor(Color.black);

            g.drawString(key + "", (int) x, (int) y);


            // todo start , center and end --> point

        }

    }

    private void edgeDraw(Graphics2D g) {
        Iterator<EdgeData> edgeIt = this.algo2.getGraph().edgeIter();
        while (edgeIt.hasNext()) {
            EdgeData next = edgeIt.next();
            double srcX = this.algo2.getGraph().getNode(next.getSrc()).getLocation().x();
            double srcY = this.algo2.getGraph().getNode(next.getSrc()).getLocation().y();
            double destX = this.algo2.getGraph().getNode(next.getDest()).getLocation().x();
            double destY = this.algo2.getGraph().getNode(next.getDest()).getLocation().y();


            //todo scale func
            g.setColor(Color.red);
            String weight = "";
            weight += next.getWeight();
            g.setColor(Color.black);
            //todo draw arrow

            //-------for Graphics2D g----------------
            g.setStroke(new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL)); // g2 is an instance of Graphics2D
            g.draw(new Line2D.Double(srcX, srcY, destX, destY));

            //-------------------------------------------

            // g.drawLine((int) srcX, (int) srcY, (int) destX, (int) destY);
            //  drawArrowLine(g,(int) srcX, 0, (int)destX, (int) destY,0,0 );

            int midX = (int) (srcX + destX) / 2;
            int midY = (int) (srcY + destY) / 2;

            System.out.println("mid x -> " + midX + " mid y -> " + midY);
            g.setColor(Color.magenta);
            g.drawString(weight + "", midX, midY);

        }
    }



    @Override
    public void mouseClicked(MouseEvent e) {

//        Point2D p = new Point(e.getX(), e.getY());
//
//        point.add(p);
        // repaint();
//        x = e.getX();
//        y = e.getY();
//
//        System.out.println("(" + x + "," + y + ")");


        System.out.println("mouse Clicked");

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
}
