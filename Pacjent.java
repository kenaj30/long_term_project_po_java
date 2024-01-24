public class Pacjent extends Osoba {
    private int idPacjenta;
    private String diagnoza;
    private static int kolejneIdPacjenta = 1;

    //constructor
    public Pacjent(String imie, String nazwisko, int wiek, String plec, String diagnoza) {
        super(imie, nazwisko, wiek, plec);
        this.idPacjenta = kolejneIdPacjenta;
        this.diagnoza = diagnoza;
    }
    //overloaded - we might know the diagnosis
    public Pacjent(String imie, String nazwisko, int wiek, String plec) {
        super(imie, nazwisko, wiek, plec);
        this.idPacjenta = kolejneIdPacjenta;
        this.diagnoza = "Brak diagnozy";
    }

    //getters (all info about person is private - cant access it in diffrent class)
    public int getIdPacjenta() {
        return idPacjenta;
    }

    public String getDiagnoza() {
        return diagnoza;
    }

    //setters (all info about person is private - cant modify it in diffrent class)
    public void setIdPacjenta(int idPacjenta) {
        this.idPacjenta = idPacjenta;
    }

    public void setDiagnoza(String diagnoza) {
        this.diagnoza = diagnoza;
    }

    //easy output of complet data of the patient
    @Override
    public String toString() {
        return "Pacjent{" +
                "idPacjenta=" + idPacjenta +
                ", diagnoza='" + diagnoza + '\'' +
                "} " + super.toString();
    }
}
