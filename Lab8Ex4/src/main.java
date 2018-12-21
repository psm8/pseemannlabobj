import java.util.*;
import java.lang.*;

public class main {
    public static void main(String[] args){
        Pracownicy.add(new Pracownik("8767676761767", 2600));
        Pracownicy.add(new Pracownik("8767676761768", 2300));
        Pracownicy.add(new Pracownik("8767676761769", 2400));
        ArrayList<Pracownik> pracownicy = Pracownicy.getFromDB();
        Collections.sort(pracownicy, new SortBrutto());
        for (int i=0; i<pracownicy.size(); i++) {
            System.out.println(pracownicy.get(i));
        }
    }
}
