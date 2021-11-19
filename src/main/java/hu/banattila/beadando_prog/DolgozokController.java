package hu.banattila.beadando_prog;

import hu.banattila.beadando_prog.models.Dolgozo;
import hu.banattila.beadando_prog.models.Futar;
import hu.banattila.beadando_prog.models.Ugyfel;
import hu.banattila.beadando_prog.utils.MyAlert;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class DolgozokController implements Initializable {

    ObservableList<Dolgozo> dolgozok;

    ObservableList<Futar> futarok;

    @FXML
    private TableView<Dolgozo> dolgozokTable;

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

    private void addNewDolgozo(){

        String adoszam = newAdoszamDolgozo.getText();
        String vnev = newVNevDolgozo.getText();
        String knev = newKNevDolgozo.getText();

        if (adoszam.isEmpty() || adoszam.isBlank()){
            new MyAlert("Hibás érték", "Az email cím nem lehet üres");
        }
        if (vnev.isBlank() || vnev.isEmpty()){
            new MyAlert("Hibás érték", "Az vezetéknév nem lehet üres");
        }
        if (knev.isEmpty() || knev.isBlank()){
            new MyAlert("Hibás érték", "Az keresztnév nem lehet üres");
        }

        Main.pc.insertDolgozo(adoszam, vnev, knev);
        dolgozokTable.setItems(FXCollections.observableArrayList(Main.pc.getDolgozok()));
    }


    private void addNewFutar(){

        String adoszam = newAdoszamFutar.getText();
        String vnev = newVNevFutar.getText();
        String knev = newKNevFutar.getText();

        if (adoszam.isEmpty() || adoszam.isBlank()){
            new MyAlert("Hibás érték", "Az email cím nem lehet üres");
        }
        if (vnev.isBlank() || vnev.isEmpty()){
            new MyAlert("Hibás érték", "Az vezetéknév nem lehet üres");
        }
        if (knev.isEmpty() || knev.isBlank()){
            new MyAlert("Hibás érték", "Az keresztnév nem lehet üres");
        }

        Main.pc.insertFutarok(adoszam, vnev, knev);

        futarokTable.setItems(FXCollections.observableArrayList(Main.pc.getFutarok()));
    }

    private void deleteDolgozo(){
        TableView.TableViewSelectionModel sm = dolgozokTable.getSelectionModel();
        sm.setSelectionMode(SelectionMode.SINGLE);
        ObservableList os = sm.getSelectedItems();
        Dolgozo uf= (Dolgozo) os.get(0);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("TÖRLÉS");
        a.setContentText("Biztosan törlöd");
        if (a.showAndWait().get() == ButtonType.OK){
            Main.pc.deleteDolgozo(uf.getAdoszam());
            dolgozokTable.setItems(FXCollections.observableArrayList(Main.pc.getDolgozok()));
        }
    }

    private void deleteFutarok(){
        TableView.TableViewSelectionModel sm = futarokTable.getSelectionModel();
        sm.setSelectionMode(SelectionMode.SINGLE);
        ObservableList os = sm.getSelectedItems();
        Futar uf= (Futar) os.get(0);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setTitle("TÖRLÉS");
        a.setContentText("Biztosan törlöd");
        if (a.showAndWait().get() == ButtonType.OK){
            Main.pc.deleteFutarok(uf.getAdoszam());
            futarokTable.setItems(FXCollections.observableArrayList(Main.pc.getFutarok()));
        }
    }

    private void updateFutarElerheto(){
        TableView.TableViewSelectionModel sm = futarokTable.getSelectionModel();
        sm.setSelectionMode(SelectionMode.SINGLE);
        ObservableList os = sm.getSelectedItems();
        Futar f = (Futar) os.get(0);
        Main.pc.updateFutar(f.getAdoszam());
        futarokTable.setItems(FXCollections.observableArrayList(Main.pc.getFutarok()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addDolgozo.setOnAction(e -> addNewDolgozo());
        addFutar.setOnAction(e-> addNewFutar());
        deleteDolgozo.setOnAction(e -> deleteDolgozo());
        deleteFutar.setOnAction(e -> deleteFutarok());
        updateFutarWorking.setOnAction(e -> updateFutarElerheto());
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
