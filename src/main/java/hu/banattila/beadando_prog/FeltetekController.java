package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Feltet;
import hu.banattila.beadando_prog.utils.MyAlert;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class FeltetekController implements Initializable {

    @FXML
    private TableView<Feltet> ftw;

    @FXML
    private TableColumn<Feltet, String> feltetMegnevezes;

    @FXML
    private TableColumn<Feltet, Integer> feltetAr;

    @FXML
    private Button searchFeltet;

    @FXML
    private Button updateFeltet;

    @FXML
    private Button addFeltet;

    @FXML
    private TextField addFeltetMegnevezes;

    @FXML
    private TextField addFeltetAr;

    @FXML
    private TextField searchFeltetMegnevezes;

    @FXML
    private TextField updateFeltetAr;

    @FXML
    private ChoiceBox<String> updateFeltetMegnezes;

    private void selectFeltetek(){
        ftw.setItems(FXCollections.observableArrayList(Main.pc.getFeltetek()));
    }

    private void insertFeltet(){
        String megnevezes = addFeltetMegnevezes.getText();
        int ar = 0;
        boolean ok = true;
        if (megnevezes.isEmpty() || megnevezes.isBlank()){
            new MyAlert("Hibás érték", "A megnevezés nem lehet ürese");
            ok = false;
        }
        if(addFeltetAr.getText().isEmpty() || addFeltetAr.getText().isBlank()){
            ok = false;
            new MyAlert("Hibás érték", "Az ár nem lehet ürese");
        } else {
            ar = Integer.parseInt(addFeltetAr.getText());
        }

        if(ok){
            Main.pc.insertFeltet(megnevezes, ar);
            selectFeltetek();
            addFeltetMegnevezes.setText("");
            addFeltetAr.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectFeltetek();
        addFeltet.setOnAction(e -> insertFeltet());
        feltetMegnevezes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMegnevezes()));
        feltetAr.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAr()).asObject());
    }
}
