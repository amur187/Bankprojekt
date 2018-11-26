/**
 * Rundet einen double-Wert auf 2 Kommastellen genau.
 * @author Alihan Atmaca, Dennis Hiller
 */

public abstract class Runden {
    /**
     * Rundet eine zahl auf 2 Kommastellen genau
     * @param zahl
     * @return gerundete Zahl
     */
    public static double zweiNachkommaStellen(double zahl){
        double ergebnis = Math.floor(zahl * 100) / 100;
        return ergebnis;
    }
}
