public class Lekarz extends PersonelMedyczny {
    private String specjalizacja;
    private String numerLicencji;

    //constructor
    public Lekarz(String imie, String nazwisko, int wiek, String plec, String stanowisko, int idPersonelu, String specjalizacja, String numerLicencji) {
        super(imie, nazwisko, wiek, plec, stanowisko, idPersonelu);
        this.specjalizacja = specjalizacja;
        this.numerLicencji = numerLicencji;
    }

    //overloaded - the doctor is not a specialist but just a med school grad :P
    public Lekarz(String imie, String nazwisko, int wiek, String plec, String stanowisko, int idPersonelu) {
        super(imie, nazwisko, wiek, plec, stanowisko, idPersonelu);
        this.specjalizacja = "Stazysta";
        this.numerLicencji = "Brak licencji - stażysta";
    }

    //getters (all info about person is private - cant access it in diffrent class)
    public String getSpecjalizacja() {
        return specjalizacja;
    }

    public String getNumerLicencji() {
        return numerLicencji;
    }

    //setters (all info about person is private - cant modify it in diffrent class)
    public void setSpecjalizacja(String specjalizacja) {
        this.specjalizacja = specjalizacja;
    }

    public void setNumerLicencji(String numerLicencji) {
        this.numerLicencji = numerLicencji;
    }

    //easy output of complet data of the doc
    @Override
    public String toString() {
        return "Lekarz{" +
                "specjalizacja='" + specjalizacja + '\'' +
                ", numerLicencji='" + numerLicencji + '\'' +
                "} " + super.toString();
    }
}
