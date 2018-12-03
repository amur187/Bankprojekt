import java.util.*;

/**
 * Steuerungsklasse f�r eine Ball-Animation
 * @author Doro
 *
 */
public class Ballspiel {
	private BallFrame f;
	private List<Ball> items = Collections.synchronizedList(new ArrayList<>());

	/**
	 * erstellt die Steuerungsklasse f�r die angegebene Oberfl�che
	 * @param f
	 */
	public Ballspiel(BallFrame f)
	{
		this.f = f;
	}
	
	/**
	 * startet einen Ball auf der Oberfl�che und l�sst ihn h�pfen
	 */
	public void ballStarten()
	{

		Runnable r1 = new Ball(f.getZeichenflaeche());
        Thread fred1 = new Thread(r1);
		Ball b = new Ball(f.getZeichenflaeche());
		fred1.start();
		items.add(b);

	}
	
	/**
	 * h�lt alle B�lle auf der Oberfl�che an, so dass sie an ihrer aktuellen Position
	 * stehen bleiben
	 */
	public void baelleStoppen() {
        synchronized(items) {
            for(int i = 0; i < items.size(); i++) {

                    System.out.println(items.get(i));
                    items.get(i).stopBall();


            }
        }
    }
	/**
	 * l�sst alle angehaltenen B�lle wieder weiter h�pfen
	 */
	public void baelleWeiter() {
		
	}

	/**
	 * l�scht alle B�lle von der Oberfl�che
	 */
	public void alleLoeschen() {
		
	}
}




