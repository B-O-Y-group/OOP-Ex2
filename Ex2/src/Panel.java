import api.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.LinkedList;

public class Panel extends JPanel implements MouseListener {

    public int x, y;
    DirectedWeightedGraph graph;
    DirectedWeightedGraphAlgorithms algo1 , algo2;
   // LinkedList<Point2D> point = new LinkedList<>();

    public Panel(DirectedWeightedGraph g) {
        algo1 = new MainAlgo(g);
        algo2 = new MainAlgo(g);

        algo1.init(g);

        algo2.init(algo1.copy());

        repaint();




        this.setBackground(Color.cyan);
        this.addMouseListener(this);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);


    }

    private void Draw(Graphics g) {
      //  Graphics2D gr = (Graphics2D) g;

        //Vertex prev = null;
        Iterator<NodeData> nodeIt = this.algo2.getGraph().nodeIter();
        while (nodeIt.hasNext()) {
            NodeData next= nodeIt.next();

            g.setColor(Color.red);
            //todo scale func

            double x = next.getLocation().x();
            double y = next.getLocation().y();

            System.out.println("key --> " + next.getKey()+" (" +x +"," + y +")");
           String key = "";
            key +=  "key " + next.getKey();
            try {
                g.fillOval((int) x, (int) y, 10, 10);
                g.setColor(Color.black);

                g.drawString(key + "", (int) x, (int)y);
            }
            catch (Exception E) {
                System.out.println( "EXP --> " + E.getCause());
            }

        }
        // todo start , center and end --> point

//        Iterator<EdgeData> edgeIt = this.algo1.getGraph().edgeIter();
//        while (edgeIt.hasNext()){
//            EdgeData next = edgeIt.next();
//
//            //todo scale func
//            g.setColor(Color.red);
//            String weight = "";
//            weight += edgeIt.next().getWeight();
//            g.setColor(Color.black);
//           // g.drawLine();
//            g.drawString(weight,edgeIt.next().getSrc(),edgeIt.next().getDest());
//
//        }



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
