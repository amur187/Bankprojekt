package uhr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
 * Die Controller Klasse
 * @author Alihan, Dennis
 *
 */
public class UhrController {

	private DigitalUhr viewDigital;
	private KreisUhr viewKreis;
	private Uhr model;
	
	/**
	 * erstellt den Controller und zeigt beide Views an
	 */
	public UhrController()
	{
		this.model = new Uhr();
		this.viewDigital = new DigitalUhr();
		this.viewKreis = new KreisUhr();
		
		kreisSetZeit();
		addListener();
		showView();
		
		
		new Thread() 
		{
			@Override
			public void run() 
			{
				try {
					while (true) {
						tick();
						Thread.sleep(1000);
					}
				}
				catch (InterruptedException e) {}
			}
		}.start();
		
	}
	
	/**
	 * Holen der aktuellen Uhrzeit und Anzeige, wenn die Uhr angestellt ist
	 */
	public void tick() 
	{
		if (viewDigital.getUhrAn())
		{
			String x = String.format("%02d:%02d:%02d", model.getStunde(), model.getMinute(),
					model.getSekunde());
			
			viewDigital.setAnzeige(x);
		}
		
		if(viewKreis.getUhrAn()) 
		{
			kreisSetZeit();
			viewKreis.repaint();
		}
		
	}
	
	/**
	 * setzt die KreisUhr auf die aktuelle Uhrzeit
	 */
	public void kreisSetZeit()
	{
		viewKreis.setZeit(model.getStunde(), model.getMinute(),model.getSekunde());
	}
	
	/**
	 * Setzt beide Views auf Visible
	 */
	public void showView() {
		this.viewDigital.setVisible(true);
		this.viewKreis.setVisible(true);
	}
	
	/**
	 * F�gt den Views ihre Listener Hinzu
	 */
	private void addListener() {
		this.viewDigital.setUhrAnButton(new UhrAnListener());
		this.viewDigital.setUhrAusButton(new UhrAusListener());
		this.viewKreis.addKeyListener(new KeyCheck());
	}

	/**
	 * Klasse zum erstellen des Listeners, welcher die Uhr anschaltet
	 * @author Alihan, Dennis
	 *
	 */
	class UhrAnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			viewDigital.setUhrAn(true);
			viewDigital.getKnopf()[0].setEnabled(false);
			viewDigital.getKnopf()[1].setEnabled(true);
		}	
	}
	
	/**
	 * Klasse zum erstellen des Listeners, welcher die Uhr ausschaltet
	 * @author Alihan, Dennis
	 *
	 */
	class UhrAusListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			viewDigital.setUhrAn(false);
			viewDigital.getKnopf()[1].setEnabled(false);
			viewDigital.getKnopf()[0].setEnabled(true);
		}	
	}
	
	/**
	 * Klasse zum erstellen des Listeners, welcher die Uhr an-/ausschaltet
	 * @author Alihan, Dennis
	 *
	 */
	class KeyCheck implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {}

		@Override
		public void keyPressed(KeyEvent e) {
			switch (Character.toUpperCase(e.getKeyChar())) {
			case 'E':
				viewKreis.setUhrAn(true);
				break; // "Ein"
			case 'A':
				viewKreis.setUhrAn(false);				
				break; // "Aus"
			}
			viewKreis.repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {}
		
	}
	
	public static void main (String[] args) {
		UhrController uc = new UhrController();
	}
	
}
