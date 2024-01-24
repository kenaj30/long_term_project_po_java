public class Pielegniarka extends PersonelMedyczny {
    private int doswiadczenie;
    private int idPielegniarki;

    //constructor
    public Pielegniarka(String imie, String nazwisko, int wiek, String plec, String stanowisko, int idPersonelu, int doswiadczenie, int idPielegniarki) {
        super(imie, nazwisko, wiek, plec, stanowisko, idPersonelu);
        this.doswiadczenie = doswiadczenie;
        this.idPielegniarki = idPielegniarki;
    }

    //getters (all info about person is private - cant access it in diffrent class)
    public int getDoswiadczenie() {
        return doswiadczenie;
    }

    public int getIdPielegniarki() {
        return idPielegniarki;
    }

    //setters (all info about person is private - cant modify it in diffrent class)
    public void setDoswiadczenie(int doswiadczenie) {
        this.doswiadczenie = doswiadczenie;
    }

    public void setIdPielegniarki(int idPielegniarki) {
        this.idPielegniarki = idPielegniarki;
    }

    //easy output of complet data of the nurse
    @Override
    public String toString() {
        return "Pielęgniarka{" +
                "doswiadczenie=" + doswiadczenie +
                ", idPielegniarki=" + idPielegniarki +
                "} " + super.toString();
    }
}
