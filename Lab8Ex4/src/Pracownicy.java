import java.util.ArrayList;

public class Pracownicy {
    public static void add(Pracownik p){
        DB db = new DB();
        db.createTable();
        db.add(p.getPesel(),p.getWynagrodzenieBrutto());
    }

    public static ArrayList<Pracownik> getFromDB(){
        DB db = new DB();
        return db.get();
    }
}
