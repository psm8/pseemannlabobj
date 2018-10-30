import javax.swing.*;
import java.awt.*;

public abstract class Shape extends JPanel{
    String name;
    int x;
    int y;
    int height;
    int width;
    public Shape(String _name, int _x, int _y, int _height, int _width) {
        this.name =_name;
        this.x =_x;
        this.y =_y;
        this.height =_height;
        this.width =_width;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    public abstract void draw(Graphics g);
}

class Square extends Shape{
    public Square(String _name, int _x, int _y, int _height, int _width) {
        super(_name,_x,_y,_height,_width);
    }
    @Override
    public void draw(Graphics g){
        g.drawRect(x,y,height,width);
    }
}

class Rectangle extends Shape{
    public Rectangle(String _name, int _x, int _y, int _height, int _width) {
        super(_name,_x,_y,_height,_width);
    }
    @Override
    public void draw(Graphics g){
        g.drawRect(x,y,height,width);
    }
}

class Circle extends Shape{
    public Circle(String _name, int _x, int _y, int _height, int _width) {
        super(_name,_x,_y,_height,_width);
    }
    @Override
    public void draw(Graphics g){
        g.drawOval(x,y,height,width);
    }
}
