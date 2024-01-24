public class PersonelMedyczny extends Osoba {
    private String stanowisko;
    private int idPersonelu;

    //constructor
    public PersonelMedyczny(String imie, String nazwisko, int wiek, String plec, String stanowisko, int idPersonelu) {
        super(imie, nazwisko, wiek, plec);
        this.stanowisko = stanowisko;
        this.idPersonelu = idPersonelu;
    }

    //getters (all info about person is private - cant access it in diffrent class)
    public String getStanowisko() {
        return stanowisko;
    }

    public int getIdPersonelu() {
        return idPersonelu;
    }

    //setters (all info about person is private - cant modify it in diffrent class)
    public void setStanowisko(String stanowisko) {
        this.stanowisko = stanowisko;
    }

    public void setIdPersonelu(int idPersonelu) {
        this.idPersonelu = idPersonelu;
    }

    //easy output of complet data of the nurse
    @Override
    public String toString() {
        return "PersonelMedyczny{" +
                "stanowisko='" + stanowisko + '\'' +
                ", idPersonelu=" + idPersonelu +
                "} " + super.toString();
    }
}
