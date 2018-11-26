import org.junit.jupiter.api.*;

/**
 * Testklasse zum Testen der Nullstellensuche
 * @author Alihan Atmaca, Dennis Hiller
 */


class JUnitTest {

    @Test
    public void testEinsFvonX() {
        double erwartet = 2.23;
        double errechnet = 0;
        try {
            errechnet = Nullstellensuche.sucheNullstelle(-1, 50, (double x) -> {
                return x * x - 5;
            });
        } catch (KeineNullstelleException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(erwartet, Runden.zweiNachkommaStellen(errechnet));
    }

    @Test
    public void testZweiFvonXKeineNullstelle() {
        String erwartet = new KeineNullstelleException().getMessage();
        String geworfen = null;

        try {
            Nullstellensuche.sucheNullstelle(-100 , -12, (double x) -> {
                return x * x - 5;
            });
        } catch (KeineNullstelleException e) {
            geworfen = e.getMessage();
            e.printStackTrace();
        }
        Assertions.assertEquals(erwartet,geworfen);
    }



    @Test
    public void testZweiGvonX() {
        double erwartet = 0.64;
        double errechnet = 0;
        try {
            errechnet = Nullstellensuche.sucheNullstelle(-1, 5, (double x) -> {
                return Math.pow(2.718281,3*x) - 7;
            });

        } catch (KeineNullstelleException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(erwartet, Runden.zweiNachkommaStellen(errechnet));
    }

    @Test
    public void testDreiHvonX()  {
        double erwartet = 5.0;
        double errechnet = 0;

        try {
           errechnet = Nullstellensuche.sucheNullstelle(-10, 10, (double x) -> {
               return ((5-x)/(Math.pow(x,3) + 2));
           });
        } catch (KeineNullstelleException e) {
            e.printStackTrace();
        }
    Assertions.assertEquals(erwartet,errechnet);
    }

    @Test
    public void testVierKvonX()  {
        String erwartet = new KeineNullstelleException().getMessage();
        String geworfen = null;

        try {
            Nullstellensuche.sucheNullstelle(-20, 113, (double x) -> {
                return (x*x + 1);
            });
        } catch (KeineNullstelleException e) {
            geworfen = e.getMessage();
            e.printStackTrace();
        }
        Assertions.assertEquals(erwartet,geworfen);
    }

}