import java.io.Serializable;

/**
 * stellt ein allgemeines Konto dar
 */
public abstract class Konto implements Comparable<Konto>, Serializable {
	/**
	 * der Kontoinhaber
	 */
	private Kunde inhaber;

	/**
	 * die Kontonummer
	 */
	private final long nummer;

	/**
	 * der aktuelle Kontostand
	 */
	private double kontostand;

	/**
	 * die verwendete Waehrung
	 */
	protected Waehrung waehrung;

	/**
	 * setzt den aktuellen Kontostand
	 * 
	 * @param kontostand
	 *            neuer Kontostand
	 */
	protected void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	/**
	 * Wenn das Konto gesperrt ist (gesperrt = true), k�nnen keine Aktionen daran
	 * mehr vorgenommen werden, die zum Schaden des Kontoinhabers w�ren (abheben,
	 * Inhaberwechsel)
	 */
	private boolean gesperrt;

	/**
	 * Setzt die beiden Eigenschaften kontoinhaber und kontonummer auf die
	 * angegebenen Werte, der anf�ngliche Kontostand wird auf 0 gesetzt.
	 *
	 * @param inhaber
	 *            Kunde
	 * @param kontonummer
	 *            long
	 * @throws IllegalArgumentException
	 *             wenn der Inhaber null
	 */
	public Konto(Kunde inhaber, long kontonummer) {
		if (inhaber == null)
			throw new IllegalArgumentException("Inhaber darf nicht null sein!");
		this.inhaber = inhaber;
		this.nummer = kontonummer;
		this.kontostand = 0;
		this.gesperrt = false;
		this.waehrung = Waehrung.EUR;
	}

	/**
	 * setzt alle Eigenschaften des Kontos auf Standardwerte
	 */
	public Konto() {
		this(Kunde.MUSTERMANN, 1234567);
	}

	/**
	 * liefert den Kontoinhaber zur�ck
	 * 
	 * @return Kunde
	 */
	public final Kunde getInhaber() {
		return this.inhaber;
	}

	/**
	 * setzt den Kontoinhaber
	 * 
	 * @param kinh
	 *            neuer Kontoinhaber
	 * @throws GesperrtException
	 *             wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException
	 *             wenn kinh null ist
	 */
	public final void setInhaber(Kunde kinh) throws GesperrtException {
		if (kinh == null)
			throw new IllegalArgumentException("Der Inhaber darf nicht null sein!");
		if (this.gesperrt)
			throw new GesperrtException(this.nummer);
		this.inhaber = kinh;

	}

	/**
	 * liefert den aktuellen Kontostand
	 * 
	 * @return double
	 */
	public final double getKontostand() {
		return kontostand;
	}

	/**
	 * liefert die Kontonummer zur�ck
	 * 
	 * @return long
	 */
	public final long getKontonummer() {
		return nummer;
	}

	/**
	 * liefert zur�ck, ob das Konto gesperrt ist oder nicht
	 * 
	 * @return
	 */
	public final boolean isGesperrt() {
		return gesperrt;
	}

	/**
	 * Erh�ht den Kontostand um den eingezahlten Betrag.
	 *
	 * @param betrag
	 *            double
	 * @throws IllegalArgumentException
	 *             wenn der betrag negativ ist
	 */
	public void einzahlen(double betrag) {
		if (betrag < 0) {
			throw new IllegalArgumentException("Negativer Betrag");
		}
		setKontostand(getKontostand() + betrag);
	}

	/**
	 * Erhoeht den KOntostand um den eingezahlten Betrag in der Waehrung w
	 * @param betrag
	 * 			double
	 * @param w
	 * 			waehrung
	 */
	public void einzahlen(double betrag,Waehrung w) {
		if (betrag < 0) {
			throw new IllegalArgumentException("Negativer Betrag");
		}
		if (getAktuelleWaehrung() == w) {
			setKontostand(getKontostand() + betrag); 
		} else if(getAktuelleWaehrung() == Waehrung.EUR && w != Waehrung.EUR) {
			setKontostand(getKontostand() + w.waehrungInEuroUmrechnen(betrag));
		} else if (getAktuelleWaehrung() != Waehrung.EUR && w == Waehrung.EUR) {
			setKontostand(getKontostand() + getAktuelleWaehrung().euroInWaehrungUmrechnen(betrag));
		} else {
			
			double tempBetrag = w.waehrungInEuroUmrechnen(betrag);
			System.out.println(tempBetrag);
			setKontostand(getKontostand() + tempBetrag);
		}
	}	
	
	/**
	 * Gibt eine Zeichenkettendarstellung der Kontodaten zur�ck.
	 */
	@Override
	public String toString() {
		String ausgabe;
		ausgabe = "Kontonummer: " + this.getKontonummerFormatiert() + System.getProperty("line.separator");
		ausgabe += "Inhaber: " + this.inhaber;
		ausgabe += "Aktueller Kontostand: " + this.kontostand + " " + getAktuelleWaehrung();
		ausgabe += this.getGesperrtText() + System.getProperty("line.separator");
		return ausgabe;
	}

	/**
	 * Mit dieser Methode wird der geforderte Betrag vom Konto abgehoben, wenn es
	 * nicht gesperrt ist.
	 *
	 * @param betrag
	 *            double
	 * @throws GesperrtException
	 *             wenn das Konto gesperrt ist
	 * @throws IllegalArgumentException
	 *             wenn der betrag negativ ist
	 * @return true, wenn die Abhebung geklappt hat, false, wenn sie abgelehnt wurde
	 */
	public abstract boolean abheben(double betrag) throws GesperrtException;

	/**
	 * Hebt Geld vom Konto ab welches in der gleichen Waehrung gefuehrt wird oder
	 * hebt eine andere Waherung vom EUR Konto ab oder hebt EUR von Konto mit
	 * anderer Waehrung ab
	 * 
	 * @param betrag
	 *            double
	 * @param w
	 *            Waehrung
	 * @return true, wenn die Abhebung geklappt hat, false, wenn sie abgelehnt wurde
	 * @throws GesperrtException
	 *             wenn das Konto gesperrt ist
	 */
	public boolean abheben(double betrag, Waehrung w) throws GesperrtException {
		if (betrag < 0) {
			throw new IllegalArgumentException();
		}
		if (this.isGesperrt()) {
			throw new GesperrtException(this.getKontonummer());
		}
		if (getAktuelleWaehrung() == w) {
			if (getKontostand() - betrag >= 1) {
				setKontostand(getKontostand() - betrag);
				return true;
			} else {
				return false;
			}
		} else if (getAktuelleWaehrung() == Waehrung.EUR && w != Waehrung.EUR) {
			if(getKontostand() - w.waehrungInEuroUmrechnen(betrag) >= 1) {
				setKontostand(getKontostand() - w.waehrungInEuroUmrechnen(betrag));
				return true;
			} else {
				return false;
			}
		} else if (getAktuelleWaehrung() != Waehrung.EUR && w == Waehrung.EUR) {
			if(getKontostand() - getAktuelleWaehrung().euroInWaehrungUmrechnen(betrag) >= 1) {
				setKontostand(getKontostand() - getAktuelleWaehrung().euroInWaehrungUmrechnen(betrag));
				return true;
			} else {
				return false; 
			}
		} else if(getAktuelleWaehrung() != Waehrung.EUR && w != Waehrung.EUR){
			if(getAktuelleWaehrung().waehrungInEuroUmrechnen(getKontostand()) - w.waehrungInEuroUmrechnen(betrag) >= 1) {
				double tempKontoInEuro = getAktuelleWaehrung().waehrungInEuroUmrechnen(getKontostand());
				double tempBetragInEuro =  w.waehrungInEuroUmrechnen(betrag);
				setKontostand(getAktuelleWaehrung().euroInWaehrungUmrechnen((tempKontoInEuro-tempBetragInEuro)));
				return true;
					} else {
						return false;
					}
		} else 
			return false;
		}
	
	/**
	 * gibt die Waehrung an in der das Konto gefuehrt wird
	 * 
	 * @return
	 */
	public Waehrung getAktuelleWaehrung() {
		return this.waehrung;
	}

	/**
	 * wechselt die Waehrung des Kontos
	 */
	public void waehrungswechsel(Waehrung neu) {
		if (getAktuelleWaehrung() == Waehrung.EUR) {
			setKontostand(neu.euroInWaehrungUmrechnen(getKontostand()));
			this.waehrung = neu;
		} else {
			setKontostand(neu.waehrungInEuroUmrechnen(getKontostand()));
			this.waehrung = neu;
		}
	}

	/**
	 * sperrt das Konto, Aktionen zum Schaden des Benutzers sind nicht mehr m�glich.
	 */
	public final void sperren() {
		this.gesperrt = true;
	}

	/**
	 * entsperrt das Konto, alle Kontoaktionen sind wieder m�glich.
	 */
	public final void entsperren() {
		this.gesperrt = false;
	}

	/**
	 * liefert eine String-Ausgabe, wenn das Konto gesperrt ist
	 * 
	 * @return "GESPERRT", wenn das Konto gesperrt ist, ansonsten ""
	 */
	public final String getGesperrtText() {
		if (this.gesperrt) {
			return "GESPERRT";
		} else {
			return "";
		}
	}

	/**
	 * liefert die ordentlich formatierte Kontonummer
	 * 
	 * @return auf 10 Stellen formatierte Kontonummer
	 */
	public String getKontonummerFormatiert() {
		return String.format("%10d", this.nummer);
	}

	/**
	 * liefert den ordentlich formatierten Kontostand
	 * 
	 * @return formatierter Kontostand mit 2 Nachkommastellen und W�hrungssymbol �
	 */
	public String getKontostandFormatiert() {
		return String.format("%10.2f Euro", this.getKontostand());
	}

	/**
	 * Vergleich von this mit other; Zwei Konten gelten als gleich, wen sie die
	 * gleiche Kontonummer haben
	 * 
	 * @param other
	 * @return true, wenn beide Konten die gleiche Nummer haben
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		if (this.nummer == ((Konto) other).nummer)
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {
		return 31 + (int) (this.nummer ^ (this.nummer >>> 32));
	}


	@Override
	public int compareTo(Konto other) {
		if (other.getKontostand() > this.getKontostand())
			return -1;
		if (other.getKontostand() < this.getKontostand())
			return 1;
		return 0;
	}
}
