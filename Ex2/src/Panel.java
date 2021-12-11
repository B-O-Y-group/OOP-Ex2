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

    DirectedWeightedGraphAlgorithms algo1, algo2;
    public static int width;
    public static int high;
    public static double minX;
    public static double minY;
    public static double maxX;
    public static double maxY;

    public Panel(DirectedWeightedGraph g) {
        Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();
        width = fullScreen.width / 2;
        high = fullScreen.height / 2;
        //   System.out.println("w "+ width + " h " + high );
        algo1 = new MainAlgo(g);
        algo2 = new MainAlgo(g);
        algo1.init(g);
        algo2.init(algo1.copy());

        //  this.setSize(width,high);

        repaint();

        this.setBackground(Color.darkGray);
        this.addMouseListener(this);
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


        System.out.println("minX -- " + minX
                + "maxX --" + maxX +
                "minY --" + minY +
                "maxY --" + maxY
                + "\n==========================================================");
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Draw(g);


    }

    private void Draw(Graphics g) {

        //  nodeDraw(g);
        //   edgeDraw(g);
        //-------for Graphics2D g----------------


        Graphics2D g1 = (Graphics2D) g;
        nodeDraw(g1);
        edgeDraw(g1);
        //---------------------------------------
    }

    public static double saCleX(double x) {

//        double ans =  (width -100) * ((x - minX) / (maxX - minX) );
//        ans = ans + 50;
        return ((x - minX) / (maxX - minX)) * ((width - 200) - 200) + 200;
    }

    public static double saCleY(double y) {
//        double ans = (high-100 )* ((y - minY) / (maxY - minY));
//        ans = ans+50;
//        return ans;
        return ((y - minY) / (maxY - minY)) * ((high - 200) - 200) + 200;
    }


    private void nodeDraw(Graphics2D g) {


        Iterator<NodeData> nodeIt = this.algo2.getGraph().nodeIter();
        while (nodeIt.hasNext()) {
            NodeData next = nodeIt.next();

            g.setColor(Color.red);
            double x = next.getLocation().x();
            double y = next.getLocation().y();
            x = saCleX(x);
            y = saCleY(y);

            // System.out.println("key --> " + next.getKey() + " (" + x + "," + y + ")");

            g.fillOval((int) x, (int) y, 10, 10);

            ///------draw key----------------
            String key = "";
            key += "key " + next.getKey();
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
            srcX = saCleX(srcX);
            double srcY = this.algo2.getGraph().getNode(next.getSrc()).getLocation().y();
            srcY = saCleY(srcY);
            double destX = this.algo2.getGraph().getNode(next.getDest()).getLocation().x();
            destX = saCleX(destX);
            double destY = this.algo2.getGraph().getNode(next.getDest()).getLocation().y();
            destY = saCleY(destY);





            //-------for Graphics2D g----------------
             g.setStroke(new BasicStroke(3f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL));
             g.draw(new Line2D.Double(srcX, srcY, destX, destY));

            //----------------------for Graphics-------------------------------------------
            //  g.drawLine((int)( srcX ) , (int) (srcY), (int) (destX ), (int) (destY));

            //--------------------------Draw arrow----------------------------------
            LineArrow arrow = new LineArrow((int) (srcX), (int) (srcY), (int) (destX), (int) (destY), Color.blue, 1);
            arrow.draw(g);



            //------- draw weight---------------
            int midX = (int) (srcX + destX) / 2;
            int midY = (int) (srcY + destY) / 2;
            g.setColor(Color.red);
            String weight = "";
            weight += next.getWeight();
            g.setColor(Color.black);
            //draw  weight
            // g.setColor(Color.magenta);
            //  g.drawString(weight + "", midX, midY);

        }
    }


    ///_______________Mouse Action___________________________________________________________

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


    //---------------------------------------------------------------------------------------------------
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

            // Calcula o ângulo da seta.
            double angle = Math.atan2(endY - y, endX - x);

            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));

            // Desenha a linha. Corta 10 pixels na ponta para a ponta não ficar grossa.
            g2.drawLine(x, y, (int) (endX - 10 * Math.cos(angle)), (int) (endY - 10 * Math.sin(angle)));

            // Obtém o AffineTransform original.
            AffineTransform tx1 = g2.getTransform();

            // Cria uma cópia do AffineTransform.
            AffineTransform tx2 = (AffineTransform) tx1.clone();

            // Translada e rotaciona o novo AffineTransform.
            tx2.translate(endX, endY);
            tx2.rotate(angle - Math.PI / 2);

            // Desenha a ponta com o AffineTransform transladado e rotacionado.
            g2.setTransform(tx2);
            g2.fill(ARROW_HEAD);

            // Restaura o AffineTransform original.
            g2.setTransform(tx1);
        }
    }
}
