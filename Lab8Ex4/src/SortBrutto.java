import java.util.*;

    public class SortBrutto implements Comparator<Pracownik> {
        public int compare(Pracownik prac1, Pracownik prac2){
            return prac1.getWynagrodzenieBrutto() < prac2.getWynagrodzenieBrutto() ? -1 : prac1.getWynagrodzenieBrutto() == prac2.getWynagrodzenieBrutto() ? 0 : 1;
        }
}
