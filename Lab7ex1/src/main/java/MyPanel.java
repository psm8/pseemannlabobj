
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class MyPanel extends JPanel {
    ArrayList<Shape> listOfShapes= new ArrayList();
    volatile int draggedAtX, draggedAtY;


    public void add(Shape shape) {
        listOfShapes.add(shape);
        repaint();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Shape shape : listOfShapes) {
            shape.draw(g);
        }
    }
    public void update(Shape shape){
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
/*                if(e.getX() > shape.getX() && e.getX() < shape.getX()+shape.getWidth() &&e.getY() > shape.getY() && e.getY() < shape.getY()+shape.getWidth()) {*/
                    draggedAtX = e.getX();
                    draggedAtY = e.getY();
/*                }*/
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                shape.setX(shape.getX() + e.getX() - draggedAtX);
                shape.setY(shape.getY() + e.getY() - draggedAtY);
            }
        });
        repaint();
    }
    public void paint(){
        MyPanel myPanel = new MyPanel();
        for (Shape tmp : listOfShapes) {
            myPanel.add(tmp);

        }
        JFrame frame = new JFrame("Shapes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 800);
        for (Shape tmp : listOfShapes) {
            myPanel.update(tmp);

        }
        frame.getContentPane().add(myPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void run(){

    }
}
/*    public void update(Shape shape){
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                *//*                if(e.getX() > shape.getX() && e.getX() < shape.getX()+shape.getWidth() &&e.getY() > shape.getY() && e.getY() < shape.getY()+shape.getWidth()) {*//*
                draggedAtX = e.getX();
                draggedAtY = e.getY();
                *//*                }*//*
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                shape.setX(shape.getX() + e.getX() - draggedAtX);
                shape.setY(shape.getY() + e.getY() - draggedAtY);
            }
        });
        repaint();
    }*/
