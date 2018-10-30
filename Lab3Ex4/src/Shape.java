import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JPanel{
    public String name;
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public abstract void draw(Graphics g);
}

class Square extends Shape{
    @Override
    public void draw(Graphics g){
        g.drawRect(80, 30, 200, 200);
    }
}

class Rectangle extends Shape{
    @Override
    public void draw(Graphics g){
        g.drawRect(200, 100, 100, 200);
    }
}

class Circle extends Shape{
    @Override
    public void draw(Graphics g){
        g.drawOval(250, 250, 60, 60);
    }
}
