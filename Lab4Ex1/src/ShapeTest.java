import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ShapeTest {


    public static void main(String[] args) throws InterruptedException {
        ArrayList<Shape> listOfShapes= new ArrayList();
        Shape rect = new Rectangle("a",12,12,12,12);
        Shape circ = new Circle("a",12,12,12,12);
        Shape squa = new Square("a",12,12,12,12);
        listOfShapes.add(rect);
        listOfShapes.add(circ);
        listOfShapes.add(squa);
        boolean run = true;
        while(run) {
            System.out.println("Przegladaj - 1");
            System.out.println("Dodaj - 2");
            System.out.println("Zamknij - 3");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            if (i == 1) {
                JFrame frame = new JFrame("Shapes");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800, 800);
                for (Shape tmp : listOfShapes) {
                    frame.add(tmp);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    Thread.sleep(2000);
                }
            }
            if (i == 2) {
                System.out.println("Shape Type:");
                Scanner sc5 = new Scanner(System.in);
                int typeShape = sc5.nextInt();
                System.out.println("Name:");
                Scanner sc6 = new Scanner(System.in);
                String name = sc6.nextLine();
                System.out.println("x:");
                Scanner sc1 = new Scanner(System.in);
                int x = sc1.nextInt();
                System.out.println("y:");
                Scanner sc2 = new Scanner(System.in);
                int y = sc2.nextInt();
                System.out.println("height:");
                Scanner sc3 = new Scanner(System.in);
                int height = sc3.nextInt();
                System.out.println("width:");
                Scanner sc4 = new Scanner(System.in);
                int width = sc4.nextInt();
                if (typeShape == 1) {
                    Shape squa1 = new Square(name, x, y, height, width);
                }
                if (typeShape == 2) {
                    Shape squa1 = new Square(name, 12, 12, 12, 12);
                }
            }
            if (i == 3){
                run = false;
            }
        }
    }
}
