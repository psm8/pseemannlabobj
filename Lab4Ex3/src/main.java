import java.util.*;
import java.lang.*;

public class main {
    public static void main(String[] args){
        ArrayList<Pracownik> Pracownicy = new ArrayList<Pracownik>();
        Pracownicy.add(new Pracownik("8767676761767", 2600));
        Pracownicy.add(new Pracownik("8767676761768", 2300));
        Pracownicy.add(new Pracownik("8767676761769", 2400));
        Collections.sort(Pracownicy, new SortBrutto());
        for (int i=0; i<Pracownicy.size(); i++) {
            System.out.println(Pracownicy.get(i));
        }
    }
}
