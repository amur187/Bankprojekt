
/**
 * Klasse die eine Methode zum finden von Nullstellen beherbergt.
 * @author Alihan Atmaca, Dennis Hiller
 */

public abstract class Nullstellensuche implements Funktion {

   private static final double GENAUIGKEIT = 0.01;

    /**
     * Überprüft anhand des Verfahrens der Intervallhalbierung ob in dem gegebenen Intervall eine Nullstelle ist.
     * @param a linke Intervallgrenz
     * @param b rechte Intervallgrenze
     * @param f gegebene Funktion
     * @return  die Stelle an der der Funktionswert 0 ist.
     * @throws KeineNullstelleException
     */

    public static double sucheNullstelle(double a, double b, Funktion f) throws KeineNullstelleException {
        double m = (a + b) / 2.0;
        if(f.berechnen(a) * f.berechnen(b) >= 0) throw new KeineNullstelleException();

        if( Math.abs( f.berechnen(m) ) < GENAUIGKEIT )
            return m;
        else if (f.berechnen(m) * f.berechnen(a) < 0)
            return sucheNullstelle(a,m,f);
        else if (f.berechnen(m) * f.berechnen(b) < 0)
            return sucheNullstelle(m,b,f);
        else
            throw new KeineNullstelleException();
    }


}
