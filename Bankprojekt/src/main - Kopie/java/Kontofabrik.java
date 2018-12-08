public class KontoFabrik {
    public KontoFabrik() {
    }

    public static Konto getKonto(KontoAbstrakteFabrik fabrik) {
        return fabrik.kontoErstellen();
    }
}
