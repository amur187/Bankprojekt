public class GirokontoFabrik implements KontoAbstrakteFabrik {
    private Kunde kunde;
    private long kontonr;
    private double  dispo;

    public GirokontoFabrik(Kunde kunde, long kontonr, double dispo) {
        this.kunde = kunde;
        this.kontonr = kontonr;
        this.dispo = dispo;
    }

    public Konto kontoErstellen() {
        return new Girokonto(this.kunde, this.kontonr, this.dispo);
    }
}
