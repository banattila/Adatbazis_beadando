package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Ugyfel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    ObservableList<Ugyfel> ugyfelek = FXCollections.observableArrayList(
            new Ugyfel("banpapi8787@gmail.com", "Bán", "Attila"),
            new Ugyfel("bebebaba@gmail.com", "Bán", "Bendegúz"),
            new Ugyfel("mesibaba@gmail.com", "Bán", "Emese"),
            new Ugyfel("bhajnalka86@hotmail.com", "Barna", "Hajnalka")
    );
    @FXML
    private Tab ugyfelTab;

    @FXML
    private Tab cimTab;

    @FXML
    private Tab pizzaTab;

    @FXML
    private Tab dolgozoTab;

    @FXML
    private Tab rendelesTab;

    @FXML
    private ListView<Ugyfel> ugyfelLista;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ugyfelLista.setItems(ugyfelek);
    }
}