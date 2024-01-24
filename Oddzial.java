import java.util.ArrayList;
import java.util.List;

public class Oddzial {
    private String nazwa;
    private int liczbaLozek;
    private List<Pacjent> listaPacjentow;
    private List<Lekarz> listaLekarzy;
    private List<Pielegniarka> listaPielęgniarek;

    //constructor
    public Oddzial(String nazwa, int liczbaLozek) {
        this.nazwa = nazwa;
        this.liczbaLozek = liczbaLozek;
        this.listaPacjentow = new ArrayList<>();
        this.listaLekarzy = new ArrayList<>();
        this.listaPielęgniarek = new ArrayList<>();
    }

    //overload - numer of beds avaible
    public Oddzial(String nazwa) {
        this.nazwa = nazwa;
        this.liczbaLozek = 10;
        this.listaPacjentow = new ArrayList<>();
        this.listaLekarzy = new ArrayList<>();
        this.listaPielęgniarek = new ArrayList<>();
    }

    //getters (all info about person is private - cant access it in diffrent class)
    public String getNazwaOddzialu() {
        return nazwa;
    }

    public int getLiczbaLozek() {
        return liczbaLozek;
    }

    public List<Pacjent> getListaPacjentow() {
        return listaPacjentow;
    }

    public List<Lekarz> getListaLekarzy() {
        return listaLekarzy;
    }

    public List<Pielegniarka> getListaPielęgniarek() {
        return listaPielęgniarek;
    }

    //setters (all info about person is private - cant modify it in diffrent class)
    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public void setLiczbaLozek(int liczbaLozek) {
        this.liczbaLozek = liczbaLozek;
    }

    //methods to add patient/doc/nurse
    public void dodajPacjenta(Pacjent pacjent) {
        listaPacjentow.add(pacjent);
    }

    public void dodajLekarza(Lekarz lekarz) {
        listaLekarzy.add(lekarz);
    }

    public void dodajPielęgniarkę(Pielegniarka pielęgniarka) {
        listaPielęgniarek.add(pielęgniarka);
    }

    //print all nurses
    public void wypiszListePielęgniarek() {
        System.out.println("Lista pielęgniarek w oddziale " + nazwa + ":");
        for (Pielegniarka pielęgniarka : listaPielęgniarek) {
            System.out.println(pielęgniarka);
        }
    }

    //print all patients
    public void wypiszListePacjentow() {
        System.out.println("Lista pacjentów w oddziale " + nazwa + ":");
        for (Pacjent pacjent : listaPacjentow) {
            System.out.println(pacjent);
        }
    }
    
    //print all docs
    public void wypiszListeLekarzy() {
        System.out.println("Lista lekarzy w oddziale " + nazwa + ":");
        for (Lekarz lekarz : listaLekarzy) {
            System.out.println(lekarz);
        }
    }

    //easy output of complet data of the ward
    @Override
    public String toString() {
        return "Oddzial{" +
                "nazwa='" + nazwa + '\'' +
                ", liczbaLozek=" + liczbaLozek +
                ", listaPacjentow=" + listaPacjentow +
                ", listaLekarzy=" + listaLekarzy +
                ", listaPielęgniarek=" + listaPielęgniarek +
                '}';
    }
}
