package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Rendeles;
import hu.banattila.beadando_prog.models.Ugyfel;
import hu.banattila.beadando_prog.utils.MyAlert;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class RendelesekController implements Initializable {

    @FXML
    private TableView<Rendeles> tw;

    private void setTableItems() {
        tw.setItems(FXCollections.observableArrayList(Main.pc.getRendelesek()));
    }

    @FXML
    private TableColumn<Rendeles, String> ugyfelmail;

    @FXML
    private TableColumn<Rendeles, String> rendeltPizza;

    @FXML
    private TableColumn<Rendeles, Integer> rendeltMeret;

    @FXML
    private TableColumn<Rendeles, Integer> rendeltMennyiseg;

    @FXML
    private TableColumn<Rendeles, String> rendeltCim;

    @FXML
    private TableColumn<Rendeles, String> rendelesIdeje;

    @FXML
    private TableColumn<Rendeles, String> rendeltFutarAzonosito;

    @FXML
    private TableColumn<Rendeles, Integer> rendelesFizetendo;

    private void setColumns() {
        setTableItems();
        rendelesIdeje.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRendelesIdeje()));
        rendeltPizza.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFajta()));
        rendeltMeret.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getMeret()).asObject());
        rendeltMennyiseg.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getRendeltMennyiseg()).asObject());
        rendeltCim.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCim()));
        rendeltFutarAzonosito.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAdoszam()));
        ugyfelmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        rendelesFizetendo.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getFizetendo()).asObject());
    }

    @FXML
    private TextField ugyfelEmail;

    @FXML
    private TextField ugyfelVnev;

    @FXML
    private TextField ugyfelKnev;

    private boolean checkUgyfelData() {
        boolean res = true;
        if (ugyfelEmail.getText().isEmpty() || ugyfelEmail.getText().isBlank()) {
            res = false;
            new MyAlert("Hibás érték", "Nem lehet üres az email értéke");
        }
        if (ugyfelVnev.getText().isEmpty() || ugyfelVnev.getText().isBlank()) {
            res = false;
            new MyAlert("Hibás érték", "Nem lehet üres a vezetéknév értéke");
        }

        if (ugyfelKnev.getText().isEmpty() || ugyfelVnev.getText().isBlank()) {
            res = false;
            new MyAlert("Hibás érték", "Nem lehet üres a keresztnév értéke");
        }
        return res;
    }

    @FXML
    private ComboBox<String> rendeltPizzaFajta;

    private void setRendeltFajta(){
        rendeltPizzaFajta.setItems(
                FXCollections.observableArrayList(Main.pc.pizzaFajtak()));
    }

    @FXML
    private TextField rendeltMenny;

    @FXML
    private TextField rendeltmeret;

    @FXML
    private TextField irsz;

    @FXML
    private TextField telepules;

    @FXML
    private TextField utca;

    @FXML
    private TextField hazszam;

    @FXML
    private Button insertRendeles;

    private boolean checkCimDataFields(){
        boolean res = true;
        if (irsz.getText().isEmpty() || irsz.getText().isBlank()) {
            res = false;
            new MyAlert("Hibás érték", "Nem lehet üres az irányítószám értéke");
        }
        if (telepules.getText().isEmpty() || telepules.getText().isBlank()) {
            res = false;
            new MyAlert("Hibás érték", "Nem lehet üres a település értéke");
        }

        if (utca.getText().isEmpty() || utca.getText().isBlank()) {
            res = false;
            new MyAlert("Hibás érték", "Nem lehet üres a utca értéke");
        }

        if (hazszam.getText().isEmpty() || hazszam.getText().isBlank()) {
            res = false;
            new MyAlert("Hibás érték", "Nem lehet üres a házszám értéke");
        }
        return res;
    }

    private boolean checkRendeltFeltetelek(){
        boolean res = true;
        if (rendeltPizzaFajta.getSelectionModel().getSelectedItem().isEmpty()) {
            res = false;
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy pizza sem");
            a.show();
        }
        if (rendeltMenny.getText().isEmpty() || rendeltMenny.getText().isBlank()) {
            res = false;
            new MyAlert("Hibás érték", "Nem lehet üres a rendelt mennyiség értéke");
        }
        return res;
    }

    private void setNumberFields() {
        rendeltMenny.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") || newValue.length() > 2) {
                rendeltMenny.setText(oldValue);
            }
        });

        rendeltmeret.textProperty().addListener((observable, oldValue, newValue) ->{
            if (!newValue.matches("\\d*") || newValue.length() > 2) {
                rendeltmeret.setText(oldValue);
            }
        });

        irsz.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*") || newValue.length() > 4) {
                irsz.setText(oldValue);
            }
        });

        hazszam.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                hazszam.setText(oldValue);
            }
        });
    }

    private void insertRendeles(){
        System.out.println(rendeltPizzaFajta.getSelectionModel().getSelectedItem());
        if (checkUgyfelData() && checkCimDataFields() && checkRendeltFeltetelek()){
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás",
                    Main.pc.insertRendeles(
                            ugyfelEmail.getText(),
                            ugyfelVnev.getText(),
                            ugyfelKnev.getText(),
                            rendeltPizzaFajta.getSelectionModel().getSelectedItem(),
                            Integer.parseInt(rendeltmeret.getText()),
                            Integer.parseInt(rendeltMenny.getText()),
                            Integer.parseInt(irsz.getText()),
                            telepules.getText(),
                            utca.getText(),
                            Integer.parseInt(hazszam.getText())
                            ), Main.pc.getRendelesek(), tw);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setColumns();
        setNumberFields();
        setRendeltFajta();
        insertRendeles.setOnAction(e -> insertRendeles());
    }
}
