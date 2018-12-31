import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void startMenu(){
        boolean started = true;
        showMenu();
        while(started){
            int auswahl = inputDialog();
            switch(auswahl){
                case 1:
                    Schnittstelle.tabelleAusgeben();
                    break;
                case 2:
                    datensatzHinzufuegen();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 0:
                    System.out.println("Goodbye");
                    started = false;
                    break;

            }
        }
    }

    private int inputDialog(){
        Scanner sc = new Scanner(System.in);
        int zahl = -1;
        try {
            zahl = sc.nextInt();
        } catch(InputMismatchException e){
            System.out.println("Eingabe nicht erlaubt!");
        }

        return zahl;
    }
private void datensatzHinzufuegen(){
        String name = stringEinlesen("Name");
        String sitz = stringEinlesen("Sitz");
        String farben = stringEinlesen("Farben");
         int mitglieder = intEinlesen("Mitgliederzahl");
         int heimstadion = intEinlesen("StadionID");

    Schnittstelle.datensatzHinzufuegen(name,sitz,farben,mitglieder,heimstadion);

}
private int intEinlesen(String typ){
    Scanner sc = new Scanner(System.in);
    int rueckgabe = 0;
    try {
        System.out.print(typ + " eingeben: ");
        rueckgabe = sc.nextInt();
    } catch(InputMismatchException e){
    }
    return rueckgabe;
}

    private String stringEinlesen(String typ){
        Scanner sc = new Scanner(System.in);
        String rueckgabe = "";
        try {
            System.out.print(typ + " eingeben: ");
            rueckgabe = sc.next();
        } catch(InputMismatchException e){
        }
        return rueckgabe;
    }

    private void showMenu(){
        System.out.println("Datenbankbeleg - Menu");
        System.out.println("----------------------");
        System.out.println("1) Ausgabe der Tabelle");
        System.out.println("2) Datensatz hinzufuegen");
        System.out.println("3) Datensatz loeschen");
        System.out.println("4) Einzelne Datensaetze anzeigen");
        System.out.println("0) Beenden");
    }


}
