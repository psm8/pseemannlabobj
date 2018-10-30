import java.util.*;

    public class SortBrutto implements Comparator<Pracownik> {
        public int compare(Pracownik prac1, Pracownik prac2){
            return prac1.wynagrodzenieBrutto < prac2.wynagrodzenieBrutto ? -1 : prac1.wynagrodzenieBrutto == prac2.wynagrodzenieBrutto ? 0 : 1;
        }
}
