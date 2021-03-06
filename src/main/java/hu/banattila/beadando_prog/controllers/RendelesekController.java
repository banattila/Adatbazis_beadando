package hu.banattila.beadando_prog.controllers;

import hu.banattila.beadando_prog.models.Rendeles;
import hu.banattila.beadando_prog.utils.MyAlert;
import hu.banattila.beadando_prog.utils.connection.PizzaConnection;
import hu.banattila.beadando_prog.utils.connection.RendelesekConnection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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
    private TableColumn<Rendeles, String> rendeltMeret;

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
        rendeltMeret.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMeret()));
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
            new MyAlert("Hib??s ??rt??k", "Nem lehet ??res az email ??rt??ke");
        }
        else if (!ugyfelEmail.getText().contains("@")) {
            new MyAlert("Hib??s ??rt??k", "Az nem valid email c??m");
            res = false;
        }
        if (ugyfelVnev.getText().isEmpty() || ugyfelVnev.getText().isBlank()) {
            res = false;
            new MyAlert("Hib??s ??rt??k", "Nem lehet ??res a vezet??kn??v ??rt??ke");
        }

        if (ugyfelKnev.getText().isEmpty() || ugyfelVnev.getText().isBlank()) {
            res = false;
            new MyAlert("Hib??s ??rt??k", "Nem lehet ??res a keresztn??v ??rt??ke");
        }
        return res;
    }

    @FXML
    private ComboBox<String> rendeltPizzaFajta;

    private void setRendeltFajtaEsMeret() {
        rendeltPizzaFajta.setItems(
                FXCollections.observableArrayList(pizzaConnection.pizzaFajtak()));
        rendeltmeret.setItems(FXCollections.observableArrayList("Kicsi (26cm)", "K??zepes (30cm)", "Csal??di (50cm)"));
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
            new MyAlert("Hib??s ??rt??k", "Nem lehet ??res az ir??ny??t??sz??m ??rt??ke");
        }
        if (telepules.getText().isEmpty() || telepules.getText().isBlank()) {
            res = false;
            new MyAlert("Hib??s ??rt??k", "Nem lehet ??res a telep??l??s ??rt??ke");
        }

        if (utca.getText().isEmpty() || utca.getText().isBlank()) {
            res = false;
            new MyAlert("Hib??s ??rt??k", "Nem lehet ??res a utca ??rt??ke");
        }

        if (hazszam.getText().isEmpty() || hazszam.getText().isBlank()) {
            res = false;
            new MyAlert("Hib??s ??rt??k", "Nem lehet ??res a h??zsz??m ??rt??ke");
        }
        return res;
    }

    private boolean checkRendeltFeltetelek() {
        boolean res = true;
        if (rendeltPizzaFajta.getSelectionModel().getSelectedItem().isEmpty()) {
            res = false;
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijel??lve");
            a.setContentText("Nincs kijel??lve egy pizza sem");
            a.show();
        }
        if (rendeltMenny.getText().isEmpty() || rendeltMenny.getText().isBlank()) {
            res = false;
            new MyAlert("Hib??s ??rt??k", "Nem lehet ??res a rendelt mennyis??g ??rt??ke");
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

            switch (rendeltM) {
                case "Kicsi":
                    meret = 26;
                    break;
                case "K??zepes":
                    meret = 30;
                    break;
                default:
                    meret = 50;
            }
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozz??ad??s",
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

    private void setRefresh() {
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
