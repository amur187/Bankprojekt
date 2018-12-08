import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * Studenten erweitern die Klasse Kunde. Student ist wer an einer Lehreinrichtung immatrikuliert ist.
 * @author dennis
 *
 */

public class Student extends Kunde{

	String studienfach;
	String universitaet;
	LocalDate immatrikulationsDatumStart;
	LocalDate immatrikulationsDatumEnde;

	/**
	 * Konstruktor zum erstellen eines Studenten
	 * @param vorname
	 * 			String
	 * @param nachname
	 * 			String
	 * @param adresse
	 * 			String
	 * @param gebdat
	 * 			LocalDate
	 * @param universitaet
	 * 			String
	 * @param studienfach
	 * 			String
	 * @param start
	 * 			LocalDate
	 * @param ende
	 * 			LocalDate
	 */
	public Student(String vorname, String nachname, String adresse, LocalDate gebdat,String universitaet,String studienfach, LocalDate start, LocalDate ende) {
		super(vorname,nachname,adresse, gebdat);
		this.immatrikulationsDatumStart = start;
		this.immatrikulationsDatumEnde = ende;
		this.universitaet = universitaet;
		this.studienfach = studienfach;

	}

	/**
	 * Erneuert die Start- und Enddaten des Semesters
	 * @param start Immatrikulationsdatum
	 * @param ende Exmatrikulationsdatum
	 */

	public void immatrikulationEintragen(LocalDate start,LocalDate ende) {
			this.immatrikulationsDatumStart = start;
			this.immatrikulationsDatumEnde = ende;
	}


	/**
	 * Ueberprueft ob das heutige Datum zwischen dem Immatrikulations und Exmatrikulationsdatum ist
	 * @return true wenn immatrikuliert, false wenn nicht
	 */
	public boolean immatrikulationAbfragen() {
		if(LocalDate.now().isAfter(immatrikulationsDatumStart) && LocalDate.now().isBefore(immatrikulationsDatumEnde) ||
				LocalDate.now().equals(immatrikulationsDatumStart) || LocalDate.now().equals(immatrikulationsDatumEnde) ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {

		String ausgabe;
		DateTimeFormatter df = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		ausgabe = this.universitaet + " " + this.studienfach  + " " + df.format(this.immatrikulationsDatumStart) + " bis " + df.format(this.immatrikulationsDatumEnde) + System.getProperty("line.separator");
		return super.toString() + ausgabe;
	}

}
