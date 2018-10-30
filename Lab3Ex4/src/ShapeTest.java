import javax.swing.*;
import java.awt.*;

public class ShapeTest {
    public static void main(String[] args){
        JFrame frame = new JFrame("Shapes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        new Rectangle();
        frame.add(new Circle());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
