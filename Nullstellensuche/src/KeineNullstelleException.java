/**
 * Exception die das Fehlen von Nullstellen beschreibt
 * @author Alihan Atmaca, Dennis Hiller
 */
public class KeineNullstelleException extends Exception {
    public KeineNullstelleException(){
        super("Hier befindet sich keine Nullstelle");
    }
}
