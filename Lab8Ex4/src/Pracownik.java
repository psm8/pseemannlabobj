public class Pracownik {
    private String pesel;
    private double wynagrodzenieBrutto;

    public Pracownik(String _pesel, double _wynagrodzenieBrutto){
        this.pesel = _pesel;
        this.wynagrodzenieBrutto = _wynagrodzenieBrutto;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public double getWynagrodzenieBrutto() {
        return wynagrodzenieBrutto;
    }

    public void setWynagrodzenieBrutto(double wynagrodzenieBrutto) {
        this.wynagrodzenieBrutto = wynagrodzenieBrutto;
    }

    public String toString()
    {
        return this.pesel + " " + this.wynagrodzenieBrutto;
    }
}
