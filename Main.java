import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int kolejnyIdPersonelu = 1;
    private static int kolejnyNumerLicencji = 1;

    public static void main(String[] args) {
        //names and surnames are taken from a txt file
        List<String> listaImion = wczytajDaneZPliku("C:\\Users\\Janek\\Desktop\\put\\sem 3\\po\\java\\projekt\\src\\imiona.txt");
        List<String> listaNazwisk = wczytajDaneZPliku("C:\\\\Users\\\\Janek\\\\Desktop\\\\put\\\\sem 3\\\\po\\\\java\\\\projekt\\\\src\\\\n" + //
                "azwiska.txt");

        //catch if file is ok
        if (listaImion.isEmpty() || listaNazwisk.isEmpty()) {
            System.out.println("Błąd: Puste dane w plikach imiona.txt lub nazwiska.txt.");
            return;
        }

        List<String> listaPersonelu = wczytajImionaZNazwiskami(listaImion, listaNazwisk);

        //creating wards based on user input
        List<Oddzial> listaOddzialow = generujListeOddzialow(podajIloscOddzialow());

        //create lists of docs and nurses 
        List<Lekarz> listaLekarzy = generujListeLekarzy(listaPersonelu);
        List<Pielegniarka> listaPielęgniarek = generujListePielęgniarek(listaPersonelu);

        //assigne workers to wards
        dodajPersonelDoOddzialow(listaOddzialow, listaLekarzy, listaPielęgniarek);

        //add patient
        //dodajReczniePacjentowDoOddzialow(listaOddzialow);

        //create list of patients
        dodajPrzypadkowychPacjentowDoOddzialow(listaOddzialow, listaPersonelu);


        Scanner scanner = new Scanner(System.in);
        int wybor;

        //while loop to do operations
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Wyświetl wszystkie oddziały");
            System.out.println("2. Wyświetl lekarzy konkretnego oddziału");
            System.out.println("3. Wyświetl pielęgniarki konkretnego oddziału");
            System.out.println("4. Wyświetl pacjentów konkretnego oddziału");
            System.out.println("5. Dodaj pacjenta do oddziału");
            System.out.println("6. Wyjście");
            System.out.print("Wybierz opcję: ");

            wybor = scanner.nextInt();
            switch (wybor) {
                case 1: //print list of wards
                    wyswietlWszystkieOddzialy(listaOddzialow);
                    break;
                case 2: //print list of docs of a ward
                    wyswietlLekarzyKonkretnegoOddzialu(listaOddzialow, scanner);
                    break;
                case 3: //print nurses of a ward
                    wyswietlPielęgniarkiKonkretnegoOddzialu(listaOddzialow, scanner);
                    break;
                case 4: //print patients of a ward
                    wyswietlPacjentowKonkretnegoOddzialu(listaOddzialow, scanner);
                    break;
                case 5: //add patient to a ward (manually)
                    dodajPacjentaDoOddzialu(listaOddzialow, scanner);
                    break;
                case 6: //end
                    System.out.println("Zakończono program.");
                    System.exit(0);
                default: //fail catch
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }

    //methood to specify number of wards
    private static int podajIloscOddzialow() {
        Scanner czytaj = new Scanner(System.in);
        System.out.print("Podaj liczbę oddziałow: ");
        //catch non int value
        while (!czytaj.hasNextInt()) {
            System.out.println("To nie jest liczba całkowita. Podaj liczbę oddziałów: ");
            czytaj.next();
        }
        int userInput = czytaj.nextInt();
        czytaj.nextLine();
        return userInput;
    }

    //read data from file - return list of strings (names)
    private static List<String> wczytajDaneZPliku(String nazwaPliku) {
        List<String> listaDanych = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(new File(nazwaPliku)))) {
            String linia;

            while ((linia = br.readLine()) != null) {
                listaDanych.add(linia);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaDanych;
    }

    //create full names - list (full n)
    private static List<String> wczytajImionaZNazwiskami(List<String> listaImion, List<String> listaNazwisk) {
        List<String> listaImionZNazwiskami = new ArrayList<>();

        for (String imie : listaImion) {
            for (String nazwisko : listaNazwisk) {
                listaImionZNazwiskami.add(imie + " " + nazwisko);
            }
        }

        return listaImionZNazwiskami;
    }

    //create list of wards
    private static List<Oddzial> generujListeOddzialow(int liczbaOddzialow) {
        List<Oddzial> listaOddzialow = new ArrayList<>();
        Random rand = new Random();

        for (int i = 1; i <= liczbaOddzialow; i++) {
            String nazwaOddzialu = "Oddzial " + i;
            int liczbaLozek = rand.nextInt(20) + 1; //rand number of beds

            Oddzial oddzial = new Oddzial(nazwaOddzialu, liczbaLozek);
            listaOddzialow.add(oddzial);
        }

        return listaOddzialow;
    }

    //create list of docs based on method returning full name
    private static List<Lekarz> generujListeLekarzy(List<String> listaImionZNazwiskami) {
        List<Lekarz> listaLekarzy = new ArrayList<>();
        Random rand = new Random();

        for (String personel : listaImionZNazwiskami) {
            String[] dane = personel.split(" ");
            String imie = dane[0];
            String nazwisko = dane[1];
            int wiek = rand.nextInt(40) + 30; //rand age 30 - 70 (normal age for doc in pl)
            String plec = (rand.nextBoolean()) ? "Mężczyzna" : "Kobieta";
            String stanowisko = "Lekarz";
            int idPersonelu = kolejnyIdPersonelu++; //uniq id
            String specjalizacja = "Specjalizacja" + rand.nextInt(5); //specialization
            String numerLicencji = "L" + kolejnyNumerLicencji++; //uniq licence 

            Lekarz lekarz = new Lekarz(imie, nazwisko, wiek, plec, stanowisko, idPersonelu, specjalizacja, numerLicencji);
            listaLekarzy.add(lekarz);
        }

        return listaLekarzy;
    }


    //create list of nurses based on method returning full name    
    private static List<Pielegniarka> generujListePielęgniarek(List<String> listaImionZNazwiskami) {
        List<Pielegniarka> listaPielęgniarek = new ArrayList<>();
        Random rand = new Random();

        for (String personel : listaImionZNazwiskami) {
            String[] dane = personel.split(" ");
            String imie = dane[0];
            String nazwisko = dane[1];
            int wiek = rand.nextInt(40) + 25; //rand age from range 25-65
            String plec = (rand.nextBoolean()) ? "Mężczyzna" : "Kobieta";
            String stanowisko = "Pielęgniarka";
            int idPersonelu = kolejnyIdPersonelu++; //uniq id
            int doswiadczenie = wiek - 25; //working experience age -25 (min age of nurse)
            int idPielegniarki = kolejnyNumerLicencji++; //uniqe licence 

            Pielegniarka pielegniarka = new Pielegniarka(imie, nazwisko, wiek, plec, stanowisko, idPersonelu, doswiadczenie, idPielegniarki);
            listaPielęgniarek.add(pielegniarka);
        }

        return listaPielęgniarek;
    }

    //assaign workers to wards
    private static void dodajPersonelDoOddzialow(List<Oddzial> listaOddzialow, List<Lekarz> listaLekarzy, List<Pielegniarka> listaPielęgniarek) {
        Random rand = new Random();
        for (Oddzial oddzial : listaOddzialow) {
            for (int i = 0; i < rand.nextInt(5) + 1; i++) {
                //add docs
                Lekarz lekarz = listaLekarzy.get(rand.nextInt(listaLekarzy.size()));
                oddzial.dodajLekarza(lekarz);
            }

            for (int i = 0; i < rand.nextInt(10) + 1; i++) {
                //add nurses
                Pielegniarka pielęgniarka = listaPielęgniarek.get(rand.nextInt(listaPielęgniarek.size()));
                oddzial.dodajPielęgniarkę(pielęgniarka);
            }
        }
    }

    //manually add a patient
    private static void dodajReczniePacjentowDoOddzialow(List<Oddzial> listaOddzialow) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Dodawanie pacjentów ręcznie:");

        for (Oddzial oddzial : listaOddzialow) {
            System.out.println("Oddział: " + oddzial.getNazwaOddzialu());
            System.out.print("Podaj liczbę pacjentów do dodania: ");
            int liczbaPacjentow = scanner.nextInt();

            for (int i = 0; i < liczbaPacjentow; i++) {
                System.out.print("Podaj imię pacjenta: ");
                String imie = scanner.next();

                System.out.print("Podaj nazwisko pacjenta: ");
                String nazwisko = scanner.next();

                System.out.print("Podaj wiek pacjenta: ");
                int wiek = scanner.nextInt();

                System.out.print("Podaj płeć pacjenta: ");
                String plec = scanner.next();

                System.out.print("Podaj diagnozę pacjenta: ");
                String diagnoza = scanner.next();

                Pacjent pacjent = new Pacjent(imie, nazwisko, wiek, plec, diagnoza);
                oddzial.dodajPacjenta(pacjent);
            }
        }
    }

    //print all wards
    private static void wyswietlWszystkieOddzialy(List<Oddzial> listaOddzialow) {
        System.out.println("Lista wszystkich oddziałów:");
        for (int i = 0; i < listaOddzialow.size(); i++) {
            System.out.println((i + 1) + ". " + listaOddzialow.get(i).getNazwaOddzialu());
        }
        System.out.println("------------------------");
    }

    //print docs of a ward
    private static void wyswietlLekarzyKonkretnegoOddzialu(List<Oddzial> listaOddzialow, Scanner scanner) {
        wyswietlWszystkieOddzialy(listaOddzialow);
        System.out.print("Wybierz numer oddziału: ");
        int numerOddzialu = scanner.nextInt();

        if (numerOddzialu > 0 && numerOddzialu <= listaOddzialow.size()) {
            Oddzial oddzial = listaOddzialow.get(numerOddzialu - 1);
            System.out.println("Lekarze w oddziale " + oddzial.getNazwaOddzialu() + ":");
            oddzial.wypiszListeLekarzy();
            System.out.println("------------------------");
        } else {
            System.out.println("Nieprawidłowy numer oddziału.");
        }
    }

    //print nurses of a ward
    private static void wyswietlPielęgniarkiKonkretnegoOddzialu(List<Oddzial> listaOddzialow, Scanner scanner) {
        wyswietlWszystkieOddzialy(listaOddzialow);
        System.out.print("Wybierz numer oddziału: ");
        int numerOddzialu = scanner.nextInt();

        if (numerOddzialu > 0 && numerOddzialu <= listaOddzialow.size()) {
            Oddzial oddzial = listaOddzialow.get(numerOddzialu - 1);
            System.out.println("Pielęgniarki w oddziale " + oddzial.getNazwaOddzialu() + ":");
            oddzial.wypiszListePielęgniarek();
            System.out.println("------------------------");
        } else {
            System.out.println("Nieprawidłowy numer oddziału.");
        }
    }

    //print patients of a ward
    private static void wyswietlPacjentowKonkretnegoOddzialu(List<Oddzial> listaOddzialow, Scanner scanner) {
        wyswietlWszystkieOddzialy(listaOddzialow);
        System.out.print("Wybierz numer oddziału: ");
        int numerOddzialu = scanner.nextInt();

        if (numerOddzialu > 0 && numerOddzialu <= listaOddzialow.size()) {
            Oddzial oddzial = listaOddzialow.get(numerOddzialu - 1);
            System.out.println("Pacjenci w oddziale " + oddzial.getNazwaOddzialu() + ":");
            oddzial.wypiszListePacjentow();
            System.out.println("------------------------");
        } else {
            System.out.println("Nieprawidłowy numer oddziału.");
        }
    }

    //assaign patient to a ward
    private static void dodajPacjentaDoOddzialu(List<Oddzial> listaOddzialow, Scanner scanner) {
        wyswietlWszystkieOddzialy(listaOddzialow);
        System.out.print("Wybierz numer oddziału, do którego chcesz dodać pacjenta: ");
        int numerOddzialu = scanner.nextInt();

        if (numerOddzialu > 0 && numerOddzialu <= listaOddzialow.size()) {
            Oddzial oddzial = listaOddzialow.get(numerOddzialu - 1);
            System.out.print("Podaj imię pacjenta: ");
            String imie = scanner.next();

            System.out.print("Podaj nazwisko pacjenta: ");
            String nazwisko = scanner.next();

            System.out.print("Podaj wiek pacjenta: ");
            int wiek = scanner.nextInt();

            System.out.print("Podaj płeć pacjenta: ");
            String plec = scanner.next();

            System.out.print("Podaj diagnozę pacjenta: ");
            String diagnoza = scanner.next();

            Pacjent pacjent = new Pacjent(imie, nazwisko, wiek, plec, diagnoza);
            oddzial.dodajPacjenta(pacjent);
            System.out.println("Pacjent dodany do oddziału " + oddzial.getNazwaOddzialu() + ".");
            System.out.println("------------------------");
        } else {
            System.out.println("Nieprawidłowy numer oddziału.");
        }
    }

    private static void dodajPrzypadkowychPacjentowDoOddzialow(List<Oddzial> listaOddzialow, List<String> listaImionZNazwiskami) {
        Random rand = new Random();
    
        for (Oddzial oddzial : listaOddzialow) {
            for (int i = 0; i < 3; i++) {
                String[] dane = listaImionZNazwiskami.get(rand.nextInt(listaImionZNazwiskami.size())).split(" ");
                String imie = dane[0];
                String nazwisko = dane[1];
                int wiek = rand.nextInt(90) + 1; //rand age from age 1-91
                String plec = (rand.nextBoolean()) ? "Mężczyzna" : "Kobieta";
    
                Pacjent pacjent = new Pacjent(imie, nazwisko, wiek, plec);
                oddzial.dodajPacjenta(pacjent);
            }
        }
    }
    
}
