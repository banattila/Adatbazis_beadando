package hu.banattila.beadando_prog.controllers;

import hu.banattila.beadando_prog.models.statisztika.ElmultNapok;
import hu.banattila.beadando_prog.models.statisztika.LegugyesebbFutar;
import hu.banattila.beadando_prog.utils.connection.StatisztikaConnection;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class StatisztikaController implements Initializable {

    private StatisztikaConnection conn;

    @FXML
    private BarChart<String, Integer> elmultHetekDia;

    @FXML
    private TextField hetekSzama;

    private void setNumberField() {
        hetekSzama.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                hetekSzama.setText(oldValue);
            }
        });
    }

    @FXML
    private Button hetekKivalaszt;

    private void setChart() {
        int hetekSz = Integer.parseInt(hetekSzama.getText());
        elmultHetekDia.setTitle("Az elmúlt " + hetekSz + " hétben rendelt pizzák száma napi bontásba\n(méretek nincsnek megkülönböztetve)");
        List<ElmultNapok> elmultNapok = conn.getElmultNapok(hetekSz);
        for (int i = 0; i < elmultNapok.size(); ++i) {
            boolean torolt = false;
            ElmultNapok nap = elmultNapok.get(i);
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(elmultNapok.get(i).getFajta());
            for (int j = i; j < elmultNapok.size(); ++j) {
                ElmultNapok belso = elmultNapok.get(j);
                if (nap.getFajta().equals(belso.getFajta())) {
                    series.getData().add(new XYChart.Data<>(elmultNapok.get(j).getDate(), elmultNapok.get(j).getMennyiseg()));
                    elmultNapok.remove(j);
                    torolt = true;
                }
            }
            if (torolt) {
                i = 0;
            }
            elmultHetekDia.getData().add(series);
        }
    }

    private void setHetekKivalaszt() {
        hetekKivalaszt.setOnAction(e -> {
            elmultHetekDia.getData().clear();
            setChart();
        });
    }

    @FXML
    private Label legnepszerubbFajta;

    @FXML
    private Label legnepszerubbMeret;

    @FXML
    private Label legnepszerubbDarab;

    private void setLegnepszerubb() {
        legnepszerubbFajta.setText(conn.getLegnepszerubb().getFajta());
        legnepszerubbDarab.setText(Integer.toString(conn.getLegnepszerubb().getDarab()));
        legnepszerubbMeret.setText(Integer.toString(conn.getLegnepszerubb().getMeret()));
    }

    @FXML
    private Label legugyesebbFutar;

    @FXML
    private Label fuvarokSzama;

    private void setLegugyesebbFutar() {
        LegugyesebbFutar f = conn.legtobbetDolgozott();
        legugyesebbFutar.setText(f.getNev());
        fuvarokSzama.setText(Integer.toString(f.getFuvarokSzama()));
    }

    @FXML
    private Button refresh;

    private void setRefresh() {
        refresh.setOnAction(e -> {
            setLegnepszerubb();
            setHetekKivalaszt();
            setLegugyesebbFutar();
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conn = new StatisztikaConnection();
        setRefresh();
        setHetekKivalaszt();
        setNumberField();
        setChart();
        setLegnepszerubb();
        setLegugyesebbFutar();
    }
}
