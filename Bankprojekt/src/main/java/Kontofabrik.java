public class Kontofabrik {
     public Konto getKonto(KontoAbstrakteFabrik fabrik) {
        return fabrik.kontoErstellen();
    }
}
