package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Dolgozo;
import hu.banattila.beadando_prog.models.Futar;
import hu.banattila.beadando_prog.utils.MyAlert;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DolgozokController implements Initializable {

    @FXML
    protected TableView<Dolgozo> dolgozokTable;

    @FXML
    private TableView<Futar> futarokTable;

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
                    Main.pc.insertDolgozo(adoszam, vnev, knev), Main.pc.getDolgozok(), dolgozokTable);
            dolgozokTable.setItems(FXCollections.observableArrayList(Main.pc.getDolgozok()));
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
                    Main.pc.insertFutarok(adoszam, vnev, knev), Main.pc.getFutarok(), futarokTable);
            futarokTable.setItems(FXCollections.observableArrayList(Main.pc.getFutarok()));
            newAdoszamFutar.setText("");
            newVNevFutar.setText("");
            newKNevFutar.setText("");
        }
    }

    private void deleteDolgozo() {
        TableView.TableViewSelectionModel sm = dolgozokTable.getSelectionModel();
        sm.setSelectionMode(SelectionMode.SINGLE);
        ObservableList os = sm.getSelectedItems();
        Dolgozo uf = (Dolgozo) os.get(0);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("TÖRLÉS");
        a.setContentText("Biztosan törlöd");
        if (a.showAndWait().get() == ButtonType.OK) {
            MyAlert.alertWithAction(a, "Sikeres törlés", Main.pc.deleteDolgozo(uf.getAdoszam()),
                    Main.pc.getDolgozok(), dolgozokTable);
        }
    }

    private void deleteFutarok() {
        TableView.TableViewSelectionModel sm = futarokTable.getSelectionModel();
        sm.setSelectionMode(SelectionMode.SINGLE);
        ObservableList os = sm.getSelectedItems();
        Futar uf = (Futar) os.get(0);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("TÖRLÉS");
        a.setContentText("Biztosan törlöd");
        if (a.showAndWait().get() == ButtonType.OK) {
            MyAlert.alertWithAction(a, "Sikeres törlés", Main.pc.deleteFutarok(uf.getAdoszam()),
                    Main.pc.getFutarok(), futarokTable);
        }
    }

    private void updateDolgozo() {
        if (!dolgozokTable.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel sm = dolgozokTable.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList os = sm.getSelectedItems();
            Dolgozo dolgozo = (Dolgozo) os.get(0);
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
                    Main.pc.updateDolgozo(dolgozo.getAdoszam(), vnev, knev),
                    Main.pc.getDolgozok(), dolgozokTable);

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy dolgozó sem");
            a.show();
        }
    }

    private void updateFutarData() {
        if (!futarokTable.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel sm = futarokTable.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList os = sm.getSelectedItems();
            Dolgozo dolgozo = (Dolgozo) os.get(0);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            String vnev = updateFutarVNev.getText();
            String knev = updateFutarKNev.getText();

            if (vnev.isBlank() || vnev.isEmpty()) {
                vnev = dolgozo.getVezeteknev();
            }
            if (knev.isEmpty() || knev.isBlank()) {
                knev = dolgozo.getKeresztnev();
            }
            MyAlert.alertWithAction(a, "Sikeres frissítés",
                    Main.pc.updateDolgozo(dolgozo.getAdoszam(), vnev, knev),
                    Main.pc.getFutarok(), futarokTable);

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy futár sem");
            a.show();
        }
    }


    private void updateFutarElerheto() {
        if (!futarokTable.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel sm = futarokTable.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList os = sm.getSelectedItems();
            Futar f = (Futar) os.get(0);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            MyAlert.alertWithAction(a, "Sikeres frissítés", Main.pc.updateFutar(f.getAdoszam()),
                    Main.pc.getFutarok(), futarokTable);

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy futár sem");
            a.show();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addDolgozo.setOnAction(e -> addNewDolgozo());
        addFutar.setOnAction(e -> addNewFutar());
        deleteDolgozo.setOnAction(e -> deleteDolgozo());
        deleteFutar.setOnAction(e -> deleteFutarok());
        updateFutarWorking.setOnAction(e -> updateFutarElerheto());
        updateDolgozo.setOnAction(e -> updateDolgozo());
        updateFutarData.setOnAction(e -> updateFutarData());
        dolgozokTable.setItems(FXCollections.observableArrayList(Main.pc.getDolgozok()));
        futarokTable.setItems(FXCollections.observableArrayList(Main.pc.getFutarok()));
        dolgozokAdoszam.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAdoszam()));
        dolgozokVNev.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getVezeteknev()));
        dolgozokKNev.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getKeresztnev()));

        futarokAdoszam.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getAdoszam()));
        futarokVNev.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getVezeteknev()));
        futarokKNev.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getKeresztnev()));
        futarokDolgozik.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getElerheto()));

    }
}
