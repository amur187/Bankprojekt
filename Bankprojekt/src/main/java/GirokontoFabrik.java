public class GirokontoFabrik implements KontoAbstrakteFabrik {
    private Kunde kunde;
    private long kontonr;
    private int dispo;

    public GirokontoFabrik(Kunde kunde, long kontonr, int dispo) {
        this.kunde = kunde;
        this.kontonr = kontonr;
        this.dispo = dispo;
    }

    public Konto kontoErstellen() {
        return new Girokonto(this.kunde, this.kontonr, (double)this.dispo);
    }
}
