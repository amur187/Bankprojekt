import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Locale;
import java.util.Scanner;


/**
 * Formatiert einen String
 */
public class stringFormatieren {


    /**
     * Formatiert den String nach den Angaben aus der Aufgabenstellung
     * @param a
     * @param b
     */
    public void aufgabe(int a, double b){
        Scanner sc = new Scanner(System.in);
        LocalDate heute = LocalDate.now();
        LocalTime jetzt = LocalTime.now();
        File output = new File("StringFormat//output.txt");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(output);
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }


        // 1 Zeile - unformatierte Ausgabe
        pw.printf("1. %d", a);
        pw.printf(System.lineSeparator());
        // 2 Zeile - 10 Stellen  Ausgabe
        pw.printf("2. %010d", a );
        pw.printf(System.lineSeparator());
        // 3 Zeile - Mit Vorzeichen und 1000er Trennzeichen
        pw.printf("3. %+,d",a);
        pw.printf(System.lineSeparator());
        // 4 Zeile - Double
        pw.printf("4. %f",b);
        pw.printf(System.lineSeparator());
        // 5 Zeile - Mit Vorzeichen und 2 Nachkommstellen
        pw.printf("5. %+.2f",b);
        pw.printf(System.lineSeparator());
        // 6 Zeile - Exponentialschreibweise
        pw.printf("6. %e",b);
        pw.printf(System.lineSeparator());
        // 7 Zeile - 2 Nachkommastellen und amerikanische Schreibweise
        pw.printf(Locale.ENGLISH,"7. %.2f",b);
        pw.printf(System.lineSeparator());
        // 8 Zeile - Prozentzeichen
        pw.printf("8. %%");
        pw.printf(System.lineSeparator());
        // 9 Zeile - heutiges Datum
        pw.printf("9. %tA %<te.%<tB.%<tY",heute);
        pw.printf(System.lineSeparator());
        // 10 Zeile - heutiges Datum italiano
        pw.printf(Locale.ITALIAN,"10. %tA %<te.%<tB.%<tY",heute);
        pw.printf(System.lineSeparator());
        // 11 Zeile - jetzige Uhrzeit US-Format
        pw.printf("11. %tI:%<tM%<tp",jetzt);
        pw.printf(System.lineSeparator());
        pw.close();

    }



}
