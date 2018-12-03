//Alihan Atmaca - s0565573
import java.time.LocalDate;

/**
 * Testprogramm f�r Konten
 * @author Doro
 *
 */
public class Kontentest {

	/**
	 * Testprogramm f�r Konten
	 *
	 * @param args wird nicht benutzt
	 */
	public static void main(String[] args) {

		Student ich = new Student("Bill", "Gates", "Moneyrichtstr. 777", LocalDate.of(1955, 10, 28), "Harvard University", "Computer Science", LocalDate.of(1973, 10, 01), LocalDate.of(1974, 10, 12));
Kunde du = new Kunde();
		Girokonto meinGiro = new Girokonto(ich, 1234, 1000.0);
		meinGiro.einzahlen(50);
		System.out.println(meinGiro.toString());

		Sparbuch meinSpar = new Sparbuch(ich, 9876);
		meinSpar.einzahlen(50);
		try {
			boolean hatGeklappt = meinSpar.abheben(70);
			System.out.println("Abhebung hat geklappt: " + hatGeklappt);
			System.out.println(meinSpar);
		} catch(GesperrtException e) {
			System.out.println("Zugriff auf gesperrtes Konto - Polizei rufen!");
		}

		Bank b = new Bank(10050000L);
        b.giroKontoErstellen(ich);
        b.sparbuchErstellen(ich);
        b.giroKontoErstellen(du);
        b.sparbuchErstellen(du);
        b.giroKontoErstellen(du);
        b.sparbuchErstellen(du);
        b.giroKontoErstellen(du);
        b.sparbuchErstellen(du);

        b.geldEinzahlen(1000000001,10000);
		b.geldEinzahlen(1000000002,10);
		b.geldEinzahlen(1000000003,10);
		b.geldEinzahlen(1000000004,10);
		b.geldEinzahlen(1000000005,10000);
		b.geldEinzahlen(1000000006,10000);

		b.kontoLoeschen(1000000003L);
		b.kontoLoeschen(1000000005L);
		b.kontoLoeschen(1000000006L);


		System.out.println(b.getAlleKonten());
        try {
            b.geldUeberweisen(1000000001,1000000002,7777,"Test");
        } catch(GesperrtException e) {
            e.printStackTrace();
        }


		for(int i = 0; i < b.getAlleKontonummern().size() ; i++) {
			System.out.println(b.getAlleKontonummern().get(i));
		}

        b.kontoLoeschen(1000000003L);


		System.out.println(b.getKundengeburtstage());


    //    System.out.println(b.getKontonummernLuecken());


	}
}