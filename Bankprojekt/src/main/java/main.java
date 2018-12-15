public class main {

    public static void main(String[] args) {
       Konto k1 = new Girokonto();
       Konto k2 = new Sparbuch();
       Kontowaechter k = new Kontowaechter();
       k1.waechterAnmelden(k);
       k1.einzahlen(200);
       k1.einzahlen(200);
        try {
            k1.abheben(300);
        } catch(GesperrtException e) {
            e.printStackTrace();
        }

    }
}
