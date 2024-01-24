public class Osoba {
    private String imie;
    private String nazwisko;
    private int wiek;
    private String plec;

    //constructor, no overloading (wouldnt make sens)
    public Osoba(String imie, String nazwisko, int wiek, String plec) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.wiek = wiek;
        this.plec = plec;
    }

    //getters (all info about person is private - cant access it in diffrent class)
    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public int getWiek() {
        return wiek;
    }

    public String getPlec() {
        return plec;
    }

    //setters (all info about person is private - cant modify it in diffrent class)
    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public void setPlec(String plec) {
        this.plec = plec;
    }

    //easy output of complet data of the person
    @Override
    public String toString() {
        return "Osoba{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", wiek=" + wiek +
                ", plec='" + plec + '\'' +
                '}';
    }
}
