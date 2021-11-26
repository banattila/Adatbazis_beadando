package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Rendeles;
import hu.banattila.beadando_prog.utils.MyAlert;
import hu.banattila.beadando_prog.utils.PizzaConnection;
import hu.banattila.beadando_prog.utils.RendelesekConnection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


public class RendelesekController implements Initializable {

    private RendelesekConnection rendelesekConnection;
    private PizzaConnection pizzaConnection;

    @FXML
    private TableView<Rendeles> tw;

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

    private void setRendeltFajtaEsMeret() {
        rendeltPizzaFajta.setItems(
                FXCollections.observableArrayList(pizzaConnection.pizzaFajtak()));
        rendeltmeret.setItems(FXCollections.observableArrayList("Kicsi", "Közepes", "Családi"));
        SingleSelectionModel<String> sm = rendeltmeret.getSelectionModel();
        sm.select(2);
    }

    @FXML
    private TextField rendeltMenny;

    @FXML
    private ComboBox<String> rendeltmeret;

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

    @FXML
    private Button refresh;

    private boolean checkCimDataFields() {
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

    private boolean checkRendeltFeltetelek() {
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

    private void insertRendeles() {
        if (checkUgyfelData() && checkCimDataFields() && checkRendeltFeltetelek()) {
            int meret = 0;
            String rendeltM = rendeltmeret.getValue();

            switch (rendeltM){
                case "Kicsi": meret = 26; break;
                case "Közepes": meret = 30; break;
                default: meret = 50;
            }
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás",
                    rendelesekConnection.insertRendeles(
                            ugyfelEmail.getText(),
                            ugyfelVnev.getText(),
                            ugyfelKnev.getText(),
                            rendeltPizzaFajta.getSelectionModel().getSelectedItem(),
                            meret,
                            Integer.parseInt(rendeltMenny.getText()),
                            Integer.parseInt(irsz.getText()),
                            telepules.getText(),
                            utca.getText(),
                            Integer.parseInt(hazszam.getText())
                    ), rendelesekConnection.getRendelesek(), tw);
        }
    }

    private void setRefresh(){
        refresh.setOnAction(e -> {
            tw.setItems(FXCollections.observableArrayList(rendelesekConnection.getRendelesek()));
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rendelesekConnection = new RendelesekConnection();
        pizzaConnection = new PizzaConnection();
        setRefresh();
        tw.setItems(FXCollections.observableArrayList(rendelesekConnection.getRendelesek()));
        setColumns();
        setNumberFields();
        setRendeltFajtaEsMeret();
        insertRendeles.setOnAction(e -> insertRendeles());
    }
}
