public enum Waehrung {
EUR(1),BGN(1.95583),LTL(3.4528),KM(1.95583);
	
	private Waehrung(double zahl) {
		this.kurs = zahl;
	}
private double kurs;

public double euroInWaehrungUmrechnen(double betrag) {
	if(this == BGN) {
		return betrag * BGN.kurs;
	} else if(this == LTL) {
		return betrag * LTL.kurs;
	} else if(this == KM) {
		return betrag * KM.kurs;
	} else {
		return betrag;	
	}
}

public double waehrungInEuroUmrechnen(double betrag) {
	if(this == BGN) {
		return betrag / BGN.kurs;
	} else if(this == LTL) {
		return betrag / LTL.kurs;
	} else if(this == KM) {
		return betrag / KM.kurs;
	} else {
		return betrag;	
	}	
}
}
