package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.RendelesAllapot;
import hu.banattila.beadando_prog.utils.MyAlert;
import hu.banattila.beadando_prog.utils.RendelesAllapotConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.List;
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

    private void setCoulumns(){
        rendeloEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEmail()));
        rendelesIdeje.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getRendelesIdeje()));
        rendelesAllapot.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().isAllapota()));
    }

    private void setCheckDataBase(){
        Thread t = new Thread(() -> {
            RendelesAllapot ra = null;
            TableView.TableViewSelectionModel<RendelesAllapot> sm = null;
            while (true){
                List<RendelesAllapot> allapotok = conn.getAllapotok();
                if (!tw.getSelectionModel().getSelectedItems().isEmpty()){
                    sm = tw.getSelectionModel();
                    sm.setSelectionMode(SelectionMode.SINGLE);
                    ObservableList<RendelesAllapot> os = sm.getSelectedItems();
                    ra = os.get(0);
                }
                tw.setItems(FXCollections.observableArrayList(allapotok));
                if (sm != null && ra != null){
                    for (int i = 0; i < allapotok.size(); ++i){
                        if (allapotok.get(i).getEmail().equals(ra.getEmail())){
                            sm.select(i);
                        }
                    }
                }
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}
            }
        });
        t.start();
    }

    private void setRendelesAllapota(){
        if (!tw.getSelectionModel().getSelectedItems().isEmpty()){
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

    private void setRendelesTeljesit(){
        rendelesTeljesit.setOnAction(e-> setRendelesAllapota());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = new RendelesAllapotConnection();
        setCheckDataBase();
        setCoulumns();
        setRendelesTeljesit();
    }
}