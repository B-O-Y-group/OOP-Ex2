import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class window  extends JFrame implements ActionListener , MouseListener {

    JTextField textField ; JLabel  label; JButton button , button2 , button3;



    public window() throws HeadlessException{
        super();
       this.setLayout(null);

       // this.textField = new JTextField();
        this.label = new JLabel("Please chose an algorithm ");
        this.button= new JButton("shortestPath ");
        this.button2= new JButton("center");
        this.button3= new JButton("tsp ");

        this.button.setBounds(10,10,400,30);
        this.button2.setBounds(10,50,400,30);
        this.button3.setBounds(10,90,400,30);
        this.add(this.button);
        this.add(this.button2);
        this.add(this.button3);



//        this.textField.setBounds(50,50,100,20);
//        this.add(this.textField);


        this.label.setBounds(1,1,400,30);
        this.label.setBackground(Color.cyan); //Todo
        this.add(this.label);

        intiGui();
    }

    private void intiGui() {
        // every button different algo

        Dimension fullScreen = Toolkit.getDefaultToolkit().getScreenSize();

         int width = fullScreen.width/2;
         int high = fullScreen.height/2;


         this.setTitle("EX2 - DirectedWeightedGraph"); // sets title of the frame
        this.setSize(width,high);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit
        this.setResizable(false); // prevent frame from being resized
        this.setVisible(true);




        this.getContentPane().setBackground(Color.black);

        ImageIcon image = new ImageIcon(); // search for image and put the path
        this.setIconImage(image.getImage());









    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
