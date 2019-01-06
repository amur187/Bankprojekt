import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KontoController implements ActionListener {

    private KontoOberflaeche _view = new KontoOberflaeche(this);
    private Konto _model = new Girokonto();


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
