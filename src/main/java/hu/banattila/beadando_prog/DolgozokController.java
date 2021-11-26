package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Dolgozo;
import hu.banattila.beadando_prog.models.Feltet;
import hu.banattila.beadando_prog.models.Futar;
import hu.banattila.beadando_prog.utils.DolgozokConnection;
import hu.banattila.beadando_prog.utils.MyAlert;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DolgozokController implements Initializable {

    private DolgozokConnection dc;

    @FXML
    public TableView<Dolgozo> dolgozokTable;

    @FXML
    public TableView<Futar> futarokTable;

    @FXML
    private TableColumn<Dolgozo, String> dolgozokAdoszam;

    @FXML
    private TableColumn<Dolgozo, String> dolgozokVNev;

    @FXML
    private TableColumn<Dolgozo, String> dolgozokKNev;

    @FXML
    private TableColumn<Futar, String> futarokAdoszam;

    @FXML
    private TableColumn<Futar, String> futarokVNev;

    @FXML
    private TableColumn<Futar, String> futarokKNev;

    @FXML
    private TableColumn<Futar, String> futarokDolgozik;

    @FXML
    private TextField newAdoszamDolgozo;

    @FXML
    private TextField newVNevDolgozo;

    @FXML
    private Button addDolgozo;

    @FXML
    private Button addFutar;

    @FXML
    private Button deleteDolgozo;

    @FXML
    private Button deleteFutar;

    @FXML
    private TextField newKNevDolgozo;

    @FXML
    private TextField newAdoszamFutar;

    @FXML
    private TextField newVNevFutar;

    @FXML
    private TextField newKNevFutar;

    @FXML
    private Button updateFutarWorking;

    @FXML
    private TextField updateDolgozoVNev;

    @FXML
    private TextField updateDolgozoKNev;

    @FXML
    private Button updateDolgozo;

    @FXML
    private TextField updateFutarVNev;

    @FXML
    private TextField updateFutarKNev;

    @FXML
    private Button updateFutarData;

    @FXML
    private Button refresh;


    private void addNewDolgozo() {
        boolean valid = true;
        String adoszam = newAdoszamDolgozo.getText();
        String vnev = newVNevDolgozo.getText();
        String knev = newKNevDolgozo.getText();

        if (adoszam.isEmpty() || adoszam.isBlank()) {
            new MyAlert("Hibás érték", "Az adószám nem lehet üres");
            valid = false;

        } else {
            if (adoszam.length() != 10) {
                new MyAlert("Hibás érték", "Az adószám csak számokból állhat és 10 karakter hosszónak kell lennie");
                valid = false;
            } else {
                for (int i = 0; i < adoszam.length(); i++) {
                    if (!Character.isDigit(adoszam.charAt(i))) {
                        new MyAlert("Hibás érték", "Az adószám csak számokból állhat és 10 karakter hosszónak kell lennie");
                        valid = false;
                        break;
                    }
                }
            }

        }
        if (vnev.isBlank() || vnev.isEmpty()) {
            new MyAlert("Hibás érték", "Az vezetéknév nem lehet üres");
            valid = false;
        }
        if (knev.isEmpty() || knev.isBlank()) {
            new MyAlert("Hibás érték", "Az keresztnév nem lehet üres");
            valid = false;
        }

        if (valid) {
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás",
                    dc.insertDolgozo(adoszam, vnev, knev), dc.getDolgozok(), dolgozokTable);
            dolgozokTable.setItems(FXCollections.observableArrayList(dc.getDolgozok()));
            newAdoszamDolgozo.setText("");
            newVNevDolgozo.setText("");
            newKNevDolgozo.setText("");
        }
    }


    private void addNewFutar() {
        boolean valid = true;
        String adoszam = newAdoszamFutar.getText();
        String vnev = newVNevFutar.getText();
        String knev = newKNevFutar.getText();

        if (adoszam.isEmpty() || adoszam.isBlank()) {
            new MyAlert("Hibás érték", "Az adószám nem lehet üres");
            valid = false;

        } else {
            if (adoszam.length() != 10) {
                new MyAlert("Hibás érték", "Az adószám csak számokból állhat és 10 karakter hosszónak kell lennie");
                valid = false;
            } else {
                for (int i = 0; i < adoszam.length(); i++) {
                    if (!Character.isDigit(adoszam.charAt(i))) {
                        new MyAlert("Hibás érték", "Az adószám csak számokból állhat és 10 karakter hosszónak kell lennie");
                        valid = false;
                        break;
                    }
                }
            }

        }
        if (vnev.isBlank() || vnev.isEmpty()) {
            new MyAlert("Hibás érték", "Az vezetéknév nem lehet üres");
            valid = false;
        }
        if (knev.isEmpty() || knev.isBlank()) {
            new MyAlert("Hibás érték", "Az keresztnév nem lehet üres");
            valid = false;
        }
        if (valid) {
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás",
                    dc.insertFutarok(adoszam, vnev, knev), dc.getFutarok(), futarokTable);
            futarokTable.setItems(FXCollections.observableArrayList(dc.getFutarok()));
            newAdoszamFutar.setText("");
            newVNevFutar.setText("");
            newKNevFutar.setText("");
        }
    }

    private void deleteDolgozo() {
        TableView.TableViewSelectionModel<Dolgozo> sm = dolgozokTable.getSelectionModel();
        sm.setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Dolgozo> os = sm.getSelectedItems();
        Dolgozo uf = os.get(0);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("TÖRLÉS");
        a.setContentText("Biztosan törlöd");
        if (a.showAndWait().get() == ButtonType.OK) {
            MyAlert.alertWithAction(a, "Sikeres törlés", dc.deleteDolgozo(uf.getAdoszam()),
                    dc.getDolgozok(), dolgozokTable);
        }
    }

    private void deleteFutarok() {
        TableView.TableViewSelectionModel<Futar> sm = futarokTable.getSelectionModel();
        sm.setSelectionMode(SelectionMode.SINGLE);
        ObservableList<Futar> os = sm.getSelectedItems();
        Futar uf = os.get(0);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("TÖRLÉS");
        a.setContentText("Biztosan törlöd");
        if (a.showAndWait().get() == ButtonType.OK) {
            MyAlert.alertWithAction(a, "Sikeres törlés", dc.deleteFutarok(uf.getAdoszam()),
                    dc.getFutarok(), futarokTable);
        }
    }

    private void updateDolgozo() {
        if (!dolgozokTable.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel<Dolgozo> sm = dolgozokTable.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Dolgozo> os = sm.getSelectedItems();
            Dolgozo dolgozo = os.get(0);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            String vnev = updateDolgozoVNev.getText();
            String knev = updateDolgozoKNev.getText();

            if (vnev.isBlank() || vnev.isEmpty()) {
                vnev = dolgozo.getVezeteknev();
            }
            if (knev.isEmpty() || knev.isBlank()) {
                knev = dolgozo.getKeresztnev();
            }
            MyAlert.alertWithAction(a, "Sikeres frissítés",
                    dc.updateDolgozo(dolgozo.getAdoszam(), vnev, knev),
                    dc.getDolgozok(), dolgozokTable);

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy dolgozó sem");
            a.show();
        }
    }

    private void updateFutarData() {
        if (!futarokTable.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel<Futar> sm = futarokTable.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Futar> os = sm.getSelectedItems();
            Futar futar = os.get(0);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            String vnev = updateFutarVNev.getText();
            String knev = updateFutarKNev.getText();

            if (vnev.isBlank() || vnev.isEmpty()) {
                vnev = futar.getVezeteknev();
            }
            if (knev.isEmpty() || knev.isBlank()) {
                knev = futar.getKeresztnev();
            }
            MyAlert.alertWithAction(a, "Sikeres frissítés",
                    dc.updateDolgozo(futar.getAdoszam(), vnev, knev),
                    dc.getFutarok(), futarokTable);

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy futár sem");
            a.show();
        }
    }


    private void updateFutarElerheto() {
        if (!futarokTable.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel<Futar> sm = futarokTable.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Futar> os = sm.getSelectedItems();
            Futar f = os.get(0);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            MyAlert.alertWithAction(a, "Sikeres frissítés", dc.updateFutar(f.getAdoszam()),
                    dc.getFutarok(), futarokTable);

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy futár sem");
            a.show();
        }
    }

    private void setRefresh(){
        refresh.setOnAction(e -> {
            futarokTable.setItems(FXCollections.observableArrayList(dc.getFutarok()));
            dolgozokTable.setItems(FXCollections.observableArrayList(dc.getDolgozok()));
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dc = new DolgozokConnection();
        futarokTable.setItems(FXCollections.observableArrayList(dc.getFutarok()));
        dolgozokTable.setItems(FXCollections.observableArrayList(dc.getDolgozok()));
        setRefresh();
        addDolgozo.setOnAction(e -> addNewDolgozo());
        addFutar.setOnAction(e -> addNewFutar());
        deleteDolgozo.setOnAction(e -> deleteDolgozo());
        deleteFutar.setOnAction(e -> deleteFutarok());
        updateFutarWorking.setOnAction(e -> updateFutarElerheto());
        updateDolgozo.setOnAction(e -> updateDolgozo());
        updateFutarData.setOnAction(e -> updateFutarData());
        dolgozokTable.setItems(FXCollections.observableArrayList(dc.getDolgozok()));
        futarokTable.setItems(FXCollections.observableArrayList(dc.getFutarok()));
        dolgozokAdoszam.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAdoszam()));
        dolgozokVNev.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getVezeteknev()));
        dolgozokKNev.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getKeresztnev()));

        futarokAdoszam.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAdoszam()));
        futarokVNev.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getVezeteknev()));
        futarokKNev.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getKeresztnev()));
        futarokDolgozik.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getElerheto()));

    }
}
