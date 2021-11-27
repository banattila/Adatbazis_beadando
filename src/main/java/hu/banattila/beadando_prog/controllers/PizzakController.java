package hu.banattila.beadando_prog.controllers;

import hu.banattila.beadando_prog.models.Feltet;
import hu.banattila.beadando_prog.models.Pizza;
import hu.banattila.beadando_prog.utils.MyAlert;
import hu.banattila.beadando_prog.utils.connection.FeltetConnection;
import hu.banattila.beadando_prog.utils.connection.PizzaConnection;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.controlsfx.control.CheckComboBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PizzakController implements Initializable {

    private PizzaConnection pizzaConnection;
    private FeltetConnection feltetConnection;
    private final int KIS_MERET = 26;
    private final int KOZEPES_MERET = 30;
    private final int CSALADI_MERET = 50;
    private Pizza kicsi;
    private Pizza kozepes;
    private Pizza csaladi;

    @FXML
    private TableView<Pizza> kispizzakTw;

    @FXML
    private TableColumn<Pizza, String> kispizzaFajta;

    @FXML
    private TableColumn<Pizza, String> kispizzaAlap;

    @FXML
    private TableColumn<Pizza, String> kispizzaOsszetevok;

    @FXML
    private TableColumn<Pizza, Integer> kispizzaAr;

    @FXML
    private TableView<Pizza> kozepespizzakTw;

    @FXML
    private TableColumn<Pizza, String> kozepespizzaFajta;

    @FXML
    private TableColumn<Pizza, String> kozepespizzaAlap;

    @FXML
    private TableColumn<Pizza, String> kozepespizzaOsszetevok;

    @FXML
    private TableColumn<Pizza, Integer> kozepespizzaAr;

    @FXML
    private TableView<Pizza> csaladipizzakTw;

    @FXML
    private TableColumn<Pizza, String> csaladipizzaFajta;

    @FXML
    private TableColumn<Pizza, String> csaladipizzaAlap;

    @FXML
    private TableColumn<Pizza, String> csaladipizzaOsszetevok;

    @FXML
    private TableColumn<Pizza, Integer> csaladipizzaAr;

    @FXML
    private TextField newpizzaFajta;

    @FXML
    private TextField newpizzaAr;

    @FXML
    private Button addpizzaBtn;

    @FXML
    private CheckComboBox<String> newpizzaOsszetevok;

    @FXML
    private TextField newpizzaAlap;

    @FXML
    private TextField updatepizzaAr;

    @FXML
    private Button updatepizzaBtn;

    @FXML
    private Button deletepizzaBtn;

    @FXML
    private Button refresh;

    private void addNewCsaladiPizza() {
        boolean ok = true;
        String fajta = newpizzaFajta.getText();
        String alap = newpizzaAlap.getText();
        int ar = 0;

        if (fajta.isEmpty() || fajta.isBlank()) {
            new MyAlert("Hibás érték", "A fajta nem lehet üres");
            ok = false;
        }

        if (alap.isBlank() || alap.isEmpty()) {
            new MyAlert("Hibás érték", "Az alap nem lehet üres");
            ok = false;
        }

        if (newpizzaAr.getText().isEmpty() || newpizzaAr.getText().isBlank()) {
            new MyAlert("Hibás érték", "Az ár nem lehet üres");
            ok = false;
        } else {
            String arStr = newpizzaAr.getText();
            for (char c : arStr.toCharArray()) {
                if (!Character.isDigit(c)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ar = Integer.parseInt(arStr);
            }
        }
        if (newpizzaOsszetevok.getItems().isEmpty()) {
            new MyAlert("Hibás érték", "Nincs kiválasztva egy feltét sem");
            ok = false;
        }
        if (ok) {
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás",
                    pizzaConnection.addPizzak(fajta, newpizzaOsszetevok.getCheckModel().getCheckedItems(), alap, ar),
                    pizzaConnection.getPizzak(CSALADI_MERET), csaladipizzakTw);
        }
    }

    private void updatePizzak() {
        TableView.TableViewSelectionModel<Pizza> sm;
        boolean ok = true;
        int newAr;
        //uj ar field check
        if (updatepizzaAr.getText().isBlank() || updatepizzaAr.getText().isEmpty()) {
            new MyAlert("Hibás érték", "Az ár nem lehet üres");
            ok = false;
        }

        if (!kispizzakTw.getSelectionModel().getSelectedItems().isEmpty() && ok) {
            sm = kispizzakTw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Pizza> os = sm.getSelectedItems();
            kicsi = os.get(0);

            if (ok) {
                newAr = Integer.parseInt(updatepizzaAr.getText());
                MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres frissítés",
                        pizzaConnection.updatePizza(kicsi.getFajta(), newAr, KIS_MERET), pizzaConnection.getPizzak(KIS_MERET), kispizzakTw);
                kicsi = null;
                kispizzakTw.getSelectionModel().select(null);
            }

        } else if (!kozepespizzakTw.getSelectionModel().getSelectedItems().isEmpty()) {
            sm = kozepespizzakTw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Pizza> os = sm.getSelectedItems();
            kozepes = os.get(0);

            if (ok) {
                newAr = Integer.parseInt(updatepizzaAr.getText());
                MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres frissítés",
                        pizzaConnection.updatePizza(kozepes.getFajta(), newAr, KOZEPES_MERET), pizzaConnection.getPizzak(KOZEPES_MERET), kozepespizzakTw);
                kozepes = null;
                kozepespizzakTw.getSelectionModel().clearSelection();
            }

        } else if (!csaladipizzakTw.getSelectionModel().getSelectedItems().isEmpty()) {
            sm = csaladipizzakTw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Pizza> os = sm.getSelectedItems();
            csaladi = os.get(0);

            if (ok) {
                newAr = Integer.parseInt(updatepizzaAr.getText());
                MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres frissítés",
                        pizzaConnection.updatePizza(csaladi.getFajta(), newAr, CSALADI_MERET), pizzaConnection.getPizzak(CSALADI_MERET), csaladipizzakTw);
                csaladi = null;
                csaladipizzakTw.getSelectionModel().clearSelection();
            }
        }
        if (ok) {
            updatepizzaAr.setText("");
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy pizza sem");
            a.show();
        }
    }

    private void setUpdatePizzak() {
        updatepizzaBtn.setOnAction(e -> updatePizzak());
    }

    private void deletePizzak() {
        TableView.TableViewSelectionModel<Pizza> sm;
        if (!kispizzakTw.getSelectionModel().getSelectedItems().isEmpty()) {
            sm = kispizzakTw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Pizza> os = sm.getSelectedItems();
            kicsi = os.get(0);

            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres frissítés",
                    pizzaConnection.deletePizza(kicsi.getFajta()), pizzaConnection.getPizzak(KIS_MERET), kispizzakTw);
            kicsi = null;
            kispizzakTw.getSelectionModel().select(null);


        } else if (!kozepespizzakTw.getSelectionModel().getSelectedItems().isEmpty()) {
            sm = kozepespizzakTw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Pizza> os = sm.getSelectedItems();
            kozepes = os.get(0);

            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres frissítés",
                    pizzaConnection.deletePizza(kozepes.getFajta()), pizzaConnection.getPizzak(KOZEPES_MERET), kozepespizzakTw);
            kozepes = null;
            kozepespizzakTw.getSelectionModel().clearSelection();


        } else if (!csaladipizzakTw.getSelectionModel().getSelectedItems().isEmpty()) {
            sm = csaladipizzakTw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Pizza> os = sm.getSelectedItems();
            csaladi = os.get(0);

            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres frissítés",
                    pizzaConnection.deletePizza(csaladi.getFajta()), pizzaConnection.getPizzak(CSALADI_MERET), csaladipizzakTw);
            csaladi = null;
            csaladipizzakTw.getSelectionModel().clearSelection();

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy pizza sem");
            a.show();
        }
    }

    private void setDeletePizzak() {
        deletepizzaBtn.setOnAction(e -> deletePizzak());
        deletepizzaBtn.setOnAction(e -> deletePizzak());
        deletepizzaBtn.setOnAction(e -> deletePizzak());
    }

    private void setAlapokEsOsszetevok() {
        List<Feltet> feltetek = feltetConnection.getFeltetek();
        List<String> osszetevok = new ArrayList<>();
        for (Feltet f : feltetek) {
            osszetevok.add(f.getMegnevezes());
        }
        newpizzaOsszetevok.getItems().addAll(FXCollections.observableArrayList(osszetevok));
    }

    private void setColumns() {
        kispizzaFajta.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFajta()));
        kispizzaAlap.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAlap()));
        kispizzaOsszetevok.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOsszetevok()));
        kispizzaAr.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAr()).asObject());
        kozepespizzaFajta.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFajta()));
        kozepespizzaAlap.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAlap()));
        kozepespizzaOsszetevok.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOsszetevok()));
        kozepespizzaAr.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAr()).asObject());
        csaladipizzaFajta.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFajta()));
        csaladipizzaAlap.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAlap()));
        csaladipizzaOsszetevok.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOsszetevok()));
        csaladipizzaAr.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAr()).asObject());
        addpizzaBtn.setOnAction(e -> addNewCsaladiPizza());
    }

    private void setRefresh() {
        refresh.setOnAction(e -> {
            kispizzakTw.setItems(FXCollections.observableArrayList(pizzaConnection.getPizzak(KIS_MERET)));
            kozepespizzakTw.setItems(FXCollections.observableArrayList(pizzaConnection.getPizzak(KOZEPES_MERET)));
            csaladipizzakTw.setItems(FXCollections.observableArrayList(pizzaConnection.getPizzak(CSALADI_MERET)));
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pizzaConnection = new PizzaConnection();
        feltetConnection = new FeltetConnection();
        kispizzakTw.setItems(FXCollections.observableArrayList(pizzaConnection.getPizzak(KIS_MERET)));
        kozepespizzakTw.setItems(FXCollections.observableArrayList(pizzaConnection.getPizzak(KOZEPES_MERET)));
        csaladipizzakTw.setItems(FXCollections.observableArrayList(pizzaConnection.getPizzak(CSALADI_MERET)));
        setRefresh();
        setAlapokEsOsszetevok();
        setColumns();
        setUpdatePizzak();
        setDeletePizzak();
    }
}
