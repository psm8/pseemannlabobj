import java.io.IOException;
import java.util.*;

public class InheritTest {
    public static void main(String [] args){
        LinkedList<Punkt3D> punkty = new LinkedList<Punkt3D>();
        boolean run=true;
        while(run){
            System.out.println("1. Wczytaj punkt 3D");
            System.out.println("2. Wyswietl wszystkie punkty");
            System.out.println("3. Oblicz odleglosc");
            System.out.println("4. Zakoncz");
            Scanner scan = new Scanner(System.in);
            int i = scan.nextInt();
            switch(i) {
                case 1:
                    System.out.println("Wprowadz x:");
                    Scanner scanX = new Scanner(System.in);
                    int x = scanX.nextInt();
                    System.out.println("Wprowadz y:");
                    Scanner scanY = new Scanner(System.in);
                    int y = scanY.nextInt();
                    System.out.println("Wprowadz x:");
                    Scanner scanZ = new Scanner(System.in);
                    int z = scanZ.nextInt();
                    Punkt3D p3d = new Punkt3D(x, y, z);
                    punkty.add(p3d);
                    break;
                case 2:
                    for(Punkt3D it:punkty){
                        System.out.println(it.getX()+" "+it.getY()+" "+it.getZ());
                    }
                    break;
                case 3:
                    System.out.println("Wprowadz x:");
                    Scanner scanX1 = new Scanner(System.in);
                    int x1 = scanX1.nextInt();
                    System.out.println("Wprowadz y:");
                    Scanner scanY1 = new Scanner(System.in);
                    int y1 = scanY1.nextInt();
                    System.out.println("Wprowadz x:");
                    Scanner scanZ1 = new Scanner(System.in);
                    int z1 = scanZ1.nextInt();
                    Punkt3D p3d1 = new Punkt3D(x1, y1, z1);
                    for(Punkt3D it:punkty){
                        System.out.println(it.distance(p3d1));
                    }
                    break;
                case 4:
                    run = false;
                    break;

            }
        }
    }
}
/*                    try {
                        System.out.println(p3d.distance(p3d3));
                    }catch (IOException e) { e.printStackTrace(); }*/