public class Kontofabrik {
    public Kontofabrik() {
    }

    public static Konto getKonto(KontoAbstrakteFabrik fabrik) {
        return fabrik.kontoErstellen();
    }
}
