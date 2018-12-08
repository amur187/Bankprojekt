
public class SparbuchFabrik implements KontoAbstrakteFabrik {
    private Kunde kunde;
    private long kontonr;

    public SparbuchFabrik(Kunde kunde, long kontonr) {
        this.kunde = kunde;
        this.kontonr = kontonr;
    }

    public Konto kontoErstellen() {
        return new Sparbuch(this.kunde, this.kontonr);
    }
}
