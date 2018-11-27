import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;



public class ShapeTest {


    public static void main(String[] args) throws InterruptedException {
        MyPanel myPanel = new MyPanel();
        Shape rect = new Rectangle("a",12,12,12,12);
        Shape circ = new Circle("a",80,80,12,12);
        Shape squa = new Square("a",280,280,12,12);
        myPanel.add(rect);
        myPanel.add(circ);
        myPanel.add(squa);
        boolean run = true;
        while(run) {
            System.out.println("Przegladaj - 1");
            System.out.println("Dodaj - 2");
            System.out.println("Zamknij - 3");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            if (i == 1) {
                myPanel.paint();
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
