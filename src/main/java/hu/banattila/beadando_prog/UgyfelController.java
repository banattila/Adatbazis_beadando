package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Ugyfel;
import hu.banattila.beadando_prog.utils.MyAlert;
import hu.banattila.beadando_prog.utils.UgyfelConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UgyfelController implements Initializable {

    private UgyfelConnection ugyfelConnection;

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
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres hozzáadás", ugyfelConnection.insertUgyfel(email, vnev, knev),
                    ugyfelConnection.getUgyfelek(), tw);
            newEmail.setText("");
            newVNev.setText("");
            newKNev.setText("");
        }
    }

    private void updateUgyfelek() {
        if (!tw.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel<Ugyfel> sm = tw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Ugyfel> os = sm.getSelectedItems();
            Ugyfel ugyfel = os.get(0);
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
                    ugyfelConnection.updateUgyfel(ugyfel.getEmail(), vnev, knev),
                    ugyfelConnection.getUgyfelek(), tw);

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy futár sem");
            a.show();
        }
    }

    private void deleteUgyfel() {
        if (!tw.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel<Ugyfel> sm = tw.getSelectionModel();
            sm.setSelectionMode(SelectionMode.SINGLE);
            ObservableList<Ugyfel> os = sm.getSelectedItems();
            Ugyfel ugyfel = os.get(0);
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("TÖRLÉS");
            a.setContentText("Biztosan törlöd?");
            if (a.showAndWait().get() == ButtonType.OK) {
                MyAlert.alertWithAction(a, "Sikeres törlés", ugyfelConnection.deleteUgyfel(ugyfel.getEmail()),
                        ugyfelConnection.getUgyfelek(), tw);
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
        tw.getSelectionModel().setSelectionMode(null);
        ugyfelConnection = new UgyfelConnection();
        Thread t = new Thread(() -> {
            Ugyfel u = null;
            TableView.TableViewSelectionModel<Ugyfel> sm = null;
            while (true){
                List<Ugyfel> ugyfelek = ugyfelConnection.getUgyfelek();
                if (!tw.getSelectionModel().getSelectedItems().isEmpty()){
                    sm = tw.getSelectionModel();
                    sm.setSelectionMode(SelectionMode.SINGLE);
                    ObservableList<Ugyfel> os = sm.getSelectedItems();
                    u = os.get(0);
                }
                tw.setItems(FXCollections.observableArrayList(ugyfelek));
                if (sm != null && u != null){
                    for (int i = 0; i < ugyfelek.size(); ++i){
                        if (ugyfelek.get(i).getEmail().equals(u.getEmail())){
                            sm.select(i);
                        }
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}
            }
        });

        deleteUgyfel.setOnAction(e -> deleteUgyfel());
        updateUgyfel.setOnAction(e -> updateUgyfelek());
        ugyfelek = FXCollections.observableArrayList(ugyfelConnection.getUgyfelek());
        addUgyfel.setOnAction(e -> addNewUgyfel());
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        vezeteknev.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVezeteknev()));
        keresztnev.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKeresztnev()));
        t.start();
    }
}
