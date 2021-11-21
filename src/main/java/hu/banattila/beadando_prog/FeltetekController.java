package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Feltet;
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

public class FeltetekController implements Initializable {

    @FXML
    private TableView<Feltet> ftw;

    @FXML
    private TableColumn<Feltet, String> feltetMegnevezes;

    @FXML
    private TableColumn<Feltet, Integer> feltetAr;

    @FXML
    private Button deleteFeltet;

    @FXML
    private Button updateFeltet;

    @FXML
    private TextField updateFeltetAr;

    @FXML
    private TextField updateFeltetMegnezes;

    @FXML
    private Button addFeltet;

    @FXML
    private TextField addFeltetMegnevezes;

    @FXML
    private TextField addFeltetAr;

    @FXML
    private TextField searchFeltet;

    @FXML
    private Button searchFeltetBtn;

    @FXML
    private TextField searchResultMegnevezes;

    @FXML
    private TextField searchResutlAr;

    @FXML
    private Button incArBtn;

    @FXML
    private Button decArBtn;


    private void selectFeltetek() {
        ftw.setItems(FXCollections.observableArrayList(Main.pc.getFeltetek()));
    }

    private void searchFeltetByMegnevezes() {
        String megnevezes = searchFeltet.getText();
        boolean ok = true;

        if (megnevezes.isEmpty() || megnevezes.isBlank()) {
            new MyAlert("Hibás érték", "A megnevezés nem lehet ürese");
            ok = false;
        }

        if (ok) {
            Feltet feltet = Main.pc.searchFeltetByMegnevezes(megnevezes);
            searchResultMegnevezes.setText(feltet.getMegnevezes());
            searchResutlAr.setText(String.valueOf(feltet.getAr()));
            searchFeltet.setText("");
        }
    }

    private void insertFeltet() {
        String megnevezes = addFeltetMegnevezes.getText();
        int ar = 0;
        boolean ok = true;
        if (megnevezes.isEmpty() || megnevezes.isBlank()) {
            new MyAlert("Hibás érték", "A megnevezés nem lehet ürese");
            ok = false;
        }
        if (addFeltetAr.getText().isEmpty() || addFeltetAr.getText().isBlank()) {
            ok = false;
            new MyAlert("Hibás érték", "Az ár nem lehet ürese");
        } else {
            ar = Integer.parseInt(addFeltetAr.getText());
        }

        if (ok) {
            Main.pc.insertFeltet(megnevezes, ar);
            selectFeltetek();
            addFeltetMegnevezes.setText("");
            addFeltetAr.setText("");
        }
    }

    private void deleteFeltet() {
        if (!ftw.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel sm = ftw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList os = sm.getSelectedItems();
            Feltet feltet = (Feltet) os.get(0);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("TÖRLÉS");
            a.setContentText("Biztosan törlöd?");
            if (a.showAndWait().get() == ButtonType.OK) {
                MyAlert.alertWithAction(a, "Sikeres törlés", Main.pc.deleteFeltet(feltet.getMegnevezes()),
                        Main.pc.getFeltetek(), ftw);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy feltét sem");
            a.show();
        }
    }

    private void incAr() {
        MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres emelés",
                Main.pc.incFeltetAr(), Main.pc.getFeltetek(), ftw);
    }

    private void decAr() {
        MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres csökkentés",
                Main.pc.decFeltetAr(), Main.pc.getFeltetek(), ftw);
    }

    private void updateFeltetArByMegnevezes() {
        boolean ok = true;
        int ar;
        String megnevezes = updateFeltetMegnezes.getText();
        if (updateFeltetAr.getText().isBlank() || updateFeltetAr.getText().isEmpty()) {
            ok = false;
            new MyAlert("Hibás érték", "Az ár nem lehet ürese");
        }

        if (megnevezes.isEmpty() || megnevezes.isBlank()) {
            ok = false;
            new MyAlert("Hibás érték", "Az ár nem lehet ürese");
        }

        if (ok) {
            ar = Integer.parseInt(updateFeltetAr.getText());
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres frissítés",
                    Main.pc.updateFeltetArByMegnevezes(megnevezes, ar), Main.pc.getFeltetek(), ftw);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        selectFeltetek();
        searchResutlAr.setEditable(false);
        searchResutlAr.setDisable(false);
        searchResultMegnevezes.setEditable(false);
        searchResultMegnevezes.setDisable(false);
        searchFeltetBtn.setOnAction(e -> searchFeltetByMegnevezes());
        addFeltet.setOnAction(e -> insertFeltet());
        deleteFeltet.setOnAction(e -> deleteFeltet());
        incArBtn.setOnAction(e -> incAr());
        decArBtn.setOnAction(e -> decAr());
        updateFeltet.setOnAction(e -> updateFeltetArByMegnevezes());
        feltetMegnevezes.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getMegnevezes()));
        feltetAr.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAr()).asObject());
    }
}