package hu.banattila.beadando_prog.controllers;

import hu.banattila.beadando_prog.models.RendelesAllapot;
import hu.banattila.beadando_prog.utils.MyAlert;
import hu.banattila.beadando_prog.utils.connection.RendelesAllapotConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class RendelesekAllapotaController implements Initializable {

    private RendelesAllapotConnection conn;

    @FXML
    private TableView<RendelesAllapot> tw;

    @FXML
    private TableColumn<RendelesAllapot, String> rendeloEmail;

    @FXML
    private TableColumn<RendelesAllapot, String> rendelesIdeje;

    @FXML
    private TableColumn<RendelesAllapot, String> rendelesAllapot;

    @FXML
    private Button rendelesTeljesit;

    @FXML
    private Button refresh;

    private void setCoulumns() {
        rendeloEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        rendelesIdeje.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRendelesIdeje()));
        rendelesAllapot.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().isAllapota()));
    }

    private void setRefresh() {
        refresh.setOnAction(e -> tw.setItems(FXCollections.observableArrayList(conn.getAllapotok())));
    }

    private void setRendelesAllapota() {
        if (!tw.getSelectionModel().getSelectedItems().isEmpty()) {
            TableView.TableViewSelectionModel<RendelesAllapot> sm = tw.getSelectionModel();
            ObservableList<RendelesAllapot> os = sm.getSelectedItems();
            RendelesAllapot ra = os.get(0);
            MyAlert.alertWithAction(new Alert(Alert.AlertType.INFORMATION), "Sikeres frissítés",
                    conn.setTeljesitett(ra.getRendelesIdeje()), conn.getAllapotok(), tw);

        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Nincs kijelölve");
            a.setContentText("Nincs kijelölve egy rendelés sem");
            a.show();
        }
    }

    private void setRendelesTeljesit() {
        rendelesTeljesit.setOnAction(e -> setRendelesAllapota());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = new RendelesAllapotConnection();
        tw.setItems(FXCollections.observableArrayList(conn.getAllapotok()));
        setRefresh();
        setCoulumns();
        setRendelesTeljesit();
    }
}