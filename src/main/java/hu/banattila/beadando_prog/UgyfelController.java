package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Ugyfel;
import hu.banattila.beadando_prog.utils.MyAlert;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class UgyfelController implements Initializable {

    ObservableList<Ugyfel> ugyfelek;

    @FXML
    private TableView<Ugyfel> tw;

    @FXML
    private TableColumn<Ugyfel, String> email;

    @FXML
    private TableColumn<Ugyfel, String> vezeteknev;

    @FXML
    private TableColumn<Ugyfel, String> keresztnev;

    @FXML
    private Button deleteUgyfel;

    @FXML
    private Button addUgyfel;

    @FXML
    private TextField newEmail;

    @FXML
    private TextField newVNev;

    @FXML
    private TextField newKNev;

    @FXML
    private TextField updateVNev;

    @FXML
    private TextField updateKNev;

    @FXML
    private Button updateUgyfel;

    private void addNewUgyfel() {
        boolean valid = true;
        String email = newEmail.getText();
        String vnev = newVNev.getText();
        String knev = newKNev.getText();

        if (email.isEmpty() || email.isBlank()) {
            new MyAlert("Hibás érték", "Az email cím nem lehet üres");
            valid = false;
        } else if (!email.contains("@")) {
            new MyAlert("Hibás érték", "Az nem valid email cím");
            valid = false;
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
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás", Main.pc.insertUgyfel(email, vnev, knev),
                    Main.pc.getUgyfelek(), tw);
            newEmail.setText("");
            newVNev.setText("");
            newKNev.setText("");
        }
    }

    private void updateUgyfelek() {
        if (!tw.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel sm = tw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList os = sm.getSelectedItems();
            Ugyfel ugyfel = (Ugyfel) os.get(0);
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            String vnev = updateVNev.getText();
            String knev = updateKNev.getText();

            if (vnev.isBlank() || vnev.isEmpty()) {
                vnev = ugyfel.getVezeteknev();
            }
            if (knev.isEmpty() || knev.isBlank()) {
                knev = ugyfel.getKeresztnev();
            }
            MyAlert.alertWithAction(a, "Sikeres frissítés",
                    Main.pc.updateUgyfel(ugyfel.getEmail(), vnev, knev),
                    Main.pc.getUgyfelek(), tw);

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy futár sem");
            a.show();
        }
    }

    private void deleteUgyfel() {
        if (!tw.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel sm = tw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList os = sm.getSelectedItems();
            Ugyfel uf = (Ugyfel) os.get(0);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("TÖRLÉS");
            a.setContentText("Biztosan törlöd?");
            if (a.showAndWait().get() == ButtonType.OK) {
                MyAlert.alertWithAction(a, "Sikeres törlés", Main.pc.deleteUgyfel(uf.getEmail()),
                        Main.pc.getUgyfelek(), tw);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy ügyfél sem");
            a.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteUgyfel.setOnAction(e -> deleteUgyfel());
        updateUgyfel.setOnAction(e -> updateUgyfelek());
        ugyfelek = FXCollections.observableArrayList(Main.pc.getUgyfelek());
        addUgyfel.setOnAction(e -> addNewUgyfel());
        tw.setItems(ugyfelek);
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        vezeteknev.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVezeteknev()));
        keresztnev.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKeresztnev()));
    }
}
