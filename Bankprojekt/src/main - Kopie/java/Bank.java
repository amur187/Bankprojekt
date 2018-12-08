import com.sun.tools.internal.ws.wsdl.document.Output;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Eine Bank verwaltet Kunden.
 *
 * @author dennis
 */
public class Bank implements Cloneable {
    private final long STARTNUMMER = 1000000000L;
    private long bankleitzahl;
    private long hoechsteNummer = STARTNUMMER;
    private HashMap<Long, Konto> bankKonten = new HashMap();


    /**
     * Kontruktor
     * Erstellt eine Bank mit der gewünschten Bankleitzahl
     *
     * @param bankleitzahl
     */
    public Bank(long bankleitzahl) {
        this.bankleitzahl = bankleitzahl;
    }

    /**
     * Gibt die Bankleitzahl der Bank zurück
     *
     * @return
     */
    public long getBankleitzahl() {
        return this.bankleitzahl;
    }

    /**
     * Erstellt ein Girokonto und fügt es der Bank hinzu
     *
     * @param inhaber
     * @return hoechsteNummer
     */
    public long giroKontoErstellen(Kunde inhaber) {
        long kontonummer = hoechsteNummer;
        Girokonto g = new Girokonto(inhaber, kontonummer , 0);
        bankKonten.put(kontonummer, g);
        hoechsteNummer++;
        return kontonummer;
    }

    /**
     * Erstellt ein Sparbuch und fügt es der Bank hinzu
     *
     * @param inhaber
     * @return hoechsteNummer
     */
    public long sparbuchErstellen(Kunde inhaber) {
        long kontonummer = hoechsteNummer;
        Sparbuch s = new Sparbuch(inhaber, kontonummer);
        bankKonten.put(kontonummer, s);
        hoechsteNummer++;
        return kontonummer;
    }

    /**
     * Gibt alle Konten und deren Kontostand aus, die in dieser Bank existieren
     *
     * @return
     */
    public String getAlleKonten() {
        String message = "Auflistung der Kontonummern:\n";
        for(Long i : bankKonten.keySet()) {
            message += System.getProperty("line.separator") + "Kontonummer: " + bankKonten.get(i).getKontonummer() + System.getProperty("line.separator") + "Kontostand: " + bankKonten.get(i).getKontostandFormatiert() + System.getProperty("line.separator");
        }
        return message;
    }

    /**
     * Gibt eine Liste zurück die alle Kontonummern enthält, die in der Bank vergeben sind
     *
     * @return
     */
    public List<Long> getAlleKontonummern() {
        List l = new ArrayList();
        for(Long i : bankKonten.keySet()) {
            l.add(i);
        }
        return l;
    }

    /**
     * Hebt Geld vom Konto ab, wenn es zu dieser Bank gehört
     *
     * @param von    Kontonummer
     * @param betrag Höhe des abzuhebenden Betrages
     * @return true wenn erfolgreich, false wenn nicht
     * @throws GesperrtException
     */
    public boolean geldAbheben(long von, double betrag) throws GesperrtException {
        //prüfen ob Konto existiert
        if(bankKonten.get(von) != null) {
            //prüfen ob Betrag abgehoben werden kann
            if(betrag > 0) {
                //Betrag vom Konto abheben
                return bankKonten.get(von).abheben(betrag);

            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    /**
     * Zahlt Geld auf einem Bankkonto bei dieser Bank ein
     *
     * @param auf    Empfängerkonto
     * @param betrag Höhe des Geldbetrages
     */
    public void geldEinzahlen(long auf, double betrag) {
        if(bankKonten.get(auf) != null) {
            bankKonten.get(auf).einzahlen(betrag);
        }
    }


    /**
     * Löscht ein Konto von der Bankliste
     *
     * @param nummer
     * @return true wenn erfolgreich, false wenn nicht
     */
    public boolean kontoLoeschen(Long nummer) {
        if(bankKonten.get(nummer) != null) {
            bankKonten.remove(nummer);
            return true;
        } else {
            return false;
        }

    }

    /**
     * Gibt den Kontostand von der Kontonummer aus
     *
     * @param nummer
     * @return den Kontostand, im Fehlerfall wird -99999 ausgegeben. Diese Bank vergibt keine solch großen Dispos
     */
    public double getKontostand(long nummer) {
        if(bankKonten.get(nummer) != null) {
            return bankKonten.get(nummer).getKontostand();
        } else {
            return Double.NaN;
        }
    }

    /**
     * Überweist Geld von einem Girokonto auf ein anderes
     *
     * @param von
     * @param auf
     * @param betrag
     * @param verwendungszweck
     * @return true wenn es geklappt hat, false wenn fehler aufgetreten sind
     * @throws GesperrtException
     */
    public boolean geldUeberweisen(long von, long auf, double betrag, String verwendungszweck) throws GesperrtException {
        //Prüfen ob Start und Zielkonto existiert
        if(bankKonten.get(von) != null && bankKonten.get(auf) != null) {
            //Prüfen ob es auch Girokonten sind
            if(bankKonten.get(von) instanceof Girokonto && bankKonten.get(auf) instanceof Girokonto) {
                //Prüfen ob von genügen Guthaben hat
                if(bankKonten.get(von).abheben(betrag) == true) {
                    bankKonten.get(auf).einzahlen(betrag);
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }

    /**
     * Sperrt alle Konten die im Minus sind
     */
    public void pleitegeierSperren() {
        bankKonten.forEach((n, k) -> {
            if(k.getKontostand() < 0) {
                k.sperren();
            }
        });
    }

    /**
     * Gibt eine Liste mit allen Kunden zurueck welche ein Guthaben von mindestens minimum haben
     *
     * @param minimum
     * @return
     */
    List<Kunde> getKundenMitVollemKonto(double minimum) {
        ArrayList<Kunde> kunden = new ArrayList<>();
        bankKonten.values().stream().forEach(konto -> { if(konto.getKontostand() >= minimum){kunden.add(konto.getInhaber());}});
        return kunden.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Liefert den Namen und den Geburtstag der Kunden
     *
     * @return
     */
    String getKundengeburtstage() {
        StringBuilder rueckgabe = new StringBuilder();
        ArrayList<String> geburtstage = new ArrayList<String>();

        bankKonten.values().stream().forEach(konto -> geburtstage.add(konto.getInhaber().getName() + " " + konto.getInhaber().getGeburtstag() + System.lineSeparator()));
        geburtstage.stream().distinct().forEach(rueckgabe::append);

        return rueckgabe.toString();
    }

    /**
     * Gibt alle freien Kontonummern zurueck
     *
     * @return
     */
    List<Long> getKontonummernLuecken() {
        /**
         * Leider fällt uns hier nichts anderes ein als die Gesamten Kontonummern durchzuiterieren.
         */
        List<Long> rueckgabe = LongStream.range(STARTNUMMER,hoechsteNummer).filter(nummer -> getAlleKontonummern().contains(nummer)).boxed().collect(Collectors.toList());

        return rueckgabe;
    }

    public Bank clone() throws CloneNotSupportedException{
        Bank b = new Bank(this.bankleitzahl);
        try {

            for(long key:bankKonten.keySet()){

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(bankKonten.get(key));

                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ObjectInputStream ois = new ObjectInputStream(bais);
                b.bankKonten.put(key,(Konto) ois.readObject());
            }
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }

    long kontoErstellen(Kontofabrik fabrik, Kunde k, long Kontonummer){
           return fabrik.get
    }

}
