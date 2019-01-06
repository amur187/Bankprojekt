import java.time.LocalDate;

/**
 * Controller für ein Konto und eine View
 */

public class KontoController {

    private Kunde kunde = new Kunde("Peter", "Lustig", "Am Löwenzahl 12", LocalDate.now());

    private Konto _model = new Girokonto(kunde, 100005, 5000, false, 121355);
    private KontoOberflaeche _view = new KontoOberflaeche(this);

    /**
     * Übergibt dem Model den Befehl einzuzahlen
     * @param betrag
     */
    public void einzahlen(String betrag) {
        try {
            _model.einzahlen(Double.valueOf(betrag));
        } catch (IllegalArgumentException e) {
            _view.fehlerMeldung();
        }
    }

    /**
     * Checkt den Kontostand des Models
     * @return
     */
    public boolean checkKontostand(){
       return  _model.checkKontostand();
    }

    /**
     * Übergibt dem Model den Befehl abzuheben
     * @param betrag
     */
    public void abheben(String betrag) {
        try {
            _model.abheben(Double.valueOf(betrag));
        } catch (GesperrtException e) {
            _view.fehlerMeldung();
        } catch (IllegalArgumentException e) {
            _view.fehlerMeldung();
        }
    }

    /**
     * Gibt die Kontonummer des Models zurück
     * @return
     */
    public String getKontonummer() {
        return _model.getKontonummerFormatiert();
    }

    /**
     * Gibt das Model zurück
     * @return
     */
    public Konto getModel() {
        return this._model;
    }
}
