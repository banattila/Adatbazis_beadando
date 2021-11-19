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
    private Button refresh;

    @FXML
    private Button addUgyfel;

    @FXML
    private TextField newEmail;

    @FXML
    private TextField newVNev;

    @FXML
    private TextField newKNev;

    private void refreshAction() {
        ugyfelek = FXCollections.observableArrayList(Main.pc.getUgyfelek());
        tw.setItems(ugyfelek);
    }

    private void addNewUgyfel(){

        String email = newEmail.getText();
        String vnev = newVNev.getText();
        String knev = newKNev.getText();

        if (email.isEmpty() || email.isBlank()){
            new MyAlert("Hibás érték", "Az email cím nem lehet üres");
        }
        if (vnev.isBlank() || vnev.isEmpty()){
            new MyAlert("Hibás érték", "Az vezetéknév nem lehet üres");
        }
        if (knev.isEmpty() || knev.isBlank()){
            new MyAlert("Hibás érték", "Az keresztnév nem lehet üres");
        }

        Main.pc.insertUgyfel(email, vnev, knev);
        tw.setItems(FXCollections.observableArrayList(Main.pc.getUgyfelek()));
    }

    private void deleteUgyfel(){
        TableView.TableViewSelectionModel sm = tw.getSelectionModel();
        sm.setSelectionMode(SelectionMode.SINGLE);
        ObservableList os = sm.getSelectedItems();
        Ugyfel uf= (Ugyfel) os.get(0);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("TÖRLÉS");
        a.setContentText("Biztosan törlöd");
        if (a.showAndWait().get() == ButtonType.OK){
            Main.pc.deleteUgyfel(uf.getEmail());
            tw.setItems(FXCollections.observableArrayList(Main.pc.getUgyfelek()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        deleteUgyfel.setOnAction(e -> deleteUgyfel());
        ugyfelek = FXCollections.observableArrayList(Main.pc.getUgyfelek());
        refresh.setOnAction(e -> refreshAction());
        addUgyfel.setOnAction(e -> addNewUgyfel());
        tw.setItems(ugyfelek);
        email.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        vezeteknev.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getVezeteknev()));
        keresztnev.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKeresztnev()));

    }
}
