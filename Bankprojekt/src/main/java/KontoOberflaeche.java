import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;

import javax.swing.*;

/**
 * Eine Oberfl�che f�r ein einzelnes Konto. Man kann einzahlen
 * und abheben und sperren und die Adresse des Kontoinhabers
 * �ndern
 *
 * @author Doro
 */
public class KontoOberflaeche extends BorderPane {
    private Text ueberschrift;
    private GridPane anzeige;
    private Text txtNummer;
    /**
     * Anzeige der Kontonummer
     */
    private Text nummer;
    private Text txtStand;
    /**
     * Anzeige des Kontostandes
     */
    private Text stand;
    private Text txtGesperrt;
    /**
     * Anzeige und �nderung des Gesperrt-Zustandes
     */
    private CheckBox gesperrt;
    private Text txtAdresse;
    /**
     * Anzeige und �nderung der Adresse des Kontoinhabers
     */
    private TextArea adresse;
    /**
     * Anzeige von Meldungen �ber Kontoaktionen
     */
    private Text meldung;
    private HBox aktionen;
    /**
     * Auswahl des Betrags f�r eine Kontoaktion
     */
    private TextField betrag;
    /**
     * l�st eine Einzahlung aus
     */
    private Button einzahlen;
    /**
     * l�st eine Abhebung aus
     */
    private Button abheben;

    /**
     * erstellt die Oberfl�che
     */
    public KontoOberflaeche(KontoController kontoController) {
        init(kontoController);
    }

    /**
     * Ruft die Oberfläche auf
     * @param kontoController
     */

    public void init(KontoController kontoController) {
        ueberschrift = new Text("Ein Konto veraendern");
        ueberschrift.setFont(new Font("Sans Serif", 25));
        BorderPane.setAlignment(ueberschrift, Pos.CENTER);
        this.setTop(ueberschrift);
        anzeige = new GridPane();
        anzeige.setPadding(new Insets(20));
        anzeige.setVgap(10);
        anzeige.setAlignment(Pos.CENTER);
        txtNummer = new Text("Kontonummer:");
        txtNummer.setFont(new Font("Sans Serif", 15));
        anzeige.add(txtNummer, 0, 0);
        nummer = new Text(kontoController.getKontonummer());
        nummer.setFont(new Font("Sans Serif", 15));
        GridPane.setHalignment(nummer, HPos.RIGHT);
        anzeige.add(nummer, 1, 0);
        txtStand = new Text("Kontostand:");
        txtStand.setFont(new Font("Sans Serif", 15));
        anzeige.add(txtStand, 0, 1);

        stand = new Text();
        stand.setFont(new Font("Sans Serif", 15));
        anzeige.getChildren().remove(stand);
        GridPane.setHalignment(stand, HPos.RIGHT);
        anzeige.add(stand, 1, 1);

        txtGesperrt = new Text("Gesperrt: ");
        txtGesperrt.setFont(new Font("Sans Serif", 15));
        anzeige.add(txtGesperrt, 0, 2);
        gesperrt = new CheckBox();
        GridPane.setHalignment(gesperrt, HPos.RIGHT);
        anzeige.add(gesperrt, 1, 2);
        txtAdresse = new Text();
        txtAdresse.setFont(new Font("Sans Serif", 15));
        anzeige.add(txtAdresse, 0, 3);
        adresse = new TextArea("");
        adresse.setPrefColumnCount(25);
        adresse.setPrefRowCount(2);
        GridPane.setHalignment(adresse, HPos.RIGHT);
        anzeige.add(adresse, 1, 3);
        meldung = new Text("Willkommen lieber Benutzer");
        meldung.setFont(new Font("Sans Serif", 15));
        meldung.setFill(Color.RED);
        anzeige.add(meldung, 0, 4, 2, 1);
        this.setCenter(anzeige);
        aktionen = new HBox();
        aktionen.setSpacing(10);
        aktionen.setAlignment(Pos.CENTER);
        betrag = new TextField("100.00");
        aktionen.getChildren().add(betrag);
        einzahlen = new Button("Einzahlen");
        aktionen.getChildren().add(einzahlen);
        abheben = new Button("Abheben");
        aktionen.getChildren().add(abheben);
        this.setBottom(aktionen);

        stringPropAndDoublePropBinding(stand.textProperty(), kontoController.getModel().kontostandProperty(), kontoController);

        gesperrt.selectedProperty().bindBidirectional(kontoController.getModel().gesperrtProperty());
        adresse.textProperty().bindBidirectional(kontoController.getModel().getInhaber().adresseProperty());

        einzahlen.setOnAction(event -> {
            kontoController.einzahlen(betrag.getText());
        });

        abheben.setOnAction(event -> {
            kontoController.abheben(betrag.getText());
        });
    }

    /**
     * Gibt Fehlermeldung aus
     */
    public void fehlerMeldung() {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Das ist nicht möglich");
    }

    /**
     * Convertiert String und Double Property und bindet sie bidirectional aneinander
     * @param sp
     * @param dp
     * @param kontoController
     */
    private void stringPropAndDoublePropBinding(StringProperty sp, DoubleProperty dp, KontoController kontoController) {
        kontoController.checkKontostand();
        StringConverter<Number> converter = new NumberStringConverter();
        Bindings.bindBidirectional(sp, dp, converter);
    }
}
