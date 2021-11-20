package hu.banattila.beadando_prog.utils;

import hu.banattila.beadando_prog.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import java.util.List;

public class MyAlert {
    Alert alert;

    public MyAlert(String title, String message) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }

    public static <T> void alertWithAction(
            Alert alert, String message,
            boolean action, List<T> getAction, TableView<T> tw) {
        boolean res = action;
        tw.setItems(FXCollections.observableArrayList(getAction));
        alert.setAlertType(Alert.AlertType.INFORMATION);
        if (res) {
            alert.setTitle("Siker");
            alert.setContentText(message);
        } else {
            alert.setTitle("Hiba");
            alert.setContentText("Hiba a folyamat során");
        }
        alert.show();
    }
}
