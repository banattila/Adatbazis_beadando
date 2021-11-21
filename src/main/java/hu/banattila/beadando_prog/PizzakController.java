package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Feltet;
import hu.banattila.beadando_prog.models.Pizza;
import hu.banattila.beadando_prog.utils.MyAlert;
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

    private final int KIS_MERET = 26;
    private final int KOZEPES_MERET = 30;
    private final int CSALADI_MERET = 50;

    @FXML
    private Tab kisPizzak;

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
    private TextField newkispizzaFajta;

    @FXML
    private TextField newkispizzaAr;

    @FXML
    private Button addkispizzaBtn;

    @FXML
    private CheckComboBox<String> newkispizzaOsszetevok;

    @FXML
    private TextField newkispizzaAlap;

    @FXML
    private Tab kozepesPizzak;

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
    private TextField newkozepespizzaFajta;

    @FXML
    private TextField newkozepespizzaAr;

    @FXML
    private Button addkozepespizzaBtn;

    @FXML
    private CheckComboBox<String> newkozepespizzaOsszetevok;

    @FXML
    private TextField newkozepespizzaAlap;

    @FXML
    private Tab csaladiPizzak;

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
    private TextField newcsaladipizzaFajta;

    @FXML
    private TextField newcsaladipizzaAr;

    @FXML
    private Button addcsaladipizzaBtn;

    @FXML
    private CheckComboBox<String> newcsaladipizzaOsszetevok;

    @FXML
    private TextField newcsaladipizzaAlap;

    @FXML
    private TextField updatekispizzaAr;

    @FXML
    private Button updatekispizzaBtn;

    @FXML
    private TextField updatekozepespizzaAr;

    @FXML
    private Button updatekozepespizzaBtn;

    @FXML
    private TextField updatecsaladipizzaAr;

    @FXML
    private Button updatecsaladipizzaBtn;

    @FXML
    private Button deletekispizzaBtn;

    @FXML
    private Button deletekozepespizzaBtn;

    @FXML
    private Button deletecsaladipizzaBtn;


    private void getKisPizzak() {
        kispizzakTw.setItems(FXCollections.observableArrayList(Main.pc.getPizzak(KIS_MERET)));
    }

    private void getKozepesPizzak() {
        kozepespizzakTw.setItems(FXCollections.observableArrayList(Main.pc.getPizzak(KOZEPES_MERET)));
    }

    private void getCsaladiPizzak() {
        csaladipizzakTw.setItems(FXCollections.observableArrayList(Main.pc.getPizzak(CSALADI_MERET)));
    }

    private void addNewKisPizza() {
        boolean ok = true;
        String fajta = newkispizzaFajta.getText();
        String alap = newkispizzaAlap.getText();
        List<String> osszetevok = new ArrayList<>();
        int ar = 0;

        if (fajta.isEmpty() || fajta.isBlank()) {
            new MyAlert("Hibás érték", "A fajta nem lehet üres");
            ok = false;
        }

        if (alap.isBlank() || alap.isEmpty()) {
            new MyAlert("Hibás érték", "Az alap nem lehet üres");
            ok = false;
        }

        if (newkispizzaAr.getText().isEmpty() || newkispizzaAr.getText().isBlank()) {
            new MyAlert("Hibás érték", "Az ár nem lehet üres");
            ok = false;
        } else {
            String arStr = newkispizzaAr.getText();
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
        if (newkispizzaOsszetevok.getItems().isEmpty()) {
            new MyAlert("Hibás érték", "Nincs kiválasztva egy feltét sem");
            ok = false;
        }
        if (ok) {

            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás",
                    Main.pc.addPizzak(fajta, newkispizzaOsszetevok.getCheckModel().getCheckedItems(), alap, ar, KIS_MERET),
                    Main.pc.getPizzak(KIS_MERET), kispizzakTw);

        }
    }

    private void addNewKozepesPizza() {
        boolean ok = true;
        String fajta = newkozepespizzaFajta.getText();
        String alap = newkozepespizzaAlap.getText();
        int ar = 0;

        if (fajta.isEmpty() || fajta.isBlank()) {
            new MyAlert("Hibás érték", "A fajta nem lehet üres");
            ok = false;
        }

        if (alap.isBlank() || alap.isEmpty()) {
            new MyAlert("Hibás érték", "Az alap nem lehet üres");
            ok = false;
        }

        if (newkozepespizzaAr.getText().isEmpty() || newkozepespizzaAr.getText().isBlank()) {
            new MyAlert("Hibás érték", "Az ár nem lehet üres");
            ok = false;
        } else {
            String arStr = newkozepespizzaAr.getText();
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
        if (newkozepespizzaOsszetevok.getItems().isEmpty()) {
            new MyAlert("Hibás érték", "Nincs kiválasztva egy feltét sem");
            ok = false;
        }
        if (ok) {

            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás",
                    Main.pc.addPizzak(fajta, newkozepespizzaOsszetevok.getCheckModel().getCheckedItems(), alap, ar, KOZEPES_MERET),
                    Main.pc.getPizzak(KOZEPES_MERET), kozepespizzakTw);

        }
    }

    private void addNewCsaladiPizza() {
        boolean ok = true;
        String fajta = newcsaladipizzaFajta.getText();
        String alap = newcsaladipizzaAlap.getText();
        int ar = 0;

        if (fajta.isEmpty() || fajta.isBlank()) {
            new MyAlert("Hibás érték", "A fajta nem lehet üres");
            ok = false;
        }

        if (alap.isBlank() || alap.isEmpty()) {
            new MyAlert("Hibás érték", "Az alap nem lehet üres");
            ok = false;
        }

        if (newcsaladipizzaAr.getText().isEmpty() || newcsaladipizzaAr.getText().isBlank()) {
            new MyAlert("Hibás érték", "Az ár nem lehet üres");
            ok = false;
        } else {
            String arStr = newcsaladipizzaAr.getText();
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
        if (newcsaladipizzaOsszetevok.getItems().isEmpty()) {
            new MyAlert("Hibás érték", "Nincs kiválasztva egy feltét sem");
            ok = false;
        }
        if (ok) {

            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás",
                    Main.pc.addPizzak(fajta, newcsaladipizzaOsszetevok.getCheckModel().getCheckedItems(), alap, ar, CSALADI_MERET),
                    Main.pc.getPizzak(CSALADI_MERET), csaladipizzakTw);

        }
    }

    private void updatePizzak(TableView tw, TextField arCol, int meret){
        if(!tw.getSelectionModel().getSelectedItems().isEmpty()){
            TableView.TableViewSelectionModel sm = tw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList os = sm.getSelectedItems();
            Pizza p = (Pizza) os.get(0);
            int newAr = 0;
            boolean ok = true;
            if (arCol.getText().isBlank() || arCol.getText().isEmpty()){
                new MyAlert("Hibás érték", "Az ár nem lehet üres");
                ok = false;
            }
            if (ok){
                newAr = Integer.parseInt(arCol.getText());
                MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres frissítés",
                        Main.pc.updatePizza(p.getFajta(), newAr, meret), Main.pc.getPizzak(meret), tw);
            }

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy pizza sem");
            a.show();
        }
    }

    private void setUpdatePizzak(){
        updatekispizzaBtn.setOnAction(e -> updatePizzak(kispizzakTw, updatekispizzaAr, KIS_MERET));
        updatekozepespizzaBtn.setOnAction(e -> updatePizzak(kozepespizzakTw, updatekozepespizzaAr, KOZEPES_MERET));
        updatecsaladipizzaBtn.setOnAction(e -> updatePizzak(csaladipizzakTw, updatecsaladipizzaAr, CSALADI_MERET));
    }

    private void deletePizzak(TableView<Pizza> tw){
        if (!tw.getSelectionModel().getSelectedItems().isEmpty()){
            TableView.TableViewSelectionModel sm = tw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList os = sm.getSelectedItems();
            Pizza p = (Pizza) os.get(0);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("TÖRLÉS");
            a.setContentText("Biztosan törlöd?");
            if (a.showAndWait().get() == ButtonType.OK){
                MyAlert.alertWithAction(a, "Sikeres törlés",
                        Main.pc.deletePizza(p.getFajta(), p.getMeret()),
                        Main.pc.getPizzak(p.getMeret()), tw);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy pizza sem");
            a.show();
        }
    }

    private void setDeletePizzak(){
        deletecsaladipizzaBtn.setOnAction(e -> deletePizzak(csaladipizzakTw));
        deletekispizzaBtn.setOnAction(e -> deletePizzak(kispizzakTw));
        deletekozepespizzaBtn.setOnAction(e -> deletePizzak(kozepespizzakTw));
    }

    private void setAlapokEsOsszetevok() {
        List<Feltet> feltetek = Main.pc.getFeltetek();
        List<String> osszetevok = new ArrayList<>();
        for (Feltet f : feltetek) {
            osszetevok.add(f.getMegnevezes());
        }
        newkispizzaOsszetevok.getItems().addAll(FXCollections.observableArrayList(osszetevok));
        newkozepespizzaOsszetevok.getItems().addAll(FXCollections.observableArrayList(osszetevok));
        newcsaladipizzaOsszetevok.getItems().addAll(FXCollections.observableArrayList(osszetevok));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getKisPizzak();
        getKozepesPizzak();
        getCsaladiPizzak();
        setAlapokEsOsszetevok();
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
        addkispizzaBtn.setOnAction(e -> addNewKisPizza());
        addkozepespizzaBtn.setOnAction(e -> addNewKozepesPizza());
        addcsaladipizzaBtn.setOnAction(e -> addNewCsaladiPizza());
        setUpdatePizzak();
        setDeletePizzak();


    }
}
