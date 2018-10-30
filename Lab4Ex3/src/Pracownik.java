public class Pracownik {
    String pesel;
    double wynagrodzenieBrutto;

    public Pracownik(String _pesel, double _wynagrodzenieBrutto){
        this.pesel = _pesel;
        this.wynagrodzenieBrutto = _wynagrodzenieBrutto;
    }
    public String toString()
    {
        return this.pesel + " " + this.wynagrodzenieBrutto;
    }
}
