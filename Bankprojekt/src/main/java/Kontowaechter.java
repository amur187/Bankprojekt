/**
 * Wächter von Konten
 */

public class Kontowaechter implements Kontobeobachter {
    @Override
    public void benachrichtigung(Konto k) {
        System.out.println("Das Konto wurde geändert");
    }
}
