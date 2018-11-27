import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public abstract class Shape extends JPanel {
    String name;
    int x;
    int y;
    int height;
    int width;
    volatile int draggedAtX;
    volatile int draggedAtY;

    public Shape(String _name, int _x, int _y, int _height, int _width) {
        this.name = _name;
        this.x = _x;
        this.y = _y;
        this.height = _height;
        this.width = _width;
    }



    public void setX(int x) {
        this.x = x;
    }



    public void setY(int y) {
        this.y = y;
    }



    public void setHeight(int height) {
        this.height = height;
    }


    public void setWidth(int width) {
        this.width = width;
    }

    public abstract void draw(Graphics g);
}

class Square extends Shape {
    public Square(String _name, int _x, int _y, int _height, int _width) {
        super(_name,_x,_y,_height,_width);
    }
    @Override
    public void draw(Graphics g){
        g.drawRect(this.x,this.y,this.height,this.width);
    }
}

class Rectangle extends Shape {
    public Rectangle(String _name, int _x, int _y, int _height, int _width) {
        super(_name,_x,_y,_height,_width);
    }
    @Override
    public void draw(Graphics g){
        g.drawRect(this.x,this.y,this.height,this.width);
    }
}

class Circle extends Shape {
    public Circle(String _name, int _x, int _y, int _height, int _width) {
        super(_name,_x,_y,_height,_width);
    }
    @Override
    public void draw(Graphics g){
        g.drawOval(this.x,this.y,this.height,this.width);
    }
}

/*public void update(){
        new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if(e.getX() > Shape.this.x && e.getX() < Shape.this.x+Shape.this.width &&e.getY() > Shape.this.y && e.getY() < Shape.this.x+Shape.this.height)
                    draggedAtX = e.getX();
                draggedAtY = e.getY();
            }
        };
        new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Shape.this.x += e.getX() - draggedAtX;
                Shape.this.y += e.getY() - draggedAtY;

            }
        };
    }*/
